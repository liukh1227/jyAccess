package com.jy.entity.form;

import java.io.Serializable;

import com.jy.EntityConstant;
import com.jy.base.entity.base.form.PageQueryForm;


public class CustomerQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String companyId;
	private String[] companyIdArray; 
	private String status;
	private String customerName;
	
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
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public boolean isQueryAll() {
		return getCompanyId()!= null && getCompanyId().trim().equals(EntityConstant.QUERY_ALL);

	} 
}
