package edu.fy.controller;

import edu.fy.entity.dto.OfficeBaseDTO;
import edu.fy.service.OfficeService;
import edu.fy.utils.BeanUtil;
import edu.fy.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author poppy
 * @Description 科室管理
 * @create 2022-06-04 16:08
 */
@RestController
@Tag(name = "科室 Controller", description = "科室管理")
public class OfficeController {

    @GetMapping("/office/query-all")
    @Operation(summary = "查询全部科室信息")
    public Result<List<OfficeBaseDTO>> queryAll() {
        return Result.success(BeanUtil.copyList(officeService.list(), OfficeBaseDTO.class));
    }
    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }
}
