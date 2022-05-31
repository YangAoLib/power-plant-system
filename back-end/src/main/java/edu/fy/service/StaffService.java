package edu.fy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.fy.entity.Staff;
import edu.fy.entity.dto.StaffQueryConditionDTO;
import edu.fy.entity.vo.StaffQueryWithCreatorAndUpdaterVO;

/**
* @author YangAo
* @description 针对表【staff(人员信息)】的数据库操作Service
* @createDate 2022-05-26 10:09:49
*/
public interface StaffService extends IService<Staff> {

    /**
     * 添加人员信息
     * @param staff 人员信息
     * @return 是否成功
     */
    Boolean saveStaff(Staff staff);

    /**
     * 根据id更新人员信息, 包括密码
     * @param staff 人员信息, 一定要包含id
     * @return 是否成功
     */
    Boolean updateStaff(Staff staff);

    /**
     * 根据条件查询人员信息
     * @param condition 人员信息查询条件
     * @return 以Page的方式返回查询到的信息
     */
    Page<StaffQueryWithCreatorAndUpdaterVO> searchStaffByCondition(StaffQueryConditionDTO condition);
}
