package com.jy.access.mapper;

import java.util.List;
import java.util.Map;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.bo.CompanyBo;
import com.jy.entity.form.CompanyQueryForm;
import com.jy.entity.po.Company;

public interface CompanyMapper extends BaseMapper{
	public List<Company>getCompanyPageList(CompanyQueryForm form);
	public List<CompanyBo>getCompanyAndBankAccountPageList(CompanyQueryForm form);
	public List<CompanyBo>getCompanyAndBankAccountAndCustomerPageList(CompanyQueryForm form);
	public List<Company>getAllCompanyList(CompanyQueryForm form);
	public CompanyBo selectCompanyAndBankAccountById(Map<String,Object> paramsMap);
}