package edu.fy.entity.dto;

import edu.fy.entity.enums.SexEnum;
import edu.fy.entity.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author YangAo
 * @Description 添加人员信息类
 * @create 2022-05-28 16:36
 */
@Schema(description = "添加人员信息类, 包含职务与科室信息")
@Data
public class StaffDutyOfficeSaveDTO {

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
    @Pattern(regexp = "^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "手机号格式有误")
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 身份证号
     */
    @NotNull
    @Pattern(regexp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}(\\d|[Xx])$", message = "身份证号格式错误")
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
    @Past
    @Schema(description = "出生日期")
    private Date birthDate;

    /**
     * 状态: 0-在职, 1-离职
     */
    @NotNull
    @Schema(description = "状态: 0-在职, 1-离职")
    private StatusEnum status;

    /**
     * 职务id列表
     */
    @Schema(description = "职务id列表")
    private List<Integer> dutyIdList;

    /**
     * 科室id列表
     */
    @Schema(description = "科室id列表")
    private List<Integer> officeIdList;

    /**
     * 创建者id
     */
    @Schema(description = "创建者id", minimum = "1")
    private Integer createId;
}
