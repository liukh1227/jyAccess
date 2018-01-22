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
import com.jy.access.service.DtKeyAttributespecificationService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DtKeyAttributeSpecificationQueryForm;




@Controller
@Scope("prototype")
@RequestMapping(value = "/bm")
public class DtKeyAttributespecificationController extends CommonController {

	private static final Logger log = Logger.getLogger(DtKeyAttributespecificationController.class);
	@Autowired
	private DtKeyAttributespecificationService dtKeyAttributespecificationService;

	/**
	 * 新增设备类型关键属性
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/dtKeyAttributespecification", method = { RequestMethod.POST })
	@ResponseBody
	public String addDtKeyAttributespecification(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDtKeyAttributespecification") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDtKeyAttributespecification", "addDtKeyAttributespecification");
			jsonStr = dtKeyAttributespecificationService.addDtKeyAttributespecification(data);
		} catch (Exception e) {
			log.error("addDtKeyAttributespecification ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDtKeyAttributespecification", null);
		return jsonStr;
	}

	/**
	 * 获取设备类型关键属性
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param dtKeyAttributespecificationId
	 * @return
	 */
	@RequestMapping(value = "/dtKeyAttributespecification/{dtKeyAttributespecificationId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getDtKeyAttributespecification(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "dtKeyAttributespecificationId") String dtKeyAttributespecificationId) {
		String jsonStr = dtKeyAttributespecificationService.getDtKeyAttributespecification(dtKeyAttributespecificationId);
		return jsonStr;
	}

	/**
	 * 更新设备类型关键属性
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param deviceTypeId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dtKeyAttributespecification/{dtKeyAttributespecificationId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDtKeyAttributespecification(
			@PathVariable(value = "dtKeyAttributespecificationId") String dtKeyAttributespecificationId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = dtKeyAttributespecificationService.updateDtKeyAttributespecification(dtKeyAttributespecificationId, data);
		return jsonStr;
	}

/**
 * 获取分页的设备类型关键属性信息列表
 * @author liukh
 * @date 2017-7-18 上午9:55:07
 * @param request
 * @param response
 * @param form
 * @return
 */
	@RequestMapping(value = "/dtKeyAttributespecification/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDtKeyAttributespecificationList(HttpServletRequest request,
			HttpServletResponse response, DtKeyAttributeSpecificationQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = dtKeyAttributespecificationService.getDtKeyAttributespecificationList(form);
		} catch (Exception e) {
			log.error("getDtKeyAttributespecificationList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

/**
 * 获取所有的设备类型关键属性信息列表
 * @author liukh
 * @date 2017-7-18 上午9:55:51
 * @param request
 * @param response
 * @param form
 * @return
 */
	@RequestMapping(value = "/dtKeyAttributespecification/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDtKeyAttributespecificationList(HttpServletRequest request,
			HttpServletResponse response, DtKeyAttributeSpecificationQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = dtKeyAttributespecificationService.getAllDtKeyAttributespecificationList(form);
		} catch (Exception e) {
			log.error("getAllDtKeyAttributespecificationList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	
	
}
