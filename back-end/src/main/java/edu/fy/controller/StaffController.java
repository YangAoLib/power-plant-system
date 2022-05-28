package edu.fy.controller;

import edu.fy.entity.Staff;
import edu.fy.entity.dto.StaffSaveDTO;
import edu.fy.entity.dto.StaffUpdateDTO;
import edu.fy.service.StaffService;
import edu.fy.utils.BeanUtil;
import edu.fy.utils.CustomException;
import edu.fy.utils.Result;
import edu.fy.utils.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            return Result.success(staffService.saveStaff(BeanUtil.copyBean(staffSaveDTO, Staff.class)));
        } catch (CustomException e) {
            return Result.fail(e);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 更新人员信息
     * @param staffUpdateDTO 需要更新的人员id与具体信息
     * @return 更新情况
     */
    @PostMapping("/staff/update-one")
    @Operation(summary = "更新人员信息")
    public Result<?> updateStaff(@RequestBody @Validated StaffUpdateDTO staffUpdateDTO) {
        try {
            return Result.success(staffService.updateStaff(BeanUtil.copyBean(staffUpdateDTO, Staff.class)));
        } catch (CustomException e) {
            return Result.fail(e);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultCode.SYSTEM_ERROR, e);
        }
    }


    @Autowired
    private StaffService staffService;
}
