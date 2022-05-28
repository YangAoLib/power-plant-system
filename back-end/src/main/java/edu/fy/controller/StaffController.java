package edu.fy.controller;

import edu.fy.entity.Staff;
import edu.fy.entity.dto.StaffSaveDTO;
import edu.fy.service.StaffService;
import edu.fy.utils.CustomException;
import edu.fy.utils.Result;
import edu.fy.utils.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangAo
 * @Description 人员信息管理
 * @create 2022-05-28 10:54
 */
@RestController
@Tag(name = "人员信息Controller", description = "人员信息管理")
public class StaffController {

    /**
     * 添加人员信息
     * @param staffSaveDTO 添加人员所需信息
     * @return 添加结果
     */
    @PostMapping("/staff/save-one")
    @Operation(summary = "添加人员信息")
    public Result<?> saveStaff(@RequestBody @Validated StaffSaveDTO staffSaveDTO) {
        try {
            Staff staff = new Staff();
            BeanUtils.copyProperties(staffSaveDTO, staff);
            return Result.success(staffService.saveStaff(staff));
        } catch (CustomException e) {
            return Result.fail(e);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }


    @Autowired
    private StaffService staffService;
}
