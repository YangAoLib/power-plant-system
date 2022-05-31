package edu.fy.entity.vo;

import edu.fy.entity.enums.SexEnum;
import edu.fy.entity.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YangAo
 * @Description 查询人员信息数据视图类
 * @create 2022-05-29 10:13
 */
@Schema(description = "查询人员信息数据视图类")
@Data
public class StaffQueryVO implements Serializable {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Integer id;

    /**
     * 人员姓名
     */
    @Schema(description = "人员姓名")
    private String name;

    /**
     * 性别: 0-男, 1-女
     */
    @Schema(description = "性别: 0-男, 1-女")
    private SexEnum sex;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String cardId;

    /**
     * 祖籍
     */
    @Schema(description = "祖籍")
    private String originalHome;

    /**
     * 出生日期
     */
    @Schema(description = "出生日期")
    private Date birthDate;

    /**
     * 状态: 0-在职, 1-离职
     */
    @Schema(description = "状态: 0-在职, 1-离职")
    private StatusEnum status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 创建者id
     */
    @Schema(description = "创建者id")
    private Integer createId;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 更新者id
     */
    @Schema(description = "更新者id")
    private Integer updateId;

    private static final long serialVersionUID = 6029521842420200982L;
}
