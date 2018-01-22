package com.jy.access.service;

import com.jy.entity.form.CustomerQueryForm;

public interface CustomerService {
/**
 * 新增客户信息
 * @author liukh
 * @date 2017-7-12 上午10:12:36
 * @param data
 * @return
 */
	public String addCustomer(String data);

	/**
	 * 修改客户信息
	 * 
	 * @author liukh
	 * @date 2017-7-12 上午10:12:36
	 * @param customerId
	 * @param data
	 * @return
	 */
	public String updateCustomer(String customerId, String data);

	/**
	 * 获取客户详细信息
	 * 
	 * @author liukh
	 * @date 2017-7-12 上午10:12:36
	 * @param customerId
	 * @return
	 */
	public String getCustomer(String customerId);
	
	/**
	 * 获取客户及客户关联的银行账户信息
	 * @author liukh
	 * @date 2017-8-3 下午9:54:23
	 * @param customerId
	 * @return
	 */
	public String getCustomerAndBankAccountList(String customerId);

	/**
	 * 获取客户信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-12 上午10:12:36
	 * @param form
	 * @return
	 */
	public String getCustomerList(CustomerQueryForm form);
	/**
	 * 获取分页的客户及关联的银行账户信息列表
	 * @author liukh
	 * @date 2017-8-4 上午10:35:50
	 * @param form
	 * @return
	 */
	public String getCustomerAndBankAccountPageList(CustomerQueryForm form);

	/**
	 * 获取所有客户信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-12 上午10:12:36
	 * @param form
	 * @return
	 */
	public String getAllCustomerList(CustomerQueryForm form);
	
}
