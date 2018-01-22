package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class StatementAccount implements Serializable {
    private String staAccount_Id;

    private String billId;

    private String staAccountName;

    private BigDecimal staAmount;

    private BigDecimal staReceivedAmount;

    private String lessorId;

    private String customerId;

    private String creator;

    private Date createTime;

    private Date staStartTime;

    private Date staEndTime;

    private String salesMan;

    private String checker;

    private String status;

    private String remark;

    private String statementType;

    private static final long serialVersionUID = 1L;

    public String getStaAccount_Id() {
        return staAccount_Id;
    }

    public void setStaAccount_Id(String staAccount_Id) {
        this.staAccount_Id = staAccount_Id == null ? null : staAccount_Id.trim();
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId == null ? null : billId.trim();
    }

    public String getStaAccountName() {
        return staAccountName;
    }

    public void setStaAccountName(String staAccountName) {
        this.staAccountName = staAccountName == null ? null : staAccountName.trim();
    }

    public BigDecimal getStaAmount() {
        return staAmount;
    }

    public void setStaAmount(BigDecimal staAmount) {
        this.staAmount = staAmount;
    }

    public BigDecimal getStaReceivedAmount() {
        return staReceivedAmount;
    }

    public void setStaReceivedAmount(BigDecimal staReceivedAmount) {
        this.staReceivedAmount = staReceivedAmount;
    }

    public String getLessorId() {
        return lessorId;
    }

    public void setLessorId(String lessorId) {
        this.lessorId = lessorId == null ? null : lessorId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
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
    @JSONField(format="yyyy-MM-dd" ) 
    public Date getStaStartTime() {
        return staStartTime;
    }

    public void setStaStartTime(Date staStartTime) {
        this.staStartTime = staStartTime;
    }
    @JSONField(format="yyyy-MM-dd" ) 
    public Date getStaEndTime() {
        return staEndTime;
    }

    public void setStaEndTime(Date staEndTime) {
        this.staEndTime = staEndTime;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan == null ? null : salesMan.trim();
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType == null ? null : statementType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", staAccount_Id=").append(staAccount_Id);
        sb.append(", billId=").append(billId);
        sb.append(", staAccountName=").append(staAccountName);
        sb.append(", staAmount=").append(staAmount);
        sb.append(", staReceivedAmount=").append(staReceivedAmount);
        sb.append(", lessorId=").append(lessorId);
        sb.append(", customerId=").append(customerId);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", staStartTime=").append(staStartTime);
        sb.append(", staEndTime=").append(staEndTime);
        sb.append(", salesMan=").append(salesMan);
        sb.append(", checker=").append(checker);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", statementType=").append(statementType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}