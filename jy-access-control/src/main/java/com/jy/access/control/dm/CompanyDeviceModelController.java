package com.jy.access.control.dm;

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
import com.jy.access.service.CompanyDeviceModelService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.CompanyDeviceModelQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/dm")
public class CompanyDeviceModelController extends CommonController {

	private static final Logger log = Logger
			.getLogger(CompanyDeviceModelController.class);
	@Autowired
	private CompanyDeviceModelService companyDeviceModelService;

	/**
	 * 新增公司型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午2:39:11
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/companyDeviceModel", method = { RequestMethod.POST })
	@ResponseBody
	public String addCompanyDeviceModel(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addCompanyDeviceModel") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addCompanyDeviceModel", "addCompanyDeviceModel");
			jsonStr = companyDeviceModelService.addCompanyDeviceModel(data);
		} catch (Exception e) {
			log.error("addCompanyDeviceModel ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addCompanyDeviceModel", null);
		return jsonStr;
	}

	/**
	 * 修改公司型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午2:40:49
	 * @param request
	 * @param response
	 * @param companyDeviceModelId
	 * @return
	 */
	@RequestMapping(value = "/companyDeviceModel/{companyDeviceModelId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getCompanyDeviceModel(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "companyDeviceModelId") String companyDeviceModelId) {
		String jsonStr = companyDeviceModelService
				.getCompanyDeviceModel(companyDeviceModelId);
		return jsonStr;
	}
	/**
	 * 
	 * @author liukh
	 * @date 2017-8-28 下午9:16:28
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/companyIdAndDeviceModelId/companyDeviceModel", method = { RequestMethod.POST })
	@ResponseBody
	public String getCompanyDeviceModelByCompanyIdAndDeviceModelId(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = companyDeviceModelService.getCompanyDeviceModelByCompanyIdAndDeviceModelId(data);
		return jsonStr;
	}
	/**
	 * 
	 * @author liukh
	 * @date 2017-8-28 下午9:16:32
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/originalCompanyIdOrCompanyIdAndDeviceModelId/companyDeviceModel", method = { RequestMethod.POST })
	@ResponseBody
	public String getCompanyDeviceModelByOriginalCompanyIdOrCompanyIdAndDeviceModelId(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = companyDeviceModelService.getCompanyDeviceModelByOriginalCompanyIdOrCompanyIdAndDeviceModelId(data);
		return jsonStr;
	}

	/**
	 * 修改公司型号信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午2:40:03
	 * @param companyDeviceModelId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/companyDeviceModel/{companyDeviceModelId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateCompanyDeviceModel(
			@PathVariable(value = "companyDeviceModelId") String companyDeviceModelId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = companyDeviceModelService.updateCompanyDeviceModel(
				companyDeviceModelId, data);
		return jsonStr;
	}

	/**
	 * 获取分页的公司型号信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午2:41:25
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/companyDeviceModel/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getCompanyDeviceModelList(HttpServletRequest request,
			HttpServletResponse response, CompanyDeviceModelQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = companyDeviceModelService.getCompanyDeviceModelList(form);
		} catch (Exception e) {
			log.error("getCompanyDeviceModelList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的公司型号信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午2:41:51
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/companyDeviceModel/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllCompanyDeviceModelList(HttpServletRequest request,
			HttpServletResponse response, CompanyDeviceModelQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = companyDeviceModelService
					.getAllCompanyDeviceModelList(form);
		} catch (Exception e) {
			log.error("getAllCompanyDeviceModelList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
