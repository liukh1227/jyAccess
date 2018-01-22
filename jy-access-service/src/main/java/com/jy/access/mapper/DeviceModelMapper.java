package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DeviceModelQueryForm;
import com.jy.entity.po.DeviceModel;
import com.jy.entity.vo.DeviceModelVo;


public interface DeviceModelMapper extends BaseMapper{
	public List<DeviceModelVo>getDeviceModelPageList(DeviceModelQueryForm form);
	public List<DeviceModel>getAllDeviceModelList(DeviceModelQueryForm form);
   
}