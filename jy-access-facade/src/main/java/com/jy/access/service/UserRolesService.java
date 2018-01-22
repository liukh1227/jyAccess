package com.jy.access.service;

import com.jy.entity.form.UserRolesQueryForm;

public interface UserRolesService {
	/**
	 * 新增用户角色关联
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午5:29:19
	 * @param data
	 * @return
	 */
	public String addUserRoles(String data);

	/**
	 * 删除用户角色关联
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午5:29:33
	 * @param data
	 * @return
	 */
	public String deleteUserRoles(String userRolesId);

	/**
	 * 获取分页的用户角色关联信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-31 下午2:25:00
	 * @param form
	 * @return
	 */
	public String getUserRolesList(UserRolesQueryForm form);

	/**
	 * 获取所有的用户角色关联信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-31 下午2:25:34
	 * @param form
	 * @return
	 */
	public String getAllUserRolesList(UserRolesQueryForm form);
	/**
	 * 获取配置的分页用户角色关联信息列表
	 * @author liukh
	 * @date 2017-9-6 上午9:31:03
	 * @param form
	 * @return
	 */
	
	public String getUserRolesConfigList(UserRolesQueryForm form);

	/**
	 * 获取所有的用户角色关联信息列表
	 * @author liukh
	 * @date 2017-9-6 上午9:31:29
	 * @param form
	 * @return
	 */
	public String getAllUserRolesConfigList(UserRolesQueryForm form);

}
