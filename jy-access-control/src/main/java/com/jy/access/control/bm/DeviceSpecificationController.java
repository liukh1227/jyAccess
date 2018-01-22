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
import com.jy.access.service.DeviceSpecificationService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DeviceSpecificationQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/bm")
public class DeviceSpecificationController extends CommonController {

	private static final Logger log = Logger.getLogger(DeviceSpecificationController.class);
	@Autowired
	private DeviceSpecificationService deviceSpecificationService;

	/**
	 * 新增设备规格
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/deviceSpecification", method = { RequestMethod.POST })
	@ResponseBody
	public String addDeviceSpecification(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDeviceSpecification") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDeviceSpecification", "addDeviceSpecification");
			jsonStr = deviceSpecificationService.addDeviceSpecification(data);
		} catch (Exception e) {
			log.error("addDeviceSpecification ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDeviceSpecification", null);
		return jsonStr;
	}

	/**
	 * 获取设备规格
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param deviceSpecificationId
	 * @return
	 */
	@RequestMapping(value = "/deviceSpecification/{deviceSpecificationId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceSpecification(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "deviceSpecificationId") String deviceSpecificationId) {
		String jsonStr = deviceSpecificationService.getDeviceSpecification(deviceSpecificationId);
		return jsonStr;
	}

	/**
	 * 更新设备规格
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param deviceTypeId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deviceSpecification/{deviceSpecificationId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updatedeviceSpecification(
			@PathVariable(value = "deviceSpecificationId") String deviceSpecificationId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = deviceSpecificationService.updateDeviceSpecification(deviceSpecificationId, data);
		return jsonStr;
	}

/**
 * 获取设备规格列表
 * @author liukh
 * @date 2017-7-17 下午5:52:05
 * @param request
 * @param response
 * @param form
 * @return
 */
	@RequestMapping(value = "/deviceSpecification/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceSpecificationList(HttpServletRequest request,
			HttpServletResponse response, DeviceSpecificationQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceSpecificationService.getDeviceSpecificationList(form);
		} catch (Exception e) {
			log.error("getDeviceSpecificationList ---- 异常,message = " + e.getMessage());
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
	@RequestMapping(value = "/deviceSpecification/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDeviceSpecificationList(HttpServletRequest request,
			HttpServletResponse response, DeviceSpecificationQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceSpecificationService.getAllDeviceSpecificationList(form);
		} catch (Exception e) {
			log.error("getAlldeviceSpecificationList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	/**
	 * 根据设备类型或者所有的规格信息列表
	 * @author liukh
	 * @date 2017-7-17 下午5:53:21
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	public String getAllDeviceSpecificationListByDeviceTypeId(HttpServletRequest request,
			HttpServletResponse response, DeviceSpecificationQueryForm form) {
		
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceSpecificationService.getAllDeviceSpecificationListByDeviceTypeId(form);
		} catch (Exception e) {
			log.error("getAllDeviceSpecificationListByDeviceTypeId ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	
	
}
