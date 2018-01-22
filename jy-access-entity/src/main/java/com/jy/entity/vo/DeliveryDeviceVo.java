package com.jy.entity.vo;

import com.jy.entity.po.DeliveryDevice;

public class DeliveryDeviceVo extends DeliveryDevice {
	
	private static final long serialVersionUID = 1L;
	private String typeName;
	private String modelName;
	private String deviceNum;
    private String sequenceNum;
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
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public String getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	@Override
	public String toString() {
		return "DeliveryDeviceVo [typeName=" + typeName + ", modelName="
				+ modelName + ", deviceNum=" + deviceNum + ", sequenceNum="
				+ sequenceNum + "]";
	} 
    

}
