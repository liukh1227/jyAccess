package com.jy.access.service;

import com.jy.entity.form.DeviceOutMakesureOrderQueryForm;
import com.jy.entity.pojo.DeviceOutMakesureOrder4PdfPojo;

public interface DeviceOutMakesureOrderService {
	/**
	 * 新增退场确认单信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:02
	 * @param data
	 * @return
	 */
	public String addDeviceOutMakesureOrder(String data);
	
	/**
	 * 批量新增退场确认单信
	 * @author liukh
	 * @date 2017-8-1 下午3:27:13
	 * @param data
	 * @return
	 */
	public String addBatchDeviceOutMakesureOrder(String data);
	

	/**
	 * 修改退场确认单信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:29
	 * @param dOutMakeSureId
	 * @param data
	 * @return
	 */
	public String updateDeviceOutMakesureOrder(String dOutMakeSureId, String data);

	/**
	 * 获取退场确认单详细信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:42
	 * @param dOutMakeSureId
	 * @return
	 */
	public String getDeviceOutMakesureOrder(String dOutMakeSureId);
	
	/**
	 * 获取退场确认单详细信息
	 * @author liukh
	 * @date 2017-8-17 上午11:31:29
	 * @param dOutMakeSureId
	 * @return
	 */
	public DeviceOutMakesureOrder4PdfPojo getDOutMakesureOrderDetailById(String dOutMakeSureId);

	/**
	 * 获取分页的退场确认单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getDeviceOutMakesureOrderList(
			DeviceOutMakesureOrderQueryForm form);

	/**
	 * 获取所有的退场确认单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getAllDeviceOutMakesureOrderList(
			DeviceOutMakesureOrderQueryForm form);
}
