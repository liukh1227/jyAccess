package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DeviceSpecificationQueryForm;
import com.jy.entity.po.DeviceSpecification;

public interface DeviceSpecificationMapper extends BaseMapper {
	
	public List<DeviceSpecification> getDeviceSpecificationPageList(DeviceSpecificationQueryForm form);

	public  List<DeviceSpecification> getAllDeviceSpecificationList(DeviceSpecificationQueryForm form);
	
	public List<DeviceSpecification> getAllDeviceSpecificationListByDeviceTypeId(DeviceSpecificationQueryForm form);
	
}