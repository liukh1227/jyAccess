package com.jy.entity.vo;

import com.jy.entity.po.Device;

public class DeviceVo extends Device {

	private static final long serialVersionUID = 1L;
	private String brandName;
	private String typeName;
	private String modelName;
	private String companyName;
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
		return "DeviceVo [brandName=" + brandName + ", typeName=" + typeName
				+ ", modelName=" + modelName + ", companyName=" + companyName
				+ "]";
	}
	
}
