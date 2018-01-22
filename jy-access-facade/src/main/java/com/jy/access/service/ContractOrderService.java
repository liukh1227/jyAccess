package com.jy.access.service;

import com.jy.entity.form.ContractOrderQueryForm;
import com.jy.entity.pojo.ContractOrder4PdfPojo;

public interface ContractOrderService {

	/**
	 * 新增签约单信息
	 * 
	 * @author liukh
	 * @date 2017-7-20 下午3:44:05
	 * @param data
	 * @return
	 */
	public String addContractOrder(String data);

	/**
	 * 新增签约单及签约设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-20 下午3:50:22
	 * @param data
	 * @return
	 */
	public String addContractOrderAndContractDeviceModel(String data);
	
	/**
	 * 从报价单生成签约单及签约设备
	 * @author liukh
	 * @date 2017-8-11 上午10:10:23
	 * @param data
	 * @return
	 */
	public String addContractOrderAndContractDeviceModelFromQuotation(String data);

/**
 * 修改签约单信息	
 * @author liukh
 * @date 2017-8-3 上午11:11:05
 * @param contractOrderId
 * @param data
 * @return
 */
	public String updateContractOrder(String contractOrderId, String data);

	/**
	 * 修改签约单及签约设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-20 下午3:50:58
	 * @param ContractOrderId
	 * @param data
	 * @return
	 */
	public String updateContractOrderAndContractDeviceModel(String contractOrderId, String data);

	/**
	 * 获取签约单信息
	 * 
	 * @author liukh
	 * @date 2017-7-20 下午3:54:12
	 * @param contractOrderId
	 * @return
	 */
	public String getContractOrder(String contractOrderId);
	
	/**
	 * 
	 * @author liukh
	 * @date 2017-8-21 下午6:09:27
	 * @param contractOrderId
	 * @return
	 */
	public ContractOrder4PdfPojo getContractOrderDetailById(String contractOrderId);

	/**
	 * 获取签约单及签约设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-20 下午3:53:10
	 * @param ContractOrderId
	 * @return
	 */
	public String getContractOrderAndContractDeviceModel(String contractOrderId);

	/**
	 * 获取分页的签约单信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-20 下午3:56:01
	 * @param form
	 * @return
	 */
	public String getContractOrderList(ContractOrderQueryForm form);



}
