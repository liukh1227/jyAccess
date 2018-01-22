package com.jy.access.service;

import com.jy.entity.form.DeviceModelQueryForm;


public interface DeviceModelService {

	/**
	 * 新增设备型号信息
	 * @author liukh
	 * @date 2017-7-11 下午10:22:23
	 * @param data
	 * @return
	 */
	public String addDeviceModel(String data);

	/**
	 * 修改设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午10:22:23
	 * @param deviceModelId
	 * @param data
	 * @return
	 */
	public String updateDeviceModel(String deviceModelId, String data);

	/**
	 * 获取设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午10:22:23
	 * @param deviceModelId
	 * @return
	 */
	public String getDeviceModel(String deviceModelId);

	/**
	 * 获取设备型号信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午10:22:23
	 * @return
	 */
	public String getDeviceModelList(DeviceModelQueryForm form);
	
	/**
	 * 获取所有设备型号信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午10:22:23
	 * @return
	 */
	public String getAllDeviceModelList(DeviceModelQueryForm form);
	

	
	

}
