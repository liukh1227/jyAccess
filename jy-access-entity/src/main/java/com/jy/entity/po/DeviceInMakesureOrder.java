package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;

public class DeviceInMakesureOrder implements Serializable {
    private String dInMakeSure_Id;

    private String contractOrderId;

    private String customerId;

    private String lessorId;

    private String customerName;

    private String leasePhone;

    private String billingType;

    private String leaseTerm;

    private Date enterTime;

    private String workSite;

    private String deviceId;

    private String operators;

    private String creator;

    private Date createTime;

    private String remark;

    private String repairPhone;

    private String status;

    private String businessTye;

    private static final long serialVersionUID = 1L;

    public String getdInMakeSure_Id() {
        return dInMakeSure_Id;
    }

    public void setdInMakeSure_Id(String dInMakeSure_Id) {
        this.dInMakeSure_Id = dInMakeSure_Id == null ? null : dInMakeSure_Id.trim();
    }

    public String getContractOrderId() {
        return contractOrderId;
    }

    public void setContractOrderId(String contractOrderId) {
        this.contractOrderId = contractOrderId == null ? null : contractOrderId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getLessorId() {
        return lessorId;
    }

    public void setLessorId(String lessorId) {
        this.lessorId = lessorId == null ? null : lessorId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getLeasePhone() {
        return leasePhone;
    }

    public void setLeasePhone(String leasePhone) {
        this.leasePhone = leasePhone == null ? null : leasePhone.trim();
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
    @JSONField(format="yyyy-MM-dd" ) 
    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getWorkSite() {
        return workSite;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite == null ? null : workSite.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators == null ? null : operators.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRepairPhone() {
        return repairPhone;
    }

    public void setRepairPhone(String repairPhone) {
        this.repairPhone = repairPhone == null ? null : repairPhone.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBusinessTye() {
        return businessTye;
    }

    public void setBusinessTye(String businessTye) {
        this.businessTye = businessTye == null ? null : businessTye.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dInMakeSure_Id=").append(dInMakeSure_Id);
        sb.append(", contractOrderId=").append(contractOrderId);
        sb.append(", customerId=").append(customerId);
        sb.append(", lessorId=").append(lessorId);
        sb.append(", customerName=").append(customerName);
        sb.append(", leasePhone=").append(leasePhone);
        sb.append(", billingType=").append(billingType);
        sb.append(", leaseTerm=").append(leaseTerm);
        sb.append(", enterTime=").append(enterTime);
        sb.append(", workSite=").append(workSite);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", operators=").append(operators);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", repairPhone=").append(repairPhone);
        sb.append(", status=").append(status);
        sb.append(", businessTye=").append(businessTye);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    @JSONField(serialize=false) 
    public boolean isMonthBillingType(){
    	return getBillingType().equals(EntityConstant.TM_BILLINGTYPE_MONTH);
    }
    @JSONField(serialize=false) 
    public boolean isWeekBillingType(){
    	return getBillingType().equals(EntityConstant.TM_BILLINGTYPE_WEEK);
    }
    @JSONField(serialize=false) 
    public boolean isDayBillingType(){
    	return getBillingType().equals(EntityConstant.TM_BILLINGTYPE_DAY);
    }
}