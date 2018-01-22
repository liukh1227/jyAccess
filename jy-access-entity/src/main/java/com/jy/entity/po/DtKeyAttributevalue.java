package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DtKeyAttributevalue implements Serializable {
    private String dTKeyAttributeValue_Id;

    private String dTKeyAttributeSpecId;

    private String deviceTypeId;

    private String attributeValue;

    private String status;

    private Integer orderSeq;

    private String creator;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getdTKeyAttributeValue_Id() {
        return dTKeyAttributeValue_Id;
    }

    public void setdTKeyAttributeValue_Id(String dTKeyAttributeValue_Id) {
        this.dTKeyAttributeValue_Id = dTKeyAttributeValue_Id == null ? null : dTKeyAttributeValue_Id.trim();
    }

 
	public String getdTKeyAttributeSpecId() {
		return dTKeyAttributeSpecId;
	}

	public void setdTKeyAttributeSpecId(String dTKeyAttributeSpecId) {
		this.dTKeyAttributeSpecId = dTKeyAttributeSpecId;
	}

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue == null ? null : attributeValue.trim();
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
        sb.append(", dTKeyAttributeValue_Id=").append(dTKeyAttributeValue_Id);
        sb.append(", dTKeyAttributeSpecId=").append(dTKeyAttributeSpecId);
        sb.append(", deviceTypeId=").append(deviceTypeId);
        sb.append(", attributeValue=").append(attributeValue);
        sb.append(", status=").append(status);
        sb.append(", orderSeq=").append(orderSeq);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}