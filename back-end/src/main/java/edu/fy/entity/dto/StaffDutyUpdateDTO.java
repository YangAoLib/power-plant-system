package edu.fy.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @author poppy
 * @Description 人员与职务关系更新类
 * @create 2022-06-01 22:24
 */
@Schema(description = "人员与职务关系更新类")
@Data
public class StaffDutyUpdateDTO {

    /**
     * 人员id
     */
    @NotNull
    @Min(1)
    @Schema(description = "人员id")
    private Integer staffId;

    /**
     * 人员对应的职务的id列表
     */
    @Schema(description = "人员对应的职务的id列表")
    private List<Integer> dutyIdList;
}
