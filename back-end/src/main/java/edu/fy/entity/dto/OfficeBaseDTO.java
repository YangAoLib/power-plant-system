package edu.fy.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author poppy
 * @Description 科室基础信息传输类
 * @create 2022-06-04 15:29
 */
@Data
@Schema(description = "科室基础信息传输类")
public class OfficeBaseDTO {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Integer id;

    /**
     * 科室名称
     */
    @Schema(description = "科室名称")
    private String name;
}
