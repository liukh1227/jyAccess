package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DtypeSpecificationQueryForm;
import com.jy.entity.po.DtypeSpecification;

public interface DtypeSpecificationMapper extends BaseMapper{
	
	
	public List<DtypeSpecification> getDtypeSpecificationPageList(DtypeSpecificationQueryForm form);
    
}