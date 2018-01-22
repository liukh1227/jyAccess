package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceBrand implements Serializable {
    private String deviceBrand_Id;

    private Boolean isDisplay;

    private Integer sequenceOrder;

    private String brandName;

    private String creator;

    private String brandLogo;

    private Date createTime;

    private String letter;

    private Boolean isHot;

    private static final long serialVersionUID = 1L;

    public String getDeviceBrand_Id() {
        return deviceBrand_Id;
    }

    public void setDeviceBrand_Id(String deviceBrand_Id) {
        this.deviceBrand_Id = deviceBrand_Id == null ? null : deviceBrand_Id.trim();
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo == null ? null : brandLogo.trim();
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss" ) 
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter == null ? null : letter.trim();
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
        sb.append(", deviceBrand_Id=").append(deviceBrand_Id);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", sequenceOrder=").append(sequenceOrder);
        sb.append(", brandName=").append(brandName);
        sb.append(", creator=").append(creator);
        sb.append(", brandLogo=").append(brandLogo);
        sb.append(", createTime=").append(createTime);
        sb.append(", letter=").append(letter);
        sb.append(", isHot=").append(isHot);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}