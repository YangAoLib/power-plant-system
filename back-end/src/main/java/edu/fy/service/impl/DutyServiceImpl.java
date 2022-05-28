package edu.fy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fy.entity.Duty;
import edu.fy.service.DutyService;
import edu.fy.mapper.DutyMapper;
import org.springframework.stereotype.Service;

/**
* @author YangAo
* @description 针对表【duty(职务)】的数据库操作Service实现
* @createDate 2022-05-26 10:09:49
*/
@Service
public class DutyServiceImpl extends ServiceImpl<DutyMapper, Duty>
    implements DutyService{

}




