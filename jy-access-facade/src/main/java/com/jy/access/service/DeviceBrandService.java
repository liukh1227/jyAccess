package com.jy.access.service;

import com.jy.entity.form.DeviceBrandQueryForm;


public interface DeviceBrandService {

	/**
	 * 新增品牌信息
	 * @author liukh
	 * @date 2017-7-6 下午10:22:23
	 * @param data
	 * @return
	 */
	public String addDeviceBrand(String data);

	/**
	 * 修改品牌信息
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:22:23
	 * @param deviceBrandId
	 * @param data
	 * @return
	 */
	public String updateDeviceBrand(String deviceBrandId, String data);

	/**
	 * 获取品牌信息
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:22:23
	 * @param deviceBrandId
	 * @return
	 */
	public String getDeviceBrand(String deviceBrandId);

	/**
	 * 获取品牌信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:22:23
	 * @return
	 */
	public String getDeviceBrandList(DeviceBrandQueryForm form);
	
	/**
	 * 获取所有品牌信息列表
	 * 
	 * @author liukh
	 + @date 2017-7-6 下午10:22:23
	 * @return
	 */
	public String getAllDeviceBrandList(DeviceBrandQueryForm form);
	
	

}
