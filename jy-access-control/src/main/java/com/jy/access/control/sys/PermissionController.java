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
import com.jy.access.service.PermissionService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.PermissionQueryForm;


@Controller
@Scope("prototype")
@RequestMapping(value = "/sys")
public class PermissionController extends CommonController {

	private static final Logger log = Logger.getLogger(PermissionController.class);
	@Autowired
	 private PermissionService permissionService;
	
	/**
	 * 新增权限信息
	 * @author liukh
	 * @date 2017-8-30 下午6:19:21
	 * @param request
	 * @param response
	 * @param session
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/permission", method = { RequestMethod.POST })
	@ResponseBody
	public String addPermission(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addPermission") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addPermission", "addPermission");
			jsonStr = permissionService.addPermission(data);
		} catch (Exception e) {
			log.error("addPermission ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addPermission", null);
		return jsonStr;
	}
	
	/**
	 * 修改权限信息
	 * @author liukh
	 * @date 2017-8-30 下午6:19:32
	 * @param permissionId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/permission/{permissionId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updatePermission(
			@PathVariable(value = "permissionId") String permissionId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = permissionService.updatePermission(permissionId, data);
			
		return jsonStr;
	}
	
/**
 * 获取分页的权限信息列表
 * @author liukh
 * @date 2017-8-30 下午6:19:43
 * @param request
 * @param response
 * @param form
 * @return
 */
	@RequestMapping(value = "/permission/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getPermissionList(HttpServletRequest request,
			HttpServletResponse response, PermissionQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = permissionService.getPermissionList(form);
		} catch (Exception e) {
			log.error("getPermissionList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

	/**
	 * 获取所有的权限信息列表
	 * @author liukh
	 * @date 2017-8-30 下午6:20:04
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/permission/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllPermissionList(HttpServletRequest request,
			HttpServletResponse response, PermissionQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = permissionService.getAllPermissionList(form);
		} catch (Exception e) {
			log.error("getAllPermissionList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	@RequestMapping(value = "/userAccountVist/permission/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllPermissionListByUserAccountId(HttpServletRequest request,
			HttpServletResponse response, PermissionQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = permissionService.getAllPermissionListByUserAccountId(form);
		} catch (Exception e) {
			log.error("getAllPermissionListByUserAccountId ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	
	

}
