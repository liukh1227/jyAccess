package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DmAttributeValueQueryForm;
import com.jy.entity.po.DmAttributevalue;

public interface DmAttributevalueMapper extends BaseMapper{
	public List<DmAttributevalue>getDmAttributevaluePageList(DmAttributeValueQueryForm form);
	public List<DmAttributevalue>getAllDmAttributevalueList(DmAttributeValueQueryForm form);
}