package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DtKeyAttributeSpecificationQueryForm;
import com.jy.entity.po.DtKeyAttributespecification;

public interface DtKeyAttributespecificationMapper extends BaseMapper{
	public List<DtKeyAttributespecification> getDtKeyAttributespecificationPageList(DtKeyAttributeSpecificationQueryForm form);
	public List<DtKeyAttributespecification> getAllDtKeyAttributespecificationList(DtKeyAttributeSpecificationQueryForm form);

}