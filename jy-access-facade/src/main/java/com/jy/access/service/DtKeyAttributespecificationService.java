package com.jy.access.service;

import com.jy.entity.form.DtKeyAttributeSpecificationQueryForm;

public interface DtKeyAttributespecificationService {
	/**
	 * 新增设备类型关键属性
	 * @author liukh
	 * @date 2017-7-10 下午10:22:23
	 * @param data
	 * @return
	 */
	public String addDtKeyAttributespecification(String data);

	/**
	 * 修改设备类型关键属性
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午10:22:23
	 * @param dSpecificationId
	 * @param data
	 * @return
	 */
	public String updateDtKeyAttributespecification(String dTKeyAttributeSpecId, String data);

	/**
	 * 获取设备类型关键属性
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午10:22:23
	 * @param dSpecificationId
	 * @return
	 */
	public String getDtKeyAttributespecification(String dTKeyAttributeSpecId);

	/**
	 * 获取设备类型关键属性信息列表
	 * @author liukh
	 * @date 2017-7-10 下午2:22:17
	 * @param form
	 * @return
	 */
	public String getDtKeyAttributespecificationList(DtKeyAttributeSpecificationQueryForm form);
	
	/**
	 * 获取所有设备类型关键属性信息列表
	 * @author liukh
	 * @date 2017-7-10 下午2:22:00
	 * @param form
	 * @return
	 */
	public String getAllDtKeyAttributespecificationList(DtKeyAttributeSpecificationQueryForm form);


}
