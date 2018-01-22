package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DeliveryOrderQueryForm;
import com.jy.entity.vo.DeliveryOrderVo;

public interface DeliveryOrderMapper extends BaseMapper{
	public  List<DeliveryOrderVo> getDeliveryOrderPageList(DeliveryOrderQueryForm form);
	public  List<DeliveryOrderVo> getAllDeliveryOrderList(DeliveryOrderQueryForm form);
  
}