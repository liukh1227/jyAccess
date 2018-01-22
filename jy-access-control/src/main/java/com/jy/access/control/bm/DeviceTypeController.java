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
import com.jy.access.service.DeviceTypeService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DeviceTypeQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/bm")
public class DeviceTypeController extends CommonController {

	private static final Logger log = Logger
			.getLogger(DeviceTypeController.class);
	@Autowired
	private DeviceTypeService deviceTypeService;

	/**
	 * 新增设备类型
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午5:53:30
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/deviceType", method = { RequestMethod.POST })
	@ResponseBody
	public String addDeviceType(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDeviceType") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDeviceType", "addDeviceType");
			jsonStr = deviceTypeService.addDeviceType(data);
		} catch (Exception e) {
			log.error("addDeviceType ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDeviceType", null);
		return jsonStr;
	}

	/**
	 * 查询设备类型信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午5:53:51
	 * @param request
	 * @param response
	 * @param deviceTypeId
	 * @return
	 */
	@RequestMapping(value = "/deviceType/{deviceTypeId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceType(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "deviceTypeId") String deviceTypeId) {
		String jsonStr = deviceTypeService.getDeviceType(deviceTypeId);
		return jsonStr;
	}

	/**
	 * 更改设备类型信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午5:54:06
	 * @param deviceTypeId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deviceType/{deviceTypeId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDeviceType(
			@PathVariable(value = "deviceTypeId") String deviceTypeId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = deviceTypeService.updateDeviceType(deviceTypeId, data);
		return jsonStr;
	}

	/**
	 * 获取分页的设备类型信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午5:54:24
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceType/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceTypeList(HttpServletRequest request,
			HttpServletResponse response, DeviceTypeQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceTypeService.getDeviceTypeList(form);
		} catch (Exception e) {
			log.error("getDeviceTypeList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取全部的设备类型信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午5:54:53
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceType/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDeviceTypeList(HttpServletRequest request,
			HttpServletResponse response, DeviceTypeQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceTypeService.getAllDeviceTypeList(form);
		} catch (Exception e) {
			log.error("getAllDeviceTypeList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有字设备类型和父设备类型信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-17 下午5:28:46
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceType/allLeveList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllLeveDeviceTypeList(HttpServletRequest request,
			HttpServletResponse response, DeviceTypeQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceTypeService.getAllLeveDeviceTypeList(form);
		} catch (Exception e) {
			log.error("getAllDeviceTypeList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
