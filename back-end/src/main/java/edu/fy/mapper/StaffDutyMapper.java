package edu.fy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.fy.entity.StaffDuty;
import edu.fy.entity.dto.DutyBaseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author poppy
* @description 针对表【staff_duty(人员与职务的关系)】的数据库操作Mapper
* @createDate 2022-06-01 21:53:12
* @Entity edu.fy.entity.StaffDuty
*/
public interface StaffDutyMapper extends BaseMapper<StaffDuty> {

    /**
     * 根据人员id查询其对应的职务信息
     * @param staffId 人员id
     * @return 人员对应的职务信息列表
     */
    List<DutyBaseDTO> selectDutyBaseByStaffId(@Param("staffId") Integer staffId);
}




