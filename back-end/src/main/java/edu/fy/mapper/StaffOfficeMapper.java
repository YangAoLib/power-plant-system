package edu.fy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.fy.entity.StaffOffice;
import edu.fy.entity.dto.OfficeBaseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author poppy
* @description 针对表【staff_office(人员与科室关系)】的数据库操作Mapper
* @createDate 2022-06-01 21:53:12
* @Entity edu.fy.entity.StaffOffice
*/
public interface StaffOfficeMapper extends BaseMapper<StaffOffice> {

    /**
     * 根据人员id查询科室信息
     * @param staffId 人员id
     * @return 人员对应的科室信息列表
     */
    List<OfficeBaseDTO> selectOfficeBaseByStaffId(@Param("staffId") Integer staffId);
}




