package com.jy.access.service;

public interface UserPermissionsService {
	/**
	 * 新增用户权限关联
	 * @author liukh
	 * @date 2017-8-30 下午5:29:19
	 * @param data
	 * @return
	 */
	public String addUserPermissions(String data);
	/**
	 * 删除用户权限关联
	 * @author liukh
	 * @date 2017-8-30 下午5:29:33
	 * @param data
	 * @return
	 */
	public String deleteUserPermissions(String userPermissionsId);
	

}
