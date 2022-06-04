package edu.fy.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author poppy
 * @Description 人员与科室关系更新类
 * @create 2022-06-01 23:17
 */
@Schema(description = "人员与科室关系更新类")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffOfficeUpdateDTO {

    /**
     * 人员id
     */
    @NotNull
    @Min(1)
    @Schema(description = "人员id")
    private Integer staffId;

    /**
     * 人员对应的科室的id列表
     */
    @Schema(description = "人员对应的科室的id列表")
    private List<Integer> officeIdList;
}
