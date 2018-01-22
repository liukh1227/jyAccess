package com.jy.access.service;

import com.jy.entity.form.PermissionQueryForm;

public interface PermissionService {
	/**
	 * 新增权限
	 * @author liukh
	 * @date 2017-8-30 上午10:09:11
	 * @param data
	 * @return
	 */
	public String addPermission(String data);
	/**
	 * 删除权限
	 * @author liukh
	 * @date 2017-8-30 上午10:12:36
	 * @param permissionId
	 * @return
	 */
	public String deletePermission(String permissionId);
	/**
	 * 修改权限
	 * @author liukh
	 * @date 2017-8-30 上午10:13:21
	 * @param permissionId
	 * @param data
	 * @return
	 */
	public String updatePermission(String permissionId,String data);
	/**
	 * 获取权限信息
	 * @author liukh
	 * @date 2017-8-30 上午10:14:00
	 * @param permissionId
	 * @return
	 */
	public String getPermission(String permissionId);
	/**
	 * 获取分页的权限信息列表
	 * @author liukh
	 * @date 2017-8-30 上午10:15:36
	 * @param form
	 * @return
	 */
	public String getPermissionList(PermissionQueryForm form);

	/**
	 * 获取所有的权限信息列表
	 * @author liukh
	 * @date 2017-8-30 上午10:16:01
	 * @param form
	 * @return
	 */
	public String getAllPermissionList(PermissionQueryForm form);
	/**
	 * 根据用户Id获取关联的所有权限信息列表
	 * @author liukh
	 * @date 2017-8-30 下午9:45:00
	 * @param form
	 * @return
	 */
	public String getAllPermissionListByUserAccountId(PermissionQueryForm form);

}
