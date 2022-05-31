package edu.fy.entity.dto;

import edu.fy.entity.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @author YangAo
 * @Description 人员基础信息类
 * @create 2022-05-29 10:47
 */
@Data
@Schema(description = "人员基础信息类")
public class StaffBaseDTO {
    /**
     * 主键
     */
    @Schema(description = "主键")
    private Integer id;
    /**
     * 人员姓名
     */
    @Length(min = 1, max = 255)
    @Schema(description = "人员姓名")
    private String name;
    /**
     * 联系电话
     */
    @Pattern(regexp = "^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "手机号格式有误")
    @Schema(description = "联系电话")
    private String phone;
    /**
     * 身份证号
     */
    @Pattern(regexp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}(\\d|[Xx])$", message = "身份证号格式错误")
    @Schema(description = "身份证号")
    private String cardId;
    /**
     * 状态: 0-在职, 1-离职
     */
    @Schema(description = "状态: 0-在职, 1-离职")
    private StatusEnum status;
}
