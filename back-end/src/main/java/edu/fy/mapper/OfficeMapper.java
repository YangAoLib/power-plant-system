package edu.fy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.fy.entity.Office;
import org.apache.ibatis.annotations.Mapper;

/**
* @author poppy
* @description 针对表【office(科室)】的数据库操作Mapper
* @createDate 2022-05-26 10:09:49
* @Entity edu.fy.entity.Office
*/
@Mapper
public interface OfficeMapper extends BaseMapper<Office> {

}




