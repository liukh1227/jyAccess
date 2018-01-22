package com.jy.access.control.bm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jy.access.control.base.CommonController;
import com.jy.access.service.DeviceBrandService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DeviceBrandQueryForm;




@Controller
@Scope("prototype")
@RequestMapping(value = "/bm")
public class DeviceBrandController extends CommonController {

	private static final Logger log = Logger.getLogger(DeviceBrandController.class);
	@Autowired
	private DeviceBrandService deviceBrandService;

	/**
	 * 新增品牌信息
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/deviceBrand", method = { RequestMethod.POST })
	@ResponseBody
	public String addDeviceBrand(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDeviceBrand") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDeviceBrand", "addDeviceBrand");
			jsonStr = deviceBrandService.addDeviceBrand(data);
		} catch (Exception e) {
			log.error("addDeviceBrand ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDeviceBrand", null);
		return jsonStr;
	}

	/**
	 * 获取品牌信息
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param deviceBrandId
	 * @return
	 */
	@RequestMapping(value = "/deviceBrand/{deviceBrandId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceBrand(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "deviceBrandId") String deviceBrandId) {
		String jsonStr = deviceBrandService.getDeviceBrand(deviceBrandId);
		return jsonStr;
	}

	/**
	 * 更新品牌信息
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param deviceTypeId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deviceBrand/{deviceBrandId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDeviceBrand(
			@PathVariable(value = "deviceBrandId") String deviceBrandId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = deviceBrandService.updateDeviceBrand(deviceBrandId, data);
		return jsonStr;
	}

	/**
	 * 获取品牌信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param brandName
	 * @param isDisplay
	 * @param numberOfItem
	 * @param page
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deviceBrand/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceBrandList(HttpServletRequest request,
			HttpServletResponse response, DeviceBrandQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceBrandService.getDeviceBrandList(form);
		} catch (Exception e) {
			log.error("getDeviceBrandList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取按顺序排列的品牌信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceBrand/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDeviceBrandList(HttpServletRequest request,
			HttpServletResponse response, DeviceBrandQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceBrandService.getAllDeviceBrandList(form);
		} catch (Exception e) {
			log.error("getAllDeviceBrandList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	
	
}
