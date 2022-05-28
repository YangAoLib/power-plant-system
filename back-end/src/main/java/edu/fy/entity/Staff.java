package edu.fy.entity;

import com.baomidou.mybatisplus.annotation.*;
import edu.fy.entity.enums.DeletedEnum;
import edu.fy.entity.enums.SexEnum;
import edu.fy.entity.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 人员信息
 * @author YangAo
 * @TableName staff
 */
@TableName(value ="staff")
@Schema(description = "人员信息类")
@Data
public class Staff implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "主键")
    private Integer id;

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
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
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
    @TableField(fill = FieldFill.UPDATE)
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 更新者id
     */
    @Schema(description = "更新者id")
    private Integer updateId;

    /**
     * 逻辑删除位: 0-未删除, 1-已删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "逻辑删除位: 0-未删除, 1-已删除")
    private DeletedEnum isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Staff other = (Staff) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCardId() == null ? other.getCardId() == null : this.getCardId().equals(other.getCardId()))
            && (this.getOriginalHome() == null ? other.getOriginalHome() == null : this.getOriginalHome().equals(other.getOriginalHome()))
            && (this.getBirthDate() == null ? other.getBirthDate() == null : this.getBirthDate().equals(other.getBirthDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateId() == null ? other.getCreateId() == null : this.getCreateId().equals(other.getCreateId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateId() == null ? other.getUpdateId() == null : this.getUpdateId().equals(other.getUpdateId()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getCardId() == null) ? 0 : getCardId().hashCode());
        result = prime * result + ((getOriginalHome() == null) ? 0 : getOriginalHome().hashCode());
        result = prime * result + ((getBirthDate() == null) ? 0 : getBirthDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateId() == null) ? 0 : getCreateId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateId() == null) ? 0 : getUpdateId().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", cardId=").append(cardId);
        sb.append(", originalHome=").append(originalHome);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createId=").append(createId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
