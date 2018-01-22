package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class StopOrderQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String contractOrderId;
	private String status;
	private String stopStatus;
	private String salesMan;
	private String creator;
	private String contractOrderNum;
	private String lessorId;
    private String[] lessorIdArray;
	private String customerName;
	    
	public String getContractOrderId() {
		return contractOrderId;
	}
	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStopStatus() {
		return stopStatus;
	}
	public void setStopStatus(String stopStatus) {
		this.stopStatus = stopStatus;
	}
	public String getSalesMan() {
		return salesMan;
	}
	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getContractOrderNum() {
		return contractOrderNum;
	}
	public void setContractOrderNum(String contractOrderNum) {
		this.contractOrderNum = contractOrderNum;
	}

	public String getLessorId() {
		return lessorId;
	}
	public void setLessorId(String lessorId) {
		this.lessorId = lessorId;
	}
	public String[] getLessorIdArray() {
		if(lessorIdArray == null && getLessorId()!= null){
			String[] strLessorId = getLessorId().split(",");
			if(strLessorId!= null){
				return strLessorId;
			}
		}
		
		return lessorIdArray;
	}
	public void setLessorIdArray(String[] lessorIdArray) {
		this.lessorIdArray = lessorIdArray;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	   
}
