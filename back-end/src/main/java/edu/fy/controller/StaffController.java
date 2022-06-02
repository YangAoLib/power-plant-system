package edu.fy.controller;

import edu.fy.entity.Staff;
import edu.fy.entity.dto.*;
import edu.fy.service.StaffDutyService;
import edu.fy.service.StaffOfficeService;
import edu.fy.service.StaffService;
import edu.fy.utils.BeanUtil;
import edu.fy.utils.CustomServiceException;
import edu.fy.utils.Result;
import edu.fy.utils.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author YangAo
 * @Description 人员信息管理
 * @create 2022-05-28 10:54
 */
@RestController
@Tag(name = "人员信息 Controller", description = "人员信息管理")
public class StaffController {

    /**
     * 添加人员信息
     *
     * @param staffSaveDTO 添加人员所需信息
     * @return 添加结果
     */
    @PostMapping("/staff/save-one")
    @Operation(summary = "添加人员信息")
    public Result<?> saveStaff(@RequestBody @Validated StaffSaveDTO staffSaveDTO) {
        try {
            return Result.success(staffService.saveStaff(BeanUtil.copyBean(staffSaveDTO, Staff.class)));
        } catch (CustomServiceException e) {
            return Result.fail(e);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 更新人员信息
     *
     * @param staffUpdateDTO 需要更新的人员id与具体信息
     * @return 更新情况
     */
    @PostMapping("/staff/update-one")
    @Operation(summary = "更新人员信息")
    public Result<?> updateStaff(@RequestBody @Validated StaffUpdateDTO staffUpdateDTO) {
        try {
            return Result.success(staffService.updateStaff(BeanUtil.copyBean(staffUpdateDTO, Staff.class)));
        } catch (CustomServiceException e) {
            return Result.fail(e);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 根据id删除人员信息
     *
     * @param id 要删除的人员的id
     * @return 删除情况
     */
    @DeleteMapping("/staff/delete-one/{id}")
    @Operation(summary = "根据id删除人员信息")
    public Result<?> deleteStaffById(@PathVariable("id") @Parameter(description = "要删除的人员id", required = true) Integer id) {
        try {
            return Result.success(staffService.removeById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 查询全部人员信息的全部信息
     *
     * @return 查询到的信息
     */
    @GetMapping("/staff/query-all")
    @Operation(summary = "查询全部人员信息的全部信息")
    public Result<?> queryAll() {
        try {
            return Result.success(staffService.searchStaffByCondition(null).getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }


    /**
     * 根据id查找人员信息
     *
     * @param id 人员id
     * @return 查询到的信息
     */
    @GetMapping("/staff/query-one/{id}")
    @Operation(description = "根据id查找人员信息")
    public Result<?> queryOne(@PathVariable("id") @Parameter(description = "要查找人员的id", required = true) Integer id) {
        // 整合信息
        StaffQueryConditionDTO conditionDTO = new StaffQueryConditionDTO();
        conditionDTO.setId(id);
        // 调用service
        try {
            return Result.success(staffService.searchStaffByCondition(conditionDTO));
        } catch (CustomServiceException e) {
            return Result.fail(e);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 根据多种条件查找人员信息
     *
     * @param condition 条件信息
     * @return 查询到的信息结果
     */
    @PostMapping("/staff/query")
    @Operation(description = "根据多种条件查找人员信息")
    public Result<?> queryByCondition(@RequestBody @Validated StaffQueryConditionDTO condition) {
        try {
            return Result.success(staffService.searchStaffByCondition(condition));
        } catch (CustomServiceException e) {
            return Result.fail(e);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 更新人员与职务的关系
     *
     * @param staffDutyUpdateDTO 人员与职务关系更新传输类
     * @return 更新结果
     */
    @PostMapping("/staff/duty/update")
    @Operation(summary = "更新人员与职务的关系")
    public Result<Boolean> updateStaffDuty(@RequestBody @Validated StaffDutyUpdateDTO staffDutyUpdateDTO) {
        return Result.success(staffDutyService.updateStaffDuty(staffDutyUpdateDTO));
    }

    /**
     * 更新人员与科室的关系
     * @param staffOfficeUpdateDTO 人员与科室关系更新类
     * @return 更新情况
     */
    @PostMapping("/staff/office/update")
    @Operation(summary = "更新人员与科室的关系")
    public Result<Boolean> updateStaffOffice(@RequestBody @Validated StaffOfficeUpdateDTO staffOfficeUpdateDTO) {
        return Result.success(staffOfficeService.updateStaffOffice(staffOfficeUpdateDTO));
    }

    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffDutyService staffDutyService;
    @Autowired
    private StaffOfficeService staffOfficeService;
}
