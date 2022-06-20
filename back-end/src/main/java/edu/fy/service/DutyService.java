package edu.fy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.fy.entity.Duty;

/**
* @author poppy
* @description 针对表【duty(职务)】的数据库操作Service
* @createDate 2022-05-26 10:09:49
*/
public interface DutyService extends IService<Duty> {

    /**
     * 根据职务id进行职务的删除, 有关联的人员则不进行删除
     * @param id 职务id
     * @return 是否删除成功
     */
    Boolean deleteDutyById(Integer id);

}
