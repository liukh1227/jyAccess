package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.QuotationDeviceQueryForm;
import com.jy.entity.vo.QuotationDeviceVo;

public interface QuotationDeviceMapper extends BaseMapper {
	public List<QuotationDeviceVo>getAllQuotationDeviceList(QuotationDeviceQueryForm form);
	public List<QuotationDeviceVo>getQuotationDevicePageList(QuotationDeviceQueryForm form);
	
}