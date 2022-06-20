package edu.fy.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.fy.entity.enums.BaseEnum;
import edu.fy.entity.enums.DeletedEnum;
import edu.fy.entity.enums.SexEnum;
import edu.fy.entity.enums.StatusEnum;
import edu.fy.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    private static SimpleModule enumModule;
    private static ObjectMapper objectMapper;
    static {
        // 通过向 objectMapper 中添加 module 来针对特点的类型来进行特殊序列化
        enumModule = new SimpleModule();
        enumModule.addSerializer(BaseEnum.class, new JsonSerializer<BaseEnum>() {
            @Override
            public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                // 开始写入对象
                gen.writeStartObject();
                // 分别指定 k v   value   desc
                gen.writeNumberField("value",value.getValue());
                gen.writeStringField("desc",value.getDesc());
                // 显式结束操作
                gen.writeEndObject();
            }
        });
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(enumModule);
    }

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
