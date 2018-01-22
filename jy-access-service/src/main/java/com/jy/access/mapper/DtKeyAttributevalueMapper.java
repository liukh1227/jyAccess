package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DtKeyAttributeValueQueryForm;
import com.jy.entity.vo.DtKeyAttributevalueVo;

public interface DtKeyAttributevalueMapper extends BaseMapper{
	public List<DtKeyAttributevalueVo> getDtKeyAttributevaluePageList(DtKeyAttributeValueQueryForm form);
	public List<DtKeyAttributevalueVo> getAllDtKeyAttributevalueList(DtKeyAttributeValueQueryForm form);
  
}