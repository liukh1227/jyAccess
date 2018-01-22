package com.jy.entity.vo;

import com.jy.entity.po.QuotationDevice;

public class QuotationDeviceVo extends QuotationDevice {

	private static final long serialVersionUID = 1L;
    private String modelName;
    private String typeName;
    private String attributeValue;
    private String dTKeyAttributeSpecName;
    private String unit;
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
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
	@Override
	public String toString() {
		return "QuotationDeviceVo [modelName=" + modelName + ", typeName="
				+ typeName + ", attributeValue=" + attributeValue
				+ ", dTKeyAttributeSpecName=" + dTKeyAttributeSpecName
				+ ", unit=" + unit + "]";
	}
    
}
