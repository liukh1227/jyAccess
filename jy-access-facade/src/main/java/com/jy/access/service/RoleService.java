package com.jy.access.service;

import com.jy.entity.form.RoleQueryForm;

public interface RoleService {
	/**
	 * 新增角色
	 * 
	 * @author liukh
	 * @date 2017-8-30 上午10:09:11
	 * @param data
	 * @return
	 */
	public String addRole(String data);

	/**
	 * 删除角色
	 * 
	 * @author liukh
	 * @date 2017-8-30 上午10:12:36
	 * @param roleId
	 * @return
	 */
	public String deleteRole(String roleId);

	/**
	 * 修改角色
	 * 
	 * @author liukh
	 * @date 2017-8-30 上午10:13:21
	 * @param RoleId
	 * @param data
	 * @return
	 */
	public String updateRole(String roleId, String data);

	/**
	 * 获取角色信息
	 * 
	 * @author liukh
	 * @date 2017-8-30 上午10:14:00
	 * @param roleId
	 * @return
	 */
	public String getRole(String roleId);

	/**
	 * 获取分页的角色信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-30 上午10:15:36
	 * @param form
	 * @return
	 */
	public String getRoleList(RoleQueryForm form);

	/**
	 * 获取所有的角色信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-30 上午10:16:01
	 * @param form
	 * @return
	 */
	public String getAllRoleList(RoleQueryForm form);

	/**
	 * 获取分页的角色信息详情列表
	 * 
	 * @author liukh
	 * @date 2017-9-5 下午11:04:40
	 * @param form
	 * @return
	 */

	public String getRoleConfigList(RoleQueryForm form);

	/**
	 * 获取所有的角色信息详情列表
	 * 
	 * @author liukh
	 * @date 2017-9-5 下午11:05:02
	 * @param form
	 * @return
	 */
	public String getAllRoleConfigList(RoleQueryForm form);

}
