package com.mengyan.tc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "day_time_statistics")
public class DayTimeStatistics extends BaseEntity {
    @Id
    @Column(name = "dts_id")
    private String dtsId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "dts_day")
    private Date dtsDay;
    @Column(name = "dts_time_start")
    private Integer dtsTimeStart;
    @Column(name = "dts_time_end")
    private Integer dtsTimeEnd;
    @Column(name = "dts_time_unit")
    private Integer dtsTimeUnit;
    @Column(name = "dts_type")
    private Integer dtsType;
    // 备注
    @Column(name = "dts_remarks")
    private String dtsRemarks;

    public String getDtsId() {
        return dtsId;
    }

    public void setDtsId(String dtsId) {
        this.dtsId = dtsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDtsDay() {
        return dtsDay;
    }

    public void setDtsDay(Date dtsDay) {
        this.dtsDay = dtsDay;
    }

    public Integer getDtsTimeStart() {
        return dtsTimeStart;
    }

    public void setDtsTimeStart(Integer dtsTimeStart) {
        this.dtsTimeStart = dtsTimeStart;
    }

    public Integer getDtsTimeEnd() {
        return dtsTimeEnd;
    }

    public void setDtsTimeEnd(Integer dtsTimeEnd) {
        this.dtsTimeEnd = dtsTimeEnd;
    }

    public Integer getDtsTimeUnit() {
        return dtsTimeUnit;
    }

    public void setDtsTimeUnit(Integer dtsTimeUnit) {
        this.dtsTimeUnit = dtsTimeUnit;
    }

    public Integer getDtsType() {
        return dtsType;
    }

    public void setDtsType(Integer dtsType) {
        this.dtsType = dtsType;
    }

    public String getDtsRemarks() {
        return dtsRemarks;
    }

    public void setDtsRemarks(String dtsRemarks) {
        this.dtsRemarks = dtsRemarks;
    }

}
