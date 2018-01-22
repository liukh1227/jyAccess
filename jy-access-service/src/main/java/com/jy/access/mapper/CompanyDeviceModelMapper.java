package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.CompanyDeviceModelQueryForm;
import com.jy.entity.vo.CompanyDeviceModelVo;

public interface CompanyDeviceModelMapper extends BaseMapper{
	public List<CompanyDeviceModelVo>getCompanyDeviceModelPageList(CompanyDeviceModelQueryForm form);
	public List<CompanyDeviceModelVo>getAllCompanyDeviceModelList(CompanyDeviceModelQueryForm form);
}