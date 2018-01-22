package com.jy.access.service;

import com.jy.entity.form.DistrictQueryForm;

public interface DistrictService {
	/**
	 * 获取分页的地区信息列表
	 * @author liukh
	 * @date 2017-9-8 上午10:33:09
	 * @param form
	 * @return
	 */
	public String getDistrictList(DistrictQueryForm form);
/**
 * 获取所有的地区信息列表
 * @author liukh
 * @date 2017-9-8 上午10:33:28
 * @param form
 * @return
 */
	public String getAllDistrictList(DistrictQueryForm form);

}
