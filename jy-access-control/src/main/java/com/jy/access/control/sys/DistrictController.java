package com.jy.access.control.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jy.access.control.base.CommonController;
import com.jy.access.service.DistrictService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DistrictQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/sys")
public class DistrictController extends CommonController {

	private static final Logger log = Logger
			.getLogger(DistrictController.class);
	@Autowired
	private DistrictService districtService;

	/**
	 * 获取分页的地区信息列表
	 * 
	 * @author liukh
	 * @date 2017-9-8 上午11:12:56
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/district/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDistricteList(HttpServletRequest request,
			HttpServletResponse response, DistrictQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = districtService.getDistrictList(form);

		} catch (Exception e) {
			log.error("getDistricteList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的地区信息列表
	 * 
	 * @author liukh
	 * @date 2017-9-8 上午11:15:13
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/district/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDistrictList(HttpServletRequest request,
			HttpServletResponse response, DistrictQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = districtService.getAllDistrictList(form);
		} catch (Exception e) {
			log.error("getAllDistrictList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
