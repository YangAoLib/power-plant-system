package edu.fy.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fy.entity.StaffDuty;
import edu.fy.entity.dto.StaffDutyUpdateDTO;
import edu.fy.mapper.StaffDutyMapper;
import edu.fy.service.StaffDutyService;
import edu.fy.utils.CustomServiceException;
import edu.fy.utils.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author poppy
* @description 针对表【staff_duty(人员与职务的关系)】的数据库操作Service实现
* @createDate 2022-06-01 21:53:12
*/
@Service
public class StaffDutyServiceImpl extends ServiceImpl<StaffDutyMapper, StaffDuty>
    implements StaffDutyService{

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStaffDuty(StaffDutyUpdateDTO staffDutyUpdateDTO) {
        // 删除掉要添加职务的用户的所有职务, 存在关系才进行删除
        LambdaUpdateWrapper<StaffDuty> updateWrapper = new LambdaUpdateWrapper<StaffDuty>().eq(StaffDuty::getStaffId, staffDutyUpdateDTO.getStaffId());
        Boolean remove = CollectionUtils.isEmpty(list(updateWrapper)) || remove(updateWrapper);
        // 获取职务列表
        List<Integer> dutyIdList = staffDutyUpdateDTO.getDutyIdList();
        // 进行职务的添加
        // 如果职务列表为空则直接跳过添加
        // 有内容则将其转换为用于添加的列表进行添加操作
        Boolean save = CollectionUtils.isEmpty(dutyIdList) || saveBatch(dutyIdList.stream().map(item -> new StaffDuty(null, staffDutyUpdateDTO.getStaffId(), item)).collect(Collectors.toList()));
        // 判断是否更新成功
        boolean result = remove && save;
        if (result) {
            // 返回更新成功
            return true;
        } else {
            // 失败则抛出业务异常
            throw new CustomServiceException("人员与职务关系更新失败", ResultCode.UNKNOWN_ERROR);
        }
    }
}




