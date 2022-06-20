package edu.fy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fy.entity.Duty;
import edu.fy.entity.StaffDuty;
import edu.fy.mapper.DutyMapper;
import edu.fy.mapper.StaffDutyMapper;
import edu.fy.service.DutyService;
import edu.fy.utils.CustomServiceException;
import edu.fy.utils.ResultCode;
import org.springframework.stereotype.Service;

/**
* @author poppy
* @description 针对表【duty(职务)】的数据库操作Service实现
* @createDate 2022-05-26 10:09:49
*/
@Service
public class DutyServiceImpl extends ServiceImpl<DutyMapper, Duty> implements DutyService{

    @Override
    public Boolean deleteDutyById(Integer id) {
        // 查询是否有人员关联, 如果有则抛出异常
        if (staffDutyMapper.exists(new LambdaQueryWrapper<StaffDuty>().eq(StaffDuty::getDutyId, id))) {
            throw new CustomServiceException("该职务下有人员关联, 无法删除", ResultCode.DATA_ASSOCIATION);
        }
        // 根据id进行删除
        return removeById(id);
    }

    private final StaffDutyMapper staffDutyMapper;

    public DutyServiceImpl(StaffDutyMapper staffDutyMapper) {
        this.staffDutyMapper = staffDutyMapper;
    }
}




