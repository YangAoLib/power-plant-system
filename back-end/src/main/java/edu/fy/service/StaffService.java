package edu.fy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.fy.entity.Staff;

/**
* @author YangAo
* @description 针对表【staff(人员信息)】的数据库操作Service
* @createDate 2022-05-26 10:09:49
*/
public interface StaffService extends IService<Staff> {

    /**
     * 添加人员信息
     * @param staff 人员信息
     * @return 添加情况
     */
    Boolean saveStaff(Staff staff);

}
