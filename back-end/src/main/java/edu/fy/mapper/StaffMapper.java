package edu.fy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.fy.entity.Staff;
import edu.fy.entity.dto.StaffQueryConditionDTO;
import edu.fy.entity.vo.StaffQueryWithCreatorAndUpdaterVO;
import org.apache.ibatis.annotations.Param;

/**
* @author YangAo
* @description 针对表【staff(人员信息)】的数据库操作Mapper
* @createDate 2022-05-26 10:09:49
* @Entity edu.fy.entity.Staff
*/
public interface StaffMapper extends BaseMapper<Staff> {

    Staff searchOneById(@Param("id") Integer id);

    Page<StaffQueryWithCreatorAndUpdaterVO> searchAllByCondition(@Param("condition") StaffQueryConditionDTO condition, IPage<StaffQueryWithCreatorAndUpdaterVO> page);
}




