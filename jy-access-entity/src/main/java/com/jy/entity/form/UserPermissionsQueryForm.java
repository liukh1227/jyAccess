package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;

public class UserPermissionsQueryForm extends PageQueryForm implements Serializable{
	private static final long serialVersionUID = 1L;
     private Integer permissionId;
     private String userAccountId;
	
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public String getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}
     

}
