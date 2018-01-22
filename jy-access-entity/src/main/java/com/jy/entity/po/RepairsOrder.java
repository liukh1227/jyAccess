package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;

public class RepairsOrder implements Serializable {
    private String repairsOrder_Id;

    private String contractOrderId;

    private String customerId;

    private String customerName;

    private String workSite;

    private String leaserPhone;

    private String remark;

    private String salesMan;

    private String creator;

    private String lessorId;

    private Date createTime;

    private String deviceId;

    private String repairsType;

    private String replaceDeviceId;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getRepairsOrder_Id() {
        return repairsOrder_Id;
    }

    public void setRepairsOrder_Id(String repairsOrder_Id) {
        this.repairsOrder_Id = repairsOrder_Id == null ? null : repairsOrder_Id.trim();
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getWorkSite() {
        return workSite;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite == null ? null : workSite.trim();
    }

    public String getLeaserPhone() {
        return leaserPhone;
    }

    public void setLeaserPhone(String leaserPhone) {
        this.leaserPhone = leaserPhone == null ? null : leaserPhone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan == null ? null : salesMan.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getLessorId() {
        return lessorId;
    }

    public void setLessorId(String lessorId) {
        this.lessorId = lessorId == null ? null : lessorId.trim();
    }
    @JSONField(format="yyyy-MM-dd" ) 
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getRepairsType() {
        return repairsType;
    }

    public void setRepairsType(String repairsType) {
        this.repairsType = repairsType == null ? null : repairsType.trim();
    }

    public String getReplaceDeviceId() {
        return replaceDeviceId;
    }

    public void setReplaceDeviceId(String replaceDeviceId) {
        this.replaceDeviceId = replaceDeviceId == null ? null : replaceDeviceId.trim();
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
        sb.append(", repairsOrder_Id=").append(repairsOrder_Id);
        sb.append(", contractOrderId=").append(contractOrderId);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", workSite=").append(workSite);
        sb.append(", leaserPhone=").append(leaserPhone);
        sb.append(", remark=").append(remark);
        sb.append(", salesMan=").append(salesMan);
        sb.append(", creator=").append(creator);
        sb.append(", lessorId=").append(lessorId);
        sb.append(", createTime=").append(createTime);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", repairsType=").append(repairsType);
        sb.append(", replaceDeviceId=").append(replaceDeviceId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
	@JSONField(serialize=false) 
    public boolean isReplaceDeviceType(){
    	return getRepairsType().equals(EntityConstant.TM_REPARIRSORDER_REPAIRSTYPE_REPLACE);
    }
	@JSONField(serialize=false) 
	public boolean isUndoDeviceType(){
		return getRepairsType().equals(EntityConstant.TM_REPARIRSORDER_REPAIRSTYPE_CREATE);
	}
	@JSONField(serialize=false) 
	public boolean isDoneDeviceType(){
		return getRepairsType().equals(EntityConstant.TM_REPARIRSORDER_REPAIRSTYPE_DONE);
	}
}