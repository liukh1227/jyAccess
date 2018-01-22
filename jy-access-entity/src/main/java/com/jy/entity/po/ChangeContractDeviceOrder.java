package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;

public class ChangeContractDeviceOrder implements Serializable {
	private String changeOrder_Id;

	private String contractDeviceId;

	private String contractOrderId;

	private String dInMakeSureId;

	private Integer quantity;

	private String billingType;

	private String leaseTerm;

	private String factBillingType;

	private String factLeaseTerm;

	private Date planStartTime;

	private Date factStartTime;

	private Date planEndTime;

	private Date factEndTime;

	private String changeType;

	private String status;

	private String creator;

	private String remark;

	private Date createTime;

	private static final long serialVersionUID = 1L;

	public String getChangeOrder_Id() {
		return changeOrder_Id;
	}

	public void setChangeOrder_Id(String changeOrder_Id) {
		this.changeOrder_Id = changeOrder_Id == null ? null : changeOrder_Id
				.trim();
	}

	public String getContractDeviceId() {
		return contractDeviceId;
	}

	public void setContractDeviceId(String contractDeviceId) {
		this.contractDeviceId = contractDeviceId == null ? null
				: contractDeviceId.trim();
	}

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId == null ? null : contractOrderId
				.trim();
	}

	public String getdInMakeSureId() {
		return dInMakeSureId;
	}

	public void setdInMakeSureId(String dInMakeSureId) {
		this.dInMakeSureId = dInMakeSureId == null ? null : dInMakeSureId
				.trim();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public String getFactBillingType() {
		return factBillingType;
	}

	public void setFactBillingType(String factBillingType) {
		this.factBillingType = factBillingType == null ? null : factBillingType
				.trim();
	}

	public String getFactLeaseTerm() {
		return factLeaseTerm;
	}

	public void setFactLeaseTerm(String factLeaseTerm) {
		this.factLeaseTerm = factLeaseTerm == null ? null : factLeaseTerm
				.trim();
	}

	@JSONField(format = "yyyy-MM-dd")
	public Date getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}

	@JSONField(format = "yyyy-MM-dd")
	public Date getFactStartTime() {
		return factStartTime;
	}

	public void setFactStartTime(Date factStartTime) {
		this.factStartTime = factStartTime;
	}

	@JSONField(format = "yyyy-MM-dd")
	public Date getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}

	@JSONField(format = "yyyy-MM-dd")
	public Date getFactEndTime() {
		return factEndTime;
	}

	public void setFactEndTime(Date factEndTime) {
		this.factEndTime = factEndTime;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType == null ? null : changeType.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ChangeContractDeviceOrder [changeOrder_Id=" + changeOrder_Id
				+ ", contractDeviceId=" + contractDeviceId
				+ ", contractOrderId=" + contractOrderId + ", dInMakeSureId="
				+ dInMakeSureId + ", quantity=" + quantity + ", billingType="
				+ billingType + ", leaseTerm=" + leaseTerm
				+ ", factBillingType=" + factBillingType + ", factLeaseTerm="
				+ factLeaseTerm + ", planStartTime=" + planStartTime
				+ ", factStartTime=" + factStartTime + ", planEndTime="
				+ planEndTime + ", factEndTime=" + factEndTime
				+ ", changeType=" + changeType + ", status=" + status
				+ ", creator=" + creator + ", remark=" + remark
				+ ", createTime=" + createTime + "]";
	}

	@JSONField(serialize = false)
	public boolean isRightChangeType() {
		return getChangeType().equals(
				EntityConstant.TM_CHANGORDER_CHANGETYPE_TUICHANG)
				|| getChangeType().equals(
						EntityConstant.TM_CHANGORDER_CHANGETYPE_YANQI);
	}

	@JSONField(serialize = false)
	public boolean isRightBillType() {
		return getBillingType().equals(EntityConstant.TM_BILLINGTYPE_DAY)
				|| getBillingType().equals(EntityConstant.TM_BILLINGTYPE_WEEK)
				|| getBillingType().equals(EntityConstant.TM_PAYMETHOD_MONTH);

	}

	@JSONField(serialize = false)
	public boolean isRightFactBillingType() {
		return getFactBillingType().equals(EntityConstant.TM_BILLINGTYPE_DAY)
				|| getFactBillingType().equals(
						EntityConstant.TM_BILLINGTYPE_WEEK)
				|| getFactBillingType().equals(
						EntityConstant.TM_PAYMETHOD_MONTH);

	}

}