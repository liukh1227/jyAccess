package com.jy.entity.vo;

import com.jy.entity.po.ChangeContractDeviceOrder;

public class ChangeContractDeviceOrderVo extends ChangeContractDeviceOrder {

	private static final long serialVersionUID = 1L;
	private String creatorUserName;
	private String typeName;
    private String modelName;
    private String deviceNum;
    private String sequenceNum;
    private String contractOrderNum;
   	public String getContractOrderNum() {
   		return contractOrderNum;
   	}
   	public void setContractOrderNum(String contractOrderNum) {
   		this.contractOrderNum = contractOrderNum;
   	}
	public String getCreatorUserName() {
		return creatorUserName;
	}
	public void setCreatorUserName(String creatorUserName) {
		this.creatorUserName = creatorUserName;
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
    

}
