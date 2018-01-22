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
import com.jy.access.service.DmAttributevalueService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DmAttributeValueQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/bm")
public class DmAttributevalueController extends CommonController {

	private static final Logger log = Logger
			.getLogger(DmAttributevalueController.class);
	@Autowired
	private DmAttributevalueService dmAttributevalueService;

	/**
	 * 新增设备规格属性值
	 * 
	 * @author liukh
	 * @date 2017-7-7 上午10:41:34
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/dmAttributevalue", method = { RequestMethod.POST })
	@ResponseBody
	public String addDmAttributevalue(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDmAttributevalue") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDmAttributevalue", "addDmAttributevalue");
			jsonStr = dmAttributevalueService.addDmAttributevalue(data);
		} catch (Exception e) {
			log.error("addDmAttributevalue ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDmAttributevalue", null);
		return jsonStr;
	}

	/**
	 * 删除设备规格属性值
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午9:35:04
	 * @param request
	 * @param response
	 * @param dmAttributevalueId
	 * @return
	 */
	@RequestMapping(value = "/delete/dmAttributevalue/{dmAttributevalueId}", method = { RequestMethod.GET })
	@ResponseBody
	public String deleteDmAttributevalue(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "dmAttributevalueId") String dmAttributevalueId) {
		String jsonStr = dmAttributevalueService
				.deleteDmAttributevalue(dmAttributevalueId);
		return jsonStr;
	}

	/**
	 * 获取分页的设备规格属性值列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午9:35:48
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/dmAttributevalue/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDmAttributevalueList(HttpServletRequest request,
			HttpServletResponse response, DmAttributeValueQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = dmAttributevalueService.getDmAttributevalueList(form);
		} catch (Exception e) {
			log.error("getDmAttributevalueList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的设备规格属性值列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 上午9:36:34
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/dmAttributevalue/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDmAttributevalueList(HttpServletRequest request,
			HttpServletResponse response, DmAttributeValueQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = dmAttributevalueService.getAllDmAttributevalueList(form);
		} catch (Exception e) {
			log.error("getAllDmAttributevalueList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
