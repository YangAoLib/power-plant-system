package edu.fy.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fy.entity.StaffOffice;
import edu.fy.entity.dto.StaffOfficeUpdateDTO;
import edu.fy.mapper.StaffOfficeMapper;
import edu.fy.service.StaffOfficeService;
import edu.fy.utils.CustomServiceException;
import edu.fy.utils.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author poppy
* @description 针对表【staff_office(人员与科室关系)】的数据库操作Service实现
* @createDate 2022-06-01 21:53:12
*/
@Service
public class StaffOfficeServiceImpl extends ServiceImpl<StaffOfficeMapper, StaffOffice>
    implements StaffOfficeService{

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStaffOffice(StaffOfficeUpdateDTO staffOfficeUpdateDTO) {
        // 删除掉要添加科室的用户的所有职务
        LambdaUpdateWrapper<StaffOffice> updateWrapper = new LambdaUpdateWrapper<StaffOffice>().eq(StaffOffice::getStaffId, staffOfficeUpdateDTO.getStaffId());
        Boolean remove = CollectionUtils.isEmpty(list(updateWrapper)) || remove(updateWrapper);
        // 获取科室列表
        List<Integer> officeIdList = staffOfficeUpdateDTO.getOfficeIdList();
        // 进行科室进行添加,
        // 如果科室列表为空则直接跳过添加
        // 有内容则将其转换为用于添加的列表进行添加操作
        Boolean save = CollectionUtils.isEmpty(officeIdList) || saveBatch(officeIdList.stream().map(item -> new StaffOffice(null, staffOfficeUpdateDTO.getStaffId(), item)).collect(Collectors.toList()));
        // 判断是否更新成功
        boolean result = remove && save;
        if (result) {
            // 返回更新成功
            return true;
        } else {
            // 失败则抛出业务异常
            throw new CustomServiceException("人员与科室关系更新失败", ResultCode.UNKNOWN_ERROR);
        }
    }
}




