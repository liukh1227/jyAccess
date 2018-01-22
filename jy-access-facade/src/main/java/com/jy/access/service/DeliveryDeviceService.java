package com.jy.access.service;

public interface DeliveryDeviceService {
	/**
	 * 新增进出库设备信息
	 * 
	 * @author liukh
	 * @date 2017-7-24 下午2:20:50
	 * @param data
	 * @return
	 */
	public String addDeliveryDevice(String data);

	/**
	 * 删除进出库设备信息
	 * 
	 * @author liukh
	 * @date 2017-7-24 下午2:21:09
	 * @param DeliveryDeviceId
	 * @return
	 */
	public String deleteDeliveryDevice(String deliveryDeviceId);
}
