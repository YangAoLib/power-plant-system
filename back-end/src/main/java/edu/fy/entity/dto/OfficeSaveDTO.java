package edu.fy.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author poppy
 * @Description 科室存储 信息传输类
 * @create 2022-06-14 21:19
 */
@Data
@Schema(description = "科室存储 信息传输类")
public class OfficeSaveDTO {

    /**
     * 科室名称
     */
    @NotBlank
    @Length(max = 20)
    @Schema(description = "科室名称")
    private String name;
}
