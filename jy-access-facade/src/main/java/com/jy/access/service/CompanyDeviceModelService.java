package com.jy.access.service;

import com.jy.entity.form.CompanyDeviceModelQueryForm;


public interface CompanyDeviceModelService {

	/**
	 * 新增公司设备型号信息
	 * @author liukh
	 * @date 2017-7-12 下午15:22:23
	 * @param data
	 * @return
	 */
	public String addCompanyDeviceModel(String data);

	/**
	 * 修改公司设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-12 下午15:22:23
	 * @param companyDeviceModelId
	 * @param data
	 * @return
	 */
	public String updateCompanyDeviceModel(String companyDeviceModelId, String data);

	/**
	 * 获取公司设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-12 下午15:22:23
	 * @param companyDeviceModelId
	 * @return
	 */
	public String getCompanyDeviceModel(String companyDeviceModelId);
	
	/**
	 * 根据公司Id和设备型号Id获取公司设备型号信息
	 * @author liukh
	 * @date 2017-8-28 下午4:02:11
	 * @param data
	 * @return
	 */
	public String getCompanyDeviceModelByCompanyIdAndDeviceModelId(String data);
	
	/**
	 * 根据公司Id和设备型号Id获取公司设备型号信息,如果每次此公司的设备Id,则选择原来的公司查询
	 * @author liukh
	 * @date 2017-8-28 下午4:02:11
	 * @param data
	 * @return
	 */
	public String getCompanyDeviceModelByOriginalCompanyIdOrCompanyIdAndDeviceModelId(String data);
	
	
	

	/**
	 * 获取公司设备型号信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-12 下午15:22:23
	 * @return
	 */
	public String getCompanyDeviceModelList(CompanyDeviceModelQueryForm form);
	
	/**
	 * 获取所有公司设备型号信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-12 下午15:22:23
	 * @return
	 */
	public String getAllCompanyDeviceModelList(CompanyDeviceModelQueryForm form);
	

	
	

}
