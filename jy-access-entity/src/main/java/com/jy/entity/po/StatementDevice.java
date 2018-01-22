package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class StatementDevice implements Serializable {
    private String staDevice_Id;

    private String staAccountId;

    private String deviceId;

    private String billingType;

    private String leaseTerm;

    private BigDecimal unitPrice;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getStaDevice_Id() {
        return staDevice_Id;
    }

    public void setStaDevice_Id(String staDevice_Id) {
        this.staDevice_Id = staDevice_Id == null ? null : staDevice_Id.trim();
    }

    public String getStaAccountId() {
        return staAccountId;
    }

    public void setStaAccountId(String staAccountId) {
        this.staAccountId = staAccountId == null ? null : staAccountId.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType == null ? null : billingType.trim();
    }

    public String getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(String leaseTerm) {
        this.leaseTerm = leaseTerm == null ? null : leaseTerm.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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
        sb.append(", staDevice_Id=").append(staDevice_Id);
        sb.append(", staAccountId=").append(staAccountId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", billingType=").append(billingType);
        sb.append(", leaseTerm=").append(leaseTerm);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}