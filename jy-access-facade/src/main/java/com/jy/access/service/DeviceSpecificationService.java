package com.jy.access.service;

import com.jy.entity.form.DeviceSpecificationQueryForm;

public interface DeviceSpecificationService {
	/**
	 * 新增设备规格
	 * @author liukh
	 * @date 2017-7-10 下午10:22:23
	 * @param data
	 * @return
	 */
	public String addDeviceSpecification(String data);

	/**
	 * 修改设备规格
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午10:22:23
	 * @param dSpecificationId
	 * @param data
	 * @return
	 */
	public String updateDeviceSpecification(String dSpecificationId, String data);

	/**
	 * 获取设备规格
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午10:22:23
	 * @param dSpecificationId
	 * @return
	 */
	public String getDeviceSpecification(String dSpecificationId);

	/**
	 * 获取设备规格信息列表
	 * @author liukh
	 * @date 2017-7-10 下午2:22:17
	 * @param form
	 * @return
	 */
	public String getDeviceSpecificationList(DeviceSpecificationQueryForm form);
	
	/**
	 * 获取所有设备规格信息列表
	 * @author liukh
	 * @date 2017-7-10 下午2:22:00
	 * @param form
	 * @return
	 */
	public String getAllDeviceSpecificationList(DeviceSpecificationQueryForm form);
	/**
	 * 根据设备类型Id获取所有设备规格信息列表
	 * @author liukh
	 * @date 2017-7-10 下午2:22:00
	 * @param form
	 * @return
	 */
	public String getAllDeviceSpecificationListByDeviceTypeId(DeviceSpecificationQueryForm form);

}
