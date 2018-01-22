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
import com.jy.access.service.ContractDeviceModelService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.ContractDeviceModelQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class ContractDeviceModelController extends CommonController {

	private static final Logger log = Logger
			.getLogger(ContractDeviceModelController.class);
	@Autowired
	private ContractDeviceModelService contractDeviceModelService;

	/**
	 * 新增签约设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-8-3 上午11:40:30
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/contractDeviceModel", method = { RequestMethod.POST })
	@ResponseBody
	public String addContractDeviceModel(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addContractDeviceModel") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addContractDeviceModel",
					"addContractDeviceModel");
			jsonStr = contractDeviceModelService.addContractDeviceModel(data);
		} catch (Exception e) {
			log.error("addContractDeviceModel ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addContractDeviceModel", null);
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

	@RequestMapping(value = "/delete/contractDeviceModel/{contractDeviceModelId}", method = { RequestMethod.GET })
	@ResponseBody
	public String deleteContractDeviceModel(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "contractDeviceModelId") String contractDeviceModelId) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = contractDeviceModelService
					.deleteContractDeviceModel(contractDeviceModelId);
		} catch (Exception e) {
			log.error("deleteContractDeviceModel ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 修改签约设备型号信息
	 * 
	 * @author liukh
	 * @date 2017-8-3 上午11:41:57
	 * @param contractDeviceModelId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/contractDeviceModel/{contractDeviceModelId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateContractDeviceModel(

			@PathVariable(value = "contractDeviceModelId") String contractDeviceModelId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = contractDeviceModelService.updateContractDeviceModel(
					contractDeviceModelId, data);
		} catch (Exception e) {
			log.error("updateContractDeviceModel ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取签约设备型号详细信息
	 * 
	 * @author liukh
	 * @date 2017-8-3 上午11:43:20
	 * @param request
	 * @param response
	 * @param contractDeviceModelId
	 * @return
	 */
	@RequestMapping(value = "/contractDeviceModel/{contractDeviceModelId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getContractDeviceModel(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "contractDeviceModelId") String contractDeviceModelId) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = contractDeviceModelService
					.getContractDeviceModel(contractDeviceModelId);
		} catch (Exception e) {
			log.error("getContractDeviceModel ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 获取所有的签约设备型号信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-21 上午11:06:30
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/contractDeviceModel/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllContractDeviceModelList(HttpServletRequest request,
			HttpServletResponse response, ContractDeviceModelQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = contractDeviceModelService
					.getAllContractDeviceModelList(form);
		} catch (Exception e) {
			log.error("getAllContractDeviceModelList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
}
