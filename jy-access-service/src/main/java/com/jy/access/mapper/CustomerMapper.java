package com.jy.access.mapper;

import java.util.List;
import java.util.Map;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.bo.CustomerBo;
import com.jy.entity.form.CustomerQueryForm;
import com.jy.entity.vo.CustomerVo;


public interface CustomerMapper extends BaseMapper{
	public List<CustomerVo>getCustomerPageList(CustomerQueryForm form);
	public List<CustomerBo>getCustomerAndBankAccountPageList(CustomerQueryForm form);
	public List<CustomerVo>getAllCustomerlList(CustomerQueryForm form);
	public CustomerBo selectCustomerAndBankAccountById(Map<String,Object> paramsMap);
  
}