package edu.fy.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fy.entity.Staff;
import edu.fy.entity.dto.*;
import edu.fy.entity.vo.StaffQueryWithCreatorUpdaterDutyOfficeVO;
import edu.fy.mapper.StaffMapper;
import edu.fy.service.StaffDutyService;
import edu.fy.service.StaffOfficeService;
import edu.fy.service.StaffService;
import edu.fy.utils.BeanUtil;
import edu.fy.utils.CustomServiceException;
import edu.fy.utils.ResultCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* @author YangAo
* @description 针对表【staff(人员信息)】的数据库操作Service实现
* @createDate 2022-05-26 10:09:49
*/
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff>
    implements StaffService{

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveStaffAndDutyAndOffice(StaffDutyOfficeSaveDTO staffDutyOfficeSaveDTO) {
        // 密码加密, 其他大部分验证通过 spring boot validation 进行完成
        staffDutyOfficeSaveDTO.setPassword(passwordEncoder.encode(staffDutyOfficeSaveDTO.getPassword()));
        // 身份证号不能重复, 先查询有无对应的身份证号, 再进行操作
        if (new LambdaQueryChainWrapper<>(baseMapper).eq(Staff::getCardId, staffDutyOfficeSaveDTO.getCardId()).list().size() > 0) {
            throw new CustomServiceException("身份证号重复", ResultCode.USER_IS_EXISTED);
        }
        // 手机号不能重复
        if (new LambdaQueryChainWrapper<>(baseMapper).eq(Staff::getPhone, staffDutyOfficeSaveDTO.getPhone()).list().size() > 0) {
            throw new CustomServiceException("手机号重复", ResultCode.USER_IS_EXISTED);
        }
        // 插入人员信息并返回id
        Staff staff = BeanUtil.copyBean(staffDutyOfficeSaveDTO, Staff.class);
        if (!save(staff)) {
            throw new CustomServiceException("人员信息添加失败", ResultCode.DATABASE_ERROR);
        }
        Integer id = staff.getId();
        // 生成用于插入职务和科室的对象
        StaffDutyUpdateDTO staffDutyUpdateDTO = new StaffDutyUpdateDTO(id, staffDutyOfficeSaveDTO.getDutyIdList());
        StaffOfficeUpdateDTO staffOfficeUpdateDTO = new StaffOfficeUpdateDTO(id, staffDutyOfficeSaveDTO.getOfficeIdList());
        // 返回添加结果
        return staffDutyService.updateStaffDuty(staffDutyUpdateDTO) && staffOfficeService.updateStaffOffice(staffOfficeUpdateDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStaff(StaffDutyOfficeUpdateDTO staffDutyOfficeUpdateDTO) {
        // id 不能为空
        Integer staffId = staffDutyOfficeUpdateDTO.getId();
        if (staffId == null) {
            throw new CustomServiceException("id不能为空", ResultCode.PARAM_IS_INVALID);
        }
        // 密码不为空, 则进行加密后再用于更新
        String password = staffDutyOfficeUpdateDTO.getPassword();
        if (StringUtils.hasText(password)) {
            staffDutyOfficeUpdateDTO.setPassword(passwordEncoder.encode(password));
        }
        // 生成用于插入职务和科室的对象, 如果传入的数据列表为空, 则不对职务和科室进行更新
        StaffDutyUpdateDTO staffDutyUpdateDTO = staffDutyOfficeUpdateDTO.getDutyIdList() == null ? null : new StaffDutyUpdateDTO(staffId, staffDutyOfficeUpdateDTO.getDutyIdList());
        StaffOfficeUpdateDTO staffOfficeUpdateDTO = staffDutyOfficeUpdateDTO.getOfficeIdList() == null ? null : new StaffOfficeUpdateDTO(staffId, staffDutyOfficeUpdateDTO.getOfficeIdList());
        // 根据id进行人员信息更新
        boolean update = updateById(BeanUtil.copyBean(staffDutyOfficeUpdateDTO, Staff.class));
        if (!update) {
            throw new CustomServiceException("人员信息更新失败", ResultCode.DATABASE_ERROR);
        }
        // 更新人员与职务、科室的关系
        return staffDutyService.updateStaffDuty(staffDutyUpdateDTO) && staffOfficeService.updateStaffOffice(staffOfficeUpdateDTO);
    }

    @Override
    public Page<StaffQueryWithCreatorUpdaterDutyOfficeVO> searchStaffByCondition(StaffQueryConditionDTO condition) {
        LambdaQueryChainWrapper<Staff> wrapper = new LambdaQueryChainWrapper<>(baseMapper);
        Page<StaffQueryWithCreatorUpdaterDutyOfficeVO> page = new Page<>(1, Long.MAX_VALUE);
        // 两个特殊条件: 空, id
        // 条件为空, 则进行全查
        if (condition == null) {
            List<StaffQueryWithCreatorUpdaterDutyOfficeVO> vos = BeanUtil.copyList(list(), StaffQueryWithCreatorUpdaterDutyOfficeVO.class);
            vos = findAndAddCreatorAndUpdater(vos);
            // 通过page返回结果
            return page.setRecords(vos);
        }
        // id不为空, 则针对id进行查找
        Integer id = null;
        if ((id = condition.getId()) != null && id != 0) {
            StaffQueryWithCreatorUpdaterDutyOfficeVO vo = null;
            // 如果根据id没有查到对应的信息则报错, 参数错误
            if ((vo = BeanUtil.copyBean(baseMapper.selectById(id), StaffQueryWithCreatorUpdaterDutyOfficeVO.class)) == null) {
                throw new CustomServiceException("id对应的数据不存在", ResultCode.PARAM_IS_INVALID);
            }
            // 正常获取后, 进行操作
            List<StaffQueryWithCreatorUpdaterDutyOfficeVO> voOne = Collections.singletonList(vo);
            voOne = findAndAddCreatorAndUpdater(voOne);
            // 通过page返回结果
            return page.setRecords(voOne);
        }
        // 如果有分页要求, 则进行分页, 否则单页大小为最大Long
        Integer current = condition.getCurrentPage();
        Integer size = condition.getPageSize();
        if (size != null && current != null && size != 0 && current != 0) {
            page = new Page<>(current, size);
        }
        return baseMapper.searchAllByCondition(condition, page);
    }

    /**
     * 根据传入的列表进行创建者和更新者信息的查找
     * @param vos 信息列表
     * @return 处理后的结果
     */
    private List<StaffQueryWithCreatorUpdaterDutyOfficeVO> findAndAddCreatorAndUpdater(List<StaffQueryWithCreatorUpdaterDutyOfficeVO> vos) {
        // 列表为空则返回null
        if (CollectionUtils.isEmpty(vos)) {
            return new ArrayList<>();
        }
        // 查找创建者和更新者信息
        for (StaffQueryWithCreatorUpdaterDutyOfficeVO vo : vos) {
            Integer createId = null;
            Integer updateId = null;
            // 只有对应的id不为空时才进行信息查找
            if ((createId = vo.getCreateId()) != null) {
                vo.setCreator(BeanUtil.copyBean(baseMapper.searchOneById(createId), StaffBaseDeletedDTO.class));
            }
            if ((updateId = vo.getUpdateId()) != null) {
                vo.setUpdater(BeanUtil.copyBean(baseMapper.searchOneById(updateId), StaffBaseDeletedDTO.class));
            }
        }
        return vos;
    }
    private final PasswordEncoder passwordEncoder;
    private final StaffDutyService staffDutyService;
    private final StaffOfficeService staffOfficeService;

    public StaffServiceImpl(PasswordEncoder passwordEncoder, StaffDutyService staffDutyService, StaffOfficeService staffOfficeService) {
        this.passwordEncoder = passwordEncoder;
        this.staffDutyService = staffDutyService;
        this.staffOfficeService = staffOfficeService;
    }
}




