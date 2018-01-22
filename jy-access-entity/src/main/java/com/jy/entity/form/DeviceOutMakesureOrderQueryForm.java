package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class DeviceOutMakesureOrderQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String contractOrderId;
	private String dInMakeSureId;
	private String status;
	
	public String getContractOrderId() {
		return contractOrderId;
	}
	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}
	
	public String getdInMakeSureId() {
		return dInMakeSureId;
	}
	public void setdInMakeSureId(String dInMakeSureId) {
		this.dInMakeSureId = dInMakeSureId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	  
	    
}
