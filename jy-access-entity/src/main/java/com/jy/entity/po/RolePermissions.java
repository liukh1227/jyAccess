package com.jy.entity.po;

import java.io.Serializable;

public class RolePermissions implements Serializable {
	private String rolePermissions_Id;
	
    private Integer roleId;

    private Integer permissionId;

    private static final long serialVersionUID = 1L;
    

    public String getRolePermissions_Id() {
		return rolePermissions_Id;
	}

	public void setRolePermissions_Id(String rolePermissions_Id) {
		this.rolePermissions_Id = rolePermissions_Id;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
        sb.append(", rolePermissions_Id=").append(rolePermissions_Id);
        sb.append(", roleId=").append(roleId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}