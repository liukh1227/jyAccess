package com.jy.access.service;

import com.jy.entity.form.StopOrderQueryForm;

public interface StopOrderService {
	/**
	 * 新增报停单信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:02
	 * @param data
	 * @return
	 */
	public String addStopOrder(String data);


	/**
	 * 修改报停单信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:29
	 * @param stopOrderId
	 * @param data
	 * @return
	 */
	public String updateStopOrder(String stopOrderId, String data);
	
	/**
	 * 删除报停单
	 * @author liukh
	 * @date 2017-8-1 下午6:51:49
	 * @param stopOrderId
	 * @return
	 */
	public String deleteStopOrder(String stopOrderId);

	/**
	 * 获取报停单详细信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:42
	 * @param stopOrderId
	 * @return
	 */
	public String getStopOrder(String stopOrderId);

	/**
	 * 获取分页的报停单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getStopOrderList(StopOrderQueryForm form);

	/**
	 * 获取所有的报停单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getAllStopOrderList(StopOrderQueryForm form);
}
