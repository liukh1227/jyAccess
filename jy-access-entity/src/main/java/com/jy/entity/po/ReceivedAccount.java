package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ReceivedAccount implements Serializable {
    private String receedAccount_Id;

    private String receableAccountId;

    private BigDecimal receedAmount;

    private Date operatorTime;

    private String creator;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getReceedAccount_Id() {
        return receedAccount_Id;
    }

    public void setReceedAccount_Id(String receedAccount_Id) {
        this.receedAccount_Id = receedAccount_Id == null ? null : receedAccount_Id.trim();
    }

    public String getReceableAccountId() {
        return receableAccountId;
    }

    public void setReceableAccountId(String receableAccountId) {
        this.receableAccountId = receableAccountId == null ? null : receableAccountId.trim();
    }

    public BigDecimal getReceedAmount() {
        return receedAmount;
    }

    public void setReceedAmount(BigDecimal receedAmount) {
        this.receedAmount = receedAmount;
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss" ) 
    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", receedAccount_Id=").append(receedAccount_Id);
        sb.append(", receableAccountId=").append(receableAccountId);
        sb.append(", receedAmount=").append(receedAmount);
        sb.append(", operatorTime=").append(operatorTime);
        sb.append(", creator=").append(creator);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}