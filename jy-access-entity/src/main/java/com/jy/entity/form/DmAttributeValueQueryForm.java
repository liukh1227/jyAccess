package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class DmAttributeValueQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String deviceModelId;
	
	
	private String status;

	public String getDeviceModelId() {
		return deviceModelId;
	}

	public void setDeviceModelId(String deviceModelId) {
		this.deviceModelId = deviceModelId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	  
	    
}
