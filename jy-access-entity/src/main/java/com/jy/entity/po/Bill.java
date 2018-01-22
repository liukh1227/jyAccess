package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Bill implements Serializable {
    private String bill_Id;

    private String contractOrderId;

    private String lessorId;

    private String customerId;

    private String bankAccountId;

    private String bankName;

    private String bankAccountNum;

    private BigDecimal billAmount;

    private BigDecimal earnestAmount;

    private BigDecimal receivedAmount;

    private BigDecimal backedAmount;

    private String creator;

    private Date createTime;

    private String salesMan;

    private String checker;

    private String status;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getBill_Id() {
        return bill_Id;
    }

    public void setBill_Id(String bill_Id) {
        this.bill_Id = bill_Id == null ? null : bill_Id.trim();
    }

    public String getContractOrderId() {
        return contractOrderId;
    }

    public void setContractOrderId(String contractOrderId) {
        this.contractOrderId = contractOrderId == null ? null : contractOrderId.trim();
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

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId == null ? null : bankAccountId.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum == null ? null : bankAccountNum.trim();
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public BigDecimal getEarnestAmount() {
        return earnestAmount;
    }

    public void setEarnestAmount(BigDecimal earnestAmount) {
        this.earnestAmount = earnestAmount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimal getBackedAmount() {
        return backedAmount;
    }

    public void setBackedAmount(BigDecimal backedAmount) {
        this.backedAmount = backedAmount;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bill_Id=").append(bill_Id);
        sb.append(", contractOrderId=").append(contractOrderId);
        sb.append(", lessorId=").append(lessorId);
        sb.append(", customerId=").append(customerId);
        sb.append(", bankAccountId=").append(bankAccountId);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankAccountNum=").append(bankAccountNum);
        sb.append(", billAmount=").append(billAmount);
        sb.append(", earnestAmount=").append(earnestAmount);
        sb.append(", receivedAmount=").append(receivedAmount);
        sb.append(", backedAmount=").append(backedAmount);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", salesMan=").append(salesMan);
        sb.append(", checker=").append(checker);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}