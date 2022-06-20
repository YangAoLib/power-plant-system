package edu.fy.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author poppy
 * @Description 科室更新 信息传输类
 * @create 2022-06-14 21:24
 */
@Data
@Schema(description = "科室更新 信息传输类")
public class OfficeUpdateDTO {


    /**
     * 主键
     */
    @NotNull
    @Min(1)
    @Schema(description = "主键")
    private Integer id;

    /**
     * 科室名称
     */
    @NotBlank
    @Length(max = 20)
    @Schema(description = "科室名称")
    private String name;

}
