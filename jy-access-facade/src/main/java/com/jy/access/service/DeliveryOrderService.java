package com.jy.access.service;

import com.jy.entity.form.DeliveryOrderQueryForm;

public interface DeliveryOrderService {
	/**
	 * 进出库信息
	 * 
	 * @author liukh
	 * @date 2017-7-12 上午10:12:36
	 * @param data
	 * @return
	 */
	public String addDeliveryOrder(String data);

	/**
	 * 新增进出库信息及送货车辆及设备信息
	 * 
	 * @author liukh
	 * @date 2017-7-24 上午10:58:22
	 * @param data
	 * @return
	 */
	public String addDeliveryOrderAndCarAndDevice(String data);

	/**
	 * 修改进出库信息
	 * 
	 * @author liukh
	 * @date 2017-7-12 上午10:12:36
	 * @paramDeliveryOrderId
	 * @param data
	 * @return
	 */
	public String updateDeliveryOrder(String deliveryOrderId, String data);

	/**
	 * 获取进出库详细信息
	 * 
	 * @author liukh
	 * @date 2017-7-12 上午10:12:36
	 * @paramDeliveryOrderId
	 * @return
	 */
	public String getDeliveryOrder(String deliveryOrderId);

	/**
	 * 获取分页的进出库息列表
	 * 
	 * @author liukh
	 * @date 2017-7-12 上午10:12:36
	 * @param form
	 * @return
	 */
	public String getDeliveryOrderList(DeliveryOrderQueryForm form);

	/**
	 * 获取所有进出库息列表
	 * 
	 * @author liukh
	 * @date 2017-7-12 上午10:12:36
	 * @param form
	 * @return
	 */
	public String getAllDeliveryOrderList(DeliveryOrderQueryForm form);
}
