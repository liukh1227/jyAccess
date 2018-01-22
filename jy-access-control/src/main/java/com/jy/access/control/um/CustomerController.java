package com.jy.access.control.um;

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
import com.jy.access.service.CustomerService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.CustomerQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/um")
public class CustomerController extends CommonController {

	private static final Logger log = Logger
			.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;

	/**
	 * 新增客户信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:10:24
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/customer", method = { RequestMethod.POST })
	@ResponseBody
	public String addCustomer(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addCustomer") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addCustomer", "addCustomer");
			jsonStr = customerService.addCustomer(data);
		} catch (Exception e) {
			log.error("addCustomer ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addCustomer", null);
		return jsonStr;
	}

	/**
	 * 获取客户信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:11:07
	 * @param request
	 * @param response
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = "/customer/{customerId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getCustomer(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "customerId") String customerId) {
		String jsonStr = customerService.getCustomer(customerId);
		return jsonStr;
	}
	/**
	 * 获取客户信息及客户关联的银行账户信息
	 * @author liukh
	 * @date 2017-8-3 下午10:09:01
	 * @param request
	 * @param response
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = "/customerAndBankAccountList/{customerId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getCustomerAndBankAccountList(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "customerId") String customerId) {
		String jsonStr = customerService.getCustomerAndBankAccountList(customerId);
		return jsonStr;
	}

	/**
	 * 更新客户信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:11:47
	 * @param customerId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/customer/{customerId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateCustomer(
			@PathVariable(value = "customerId") String customerId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = customerService.updateCustomer(customerId, data);
		return jsonStr;
	}

	/**
	 * 获取分页的客户信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:12:29
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/customer/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getCustomerList(HttpServletRequest request,
			HttpServletResponse response, CustomerQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = customerService.getCustomerList(form);
		} catch (Exception e) {
			log.error("getCustomerList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	@RequestMapping(value = "/customerListAndBankAccountList/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getCustomerAndBankAccountPageList(HttpServletRequest request,
			HttpServletResponse response, CustomerQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = customerService.getCustomerAndBankAccountPageList(form);
		} catch (Exception e) {
			log.error("getCustomerAndBankAccountPageList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}

	/**
	 * 获取所有的客户信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:12:49
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/customer/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllCustomerList(HttpServletRequest request,
			HttpServletResponse response, CustomerQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = customerService.getAllCustomerList(form);
		} catch (Exception e) {
			log.error("getAllCustomerList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
