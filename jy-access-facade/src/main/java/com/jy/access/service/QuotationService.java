package com.jy.access.service;

import com.jy.entity.form.QuotationQueryForm;

public interface QuotationService {

	/**
	 * 新增报价单信息
	 * @author liukh
	 * @date 2017-7-13 下午8:52:02
	 * @param data
	 * @return
	 */
	public String addQuotation(String data);
	/**
	 * 新增报价单及报价设备型号信息
	 * @author liukh
	 * @date 2017-7-13 下午8:52:02
	 * @param data
	 * @return
	 */
	public String addQuotationAndQuotationDevice(String data);
	
	/**
	 * 修改报价单信息
	 * @author liukh
	 * @date 2017-8-10 上午11:52:47
	 * @param quotationId
	 * @param data
	 * @return
	 */
	public String updateQuotation(String quotationId, String data);

	/**
	 * 修改报价信息及报价设备信息
	 * 
	 * @author liukh
	 * @date 2017-7-13 下午8:52:02
	 * @param quotationId
	 * @param data
	 * @return
	 */
	public String updateQuotationAndQuotationDevice(String quotationId, String data);
	
	/**
	 * 获取报价详细信息
	 * @author liukh
	 * @date 2017-8-10 上午11:53:51
	 * @param quotationId
	 * @return
	 */
	public String getQuotation(String quotationId);

	/**
	 * 获取报价及报价设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-13下午8:52:02
	 * @param quotationId
	 * @return
	 */
	public String getQuotationAndQuotationDevice(String quotationId);

	/**
	 * 获取报价信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-13下午10:22:23
	 * @return
	 */
	public String getQuotationList(QuotationQueryForm form);
	
	

}
