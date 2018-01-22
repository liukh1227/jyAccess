package com.jy.access.service;

import com.jy.entity.form.DtKeyAttributeValueQueryForm;


public interface DtKeyAttributevalueServie {
	/**
	 * 新增设备类型关键参数
	 * @author liukh
	 * @date 2017-7-10 下午10:22:23
	 * @param data
	 * @return
	 */
	public String addDtKeyAttributevalue(String data);

	/**
	 * 修改设备类型关键参数
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午10:22:23
	 * @param dSpecificationId
	 * @param data
	 * @return
	 */
	public String updateDtKeyAttributevalue(String dTKeyAttributeValueId, String data);

	/**
	 * 获取设备类型关键参数
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午10:22:23
	 * @param dSpecificationId
	 * @return
	 */
	public String getDtKeyAttributevalue(String dTKeyAttributeValueId);

	/**
	 * 获取设备类型关键参数信息列表
	 * @author liukh
	 * @date 2017-7-10 下午2:22:17
	 * @param form
	 * @return
	 */
	public String getDtKeyAttributevalueList(DtKeyAttributeValueQueryForm form);
	
	/**
	 * 获取所有设备类型关键参数信息列表
	 * @author liukh
	 * @date 2017-7-10 下午2:22:00
	 * @param form
	 * @return
	 */
	public String getAllDtKeyAttributevalueList(DtKeyAttributeValueQueryForm form);

}
