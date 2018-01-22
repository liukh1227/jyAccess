package com.jy.access.control.sys;

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
import com.jy.access.service.UserAccountServie;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.UserAccountQueryForm;


@Controller
@Scope("prototype")
@RequestMapping(value = "/sys")
public class UserAccountController extends CommonController {

	private static final Logger log = Logger.getLogger(UserAccountController.class);
	@Autowired
   private UserAccountServie userAccountServie;
	
	/**
	 * 新增用户账号
	 * @author liukh
	 * @date 2017-8-7 下午6:11:42
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/userAccount", method = { RequestMethod.POST })
	@ResponseBody
	public String addUserAccount(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addUserAccount") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addUserAccount", "addUserAccount");
			jsonStr = userAccountServie.addUserAccount(data);
		} catch (Exception e) {
			log.error("addUserAccount ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addUserAccount", null);
		return jsonStr;
	}
	@RequestMapping(value = "/userAccount/cellPhone/validCode", method = { RequestMethod.POST })
	@ResponseBody
	public String getValidCode(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("getValidCode") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("getValidCode", "getValidCode");
			jsonStr = userAccountServie.addMessageHistoryAndGetValidCode(data);
		} catch (Exception e) {
			log.error("getValidCode ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		log.info("getValidCode ---- jsonStr ========= " + jsonStr);
		session.setAttribute("getValidCode", null);
		return jsonStr;
	}
	
	@RequestMapping(value = "/userAccount/{userAccountId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateUserAccount(
			@PathVariable(value = "userAccountId") String userAccountId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = userAccountServie.updateUserAccount(userAccountId, data);
		return jsonStr;
	}
	
	@RequestMapping(value = "/userAccount/password/{userAccountId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateUserAccountPassword(
			@PathVariable(value = "userAccountId") String userAccountId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = userAccountServie.updatePassword(userAccountId, data);
		return jsonStr;
	}
	
	@RequestMapping(value = "/userAccount/reset/password", method = { RequestMethod.POST })
	@ResponseBody
	public String updateResetUserAccountPassword(
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = userAccountServie.updatePasswordReset(data);
		return jsonStr;
	}
	
	
	@RequestMapping(value = "/login/userAccount", method = { RequestMethod.POST })
	@ResponseBody
	public String UserAccountLogin(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("UserAccountLogin") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("UserAccountLogin", "UserAccountLogin");
			jsonStr = userAccountServie.getUserAccountDetailInfor(data);
		} catch (Exception e) {
			log.error("UserAccountLogin ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		log.info("UserAccountLogin ---- jsonStr ========= " + jsonStr);
		session.setAttribute("UserAccountLogin", null);
		return jsonStr;
	}
	
	
	/**
	 * 获取分页的账号信息列表
	 * @author liukh
	 * @date 2017-8-7 下午6:13:33
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/userAccount/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getUserAccountList(HttpServletRequest request,
			HttpServletResponse response, UserAccountQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = userAccountServie.getUserAccountList(form);
		} catch (Exception e) {
			log.error("getUserAccountList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	/**
	 * 获取全部用户账号信息列表
	 * @author liukh
	 * @date 2017-8-7 下午6:14:22
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/userAccount/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllUserAccountList(HttpServletRequest request,
			HttpServletResponse response, UserAccountQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = userAccountServie.getAllUserAccountList(form);
		} catch (Exception e) {
			log.error("getAllUserAccountList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	
	@RequestMapping(value = "/userAccount/noConfigRole/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllNoConfigRoleUserAccountList(HttpServletRequest request,
			HttpServletResponse response, UserAccountQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = userAccountServie.getAllNoConfigRoleUserAccountList(form);
		} catch (Exception e) {
			log.error("getAllNoConfigRoleUserAccountList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	
	
	
	
}
