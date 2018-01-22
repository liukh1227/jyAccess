package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DistrictQueryForm;
import com.jy.entity.po.District;

public interface DistrictMapper extends BaseMapper{
	public List<District>getDistrictPageList(DistrictQueryForm form);
	public List<District>getAllDistrictList(DistrictQueryForm form);
    
}