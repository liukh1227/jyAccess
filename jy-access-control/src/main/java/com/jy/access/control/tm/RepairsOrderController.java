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
import com.jy.access.service.RepairsOrderService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.RepairsOrderQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class RepairsOrderController extends CommonController {
	private static final Logger log = Logger.getLogger(RepairsOrderController.class);
	@Autowired
	private RepairsOrderService repairsOrderService;

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
	@RequestMapping(value = "/repairsOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String addRepairsOrder(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addRepairsOrder") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addRepairsOrder", "addRepairsOrder");
			jsonStr = repairsOrderService.addRepairsOrder(data);
		} catch (Exception e) {
			log.error("addRepairsOrder ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addRepairsOrder", null);
		return jsonStr;
	}

	/**
	 * 修改报修单
	 * 
	 * @author liukh
	 * @date 2017-8-2 下午5:48:22
	 * @param repairsOrderId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/repairsOrder/{repairsOrderId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updaterepairsOrder(
			@PathVariable(value = "repairsOrderId") String repairsOrderId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = repairsOrderService.updateRepairsOrder(
					repairsOrderId, data);
		} catch (Exception e) {
			log.error("updateRepairsOrder ---- 异常,message = "
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
	 * @param repairsOrderId
	 * @return
	 */

	@RequestMapping(value = "/repairsOrder/{repairsOrderId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getRepairsOrder(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "repairsOrderId") String repairsOrderId) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = repairsOrderService
					.getRepairsOrder(repairsOrderId);
		} catch (Exception e) {
			log.error("getRepairsOrder ---- 异常,message = "
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
	@RequestMapping(value = "/repairsOrder/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getRepairsOrderList(HttpServletRequest request,
			HttpServletResponse response, RepairsOrderQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = repairsOrderService
					.getRepairsOrderList(form);
		} catch (Exception e) {
			log.error("getRepairsOrderList ---- 异常,message = "
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
	@RequestMapping(value = "/repairsOrder/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllRepairsOrderList(HttpServletRequest request,
			HttpServletResponse response, RepairsOrderQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = repairsOrderService
					.getAllRepairsOrderList(form);
		} catch (Exception e) {
			log.error("getAllRepairsOrderList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}