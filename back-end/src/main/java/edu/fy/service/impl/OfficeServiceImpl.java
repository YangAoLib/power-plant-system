package edu.fy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fy.entity.Office;
import edu.fy.service.OfficeService;
import edu.fy.mapper.OfficeMapper;
import org.springframework.stereotype.Service;

/**
* @author YangAo
* @description 针对表【office(科室)】的数据库操作Service实现
* @createDate 2022-05-26 10:09:49
*/
@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeMapper, Office>
    implements OfficeService{

}




