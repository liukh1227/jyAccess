package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceSpecification implements Serializable {
    private String dSpecification_Id;

    private String dSpecificationName;

    private String unit;

    private String status;

    private Integer orderSeq;

    private String creator;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getdSpecification_Id() {
        return dSpecification_Id;
    }

    public void setdSpecification_Id(String dSpecification_Id) {
        this.dSpecification_Id = dSpecification_Id == null ? null : dSpecification_Id.trim();
    }

    public String getdSpecificationName() {
        return dSpecificationName;
    }

    public void setdSpecificationName(String dSpecificationName) {
        this.dSpecificationName = dSpecificationName == null ? null : dSpecificationName.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss" ) 
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dSpecification_Id=").append(dSpecification_Id);
        sb.append(", dSpecificationName=").append(dSpecificationName);
        sb.append(", unit=").append(unit);
        sb.append(", status=").append(status);
        sb.append(", orderSeq=").append(orderSeq);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}