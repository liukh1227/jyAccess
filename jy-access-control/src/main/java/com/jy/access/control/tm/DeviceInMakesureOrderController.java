package com.jy.access.control.tm;

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
import com.jy.access.service.DeviceInMakesureOrderService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DeviceInMakesureOrderQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class DeviceInMakesureOrderController extends CommonController {
	private static final Logger log = Logger
			.getLogger(DeviceInMakesureOrderController.class);
	@Autowired
	private DeviceInMakesureOrderService deviceInMakesureOrderService;

	/**
	 * 新增进场确认单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:46:07
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/deviceInMakesureOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String addDeviceInMakesureOrder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDeviceInMakesureOrder") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDeviceInMakesureOrder",
					"addDeviceInMakesureOrder");
			jsonStr = deviceInMakesureOrderService
					.addDeviceInMakesureOrder(data);
		} catch (Exception e) {
			log.error("addDeviceInMakesureOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDeviceInMakesureOrder", null);
		return jsonStr;
	}

	/**
	 * 批量新增进场确认单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:47:01
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/batch/deviceInMakesureOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String addBatchDeviceInMakesureOrder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addBatchDeviceInMakesureOrder") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addBatchDeviceInMakesureOrder",
					"addBatchDeviceInMakesureOrder");
			jsonStr = deviceInMakesureOrderService
					.addBatchDeviceInMakesureOrder(data);
		} catch (Exception e) {
			log.error("addBatchDeviceInMakesureOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addBatchDeviceInMakesureOrder", null);
		return jsonStr;
	}

	/**
	 * 修改进场确认单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:48:22
	 * @param dInMakeSureId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deviceInMakesureOrder/{dInMakeSureId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDeviceInMakesureOrder(
			@PathVariable(value = "dInMakeSureId") String dInMakeSureId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceInMakesureOrderService.updateDeviceInMakesureOrder(
					dInMakeSureId, data);
		} catch (Exception e) {
			log.error("updateDeviceInMakesureOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取进场确认单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:49:05
	 * @param request
	 * @param response
	 * @param dInMakeSureId
	 * @return
	 */

	@RequestMapping(value = "/deviceInMakesureOrder/{dInMakeSureId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceInMakesureOrder(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "dInMakeSureId") String dInMakeSureId) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceInMakesureOrderService
					.getDeviceInMakesureOrder(dInMakeSureId);
		} catch (Exception e) {
			log.error("getDeviceInMakesureOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取分页的进场确认单信列表
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:49:51
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceInMakesureOrder/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceInMakesureOrderList(HttpServletRequest request,
			HttpServletResponse response, DeviceInMakesureOrderQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceInMakesureOrderService
					.getDeviceInMakesureOrderList(form);
		} catch (Exception e) {
			log.error("getDeviceInMakesureOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有进场确认单信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:50:27
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceInMakesureOrder/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDeviceInMakesureOrderList(HttpServletRequest request,
			HttpServletResponse response, DeviceInMakesureOrderQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceInMakesureOrderService
					.getAllDeviceInMakesureOrderList(form);
		} catch (Exception e) {
			log.error("getAllDeviceInMakesureOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取分页的去除已退场的进场确认单信列表
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:49:51
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceInMakesureOrder4DOutMOrder/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceInMakesureOrder4DOutMOrderList(
			HttpServletRequest request, HttpServletResponse response,
			DeviceInMakesureOrderQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceInMakesureOrderService
					.getDeviceInMakesureOrder4DOutMOrderPageList(form);
		} catch (Exception e) {
			log.error("getDeviceInMakesureOrder4DOutMOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有去除已退场的进场确认单信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:50:27
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceInMakesureOrder4DOutMOrder/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDeviceInMakesureOrder4DOutMOrderList(
			HttpServletRequest request, HttpServletResponse response,
			DeviceInMakesureOrderQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceInMakesureOrderService
					.getAllDeviceInMakesureOrder4DOutMOrderList(form);
		} catch (Exception e) {
			log.error("getAllDeviceInMakesureOrder4DOutMOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
