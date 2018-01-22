package com.jy.entity.form;

import java.io.Serializable;

import com.jy.EntityConstant;
import com.jy.base.entity.base.form.PageQueryForm;


public class CompanyDeviceModelQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String deviceBrandId;
	private String deviceTypeId;
	private String deviceModelId;
	private String companyId;
	private String[] companyIdArray;
	private String status;
	private String modelName;
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
	
	public String[] getCompanyIdArray() {
		if(companyIdArray == null && getCompanyId()!= null){
			String[] strCompanyId = getCompanyId().split(",");
			if(strCompanyId!= null){
				return strCompanyId;
			}
		}
		
		return companyIdArray;
	}
	public void setCompanyIdArray(String[] companyIdArray) {
		this.companyIdArray = companyIdArray;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getDeviceBrandId() {
		return deviceBrandId;
	}
	public void setDeviceBrandId(String deviceBrandId) {
		this.deviceBrandId = deviceBrandId;
	}
	public String getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	
	public boolean isQueryAll() {
		return getCompanyId()!= null && getCompanyId().trim().equals(EntityConstant.QUERY_ALL);

	} 
}
