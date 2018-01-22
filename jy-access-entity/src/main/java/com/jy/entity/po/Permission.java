package com.jy.entity.po;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer permission_Id;

    private String permissionName;

    private String resourceUrl;

    private String permissionType;

    private static final long serialVersionUID = 1L;

    public Integer getPermission_Id() {
        return permission_Id;
    }

    public void setPermission_Id(Integer permission_Id) {
        this.permission_Id = permission_Id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType == null ? null : permissionType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", permission_Id=").append(permission_Id);
        sb.append(", permissionName=").append(permissionName);
        sb.append(", resourceUrl=").append(resourceUrl);
        sb.append(", permissionType=").append(permissionType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}