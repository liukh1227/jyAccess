package com.jy.entity.vo;

import com.jy.entity.po.RolePermissions;

public class RolePermissionsVo extends RolePermissions {
	
	private static final long serialVersionUID = 1L;
	private String roleName;
    private String permissionName;
    private String resourceUrl;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
    
    
}
