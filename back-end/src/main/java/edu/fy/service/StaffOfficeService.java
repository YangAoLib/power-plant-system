package edu.fy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.fy.entity.StaffOffice;
import edu.fy.entity.dto.StaffOfficeUpdateDTO;

/**
* @author poppy
* @description 针对表【staff_office(人员与科室关系)】的数据库操作Service
* @createDate 2022-06-01 21:53:12
*/
public interface StaffOfficeService extends IService<StaffOffice> {

    /**
     * 根据人员id与科室id列表进行用户的职务更新
     * @param staffOfficeUpdateDTO 需要更新的人员id与科室id列表
     * @return 更新情况
     */
    Boolean updateStaffOffice(StaffOfficeUpdateDTO staffOfficeUpdateDTO);
}
