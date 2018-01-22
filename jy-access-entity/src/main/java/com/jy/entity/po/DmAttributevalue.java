package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DmAttributevalue implements Serializable {
	
    private String dMAttributeValue_Id;

    private String deviceModelId;

    private String dSpecificationId;

    private String deviceTypeId;

    private String attributeValue;

    private String status;

    private Integer orderSeq;

    private String creator;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getdMAttributeValue_Id() {
        return dMAttributeValue_Id;
    }

    public void setdMAttributeValue_Id(String dMAttributeValue_Id) {
        this.dMAttributeValue_Id = dMAttributeValue_Id == null ? null : dMAttributeValue_Id.trim();
    }

    public String getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(String deviceModelId) {
        this.deviceModelId = deviceModelId == null ? null : deviceModelId.trim();
    }

    public String getdSpecificationId() {
        return dSpecificationId;
    }

    public void setdSpecificationId(String dSpecificationId) {
        this.dSpecificationId = dSpecificationId == null ? null : dSpecificationId.trim();
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId == null ? null : deviceTypeId.trim();
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
        sb.append(", dMAttributeValue_Id=").append(dMAttributeValue_Id);
        sb.append(", deviceModelId=").append(deviceModelId);
        sb.append(", dSpecificationId=").append(dSpecificationId);
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