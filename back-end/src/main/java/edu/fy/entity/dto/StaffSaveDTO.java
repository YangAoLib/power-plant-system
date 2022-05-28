package edu.fy.entity.dto;

import edu.fy.entity.enums.SexEnum;
import edu.fy.entity.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author YangAo
 * @Description 添加人员信息类
 * @create 2022-05-28 16:36
 */
@Schema(description = "添加人员信息类")
@Data
public class StaffSaveDTO {
    /**
     * 人员姓名
     */
    @NotBlank
    @Length(min = 1, max = 255)
    @Schema(description = "人员姓名")
    private String name;

    /**
     * 密码
     */
    @NotBlank
    @Schema(description = "密码")
    private String password;

    /**
     * 性别: 0-男, 1-女
     */
    @NotNull
    @Schema(description = "性别: 0-男, 1-女")
    private SexEnum sex;

    /**
     * 联系电话
     */
    @NotNull
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式有误")
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 身份证号
     */
    @NotNull
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[\\dXx]$", message = "身份证号格式错误")
    @Schema(description = "身份证号")
    private String cardId;

    /**
     * 祖籍
     */
    @NotBlank
    @Length(min = 1, max = 255)
    @Schema(description = "祖籍")
    private String originalHome;

    /**
     * 出生日期
     */
    @NotNull
    @Schema(description = "出生日期")
    private Date birthDate;

    /**
     * 状态: 0-在职, 1-离职
     */
    @NotNull
    @Schema(description = "状态: 0-在职, 1-离职")
    private StatusEnum status;

    /**
     * 创建者id
     */
    @Schema(description = "创建者id")
    private Integer createId;
}
