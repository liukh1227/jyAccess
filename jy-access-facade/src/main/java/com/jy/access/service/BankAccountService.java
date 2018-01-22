package com.jy.access.service;

import com.jy.entity.form.BankAccountQueryForm;
import com.jy.entity.po.BankAccount;

public interface BankAccountService {

	/**
	 * 新增银行账号信息
	 * @author liukh
	 * @date 2017-7-12 上午11:22:22
	 * @param data
	 * @return
	 */
	public String addBankAccount(String data);

	/**
	 * 修改银行账号信息
	 * @author liukh
	 * @date 2017-7-12 上午11:22:22
	 * @param bankAccountId
	 * @param data
	 * @return
	 */
	public String updateBankAccount(String bankAccountId, String data);
	
	/**
	 * 设置其他银行账号为非默认
	 * @author liukh
	 * @date 2017-7-25 下午6:32:50
	 * @param updateDefaultBankAccount
	 * @return
	 */
	public String updateBankAccountIsNotDefalut(BankAccount updateDefaultBankAccount);

	/**
	 * 获取银行账号信息
	 * @author liukh
	 * @date 2017-7-12 上午11:22:22
	 * @param bankAccountId
	 * @return
	 */
	public String getBankAccount(String bankAccountId);

	/**
	 * 获取银行账号信息列表
	 * @author liukh
	 * @date 2017-7-12 上午11:24:08
	 * @param form
	 * @return
	 */
	public String getBankAccountList(BankAccountQueryForm form);

	/**
	 * 获取所有的银行账号信息列表
	 * @author liukh
	 * @date 2017-7-12 上午11:24:25
	 * @param form
	 * @return
	 */
	public String getAllBankAccountList(BankAccountQueryForm form);


}
