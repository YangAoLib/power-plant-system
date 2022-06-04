package edu.fy.controller;

import edu.fy.entity.enums.DeletedEnum;
import edu.fy.entity.enums.SexEnum;
import edu.fy.entity.enums.StatusEnum;
import edu.fy.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author YangAo
 * @Description 返回各个枚举类
 * @create 2022-05-29 11:35
 */
@RestController
@RequestMapping("/enums")
@Tag(name = "枚举 Controller", description = "返回枚举类型信息")
public class EnumsController {

    @GetMapping("/deleted")
    @Operation(summary = "逻辑删除位")
    public Result<List<DeletedEnum>> deletedEnums() {
        return Result.success(Arrays.asList(DeletedEnum.values()));
    }

    @GetMapping("/sex")
    @Operation(summary = "性别")
    public Result<List<SexEnum>> sexEnums() {
        return Result.success(Arrays.asList(SexEnum.values()));
    }

    @GetMapping("/status")
    @Operation(summary = "在职状态")
    public Result<List<StatusEnum>> statusEnums() {
        return Result.success(Arrays.asList(StatusEnum.values()));
    }
}
