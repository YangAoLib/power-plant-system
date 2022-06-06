package edu.fy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.fy.entity.Staff;
import edu.fy.entity.dto.StaffQueryConditionDTO;
import edu.fy.entity.vo.StaffQueryWithCreatorUpdaterDutyOfficeVO;
import org.apache.ibatis.annotations.Param;

/**
* @author YangAo
* @description 针对表【staff(人员信息)】的数据库操作Mapper
* @createDate 2022-05-26 10:09:49
* @Entity edu.fy.entity.Staff
*/
public interface StaffMapper extends BaseMapper<Staff> {

    /**
     * 根据id查询人员信息, 无视逻辑删除
     * @param id 人员id
     * @return 人员信息
     */
    Staff searchOneById(@Param("id") Integer id);

    /**
     * 根据条件进行查询人员信息
     * @param condition 条件
     * @param page 分页信息
     * @return 人员、创建者、更新者、职务、科室 信息
     */
    Page<StaffQueryWithCreatorUpdaterDutyOfficeVO> searchAllByCondition(@Param("condition") StaffQueryConditionDTO condition, IPage<StaffQueryWithCreatorUpdaterDutyOfficeVO> page);
}




