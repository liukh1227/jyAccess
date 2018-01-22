package com.jy.access.mapper;

import java.util.List;
import java.util.Map;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.bo.QuotationBo;
import com.jy.entity.form.QuotationQueryForm;
import com.jy.entity.vo.QuotationVo;

public interface QuotationMapper extends BaseMapper{
	public List<QuotationVo>getQuotationPageList(QuotationQueryForm form);
	
	public QuotationBo selectQuotationAndDeviceById(Map<String,Object> paramsMap);
  
}