package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DeviceBrandQueryForm;
import com.jy.entity.po.DeviceBrand;

public interface DeviceBrandMapper extends BaseMapper{
	 public  List<DeviceBrand> getDeviceBrandPageList(DeviceBrandQueryForm form);
	 public  List<DeviceBrand> getAllDeviceBrandList(DeviceBrandQueryForm form);
}