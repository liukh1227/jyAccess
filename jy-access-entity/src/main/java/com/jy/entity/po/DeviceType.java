package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceType implements Serializable {
    private String deviceType_Id;

    private Boolean isDisplay;

    private Integer sequenceOrder;

    private String typeName;

    private String parentId;

    private String creator;

    private String parid;

    private Date createTime;

    private Boolean isChild;

    private Boolean isHot;

    private static final long serialVersionUID = 1L;

    public String getDeviceType_Id() {
        return deviceType_Id;
    }

    public void setDeviceType_Id(String deviceType_Id) {
        this.deviceType_Id = deviceType_Id == null ? null : deviceType_Id.trim();
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getSequenceOrder() {
        return sequenceOrder;
    }

    public void setSequenceOrder(Integer sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getParid() {
        return parid;
    }

    public void setParid(String parid) {
        this.parid = parid == null ? null : parid.trim();
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss" ) 
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsChild() {
        return isChild;
    }

    public void setIsChild(Boolean isChild) {
        this.isChild = isChild;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceType_Id=").append(deviceType_Id);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", sequenceOrder=").append(sequenceOrder);
        sb.append(", typeName=").append(typeName);
        sb.append(", parentId=").append(parentId);
        sb.append(", creator=").append(creator);
        sb.append(", parid=").append(parid);
        sb.append(", createTime=").append(createTime);
        sb.append(", isChild=").append(isChild);
        sb.append(", isHot=").append(isHot);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}