package com.jy.entity.po;

import java.io.Serializable;

public class UserPermissions implements Serializable {
	private String userPermissions_Id;
	
    private String userAccountId;

    private Integer permissionId;

    private static final long serialVersionUID = 1L;
    

    public String getUserPermissions_Id() {
		return userPermissions_Id;
	}

	public void setUserPermissions_Id(String userPermissions_Id) {
		this.userPermissions_Id = userPermissions_Id;
	}

	public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId == null ? null : userAccountId.trim();
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userPermissions_Id=").append(userPermissions_Id);
        sb.append(", userAccountId=").append(userAccountId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}