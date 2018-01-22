package com.jy.entity.po;

import java.io.Serializable;

public class UserRoles implements Serializable {
	private String userRoles_Id;
	
    private String userAccountId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
    
    

    public String getUserRoles_Id() {
		return userRoles_Id;
	}

	public void setUserRoles_Id(String userRoles_Id) {
		this.userRoles_Id = userRoles_Id;
	}

	public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId == null ? null : userAccountId.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userRoles_Id=").append(userRoles_Id);
        sb.append(", userAccountId=").append(userAccountId);
        sb.append(", roleId=").append(roleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}