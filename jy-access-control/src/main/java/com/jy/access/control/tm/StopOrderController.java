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
import com.jy.access.service.StopOrderService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.StopOrderQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class StopOrderController extends CommonController {
	private static final Logger log = Logger.getLogger(StopOrderController.class);
	@Autowired
	private StopOrderService stopOrderService;

	/**
	 * 新增报修单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:46:07
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/stopOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String addStopOrder(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addStopOrder") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addStopOrder", "addStopOrder");
			jsonStr = stopOrderService.addStopOrder(data);
		} catch (Exception e) {
			log.error("addStopOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addStopOrder", null);
		return jsonStr;
	}

	/**
	 * 修改报修单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:48:22
	 * @param stopOrderId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/stopOrder/{stopOrderId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateStopOrder(
			@PathVariable(value = "stopOrderId") String stopOrderId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = stopOrderService.updateStopOrder(stopOrderId, data);
		} catch (Exception e) {
			log.error("updateStopOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取报修单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:49:05
	 * @param request
	 * @param response
	 * @param stopOrderId
	 * @return
	 */

	@RequestMapping(value = "/stopOrder/{stopOrderId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getStopOrder(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "stopOrderId") String stopOrderId) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = stopOrderService.getStopOrder(stopOrderId);
		} catch (Exception e) {
			log.error("getStopOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取分页的报修单信列表
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:49:51
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/stopOrder/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getStopOrderList(HttpServletRequest request,
			HttpServletResponse response, StopOrderQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = stopOrderService
					.getStopOrderList(form);
		} catch (Exception e) {
			log.error("getStopOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有报修单信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:50:27
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/stopOrder/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllStopOrderList(HttpServletRequest request,
			HttpServletResponse response, StopOrderQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = stopOrderService
					.getAllStopOrderList(form);
		} catch (Exception e) {
			log.error("getAllStopOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}