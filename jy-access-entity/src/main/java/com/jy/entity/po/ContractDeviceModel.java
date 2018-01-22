package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;
import com.jy.entity.vo.QuotationDeviceVo;

public class ContractDeviceModel implements Serializable {
    private String contractDeviceModel_Id;

    private String contractOrderId;

    private String deviceModelId;

    private Integer quantity;

    private String billingType;

    private String leaseTerm;

    private Date planStartTime;

    private Date plantEndTime;

    private BigDecimal unitPrice;

    private BigDecimal deviceTotalPrice;

    private String changeTye;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getContractDeviceModel_Id() {
        return contractDeviceModel_Id;
    }

    public void setContractDeviceModel_Id(String contractDeviceModel_Id) {
        this.contractDeviceModel_Id = contractDeviceModel_Id == null ? null : contractDeviceModel_Id.trim();
    }

    public String getContractOrderId() {
        return contractOrderId;
    }

    public void setContractOrderId(String contractOrderId) {
        this.contractOrderId = contractOrderId == null ? null : contractOrderId.trim();
    }

    public String getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(String deviceModelId) {
        this.deviceModelId = deviceModelId == null ? null : deviceModelId.trim();
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
    @JSONField(format="yyyy-MM-dd" ) 
    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }
    @JSONField(format="yyyy-MM-dd" ) 
    public Date getPlantEndTime() {
        return plantEndTime;
    }

    public void setPlantEndTime(Date plantEndTime) {
        this.plantEndTime = plantEndTime;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getDeviceTotalPrice() {
        return deviceTotalPrice;
    }

    public void setDeviceTotalPrice(BigDecimal deviceTotalPrice) {
        this.deviceTotalPrice = deviceTotalPrice;
    }

    public String getChangeTye() {
        return changeTye;
    }

    public void setChangeTye(String changeTye) {
        this.changeTye = changeTye == null ? null : changeTye.trim();
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
        sb.append(", contractDeviceModel_Id=").append(contractDeviceModel_Id);
        sb.append(", contractOrderId=").append(contractOrderId);
        sb.append(", deviceModelId=").append(deviceModelId);
        sb.append(", quantity=").append(quantity);
        sb.append(", billingType=").append(billingType);
        sb.append(", leaseTerm=").append(leaseTerm);
        sb.append(", planStartTime=").append(planStartTime);
        sb.append(", plantEndTime=").append(plantEndTime);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", deviceTotalPrice=").append(deviceTotalPrice);
        sb.append(", changeTye=").append(changeTye);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    @JSONField(serialize=false) 
    public boolean  isRightBillType(){
    	return getBillingType().equals(EntityConstant.TM_BILLINGTYPE_DAY)  || getBillingType().equals(EntityConstant.TM_BILLINGTYPE_WEEK)  || getBillingType().equals(EntityConstant.TM_PAYMETHOD_MONTH);
    	
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
    
    @JSONField(serialize=false)
    public void copyFromQuotation(QuotationDeviceVo other){
    	setDeviceModelId(other.getDeviceModelId());
    	setQuantity(other.getQuantity());
    	setStatus(EntityConstant.STATUS_VALID);
    	setChangeTye(EntityConstant.TM_CHANGORDER_CHANGETYPE_ZHENGCHANG);
    	
    }
}