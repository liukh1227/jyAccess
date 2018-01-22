package com.jy.entity.vo;

import com.jy.entity.po.RepairsOrder;

public class RepairsOrderVo extends RepairsOrder {

	private static final long serialVersionUID = 1L;
	private String typeName;
    private String modelName;
    private String deviceNum;
    private String sequenceNum;
    private String replaceDeviceTypeName;
    private String replaceDeviceModelName;
    private String replaceDeviceNum;
    private String replaceDeviceSequenceNum;
    private String salesManUserName;
    private String creatorUserName;
    private String contractOrderNum;
   	public String getContractOrderNum() {
   		return contractOrderNum;
   	}
   	public void setContractOrderNum(String contractOrderNum) {
   		this.contractOrderNum = contractOrderNum;
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
	public String getReplaceDeviceTypeName() {
		return replaceDeviceTypeName;
	}
	public void setReplaceDeviceTypeName(String replaceDeviceTypeName) {
		this.replaceDeviceTypeName = replaceDeviceTypeName;
	}
	public String getReplaceDeviceModelName() {
		return replaceDeviceModelName;
	}
	public void setReplaceDeviceModelName(String replaceDeviceModelName) {
		this.replaceDeviceModelName = replaceDeviceModelName;
	}
	public String getReplaceDeviceNum() {
		return replaceDeviceNum;
	}
	public void setReplaceDeviceNum(String replaceDeviceNum) {
		this.replaceDeviceNum = replaceDeviceNum;
	}
	public String getReplaceDeviceSequenceNum() {
		return replaceDeviceSequenceNum;
	}
	public void setReplaceDeviceSequenceNum(String replaceDeviceSequenceNum) {
		this.replaceDeviceSequenceNum = replaceDeviceSequenceNum;
	}
	
	public String getSalesManUserName() {
		return salesManUserName;
	}
	public void setSalesManUserName(String salesManUserName) {
		this.salesManUserName = salesManUserName;
	}
	public String getCreatorUserName() {
		return creatorUserName;
	}
	public void setCreatorUserName(String creatorUserName) {
		this.creatorUserName = creatorUserName;
	}
	@Override
	public String toString() {
		return "RepairsOrderVo [typeName=" + typeName + ", modelName="
				+ modelName + ", deviceNum=" + deviceNum + ", sequenceNum="
				+ sequenceNum + ", replaceDeviceTypeName="
				+ replaceDeviceTypeName + ", replaceDeviceModelName="
				+ replaceDeviceModelName + ", replaceDeviceNum="
				+ replaceDeviceNum + ", replaceDeviceSequenceNum="
				+ replaceDeviceSequenceNum + ", salesManUserName="
				+ salesManUserName + ", creatorUserName=" + creatorUserName
				+ "]";
	}
	
    
   
}
