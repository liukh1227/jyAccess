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
import com.jy.access.service.DeviceModelService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DeviceModelQueryForm;




@Controller
@Scope("prototype")
@RequestMapping(value = "/bm")
public class DeviceModelController extends CommonController {

	private static final Logger log = Logger.getLogger(DeviceModelController.class);
	@Autowired
	private DeviceModelService deviceModelService;

	/**
	 * 新增设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/deviceModel", method = { RequestMethod.POST })
	@ResponseBody
	public String addDeviceModel(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDeviceModel") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDeviceModel", "addDeviceModel");
			jsonStr = deviceModelService.addDeviceModel(data);
		} catch (Exception e) {
			log.error("addDeviceModel ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDeviceModel", null);
		return jsonStr;
	}

	/**
	 * 获取设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param deviceModelId
	 * @return
	 */
	@RequestMapping(value = "/deviceModel/{deviceModelId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceModel(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "deviceModelId") String deviceModelId) {
		String jsonStr = deviceModelService.getDeviceModel(deviceModelId);
		return jsonStr;
	}

	/**
	 * 更新设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param deviceTypeId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deviceModel/{deviceModelId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDeviceModel(
			@PathVariable(value = "deviceModelId") String deviceModelId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = deviceModelService.updateDeviceModel(deviceModelId, data);
		return jsonStr;
	}

	/**
	 * 获取分页的设备型号信息列表
	 * @author liukh
	 * @date 2017-7-17 下午5:32:53
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceModel/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceModelList(HttpServletRequest request,
			HttpServletResponse response, DeviceModelQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceModelService.getDeviceModelList(form);
		} catch (Exception e) {
			log.error("getDeviceModelList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 所有设备型号信息
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceModel/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDeviceModelList(HttpServletRequest request,
			HttpServletResponse response, DeviceModelQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceModelService.getAllDeviceModelList(form);
		} catch (Exception e) {
			log.error("getAllDeviceModelList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	
	
}
