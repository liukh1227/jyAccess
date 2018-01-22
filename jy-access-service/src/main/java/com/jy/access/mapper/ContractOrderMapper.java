package com.jy.access.mapper;

import java.util.List;
import java.util.Map;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.bo.ContractOrderBo;
import com.jy.entity.form.ContractOrderQueryForm;
import com.jy.entity.vo.ContractOrderVo;


public interface ContractOrderMapper extends BaseMapper{
		 
	
	public List<ContractOrderVo>getContractOrderPageList(ContractOrderQueryForm form);
	
	public ContractOrderBo selectContractOrderAndCDModelById(Map<String,Object> paramsMap);
}