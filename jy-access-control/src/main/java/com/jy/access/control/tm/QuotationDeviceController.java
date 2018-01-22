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
import com.jy.access.service.QuotationDeviceService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.QuotationDeviceQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class QuotationDeviceController extends CommonController {

	private static final Logger log = Logger
			.getLogger(QuotationDeviceController.class);
	@Autowired
	private QuotationDeviceService quotationDeviceService;

	/**
	 * 报价设备信息
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/quotationDevice", method = { RequestMethod.POST })
	@ResponseBody
	public String addQuotationDevice(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addQuotationDevice") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addQuotationDevice", "addQuotationDevice");
			jsonStr = quotationDeviceService.addQuotationDevice(data);
		} catch (Exception e) {
			log.error("addQuotationDevice ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addQuotationDevice", null);
		return jsonStr;
	}
	
	
	/**
	 * 删除报价设备
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午10:30:50
	 * @param request
	 * @param response
	 * @param quodeviceId
	 * @return
	 */

	@RequestMapping(value = "/delete/quotationDevice/{quodeviceId}", method = { RequestMethod.GET })
	@ResponseBody
	public String deleteQuotationDevice(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "quodeviceId") String quodeviceId) {
		String jsonStr = quotationDeviceService.deleteQuotationDevice(quodeviceId);
		return jsonStr;
	}
	
	/**
	 * 更新报价设备信息
	 * @author liukh
	 * @date 2017-8-10 下午2:58:36
	 * @param quodeviceId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/quotationDevice/{quodeviceId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateQuotationDevice(
			@PathVariable(value = "quodeviceId") String quodeviceId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = quotationDeviceService.updateQuotationDevice(quodeviceId, data);
		return jsonStr;
	}

	

	/**
	 * 获取报价设备详细信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午10:24:30
	 * @param request
	 * @param response
	 * @param QuotationId
	 * @return
	 */
	@RequestMapping(value = "/quotationDevice/{quodeviceId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getQuotationDevice(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "quodeviceId") String quodeviceId) {
		String jsonStr = quotationDeviceService.getQuotationDevice(quodeviceId);
		return jsonStr;
	}

	/**
	 * 获取分页的报价设备列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午10:26:02
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/quotationDevice/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getQuotationDeviceList(HttpServletRequest request,
			HttpServletResponse response, QuotationDeviceQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = quotationDeviceService.getQuotationDeviceList(form);
		} catch (Exception e) {
			log.error("getQuotationDeviceList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的报价设备列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午10:29:05
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/quotationDevice/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllQuotationDeviceList(HttpServletRequest request,
			HttpServletResponse response, QuotationDeviceQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = quotationDeviceService.getAllQuotationDeviceList(form);
		} catch (Exception e) {
			log.error("getAllQuotationDeviceList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}



}
