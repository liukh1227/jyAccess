package com.jy.entity.form;

import java.io.Serializable;

import com.jy.EntityConstant;
import com.jy.base.entity.base.form.PageQueryForm;


public class UserAccountQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String companyId;
    private String[] companyIdArray;
	private String status;
	private String sysLevel;
	private String account;
	private String userName;
	private Integer roleId;

	
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
	public String getSysLevel() {
		return sysLevel;
	}
	public void setSysLevel(String sysLevel) {
		this.sysLevel = sysLevel;
	}

	public boolean isQueryAll() {
		return getCompanyId()!= null && getCompanyId().trim().equals(EntityConstant.QUERY_ALL);

	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	} 
	      
}
