package com.jy.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;

public class DevieAllotVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String allotType ;
	private String deviceModelId;
    private String companyId;
    private BigDecimal rentPerDay;
    private BigDecimal rentPerWeek;
    private BigDecimal rentPerMonth;
    private Date createTime;
    private String status;
    
	public String getAllotType() {
		return allotType;
	}
	public void setAllotType(String allotType) {
		this.allotType = allotType;
	}
	public String getDeviceModelId() {
		return deviceModelId;
	}
	public void setDeviceModelId(String deviceModelId) {
		this.deviceModelId = deviceModelId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public BigDecimal getRentPerDay() {
		return rentPerDay;
	}
	public void setRentPerDay(BigDecimal rentPerDay) {
		this.rentPerDay = rentPerDay;
	}
	public BigDecimal getRentPerWeek() {
		return rentPerWeek;
	}
	public void setRentPerWeek(BigDecimal rentPerWeek) {
		this.rentPerWeek = rentPerWeek;
	}
	public BigDecimal getRentPerMonth() {
		return rentPerMonth;
	}
	public void setRentPerMonth(BigDecimal rentPerMonth) {
		this.rentPerMonth = rentPerMonth;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	@JSONField(serialize=false) 
	public boolean isRightAllotType(){
		return (getAllotType().equals(EntityConstant.DEVICE_COMPANYMODEL_ALLOTTYPE_EXIST) || getAllotType().equals(EntityConstant.DEVICE_COMPANYMODEL_ALLOTTYPE_NONEXISTENCE));
	}
	@JSONField(serialize=false) 
	public boolean isExistAllotType(){
		return getAllotType().equals(EntityConstant.DEVICE_COMPANYMODEL_ALLOTTYPE_EXIST);
	}
    
	@JSONField(serialize=false) 
	public boolean isNonexistenceAllotType(){
		return getAllotType().equals(EntityConstant.DEVICE_COMPANYMODEL_ALLOTTYPE_NONEXISTENCE);
	}
	

}
