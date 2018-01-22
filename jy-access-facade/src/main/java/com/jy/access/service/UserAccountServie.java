package com.jy.access.service;

import com.jy.entity.form.UserAccountQueryForm;

public interface UserAccountServie {
	/**
	 * 获取手机验证码，并增加短信历史
	 * @author liukh
	 * @date 2017-7-18 上午10:58:34
	 * @param data
	 * @return
	 */
	public String addMessageHistoryAndGetValidCode(String data);
	
	/**
	 * 检验手机验证码是否正确
	 * @author liukh
	 * @date 2017-7-18 上午11:01:31
	 * @param cellPhone
	 * @param validCode
	 * @return
	 */
	public String checkValidCode(String cellPhone,String validCode);
	/**
	 * 验证账户是否已经存在
	 * @author liukh
	 * @date 2017-7-18 上午11:03:22
	 * @param account
	 * @return
	 */
	public String isValidUserAccount(String account);
	
	/**
	 * 新增用户账号
	 * @author liukh
	 * @date 2017-7-18 上午11:04:21
	 * @param data
	 * @return
	 */
	public String addUserAccount(String data);
	/**
	 * 根据用户Id获取用户账号的信息
	 * @author liukh
	 * @date 2017-7-18 上午11:13:34
	 * @param userAccountId
	 * @return
	 */
	public String getUserAccountById(String userAccountId);
	/**
	 * 根据用户Id或账号获取用户账号的信息
	 * @author liukh
	 * @date 2017-7-18 上午11:14:24
	 * @param data
	 * @return
	 */
	public String getUserAccountByIdOrAccount(String data);
	/**
	 * 获取用户账号的详细信息
	 * @author liukh
	 * @date 2017-7-18 上午11:14:53
	 * @param data
	 * @return
	 */
	public String getUserAccountDetailInfor(String data);
	
	/**
	 * 更新用户信息
	 * @author liukh
	 * @date 2017-7-18 上午11:24:13
	 * @param userAccountId
	 * @param data
	 * @return
	 */
	public String updateUserAccount(String userAccountId, String data);
	
	/**
	 * 修改用户密码
	 * @author liukh
	 * @date 2017-7-18 上午11:25:10
	 * @param userAccountId
	 * @param data
	 * @return
	 */
	public String updatePassword(String userAccountId,String data);
	/**
	 * 重置用户密码
	 * @author liukh
	 * @date 2017-7-18 上午11:25:36
	 * @param data
	 * @return
	 */
	public String updatePasswordReset(String data);
	
	/**
	 * 获取分页的用户账号信息列表
	 * @author liukh
	 * @date 2017-7-18 上午11:17:31
	 * @param form
	 * @return
	 */
	public String getUserAccountList(UserAccountQueryForm form);
	/**
	 * 获取所有的用户账号信息列表
	 * @author liukh
	 * @date 2017-7-18 上午11:18:14
	 * @param form
	 * @return
	 */
	public String getAllUserAccountList(UserAccountQueryForm form);
	
	/**
	 * 获取所有的没有配置用户角色的用户信息列表
	 * @author liukh
	 * @date 2017-9-6 下午4:59:16
	 * @param form
	 * @return
	 */
	public String getAllNoConfigRoleUserAccountList(UserAccountQueryForm form);
	

}
