package edu.fy.controller;

import edu.fy.entity.Duty;
import edu.fy.entity.dto.DutyBaseDTO;
import edu.fy.entity.dto.DutySaveDTO;
import edu.fy.entity.dto.DutyUpdateDTO;
import edu.fy.service.DutyService;
import edu.fy.utils.BeanUtil;
import edu.fy.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author poppy
 * @Description 职务管理
 * @create 2022-06-04 16:04
 */
@RestController
@Tag(name = "职务 Controller", description = "职务管理")
public class DutyController {

    /**
     * 查询全部职务
     * @return 查询到的职务信息
     */
    @GetMapping("/duty/query-all")
    @Operation(summary = "查询全部职务")
    public Result<List<DutyBaseDTO>> queryAll() {
        return Result.success(BeanUtil.copyList(dutyService.list(), DutyBaseDTO.class));
    }

    /**
     * 添加职务
     * @param dutySaveDTO 要添加的职务信息(职务名称)
     * @return 添加情况, 是否添加成功
     */
    @PostMapping("/duty/save")
    @Operation(summary = "添加职务")
    public Result<Boolean> saveDuty(@RequestBody @Validated DutySaveDTO dutySaveDTO) {
        return Result.success(dutyService.save(BeanUtil.copyBean(dutySaveDTO, Duty.class)));
    }

    /**
     * 更新职务信息
     * @param dutyUpdateDTO 要更新的职务信息(主键, 职务名称)
     * @return 更新情况, 是否更新成功
     */
    @PostMapping("/duty/update")
    @Operation(summary = "更新职务")
    public Result<Boolean> updateDuty(@RequestBody @Validated DutyUpdateDTO dutyUpdateDTO) {
        return Result.success(dutyService.updateById(BeanUtil.copyBean(dutyUpdateDTO, Duty.class)));
    }

    /**
     * 根据id删除职务, 有关联人员则无法删除
     * @param id 要删除的职务id
     * @return 删除情况, 是否删除成功
     */
    @DeleteMapping("/duty/delete/{id}")
    @Operation(summary = "根据id删除职务, 有关联人员则无法删除")
    public Result<Boolean> deleteDutyById(@PathVariable("id") Integer id) {
        return Result.success(dutyService.deleteDutyById(id));
    }


    private final DutyService dutyService;

    public DutyController(DutyService dutyService) {
        this.dutyService = dutyService;
    }
}
