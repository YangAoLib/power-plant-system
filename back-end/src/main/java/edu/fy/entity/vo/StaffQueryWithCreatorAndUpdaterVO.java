package edu.fy.entity.vo;

import edu.fy.entity.dto.StaffBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YangAo
 * @Description 查询人员信息数据视图类, 携带有创建者信息和更新者信息
 * @create 2022-05-29 10:13
 */
@Schema(description = "查询人员信息数据视图类, 携带有创建者信息和更新者信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class StaffQueryWithCreatorAndUpdaterVO extends StaffQueryVO {

    /**
     * 创建者信息
     */
    @Schema(description = "创建者信息")
    private StaffBaseDTO creator;

    /**
     * 更新者信息
     */
    @Schema(description = "更新者信息")
    private StaffBaseDTO updater;

    private static final long serialVersionUID = -2744843233628471468L;
}
