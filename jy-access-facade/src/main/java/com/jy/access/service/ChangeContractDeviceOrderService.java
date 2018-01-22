package com.jy.access.service;

import com.jy.entity.form.ChangeContractDeviceOrderQueryForm;

public interface ChangeContractDeviceOrderService {
	/**
	 * 新增延期退场单
	 * 
	 * @author liukh
	 * @date 2017-8-9 上午10:39:32
	 * @param data
	 * @return
	 */
	public String addChangeContractDeviceOrder(String data);

	/**
	 * 修改延期退场单
	 * 
	 * @author liukh
	 * @date 2017-8-9 上午10:58:16
	 * @param changeOrderId
	 * @param data
	 * @return
	 */
	public String updateChangeContractDeviceOrder(String changeOrderId,
			String data);

	/**
	 * 获取延期退场单
	 * 
	 * @author liukh
	 * @date 2017-8-9 上午10:58:51
	 * @param changeOrderId
	 * @return
	 */
	public String getChangeContractDeviceOrder(String changeOrderId);
	

	/**
	 * 删除延期退场单
	 * 
	 * @author liukh
	 * @date 2017-8-9 上午11:00:11
	 * @param changeOrderId
	 * @return
	 */
	public String deleteChangeContractDeviceOrder(String changeOrderId);

	/**
	 * 获取分页的延期退场单信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-9 上午10:59:07
	 * @param form
	 * @return
	 */
	public String getChangeContractDeviceOrderList(
			ChangeContractDeviceOrderQueryForm form);

	/**
	 * 获取所有的延期退场单信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-9 上午10:59:33
	 * @param form
	 * @return
	 */
	public String getAllChangeContractDeviceOrderList(
			ChangeContractDeviceOrderQueryForm form);

}
