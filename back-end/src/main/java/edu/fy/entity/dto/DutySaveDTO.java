package edu.fy.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author poppy
 * @Description 职务存储 信息传输类
 * @create 2022-06-14 20:20
 */
@Data
@Schema(description = "职务存储 信息传输类")
public class DutySaveDTO {

    /**
     * 职务名称
     */
    @NotBlank
    @Length(max = 20)
    @Schema(description = "职务名称")
    private String name;
}
