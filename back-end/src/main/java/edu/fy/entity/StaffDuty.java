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
 * 人员与职务的关系
 * @TableName staff_duty
 * @author poppy
 */
@TableName(value ="staff_duty")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffDuty implements Serializable {
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
     * 职务id
     */
    private Integer dutyId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
