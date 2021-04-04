package com.mengyan.tc.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 实体类基类
 */
@MappedSuperclass
public class BaseEntity {
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "modification_time")
    private Date modificationTime;
    // 1 为删除
    @Column(name = "is_delete")
    private Integer isDelete;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
