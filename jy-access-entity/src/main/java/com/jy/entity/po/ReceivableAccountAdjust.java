package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ReceivableAccountAdjust implements Serializable {
    private String receableAdjust_Id;

    private String receableAccountId;

    private BigDecimal originalAmount;

    private BigDecimal adjustAmount;

    private String adjustRemark;

    private String creator;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getReceableAdjust_Id() {
        return receableAdjust_Id;
    }

    public void setReceableAdjust_Id(String receableAdjust_Id) {
        this.receableAdjust_Id = receableAdjust_Id == null ? null : receableAdjust_Id.trim();
    }

    public String getReceableAccountId() {
        return receableAccountId;
    }

    public void setReceableAccountId(String receableAccountId) {
        this.receableAccountId = receableAccountId == null ? null : receableAccountId.trim();
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public BigDecimal getAdjustAmount() {
        return adjustAmount;
    }

    public void setAdjustAmount(BigDecimal adjustAmount) {
        this.adjustAmount = adjustAmount;
    }

    public String getAdjustRemark() {
        return adjustRemark;
    }

    public void setAdjustRemark(String adjustRemark) {
        this.adjustRemark = adjustRemark == null ? null : adjustRemark.trim();
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
        sb.append(", receableAdjust_Id=").append(receableAdjust_Id);
        sb.append(", receableAccountId=").append(receableAccountId);
        sb.append(", originalAmount=").append(originalAmount);
        sb.append(", adjustAmount=").append(adjustAmount);
        sb.append(", adjustRemark=").append(adjustRemark);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}