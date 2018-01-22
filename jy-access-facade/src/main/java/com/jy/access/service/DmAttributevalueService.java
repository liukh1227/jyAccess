package com.jy.access.service;

import com.jy.entity.form.DmAttributeValueQueryForm;

public interface DmAttributevalueService {

	/**
	 * 新增设备规格属性值
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午3:38:36
	 * @param data
	 * @return
	 */
	public String addDmAttributevalue(String data);

	/**
	 * 删除设备规格属性值
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午3:39:04
	 * @param dMAttributeValueId
	 * @return
	 */
	public String deleteDmAttributevalue(String dMAttributeValueId);

	/**
	 * 获取分页的设备规格属性值列表
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午3:39:41
	 * @param form
	 * @return
	 */
	public String getDmAttributevalueList(DmAttributeValueQueryForm form);

	/**
	 * 获取所有的设备规格属性值信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-11 下午3:40:15
	 * @param form
	 * @return
	 */
	public String getAllDmAttributevalueList(DmAttributeValueQueryForm form);
}
