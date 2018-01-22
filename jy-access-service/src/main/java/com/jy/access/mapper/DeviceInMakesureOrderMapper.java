package com.jy.access.mapper;

import java.util.List;
import java.util.Map;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DeviceInMakesureOrderQueryForm;
import com.jy.entity.pojo.DeviceInMakesureOrder4PdfPojo;
import com.jy.entity.vo.DeviceInMakesureOrderVo;

public interface DeviceInMakesureOrderMapper extends BaseMapper{
	 public DeviceInMakesureOrder4PdfPojo getDInMakesureOrderDetailById(Map<String,Object> params);
	 public  List<DeviceInMakesureOrderVo> getDeviceInMakesureOrderPageList(DeviceInMakesureOrderQueryForm form);
	 public  List<DeviceInMakesureOrderVo> getAllDeviceInMakesureOrderList(DeviceInMakesureOrderQueryForm form);
	 public  List<DeviceInMakesureOrderVo> getDeviceInMakesureOrder4DOutMOrderPageList(DeviceInMakesureOrderQueryForm form);
	 public  List<DeviceInMakesureOrderVo> getAllDeviceInMakesureOrder4DOutMOrderList(DeviceInMakesureOrderQueryForm form);
}