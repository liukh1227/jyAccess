package com.jy.access.service;

import com.jy.entity.form.QuotationDeviceQueryForm;

public interface QuotationDeviceService {
	/**
	 * 新增报价设备
	 * @author liukh
	 * @date 2017-8-10 上午11:46:43
	 * @param data
	 * @return
	 */
	public String addQuotationDevice(String data);
	
	/**
	 * 删除报价设备
	 * @author liukh
	 * @date 2017-7-14 上午10:38:09
	 * @param quodeviceId
	 * @return
	 */
	public String deleteQuotationDevice(String quodeviceId);
	/**
	 * 修改报价设备
	 * @author liukh
	 * @date 2017-8-10 上午11:50:06
	 * @param quodeviceId
	 * @param data
	 * @return
	 */
	
	public String updateQuotationDevice(String quodeviceId,String data);
	
	/**
	 * 获取报价设备详细信息
	 * @author liukh
	 * @date 2017-8-10 上午11:47:34
	 * @param quodeviceId
	 * @return
	 */
	public String getQuotationDevice(String quodeviceId);
	
	
	/**
	 * 获取分页的报价设备列表
	 * @author liukh
	 * @date 2017-8-10 上午11:48:28
	 * @param form
	 * @return
	 */
	public String getQuotationDeviceList(QuotationDeviceQueryForm form);
	
	
	/**
	 * 获取所有的报价设备列表
	 * @author liukh
	 * @date 2017-7-14 上午10:36:45
	 * @param form
	 * @return
	 */
	public String getAllQuotationDeviceList(QuotationDeviceQueryForm form);
	

	
}
