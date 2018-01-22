package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class DeviceSpecificationQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
   private String dSpecificationName;
   private String deviceTypeId;
	
	 private String status;


	public String getdSpecificationName() {
		return dSpecificationName;
	}

	public void setdSpecificationName(String dSpecificationName) {
		this.dSpecificationName = dSpecificationName;
	}
	
	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	  
}
