package com.jy.entity.vo;

import com.jy.entity.po.ContractDeviceModel;

public class ContractDeviceModelVo extends ContractDeviceModel {
	private static final long serialVersionUID = 1L;
	private String modelName;
	private String typeName;
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
	@Override
	public String toString() {
		return "ContractDeviceModelVo [modelName=" + modelName + ", typeName="
				+ typeName + "]";
	}
	

}
