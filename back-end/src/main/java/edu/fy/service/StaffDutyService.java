package edu.fy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.fy.entity.StaffDuty;
import edu.fy.entity.dto.StaffDutyUpdateDTO;

/**
* @author poppy
* @description 针对表【staff_duty(人员与职务的关系)】的数据库操作Service
* @createDate 2022-06-01 21:53:12
*/
public interface StaffDutyService extends IService<StaffDuty> {

    /**
     * 根据人员id与职务id列表进行用户的职务更新
     * @param staffDutyUpdateDTO 需要更新的人员id与职务id列表
     * @return 更新是否成功
     * @author poppy
     */
    Boolean updateStaffDuty(StaffDutyUpdateDTO staffDutyUpdateDTO);
}
