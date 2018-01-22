package com.jy.access.service;

import com.jy.entity.form.RepairsOrderQueryForm;

public interface RepairsOrderService {
	/**
	 * 新增报修单信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:02
	 * @param data
	 * @return
	 */
	public String addRepairsOrder(String data);


	/**
	 * 修改报修单信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:29
	 * @param repairsOrderId
	 * @param data
	 * @return
	 */
	public String updateRepairsOrder(String repairsOrderId, String data);
	
	/**
	 * 删除报修单
	 * @author liukh
	 * @date 2017-8-1 下午6:51:49
	 * @param repairsOrderId
	 * @return
	 */
	public String deleteRepairsOrder(String repairsOrderId);

	/**
	 * 获取报修单详细信息
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:47:42
	 * @param repairsOrderId
	 * @return
	 */
	public String getRepairsOrder(String repairsOrderId);

	/**
	 * 获取分页的报修单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getRepairsOrderList(
			RepairsOrderQueryForm form);

	/**
	 * 获取所有的报修单列表
	 * 
	 * @author liukh
	 * @date 2017-8-1 上午11:48:29
	 * @param form
	 * @return
	 */
	public String getAllRepairsOrderList(
			RepairsOrderQueryForm form);
}
