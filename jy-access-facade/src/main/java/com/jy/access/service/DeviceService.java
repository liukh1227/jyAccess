package com.jy.access.service;

import com.jy.entity.form.DeviceQueryForm;

public interface DeviceService {

	/**
	 * 新增设备信息
	 * @author liukh
	 * @date 2017-7-13下午10:22:23
	 * @param data
	 * @return
	 */
	public String addDevice(String data);

	/**
	 * 修改设备信息
	 * 
	 * @author liukh
	 * @date 2017-7-13下午10:22:23
	 * @param deviceId
	 * @param data
	 * @return
	 */
	public String updateDevice(String deviceId, String data);
	
	/**
	 * 设备分配
	 * @author liukh
	 * @date 2017-8-28 下午5:48:26
	 * @param deviceId
	 * @param data
	 * @return
	 */
	public String updateDevieAllot(String deviceId, String data);

	/**
	 * 获取设备信息
	 * 
	 * @author liukh
	 * @date 2017-7-13下午10:22:23
	 * @param deviceId
	 * @return
	 */
	public String getDevice(String deviceId);

	/**
	 * 获取设备信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-13下午10:22:23
	 * @return
	 */
	public String getDeviceList(DeviceQueryForm form);
	
	/**
	 * 获取所有设备信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-13下午10:22:23
	 * @return
	 */
	public String getAllDeviceList(DeviceQueryForm form);
	/**
	 * 获取设备的统计信息
	 * @author liukh
	 * @date 2017-7-13 上午10:38:28
	 * @param form
	 * @return
	 */
	public String getDeviceStatisticsList(DeviceQueryForm form);
	/**
	 * 获取按公司的设备统计信息
	 * @author liukh
	 * @date 2017-7-13 上午11:39:59
	 * @param form
	 * @return
	 */
	public String getDeviceGroupByCompanyStatisticsList(DeviceQueryForm form);
	
	

}
