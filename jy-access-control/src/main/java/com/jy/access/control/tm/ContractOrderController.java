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
import com.jy.access.service.ContractOrderService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.ContractOrderQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class ContractOrderController extends CommonController {

	private static final Logger log = Logger.getLogger(ContractOrderController.class);
	@Autowired
	private ContractOrderService contractOrderService;

/**
 * 新增签约单
 * @author liukh
 * @date 2017-7-21 上午10:14:47
 * @param request
 * @param response
 * @param data
 * @return
 */
	@RequestMapping(value = "/contractOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String addContractOrder(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addContractOrder") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addContractOrder", "addContractOrder");
			jsonStr = contractOrderService.addContractOrder(data);
		} catch (Exception e) {
			log.error("addContractOrder ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addContractOrder", null);
		return jsonStr;
	}
	
	/**
	 * 新增签约单及签约设备型号信息
	 * @author liukh
	 * @date 2017-7-21 上午10:16:43
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/contractOrderAndContractDeviceModel", method = { RequestMethod.POST })
	@ResponseBody
	public String addContractOrderAndContractDeviceModel(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addContractOrderAndContractDeviceModel") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addContractOrderAndContractDeviceModel", "addContractOrderAndContractDeviceModel");
			jsonStr = contractOrderService.addContractOrderAndContractDeviceModel(data);
		} catch (Exception e) {
			log.error("addContractOrderAndContractDeviceModel ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addContractOrderAndContractDeviceModel", null);
		return jsonStr;
	}
	
	/**
	 * 从报价单中生成签约单及签约设备信息
	 * @author liukh
	 * @date 2017-8-11 上午11:33:05
	 * @param request
	 * @param response
	 * @param session
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/contractOrderAndContractDeviceModelFromQuotation", method = { RequestMethod.POST })
	@ResponseBody
	public String addContractOrderAndContractDeviceModelFromQuotation(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addContractOrderAndContractDeviceModelFromQuotation") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addContractOrderAndContractDeviceModelFromQuotation", "addContractOrderAndContractDeviceModelFromQuotation");
			jsonStr = contractOrderService.addContractOrderAndContractDeviceModelFromQuotation(data);
		} catch (Exception e) {
			log.error("addContractOrderAndContractDeviceModelFromQuotation ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addContractOrderAndContractDeviceModelFromQuotation", null);
		return jsonStr;
	}
	
	
	/**
	 * 修改签约单信息
	 * @author liukh
	 * @date 2017-8-3 上午11:38:07
	 * @param contractOrderId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = "/contractOrder/{contractOrderId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateContractOrder(
			
			@PathVariable(value = "contractOrderId") String contractOrderId, 
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr =  contractOrderService.updateContractOrder(contractOrderId, data);
		} catch (Exception e) {
			log.error("updateContractOrder ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}

/**
 * 更新签约单及签约设备型号信息
 * @author liukh
 * @date 2017-7-21 上午10:57:14
 * @param contractOrderId
 * @param data
 * @param request
 * @param response
 * @return
 */
	@RequestMapping(value = "/contractOrderAndContractDeviceModel/{contractOrderId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateContractOrderAndContractDeviceModel(
			
			@PathVariable(value = "contractOrderId") String contractOrderId, 
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr =  contractOrderService.updateContractOrderAndContractDeviceModel(contractOrderId, data);
		} catch (Exception e) {
			log.error("updateContractOrderAndContractDeviceModel ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	/**
	 * 获取签约单信息
	 * @author liukh
	 * @date 2017-7-21 上午11:00:35
	 * @param request
	 * @param response
	 * @param contractOrderId
	 * @return
	 */
		
		@RequestMapping(value = "/contractOrder/{contractOrderId}", method = { RequestMethod.GET })
		@ResponseBody
		public String getContractOrder(HttpServletRequest request,
				HttpServletResponse response,
				@PathVariable(value = "contractOrderId") String contractOrderId) {
			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				jsonStr =  contractOrderService.getContractOrder(contractOrderId);
			} catch (Exception e) {
				log.error("getContractOrder ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			return jsonStr;
		}
	/**
	 * 获取签约单的详细信息
	 * @author liukh
	 * @date 2017-7-21 上午11:03:24
	 * @param request
	 * @param response
	 * @param contractOrderId
	 * @return
	 */
		@RequestMapping(value = "/contractOrderAndContractDeviceModel/{contractOrderId}", method = { RequestMethod.GET })
		@ResponseBody
		public String getContractOrderAndContractDeviceModel(HttpServletRequest request,
				HttpServletResponse response,
				@PathVariable(value = "contractOrderId") String contractOrderId) {
			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				jsonStr =  contractOrderService.getContractOrderAndContractDeviceModel(contractOrderId);
			} catch (Exception e) {
				log.error("getContractOrderAndContractDeviceModel ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			return jsonStr;
			
		}

/**
 * 获取分页的签约单信息列表
 * @author liukh
 * @date 2017-7-21 上午11:04:45
 * @param request
 * @param response
 * @param form
 * @return
 */
		@RequestMapping(value = "/contractOrder/list", method = { RequestMethod.GET })
		@ResponseBody
		public String getContractOrderList(HttpServletRequest request,
				HttpServletResponse response, ContractOrderQueryForm  form) {
			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				jsonStr = contractOrderService.getContractOrderList(form);
			} catch (Exception e) {
				log.error("getContractOrderList ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			return jsonStr;

		}

}
