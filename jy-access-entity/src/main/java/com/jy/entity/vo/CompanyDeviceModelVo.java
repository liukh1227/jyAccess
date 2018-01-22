package com.jy.entity.vo;

import com.jy.entity.po.CompanyDeviceModel;

public class CompanyDeviceModelVo extends CompanyDeviceModel {

	private static final long serialVersionUID = 1L;
	private String deviceBrandId;
	private String brandName;
	private String deviceTypeId;
	private String typeName;
	private String modelName;
	private String companyName;
	
	public String getDeviceBrandId() {
		return deviceBrandId;
	}
	public void setDeviceBrandId(String deviceBrandId) {
		this.deviceBrandId = deviceBrandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "CompanyDeviceModelVo [deviceBrandId=" + deviceBrandId
				+ ", brandName=" + brandName + ", deviceTypeId=" + deviceTypeId
				+ ", typeName=" + typeName + ", modelName=" + modelName
				+ ", companyName=" + companyName + "]";
	}
	
	
}
