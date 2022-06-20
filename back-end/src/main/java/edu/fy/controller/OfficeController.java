package edu.fy.controller;

import edu.fy.entity.Office;
import edu.fy.entity.dto.OfficeBaseDTO;
import edu.fy.entity.dto.OfficeSaveDTO;
import edu.fy.entity.dto.OfficeUpdateDTO;
import edu.fy.service.OfficeService;
import edu.fy.utils.BeanUtil;
import edu.fy.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author poppy
 * @Description 科室管理
 * @create 2022-06-04 16:08
 */
@RestController
@Tag(name = "科室 Controller", description = "科室管理")
public class OfficeController {

    /**
     * 查询全部科室信息
     * @return 查询到的科室信息
     */
    @GetMapping("/office/query-all")
    @Operation(summary = "查询全部科室信息")
    public Result<List<OfficeBaseDTO>> queryAll() {
        return Result.success(BeanUtil.copyList(officeService.list(), OfficeBaseDTO.class));
    }

    /**
     * 添加科室信息
     * @param officeSaveDTO 要添加的科室信息(科室名称)
     * @return 添加情况, 是否添加成功
     */
    @PostMapping("/office/save")
    @Operation(summary = "添加科室信息")
    public Result<Boolean> saveOffice(@RequestBody @Validated OfficeSaveDTO officeSaveDTO) {
        return Result.success(officeService.save(BeanUtil.copyBean(officeSaveDTO, Office.class)));
    }

    /**
     * 更新科室信息
     * @param officeUpdateDTO 要更新的科室信息(主键, 科室名称)
     * @return 更新情况, 是否更新成功
     */
    @PostMapping("/office/update")
    @Operation(summary = "更新科室信息")
    public Result<Boolean> updateOffice(@RequestBody @Validated OfficeUpdateDTO officeUpdateDTO) {
        return Result.success(officeService.updateById(BeanUtil.copyBean(officeUpdateDTO, Office.class)));
    }

    /**
     * 根据id删除科室信息, 有关联人员则无法删除
     * @param id 要删除的科室id
     * @return 删除情况, 是否删除成功
     */
    @DeleteMapping("/office/delete/{id}")
    @Operation(summary = "根据id删除科室信息, 有关联人员则无法删除")
    public Result<Boolean> deleteOfficeById(@PathVariable("id") Integer id) {
        return Result.success(officeService.deleteOfficeById(id));
    }



    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }
}
