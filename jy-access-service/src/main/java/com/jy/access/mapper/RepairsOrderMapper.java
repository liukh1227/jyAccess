package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.RepairsOrderQueryForm;
import com.jy.entity.vo.RepairsOrderVo;

public interface RepairsOrderMapper extends BaseMapper{
	public List<RepairsOrderVo>getRepairsOrderPageList(RepairsOrderQueryForm form);
	public List<RepairsOrderVo>getAllRepairsOrderList(RepairsOrderQueryForm form);
}