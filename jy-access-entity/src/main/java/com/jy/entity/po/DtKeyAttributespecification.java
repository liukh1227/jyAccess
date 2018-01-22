package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DtKeyAttributespecification implements Serializable {
    private String dTKeyAttributeSpec_Id;

    private String dTKeyAttributeSpecName;

    private String unit;

    private String status;

    private Integer orderSeq;

    private String creator;

    private Date createTime;

    private String deviceTypeId;

    private static final long serialVersionUID = 1L;

    public String getdTKeyAttributeSpec_Id() {
        return dTKeyAttributeSpec_Id;
    }

    public void setdTKeyAttributeSpec_Id(String dTKeyAttributeSpec_Id) {
        this.dTKeyAttributeSpec_Id = dTKeyAttributeSpec_Id == null ? null : dTKeyAttributeSpec_Id.trim();
    }

    public String getdTKeyAttributeSpecName() {
        return dTKeyAttributeSpecName;
    }

    public void setdTKeyAttributeSpecName(String dTKeyAttributeSpecName) {
        this.dTKeyAttributeSpecName = dTKeyAttributeSpecName == null ? null : dTKeyAttributeSpecName.trim();
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

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dTKeyAttributeSpec_Id=").append(dTKeyAttributeSpec_Id);
        sb.append(", dTKeyAttributeSpecName=").append(dTKeyAttributeSpecName);
        sb.append(", unit=").append(unit);
        sb.append(", status=").append(status);
        sb.append(", orderSeq=").append(orderSeq);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", deviceTypeId=").append(deviceTypeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}