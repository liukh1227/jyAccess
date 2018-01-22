package com.jy.access.service;

import com.jy.entity.form.ContractDeviceModelQueryForm;

public interface ContractDeviceModelService {

	/**
	 * 新增签约设备型号信息
	 * @author liukh
	 * @date 2017-8-3 上午11:11:58
	 * @param data
	 * @return
	 */
	public String addContractDeviceModel(String data);
	
	
	/**
	 * 删除签约设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-20 下午3:56:49
	 * @param quodeviceId
	 * @return
	 */
	public String deleteContractDeviceModel(String contractDeviceModelId);
	
	

	/**
	 * 修改签约设备型号信息
	 * @author liukh
	 * @date 2017-8-3 上午11:12:44
	 * @param contractOrderId
	 * @param data
	 * @return
	 */
	public String updateContractDeviceModel(String contractDeviceModelId, String data);

	
	/**
	 * 获取签约设备型号信息详细信息
	 * @author liukh
	 * @date 2017-8-3 上午11:31:26
	 * @param contractDeviceModelId
	 * @return
	 */
	public String getContractDeviceModel(String contractDeviceModelId);
	
	
	/**
	 * 获取所有的签约设备型号信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-20 下午3:55:36
	 * @param form
	 * @return
	 */
	public String getAllContractDeviceModelList(ContractDeviceModelQueryForm form);



}
