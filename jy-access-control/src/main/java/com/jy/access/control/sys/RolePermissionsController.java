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
import com.jy.access.service.RolePermissionsService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.RolePermissionsQueryForm;


@Controller
@Scope("prototype")
@RequestMapping(value = "/sys")
public class RolePermissionsController extends CommonController {

	private static final Logger log = Logger.getLogger(RolePermissionsController.class);
	@Autowired
   private RolePermissionsService rolePermissionsService;
	
/**
 * 新增权限角色关联
 * @author liukh
 * @date 2017-8-30 下午9:22:22
 * @param request
 * @param response
 * @param session
 * @param data
 * @return
 */
	@RequestMapping(value = "/rolePermissions", method = { RequestMethod.POST })
	@ResponseBody
	public String addRolePermissions(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addRolePermissions") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addRolePermissions", "addRolePermissions");
			jsonStr = rolePermissionsService.addRolePermissions(data);
		} catch (Exception e) {
			log.error("addRolePermissions ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addRolePermissions", null);
		return jsonStr;
	}
	
	/**
	 * 删除权限角色关联
	 * @author liukh
	 * @date 2017-8-30 下午9:22:40
	 * @param request
	 * @param response
	 * @param rolePermissionsId
	 * @return
	 */
	@RequestMapping(value = "/delete/rolePermissions/{rolePermissionsId}", method = { RequestMethod.GET })
	@ResponseBody
	public String deleteRolePermissions(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "rolePermissionsId") String rolePermissionsId) {
		String jsonStr = rolePermissionsService.deleteRolePermissions(rolePermissionsId);
		return jsonStr;
	}
	
	/**
	 * 获取分页的权限角色关联信息列表
	 * @author liukh
	 * @date 2017-8-31 上午11:40:22
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/rolePermissions/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getRolePermissionsList(HttpServletRequest request,
			HttpServletResponse response, RolePermissionsQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = rolePermissionsService.getRolePermissionsList(form);
		} catch (Exception e) {
			log.error("getRolePermissionsList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

/**
 * 获取所有的权限角色关联信息列表
 * @author liukh
 * @date 2017-8-31 上午11:40:47
 * @param request
 * @param response
 * @param form
 * @return
 */
	@RequestMapping(value = "/rolePermissions/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllRolePermissionsList(HttpServletRequest request,
			HttpServletResponse response, RolePermissionsQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = rolePermissionsService.getAllRolePermissionsList(form);
		} catch (Exception e) {
			log.error("getAllRolePermissionsList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	
	
}
