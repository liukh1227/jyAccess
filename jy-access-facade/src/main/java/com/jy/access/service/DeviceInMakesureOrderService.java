package com.jy.access.service;

import com.jy.entity.form.DeviceInMakesureOrderQueryForm;
import com.jy.entity.pojo.DeviceInMakesureOrder4PdfPojo;

public interface DeviceInMakesureOrderService {
	/**
	 * 新增进场确认单信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:02
	 * @param data
	 * @return
	 */
	public String addDeviceInMakesureOrder(String data);
	/**
	 * 批量新增进场确认单信
	 * @author liukh
	 * @date 2017-8-1 下午3:27:13
	 * @param data
	 * @return
	 */
	public String addBatchDeviceInMakesureOrder(String data);

	/**
	 * 修改进场确认单信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:29
	 * @param dInMakeSureId
	 * @param data
	 * @return
	 */
	public String updateDeviceInMakesureOrder(String dInMakeSureId, String data);

	/**
	 * 获取进场确认单详细信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:42
	 * @param dInMakeSureId
	 * @return
	 */
	public String getDeviceInMakesureOrder(String dInMakeSureId);
	
	/**
	 * 获取进场确认单的详细信息
	 * @author liukh
	 * @date 2017-8-16 下午5:37:26
	 * @param dInMakeSureId
	 * @return
	 */
	public DeviceInMakesureOrder4PdfPojo getDInMakesureOrderDetailById(String dInMakeSureId);

	/**
	 * 获取分页的进场确认单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getDeviceInMakesureOrderList(
			DeviceInMakesureOrderQueryForm form);

	/**
	 * 获取所有的进场确认单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getAllDeviceInMakesureOrderList(
			DeviceInMakesureOrderQueryForm form);
	/**
	 * 获取分页的去除已退场的设备进场确认单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getDeviceInMakesureOrder4DOutMOrderPageList(
			DeviceInMakesureOrderQueryForm form);
	
	/**
	 * 获取所有的去除已退场的设备进场确认单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getAllDeviceInMakesureOrder4DOutMOrderList(
			DeviceInMakesureOrderQueryForm form);
}
