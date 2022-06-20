package edu.fy.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author poppy
 * @Description 职务更新 信息传输类
 * @create 2022-06-14 20:30
 */
@Data
@Schema(description = "职务更新 信息传输类")
public class DutyUpdateDTO {

    /**
     * 主键
     */
    @NotNull
    @Schema(description = "主键")
    private Integer id;

    /**
     * 职务名
     */
    @NotBlank
    @Length(max = 20)
    @Schema(description = "职务名")
    private String name;
}
