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
import com.jy.access.service.DeviceOutMakesureOrderService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DeviceOutMakesureOrderQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class DeviceOutMakesureOrderController extends CommonController {
	private static final Logger log = Logger
			.getLogger(DeviceOutMakesureOrderController.class);
	@Autowired
	private DeviceOutMakesureOrderService deviceOutMakesureOrderService;

	/**
	 * 新增退场确认单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:46:07
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/deviceOutMakesureOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String addDeviceOutMakesureOrder(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDeviceOutMakesureOrder") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDeviceOutMakesureOrder", "addDeviceOutMakesureOrder");
			jsonStr = deviceOutMakesureOrderService
					.addDeviceOutMakesureOrder(data);
		} catch (Exception e) {
			log.error("addDeviceOutMakesureOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDeviceOutMakesureOrder", null);
		return jsonStr;
	}

	/**
	 * 批量新增退场确认单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:47:01
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/batch/deviceOutMakesureOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String addBatchDeviceOutMakesureOrder(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addBatchDeviceOutMakesureOrder") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addBatchDeviceOutMakesureOrder", "addBatchDeviceOutMakesureOrder");
			jsonStr = deviceOutMakesureOrderService
					.addBatchDeviceOutMakesureOrder(data);
		} catch (Exception e) {
			log.error("addBatchDeviceOutMakesureOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addBatchDeviceOutMakesureOrder", null);
		return jsonStr;
	}

	/**
	 * 修改退场确认单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:48:22
	 * @param dInMakeSureId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deviceOutMakesureOrder/{dInMakeSureId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDeviceOutMakesureOrder(
			@PathVariable(value = "dInMakeSureId") String dInMakeSureId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceOutMakesureOrderService
					.updateDeviceOutMakesureOrder(dInMakeSureId, data);
		} catch (Exception e) {
			log.error("updateDeviceOutMakesureOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取退场确认单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:49:05
	 * @param request
	 * @param response
	 * @param dInMakeSureId
	 * @return
	 */

	@RequestMapping(value = "/deviceOutMakesureOrder/{dInMakeSureId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceOutMakesureOrder(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "dInMakeSureId") String dInMakeSureId) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceOutMakesureOrderService
					.getDeviceOutMakesureOrder(dInMakeSureId);
		} catch (Exception e) {
			log.error("getDeviceOutMakesureOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取分页的退场确认单信列表
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:49:51
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceOutMakesureOrder/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceOutMakesureOrderList(HttpServletRequest request,
			HttpServletResponse response, DeviceOutMakesureOrderQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceOutMakesureOrderService
					.getDeviceOutMakesureOrderList(form);
		} catch (Exception e) {
			log.error("getDeviceOutMakesureOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有退场确认单信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:50:27
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deviceOutMakesureOrder/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDeviceOutMakesureOrderList(HttpServletRequest request,
			HttpServletResponse response, DeviceOutMakesureOrderQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = deviceOutMakesureOrderService
					.getAllDeviceOutMakesureOrderList(form);
		} catch (Exception e) {
			log.error("getAllDeviceOutMakesureOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
