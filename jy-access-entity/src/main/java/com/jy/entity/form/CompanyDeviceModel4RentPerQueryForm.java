package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class CompanyDeviceModel4RentPerQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String deviceModelId;
	private String companyId;
	private String originalCompanyId;
	private String status;
	

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
	
	public String getOriginalCompanyId() {
		return originalCompanyId;
	}
	public void setOriginalCompanyId(String originalCompanyId) {
		this.originalCompanyId = originalCompanyId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
