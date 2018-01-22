package com.jy.entity.vo;

import com.jy.entity.po.UserRoles;

public class UserRolesVo extends UserRoles {
	
	private static final long serialVersionUID = 1L;
	private String userName;
    private String roleName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
   
}
