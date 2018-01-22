package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class DtypeSpecificationQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String deviceTypeId;

    private String dSpecificationId;

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getdSpecificationId() {
		return dSpecificationId;
	}

	public void setdSpecificationId(String dSpecificationId) {
		this.dSpecificationId = dSpecificationId;
	}
	  
    
	    
}
