package edu.fy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.fy.entity.Office;

/**
* @author poppy
* @description 针对表【office(科室)】的数据库操作Service
* @createDate 2022-05-26 10:09:49
*/
public interface OfficeService extends IService<Office> {

    /**
     * 根据id删除科室信息, 要删除的科室有人员关联则不进行删除
     * @param id 要删除的科室id
     * @return 是否删除成功
     */
    Boolean deleteOfficeById(Integer id);

}
