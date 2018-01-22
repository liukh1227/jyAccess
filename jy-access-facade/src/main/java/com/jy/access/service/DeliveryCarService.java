package com.jy.access.service;

public interface DeliveryCarService {
	/**
	 * 新增送货车辆信息
	 * 
	 * @author liukh
	 * @date 2017-7-24 下午2:20:50
	 * @param data
	 * @return
	 */
	public String addDeliveryCar(String data);

	/**
	 * 删除送货车辆信息
	 * 
	 * @author liukh
	 * @date 2017-7-24 下午2:21:09
	 * @param deliveryCarId
	 * @return
	 */
	public String deleteDeliveryCar(String deliveryCarId);

}
