package com.jy.access.mapper;

import java.util.List;
import java.util.Map;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DeviceOutMakesureOrderQueryForm;
import com.jy.entity.pojo.DeviceOutMakesureOrder4PdfPojo;
import com.jy.entity.vo.DeviceOutMakesureOrderVo;

public interface DeviceOutMakesureOrderMapper extends BaseMapper{
	 public DeviceOutMakesureOrder4PdfPojo getDOutMakesureOrderDetailById(Map<String,Object> params);
	 public  List<DeviceOutMakesureOrderVo> getDeviceOutMakesureOrderPageList(DeviceOutMakesureOrderQueryForm form);
	 public  List<DeviceOutMakesureOrderVo> getAllDeviceOutMakesureOrderList(DeviceOutMakesureOrderQueryForm form);
}