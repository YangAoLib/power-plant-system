package edu.fy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 人员与科室关系
 * @TableName staff_office
 * @author poppy
 */
@TableName(value ="staff_office")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffOffice implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 人员id
     */
    private Integer staffId;

    /**
     * 科室id
     */
    private Integer officeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
