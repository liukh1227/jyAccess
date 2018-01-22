package com.jy.entity.vo;

import com.jy.entity.po.DeviceModel;

public class DeviceModelVo extends DeviceModel {
	
	private static final long serialVersionUID = 1L;
	private String typeName;
	private String brandName;
	private String dTKeyAttributeSpecName;
	private String unit;
	private String attributeValue;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getdTKeyAttributeSpecName() {
		return dTKeyAttributeSpecName;
	}
	public void setdTKeyAttributeSpecName(String dTKeyAttributeSpecName) {
		this.dTKeyAttributeSpecName = dTKeyAttributeSpecName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	@Override
	public String toString() {
		return "DeviceModelVo [typeName=" + typeName + ", brandName="
				+ brandName + ", dTKeyAttributeSpecName="
				+ dTKeyAttributeSpecName + ", unit=" + unit
				+ ", attributeValue=" + attributeValue + "]";
	}
	

}
