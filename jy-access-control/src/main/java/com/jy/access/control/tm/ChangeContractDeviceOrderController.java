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
import com.jy.access.service.ChangeContractDeviceOrderService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.ChangeContractDeviceOrderQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class ChangeContractDeviceOrderController extends CommonController {

	private static final Logger log = Logger
			.getLogger(ChangeContractDeviceOrderController.class);
	@Autowired
	private ChangeContractDeviceOrderService changeContractDeviceOrderService;

	/**
	 * 新增延期退场设备单
	 * 
	 * @author liukh
	 * @date 2017-8-9 下午3:32:39
	 * @param request
	 * @param response
	 * @param session
	 * @param data
	 *            changeType Y 延期，T 提前退场
	 * @return
	 */
	@RequestMapping(value = "/changeContractDeviceOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String addChangeContractDeviceOrder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addChangeContractDeviceOrder") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addChangeContractDeviceOrder",
					"addChangeContractDeviceOrder");
			jsonStr = changeContractDeviceOrderService
					.addChangeContractDeviceOrder(data);
		} catch (Exception e) {
			log.error("addChangeContractDeviceOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addChangeContractDeviceOrder", null);
		return jsonStr;
	}

	/**
	 * 删除延期退场设备单
	 * 
	 * @author liukh
	 * @date 2017-8-9 下午3:45:28
	 * @param request
	 * @param response
	 * @param changeOrderId
	 * @return
	 */
	@RequestMapping(value = "/delete/changeContractDeviceOrder/{changeOrderId}", method = { RequestMethod.GET })
	@ResponseBody
	public String deleteChangeContractDeviceOrder(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "changeOrderId") String changeOrderId) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = changeContractDeviceOrderService
					.deleteChangeContractDeviceOrder(changeOrderId);
		} catch (Exception e) {
			log.error("deleteChangeContractDeviceOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 修改延期退场设备单
	 * 
	 * @author liukh
	 * @date 2017-8-9 下午3:36:47
	 * @param changeOrderId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/changeContractDeviceOrder/{changeOrderId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateChangeContractDeviceOrder(
			@PathVariable(value = "changeOrderId") String changeOrderId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = changeContractDeviceOrderService
					.updateChangeContractDeviceOrder(changeOrderId, data);
		} catch (Exception e) {
			log.error("updateChangeContractDeviceOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取延期退场设备单详细信息
	 * 
	 * @author liukh
	 * @date 2017-8-9 下午3:38:14
	 * @param request
	 * @param response
	 * @param changeOrderId
	 * @return
	 */
	@RequestMapping(value = "/changeContractDeviceOrder/{changeOrderId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getChangeContractDeviceOrder(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "changeOrderId") String changeOrderId) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = changeContractDeviceOrderService
					.getChangeContractDeviceOrder(changeOrderId);
		} catch (Exception e) {
			log.error("getChangeContractDeviceOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取分页的延期退场设备单信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-9 下午3:46:30
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/changeContractDeviceOrder/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getChangeContractDeviceOrderList(HttpServletRequest request,
			HttpServletResponse response,
			ChangeContractDeviceOrderQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = changeContractDeviceOrderService
					.getChangeContractDeviceOrderList(form);
		} catch (Exception e) {
			log.error("getDeliveryOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的延期退场设备单信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-9 下午3:46:55
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/changeContractDeviceOrder/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllChangeContractDeviceOrderList(
			HttpServletRequest request, HttpServletResponse response,
			ChangeContractDeviceOrderQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = changeContractDeviceOrderService
					.getAllChangeContractDeviceOrderList(form);
		} catch (Exception e) {
			log.error("getAllChangeContractDeviceOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
