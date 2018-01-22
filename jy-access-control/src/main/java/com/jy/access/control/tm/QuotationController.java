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
import com.jy.access.service.QuotationService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.QuotationQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class QuotationController extends CommonController {

	private static final Logger log = Logger
			.getLogger(QuotationController.class);
	@Autowired
	private QuotationService quotationService;

	/**
	 * 新增报价单
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/quotation", method = { RequestMethod.POST })
	@ResponseBody
	public String addQuotation(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addQuotation") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addQuotation", "addQuotation");
			jsonStr = quotationService.addQuotation(data);
		} catch (Exception e) {
			log.error("addQuotation ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addQuotation", null);
		return jsonStr;
	}

	/**
	 * 新增报价单及报价设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午10:20:01
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/quotationAndQuotationDevice", method = { RequestMethod.POST })
	@ResponseBody
	public String addQuotationAndQuotationDevice(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("adQuotationAndQuotationDevice") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("adQuotationAndQuotationDevice", "adQuotationAndQuotationDevice");
			jsonStr = quotationService.addQuotationAndQuotationDevice(data);
		} catch (Exception e) {
			log.error("adQuotationAndQuotationDevice ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("adQuotationAndQuotationDevice", null);
		return jsonStr;
	}
	
	/**
	 * 修改报价单信息
	 * @author liukh
	 * @date 2017-8-10 下午3:00:14
	 * @param quotationId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/quotation/{quotationId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateQuotation(
			@PathVariable(value = "quotationId") String quotationId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = quotationService.updateQuotation(quotationId, data);
		return jsonStr;
	}

	/**
	 * 更新报价信息及报价设备信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午10:22:52
	 * @param QuotationId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/quotationAndQuotationDevice/{quotationId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateQuotationAndQuotationDevice(
			@PathVariable(value = "quotationId") String quotationId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = quotationService.updateQuotationAndQuotationDevice(
				quotationId, data);
		return jsonStr;
	}

/**
 * 获取报价单信息
 * @author liukh
 * @date 2017-8-10 下午3:01:36
 * @param request
 * @param response
 * @param quotationId
 * @return
 */
	@RequestMapping(value = "/quotation/{quotationId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getQuotation(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "quotationId") String quotationId) {
		String jsonStr = quotationService.getQuotation(quotationId);
		return jsonStr;
	}
	
	/**
	 * 获取报价及报价设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午10:24:30
	 * @param request
	 * @param response
	 * @param QuotationId
	 * @return
	 */
	@RequestMapping(value = "/quotationAndQuotationDevice/{quotationId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getQuotationAndQuotationDevice(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "quotationId") String quotationId) {
		String jsonStr = quotationService
				.getQuotationAndQuotationDevice(quotationId);
		return jsonStr;
	}

	/**
	 * 获取分页的报价信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午10:26:02
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/quotation/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getQuotationList(HttpServletRequest request,
			HttpServletResponse response, QuotationQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = quotationService.getQuotationList(form);
		} catch (Exception e) {
			log.error("getQuotationList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}


}
