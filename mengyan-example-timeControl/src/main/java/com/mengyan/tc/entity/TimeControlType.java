package com.mengyan.tc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 每段时间的用途类型
 */
@Entity
@Table(name = "time_control_type")
public class TimeControlType extends BaseEntity {
    @Id
    private Integer typeId;
    // 类型名称
    private String typeName;
    // 大类id
    private Integer typeParentId;
    // 代表颜色
    private Integer typeColor;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeParentId() {
        return typeParentId;
    }

    public void setTypeParentId(Integer typeParentId) {
        this.typeParentId = typeParentId;
    }

    public Integer getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(Integer typeColor) {
        this.typeColor = typeColor;
    }
}
