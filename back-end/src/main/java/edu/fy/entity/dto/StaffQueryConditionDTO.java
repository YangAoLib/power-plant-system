package edu.fy.entity.dto;

import edu.fy.entity.enums.SexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author YangAo
 * @Description 人员信息条件查询类
 * @create 2022-05-29 10:07
 */
@Schema(description = "人员信息条件查询类")
@Data
@EqualsAndHashCode(callSuper = false)
public class StaffQueryConditionDTO extends StaffBaseDTO {

    /**
     * 性别: 0-男, 1-女
     */
    @Schema(description = "性别: 0-男, 1-女")
    private SexEnum sex;

    /**
     * 祖籍
     */
    @Length(max = 255)
    @Schema(description = "祖籍")
    private String originalHome;

    /**
     * 出生日期的起始时间
     */
    @Schema(description = "出生日期的起始时间")
    private Date birthDateStart;

    /**
     * 出生日期的末尾时间
     */
    @Schema(description = "出生日期的末尾时间")
    private Date birthDateEnd;

    /**
     * 创建时间的起始时间
     */
    @Schema(description = "创建时间起始时间")
    private Date createTimeStart;

    /**
     * 创建时间的末尾时间
     */
    @Schema(description = "创建时间的末尾时间")
    private Date createTimeEnd;

    /**
     * 更新时间的起始时间
     */
    @Schema(description = "更新时间的起始时间")
    private Date updateTimeStart;

    /**
     * 更新时间的末尾时间
     */
    @Schema(description = "更新时间的末尾时间")
    private Date updateTimeEnd;

    /**
     * 创建者id
     */
    @Schema(description = "创建者id")
    private Integer createId;

    /**
     * 创建者身份证号
     */
    @Pattern(regexp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}(\\d|[Xx])$", message = "身份证号格式错误")
    @Schema(description = "创建者身份证号")
    private String creatorCardId;

    /**
     * 更新者id
     */
    @Schema(description = "更新者id")
    private Integer updateId;

    /**
     * 更新者身份证号
     */
    @Pattern(regexp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}(\\d|[Xx])$", message = "身份证号格式错误")
    @Schema(description = "更新者身份证号")
    private String updaterCardId;

    /**
     * 单页数据的个数
     */
    @Min(value = 1, message = "单页数据量不能小于1")
    @Schema(description = "单页数据的个数")
    private Integer pageSize;

    /**
     * 当前页数
     */
    @Min(value = 1, message = "当前页码不能小于1")
    @Schema(description = "当前页数")
    private Integer currentPage;
}
