package com.jy.access.service;

import com.jy.entity.form.RolePermissionsQueryForm;

public interface RolePermissionsService {
	/**
	 * 新增权限角色关联
	 * @author liukh
	 * @date 2017-8-30 下午5:22:16
	 * @param data
	 * @return
	 */
	public String addRolePermissions(String data);
	/**
	 * 移除权限角色权限管理
	 * @author liukh
	 * @date 2017-8-30 下午5:22:35
	 * @param data
	 * @return
	 */
	public String deleteRolePermissions(String rolePermissionsId);
	
	/**
	 * 获取分页的角色权限关联信息列表
	 * @author liukh
	 * @date 2017-8-31 上午11:08:42
	 * @param form
	 * @return
	 */
	public String getRolePermissionsList(RolePermissionsQueryForm form);

	/**
	 * 获取所有的角色权限关联信息列表
	 * @author liukh
	 * @date 2017-8-31 上午11:09:36
	 * @param form
	 * @return
	 */
	public String getAllRolePermissionsList(RolePermissionsQueryForm form);

}
