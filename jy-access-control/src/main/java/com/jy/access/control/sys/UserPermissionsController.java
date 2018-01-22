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
import com.jy.access.service.UserPermissionsService;
import com.jy.base.entity.base.dto.BaseDto;


@Controller
@Scope("prototype")
@RequestMapping(value = "/sys")
public class UserPermissionsController extends CommonController {

	private static final Logger log = Logger.getLogger(UserPermissionsController.class);
	@Autowired
   private UserPermissionsService userPermissionsService;
	
/**
 * 新增用户权限关联
 * @author liukh
 * @date 2017-8-30 下午9:21:35
 * @param request
 * @param response
 * @param session
 * @param data
 * @return
 */
	@RequestMapping(value = "/userPermissions", method = { RequestMethod.POST })
	@ResponseBody
	public String addUserPermissions(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addUserPermissions") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addUserPermissions", "addUserPermissions");
			jsonStr = userPermissionsService.addUserPermissions(data);
		} catch (Exception e) {
			log.error("addUserPermissions ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addUserPermissions", null);
		return jsonStr;
	}
	
	/**
	 * 删除用户权限关联
	 * @author liukh
	 * @date 2017-8-30 下午9:22:00
	 * @param request
	 * @param response
	 * @param userPermissionsId
	 * @return
	 */
	@RequestMapping(value = "/delete/userPermissions/{userPermissionsId}", method = { RequestMethod.GET })
	@ResponseBody
	public String deleteUserPermissions(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "userPermissionsId") String userPermissionsId) {
		String jsonStr = userPermissionsService.deleteUserPermissions(userPermissionsId);
		return jsonStr;
	}
	
	
}
