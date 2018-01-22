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
import com.jy.access.service.UserRolesService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.UserRolesQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/sys")
public class UserRolesController extends CommonController {

	private static final Logger log = Logger
			.getLogger(UserRolesController.class);
	@Autowired
	private UserRolesService userRolesService;

	/**
	 * 增加用户角色关联
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午9:21:00
	 * @param request
	 * @param response
	 * @param session
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/userRoles", method = { RequestMethod.POST })
	@ResponseBody
	public String addUserRoles(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addUserRoles") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addUserRoles", "addUserRoles");
			jsonStr = userRolesService.addUserRoles(data);
		} catch (Exception e) {
			log.error("addUserRoles ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addUserRoles", null);
		return jsonStr;
	}

	/**
	 * 删除用户角色关联
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午9:21:18
	 * @param request
	 * @param response
	 * @param userRolesId
	 * @return
	 */
	@RequestMapping(value = "/delete/userRoles/{userRolesId}", method = { RequestMethod.GET })
	@ResponseBody
	public String deleteUserPermissions(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "userRolesId") String userRolesId) {
		String jsonStr = userRolesService.deleteUserRoles(userRolesId);
		return jsonStr;
	}

	/**
	 * 获取分页的用户角色关联信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-31 上午11:40:22
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/userRoles/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getUserRolesList(HttpServletRequest request,
			HttpServletResponse response, UserRolesQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = userRolesService.getUserRolesList(form);

		} catch (Exception e) {
			log.error("getUserRolesList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的用户角色关联信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-31 上午11:40:47
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/userRoles/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllUserRolesList(HttpServletRequest request,
			HttpServletResponse response, UserRolesQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = userRolesService.getAllUserRolesList(form);
		} catch (Exception e) {
			log.error("getAllUserRolesList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取分页的用户配置用户角色关联信息列表
	 * 
	 * @author liukh
	 * @date 2017-9-6 上午9:40:00
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/userRolesConfig/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getUserRolesConfigList(HttpServletRequest request,
			HttpServletResponse response, UserRolesQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = userRolesService.getUserRolesConfigList(form);

		} catch (Exception e) {
			log.error("getUserRolesConfigList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的用户配置用户角色关联信息列表
	 * 
	 * @author liukh
	 * @date 2017-9-6 上午9:40:35
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/userRolesConfig/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllUserRolesConfigList(HttpServletRequest request,
			HttpServletResponse response, UserRolesQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = userRolesService.getAllUserRolesConfigList(form);
		} catch (Exception e) {
			log.error("getAllUserRolesConfigList ---- 异常,message = "
					+ e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
