package com.jy.access.service;

import java.util.List;

import com.jy.entity.form.CompanyQueryForm;
import com.jy.entity.po.Company;

public interface CompanyService {

	/**
	 * 新增公司信息
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午5:01:50
	 * @param data
	 * @return
	 */
	public String addCompany(String data);

	/**
	 * 修改公司信息
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午5:02:12
	 * @param CompanyId
	 * @param data
	 * @return
	 */
	public String updateCompany(String CompanyId, String data);

	/**
	 * 获取公司详细信息
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午5:02:46
	 * @param CompanyId
	 * @return
	 */
	public String getCompany(String companyId);

	/**
	 * 获取公司及公司关联的银行账户详细信息
	 * 
	 * @author liukh
	 * @date 2017-8-3 下午9:53:28
	 * @param CompanyId
	 * @return
	 */
	public String getCompanyAndBankAccountList(String companyId);

	/**
	 * 获取公司信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午5:03:01
	 * @param form
	 * @return
	 */
	public String getCompanyList(CompanyQueryForm form);

	/**
	 * 获取分页的公司及关联的银行账户信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-4 上午10:36:44
	 * @param form
	 * @return
	 */
	public String getCompanyAndBankAccountPageList(CompanyQueryForm form);

	/**
	 * 获取分页的公司及关联的银行账户信息及关联的客户信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-4 下午6:15:48
	 * @param form
	 * @return
	 */
	public String getCompanyAndBankAccountAndCustomerPageList(
			CompanyQueryForm form);

	/**
	 * 获取所有公司信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午5:03:13
	 * @param form
	 * @return
	 */
	public String getAllCompanyList(CompanyQueryForm form);

	/**
	 * 获取当前公司及下属的公司信息
	 * 
	 * @author liukh
	 * @date 2017-8-14 下午5:57:00
	 * @param companyId
	 * @param cachCompanyList
	 * @return
	 */
	public List<Company> getCurrentAndChildCompanyList(String companyId,
			List<Company> cachCompanyList);
	
	/**
	 * 获取当前公司及下属的公司的详细信息
	 * @author liukh
	 * @date 2017-8-30 下午4:13:38
	 * @param companyId
	 * @param cachCompanyList
	 * @return
	 */
	public List<Company> getCurrentAndChildCompanyDetailList(String companyId,
			List<Company> cachCompanyList);

	/**
	 * 根据父公司Id获取公司信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-14 下午5:56:34
	 * @param companyId
	 * @return
	 */
	public List<Company> getChildListByParentId(String companyId);

	/**
	 * 获取自己公司及下属公司
	 * 
	 * @author liukh
	 * @date 2017-7-24 下午2:46:57
	 * @param form
	 * @return
	 */
	public String getAllCompanyAndSubordinate(String companyId);
	
	/**
	 * 根据自己的账号Id获取自己可以访问公司的详细信息
	 * @author liukh
	 * @date 2017-8-30 下午4:12:24
	 * @param userAccountId
	 * @return
	 */
	public String getAllCanVisitCompanyAndSubordinateDetailByUserAccountId(String userAccountId);
	
	/**
	 * 根据自己的账号Id获取自己可以访问公司的信息
	 * @author liukh
	 * @date 2017-8-28 下午3:19:47
	 * @param userAccountId
	 * @return
	 */
	public String getAllCanVisitCompanyAndSubordinateByUserAccountId(String userAccountId);

}
