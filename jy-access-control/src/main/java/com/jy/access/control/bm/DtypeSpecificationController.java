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
import com.jy.access.service.DtypeSpecificationService;
import com.jy.base.entity.base.dto.BaseDto;

@Controller
@Scope("prototype")
@RequestMapping(value = "/bm")
public class DtypeSpecificationController extends CommonController {

	private static final Logger log = Logger.getLogger(DtypeSpecificationController.class);
	@Autowired
	private DtypeSpecificationService dtypeSpecificationService;

/**
 * 新增设备规格和设备类型关联
 * @author liukh
 * @date 2017-7-18 上午10:06:58
 * @param request
 * @param response
 * @param data
 * @return
 */
	@RequestMapping(value = "/dtypeSpecification", method = { RequestMethod.POST })
	@ResponseBody
	public String addDtypeSpecification(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDtypeSpecification") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDtypeSpecification", "addDtypeSpecification");
			jsonStr = dtypeSpecificationService.addDtypeSpecification(data);
		} catch (Exception e) {
			log.error("addDtypeSpecification ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDtypeSpecification", null);
		return jsonStr;
	}
	/**
	 * 删除设备规格和设备类型关联
	 * @author liukh
	 * @date 2017-7-18 上午10:10:57
	 * @param request
	 * @param response
	 * @param dtypeSpecificationId
	 * @return
	 */
	@RequestMapping(value = "/delete/dtypeSpecification/{dtypeSpecificationId}", method = { RequestMethod.GET })
	@ResponseBody
	public String deleteDtypeSpecification(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "dtypeSpecificationId") String dtypeSpecificationId) {
		String jsonStr = dtypeSpecificationService.deleteDtypeSpecification(dtypeSpecificationId);
		return jsonStr;
	}

	
	
	
}
