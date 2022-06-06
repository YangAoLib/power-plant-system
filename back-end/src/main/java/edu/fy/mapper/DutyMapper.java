package edu.fy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.fy.entity.Duty;
import org.apache.ibatis.annotations.Mapper;

/**
* @author YangAo
* @description 针对表【duty(职务)】的数据库操作Mapper
* @createDate 2022-05-26 10:09:49
* @Entity edu.fy.entity.Duty
*/
@Mapper
public interface DutyMapper extends BaseMapper<Duty> {

}




