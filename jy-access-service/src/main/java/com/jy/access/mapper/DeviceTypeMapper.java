package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DeviceTypeQueryForm;
import com.jy.entity.po.DeviceType;
import com.jy.entity.pojo.DeviceTypePojo;

public interface DeviceTypeMapper extends BaseMapper{
	public List<DeviceType> getDeviceTypePageList(DeviceTypeQueryForm form);
	public  List<DeviceType> getAllDeviceTypeList(DeviceTypeQueryForm form);
	public  List<DeviceTypePojo> getAllLeveDeviceTypeList(DeviceTypeQueryForm form);
   
}