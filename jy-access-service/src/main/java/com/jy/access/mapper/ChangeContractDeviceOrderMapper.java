package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.ChangeContractDeviceOrderQueryForm;
import com.jy.entity.po.ChangeContractDeviceOrder;

public interface ChangeContractDeviceOrderMapper extends BaseMapper{
	public List<ChangeContractDeviceOrder>getChangeContractDeviceOrderPageList(ChangeContractDeviceOrderQueryForm form);
	public List<ChangeContractDeviceOrder>getAllChangeContractDeviceOrderList(ChangeContractDeviceOrderQueryForm form);
	
}