package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ReceivedAccountAdjust implements Serializable {
    private String receedAdjust_Id;

    private String receedAccountId;

    private BigDecimal originalAmount;

    private BigDecimal adjustAmount;

    private String creator;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getReceedAdjust_Id() {
        return receedAdjust_Id;
    }

    public void setReceedAdjust_Id(String receedAdjust_Id) {
        this.receedAdjust_Id = receedAdjust_Id == null ? null : receedAdjust_Id.trim();
    }

    public String getReceedAccountId() {
        return receedAccountId;
    }

    public void setReceedAccountId(String receedAccountId) {
        this.receedAccountId = receedAccountId == null ? null : receedAccountId.trim();
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
        sb.append(", receedAdjust_Id=").append(receedAdjust_Id);
        sb.append(", receedAccountId=").append(receedAccountId);
        sb.append(", originalAmount=").append(originalAmount);
        sb.append(", adjustAmount=").append(adjustAmount);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}