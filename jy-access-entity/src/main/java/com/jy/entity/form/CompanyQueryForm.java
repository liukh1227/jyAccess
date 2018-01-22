package com.jy.entity.form;

import java.io.Serializable;

import com.jy.EntityConstant;
import com.jy.base.entity.base.form.PageQueryForm;


public class CompanyQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
    private String parentComanyId;

    private String companyName;

    private String city;

    private String companyBusinessType;

    private String status;
    
    private String companyId;
    
    private String[] companyIdArray; 
    
    private String userAccountId;

	public String getParentComanyId() {
		return parentComanyId;
	}

	public void setParentComanyId(String parentComanyId) {
		this.parentComanyId = parentComanyId;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyBusinessType() {
		return companyBusinessType;
	}

	public void setCompanyBusinessType(String companyBusinessType) {
		this.companyBusinessType = companyBusinessType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}
	
	public boolean isQueryAll() {
		return getCompanyId()!= null && getCompanyId().trim().equals(EntityConstant.QUERY_ALL);

	}
        
}
