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
import com.jy.access.service.RoleService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.RoleQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/sys")
public class RoleController extends CommonController {

	private static final Logger log = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;

	/**
	 * 新增角色信息
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午6:17:33
	 * @param request
	 * @param response
	 * @param session
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/role", method = { RequestMethod.POST })
	@ResponseBody
	public String addRole(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addRole") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addRole", "addRole");
			jsonStr = roleService.addRole(data);
		} catch (Exception e) {
			log.error("addRole ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addRole", null);
		return jsonStr;
	}

	/**
	 * 修改角色信息
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午6:17:49
	 * @param roleId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/role/{roleId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateRole(@PathVariable(value = "roleId") String roleId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = roleService.updateRole(roleId, data);

		return jsonStr;
	}

	/**
	 * 获取权限信息
	 * 
	 * @author liukh
	 * @date 2017-8-31 上午11:59:34
	 * @param roleId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/role/{roleId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getRole(@PathVariable(value = "roleId") String roleId,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = roleService.getRole(roleId);

		return jsonStr;
	}

	/**
	 * 获取分页的角色信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午6:18:00
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/role/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getRoleList(HttpServletRequest request,
			HttpServletResponse response, RoleQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = roleService.getRoleList(form);
		} catch (Exception e) {
			log.error("getRoleList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的角色信息列表
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午6:18:22
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/role/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllRoleList(HttpServletRequest request,
			HttpServletResponse response, RoleQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = roleService.getAllRoleList(form);
		} catch (Exception e) {
			log.error("getAllRoleList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取分页的配置角色信息列表
	 * 
	 * @author liukh
	 * @date 2017-9-6 上午9:20:29
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getRoleConfigPageList(HttpServletRequest request,
			HttpServletResponse response, RoleQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = roleService.getRoleConfigList(form);
		} catch (Exception e) {
			log.error("getRoleConfigPageList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的配置的角色信息列表
	 * 
	 * @author liukh
	 * @date 2017-9-6 上午9:22:11
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllRoleConfigList(HttpServletRequest request,
			HttpServletResponse response, RoleQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = roleService.getAllRoleConfigList(form);
		} catch (Exception e) {
			log.error("getAllRoleConfigList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

}
