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
import com.jy.access.service.DtKeyAttributevalueServie;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DtKeyAttributeValueQueryForm;




@Controller
@Scope("prototype")
@RequestMapping(value = "/bm")
public class DtKeyAttributevalueController extends CommonController {

	private static final Logger log = Logger.getLogger(DtKeyAttributevalueController.class);
	@Autowired
	private DtKeyAttributevalueServie dtKeyAttributevalueServie;

/**
 * 新增设备类型关键属性值
 * @author liukh
 * @date 2017-7-18 上午10:01:05
 * @param request
 * @param response
 * @param data
 * @return
 */
	@RequestMapping(value = "/dtKeyAttributevalue", method = { RequestMethod.POST })
	@ResponseBody
	public String addDtKeyAttributevalue(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDtKeyAttributevalue") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDtKeyAttributevalue", "addDtKeyAttributevalue");
			jsonStr = dtKeyAttributevalueServie.addDtKeyAttributevalue(data);
		} catch (Exception e) {
			log.error("addDtKeyAttributevalue ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDtKeyAttributevalue", null);
		return jsonStr;
	}

	/**
	 * 获取设备类型关键属性值
	 * @author liukh
	 * @date 2017-7-18 上午10:01:52
	 * @param request
	 * @param response
	 * @param dtKeyAttributevalueId
	 * @return
	 */
	@RequestMapping(value = "/dtKeyAttributevalue/{dtKeyAttributevalueId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getDtKeyAttributevalue(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "dtKeyAttributevalueId") String dtKeyAttributevalueId) {
		String jsonStr = dtKeyAttributevalueServie.getDtKeyAttributevalue(dtKeyAttributevalueId);
		return jsonStr;
	}

/**
 * 更新设备类型关键属性值
 * @author liukh
 * @date 2017-7-18 上午10:02:07
 * @param dtKeyAttributevalueId
 * @param data
 * @param request
 * @param response
 * @return
 */
	@RequestMapping(value = "/dtKeyAttributevalue/{dtKeyAttributevalueId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDtKeyAttributevalue(
			@PathVariable(value = "dtKeyAttributevalueId") String dtKeyAttributevalueId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = dtKeyAttributevalueServie.updateDtKeyAttributevalue(dtKeyAttributevalueId, data);
		return jsonStr;
	}

/**
 * 获取分页的设备类型关键属性值列表
 * @author liukh
 * @date 2017-7-18 上午10:02:19
 * @param request
 * @param response
 * @param form
 * @return
 */
	@RequestMapping(value = "/dtKeyAttributevalue/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDtKeyAttributevalueList(HttpServletRequest request,
			HttpServletResponse response, DtKeyAttributeValueQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = dtKeyAttributevalueServie.getDtKeyAttributevalueList(form);
		} catch (Exception e) {
			log.error("getDtKeyAttributevalueList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

/**
 * 获取所有的设备类型关键属性值列表
 * @author liukh
 * @date 2017-7-18 上午10:02:36
 * @param request
 * @param response
 * @param form
 * @return
 */
	@RequestMapping(value = "/dtKeyAttributevalue/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDtKeyAttributevalueList(HttpServletRequest request,
			HttpServletResponse response, DtKeyAttributeValueQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = dtKeyAttributevalueServie.getAllDtKeyAttributevalueList(form);
		} catch (Exception e) {
			log.error("getAllDtKeyAttributevalueList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	
	
}
