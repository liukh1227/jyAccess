package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.StopOrderQueryForm;
import com.jy.entity.vo.StopOrderVo;

public interface StopOrderMapper extends BaseMapper{
	public List<StopOrderVo>getStopOrderPageList(StopOrderQueryForm form);
	public List<StopOrderVo>getAllStopOrderList(StopOrderQueryForm form);
}