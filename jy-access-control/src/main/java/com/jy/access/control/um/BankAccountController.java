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
import com.jy.access.service.BankAccountService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.BankAccountQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/um")
public class BankAccountController extends CommonController {

	private static final Logger log = Logger
			.getLogger(BankAccountController.class);
	@Autowired
	private BankAccountService bankAccountService;

	/**
	 * 新增银行账户信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午2:54:19
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/bankAccount", method = { RequestMethod.POST })
	@ResponseBody
	public String addBankAccount(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addBankAccount") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addBankAccount", "addBankAccount");
			jsonStr = bankAccountService.addBankAccount(data);
		} catch (Exception e) {
			log.error("addBankAccount ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addBankAccount", null);
		return jsonStr;
	}

	/**
	 * 获取银行账号信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午2:54:55
	 * @param request
	 * @param response
	 * @param bankAccountId
	 * @return
	 */
	@RequestMapping(value = "/bankAccount/{bankAccountId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getBankAccount(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "bankAccountId") String bankAccountId) {
		String jsonStr = bankAccountService.getBankAccount(bankAccountId);
		return jsonStr;
	}

	/**
	 * 更新银行账号信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午2:56:32
	 * @param bankAccountId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bankAccount/{bankAccountId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateBankAccount(
			@PathVariable(value = "bankAccountId") String bankAccountId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = bankAccountService.updateBankAccount(bankAccountId,
				data);
		return jsonStr;
	}

	/**
	 * 获取分页的银行账户信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:00:15
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/bankAccount/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getBankAccountList(HttpServletRequest request,
			HttpServletResponse response, BankAccountQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = bankAccountService.getBankAccountList(form);
		} catch (Exception e) {
			log.error("getBankAccountList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的银行账号信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:03:19
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/bankAccount/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllBankAccountList(HttpServletRequest request,
			HttpServletResponse response, BankAccountQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = bankAccountService.getAllBankAccountList(form);
		} catch (Exception e) {
			log.error("getAllBankAccountList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
