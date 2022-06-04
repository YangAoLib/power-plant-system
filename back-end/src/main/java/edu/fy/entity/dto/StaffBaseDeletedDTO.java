package edu.fy.entity.dto;

import edu.fy.entity.enums.DeletedEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YangAo
 * @Description 人员基础信息, 带有删除位
 * @create 2022-05-29 15:48
 */
@Schema(description = "人员基础信息, 带有删除位")
@Data
@EqualsAndHashCode(callSuper = false)
public class StaffBaseDeletedDTO extends StaffBaseDTO{

    @Schema(description = "逻辑删除位: 0-未删除, 1-已删除")
    private DeletedEnum isDeleted;

}
