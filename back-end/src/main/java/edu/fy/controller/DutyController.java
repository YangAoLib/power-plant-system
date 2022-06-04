package edu.fy.controller;

import edu.fy.entity.dto.DutyBaseDTO;
import edu.fy.service.DutyService;
import edu.fy.utils.BeanUtil;
import edu.fy.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author poppy
 * @Description 职务管理
 * @create 2022-06-04 16:04
 */
@RestController
@Tag(name = "职务 Controller", description = "职务管理")
public class DutyController {

    @GetMapping("/duty/query-all")
    @Operation(summary = "查询全部职务")
    public Result<List<DutyBaseDTO>> queryAll() {
        return Result.success(BeanUtil.copyList(dutyService.list(), DutyBaseDTO.class));
    }

    private final DutyService dutyService;

    public DutyController(DutyService dutyService) {
        this.dutyService = dutyService;
    }
}
