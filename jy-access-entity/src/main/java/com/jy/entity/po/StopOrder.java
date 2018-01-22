package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;

public class StopOrder implements Serializable {
    private String stopOrder_Id;

    private String contractOrderId;

    private String customerId;

    private String customerName;

    private String workSite;

    private String leaserPhone;

    private String remark;

    private String salesMan;

    private String creator;

    private String lessorId;

    private String deviceId;

    private Date createTime;

    private Date stopStartTime;

    private Date stopEndTime;

    private String status;
    
    private String stopStatus;

    private static final long serialVersionUID = 1L;

    public String getStopOrder_Id() {
        return stopOrder_Id;
    }

    public void setStopOrder_Id(String stopOrder_Id) {
        this.stopOrder_Id = stopOrder_Id == null ? null : stopOrder_Id.trim();
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss" ) 
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @JSONField(format="yyyy-MM-dd" ) 
    public Date getStopStartTime() {
        return stopStartTime;
    }

    public void setStopStartTime(Date stopStartTime) {
        this.stopStartTime = stopStartTime;
    }
    @JSONField(format="yyyy-MM-dd" ) 
    public Date getStopEndTime() {
        return stopEndTime;
    }

    public void setStopEndTime(Date stopEndTime) {
        this.stopEndTime = stopEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    public String getStopStatus() {
		return stopStatus;
	}

	public void setStopStatus(String stopStatus) {
		this.stopStatus = stopStatus;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stopOrder_Id=").append(stopOrder_Id);
        sb.append(", contractOrderId=").append(contractOrderId);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", workSite=").append(workSite);
        sb.append(", leaserPhone=").append(leaserPhone);
        sb.append(", remark=").append(remark);
        sb.append(", salesMan=").append(salesMan);
        sb.append(", creator=").append(creator);
        sb.append(", lessorId=").append(lessorId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", createTime=").append(createTime);
        sb.append(", stopStartTime=").append(stopStartTime);
        sb.append(", stopEndTime=").append(stopEndTime);
        sb.append(", status=").append(status);
        sb.append(", stopStatus=").append(stopStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    @JSONField(serialize=false) 
    public boolean isResetStopOrder(){
    	return getStopStatus().equals(EntityConstant.TM_STOPORDER_STOPSTATUS_RESET);
    }
    @JSONField(serialize=false) 
    public boolean isStopStopOrder(){
    	return getStopStatus().equals(EntityConstant.TM_STOPORDER_STOPSTATUS_STOP);
    }
}