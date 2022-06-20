package edu.fy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fy.entity.Office;
import edu.fy.entity.StaffOffice;
import edu.fy.mapper.OfficeMapper;
import edu.fy.mapper.StaffOfficeMapper;
import edu.fy.service.OfficeService;
import edu.fy.utils.CustomServiceException;
import edu.fy.utils.ResultCode;
import org.springframework.stereotype.Service;

/**
* @author poppy
* @description 针对表【office(科室)】的数据库操作Service实现
* @createDate 2022-05-26 10:09:49
*/
@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeMapper, Office>
    implements OfficeService{

    @Override
    public Boolean deleteOfficeById(Integer id) {
        // 科室如果有人员关联则不进行删除操作
        if (staffOfficeMapper.exists(new LambdaQueryWrapper<StaffOffice>().eq(StaffOffice::getOfficeId, id))) {
            throw new CustomServiceException("该科室下有人员关联, 无法删除", ResultCode.DATA_ASSOCIATION);
        }
        // 删除科室
        return removeById(id);
    }

    private final StaffOfficeMapper staffOfficeMapper;

    public OfficeServiceImpl(StaffOfficeMapper staffOfficeMapper) {
        this.staffOfficeMapper = staffOfficeMapper;
    }
}




