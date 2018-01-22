package com.jy.access.service;

import com.jy.entity.form.DeviceTypeQueryForm;


public interface DeviceTypeService {

	/**
	 * 新增设备类型信息
	 * @author liukh
	 * @date 2017-7-6 下午10:22:23
	 * @param data
	 * @return
	 */
	public String addDeviceType(String data);

	/**
	 * 修改设备类型信息
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:22:23
	 * @param deviceTypeId
	 * @param data
	 * @return
	 */
	public String updateDeviceType(String deviceTypeId, String data);

	/**
	 * 获取设备类型信息
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:22:23
	 * @param deviceTypeId
	 * @return
	 */
	public String getDeviceType(String deviceTypeId);

	/**
	 * 获取设备类型信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:22:23
	 * @return
	 */
	public String getDeviceTypeList(DeviceTypeQueryForm form);
	
	/**
	 * 获取所有设备类型信息列表
	 * 
	 * @author liukh
	 + @date 2017-7-6 下午10:22:23
	 * @return
	 */
	public String getAllDeviceTypeList(DeviceTypeQueryForm form);
	
	/**
	 * 获取所有子设备类型和父设备类型统计
	 * 
	 * @author liukh
	 + @date 2017-7-6 下午10:22:23
	 * @return
	 */
	public String getAllLeveDeviceTypeList(DeviceTypeQueryForm form);
	
	
}
