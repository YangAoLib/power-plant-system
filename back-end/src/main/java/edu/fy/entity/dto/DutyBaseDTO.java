package edu.fy.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author poppy
 * @Description 职务基础信息传输类
 * @create 2022-06-03 8:35
 */
@Data
@Schema(description = "职务基础信息传输类")
public class DutyBaseDTO {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Integer id;

    /**
     * 职务名
     */
    @Schema(description = "职务名")
    private String name;
}
