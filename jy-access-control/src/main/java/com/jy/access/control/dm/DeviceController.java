package com.jy.access.control.dm;

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
import com.jy.access.service.DeviceService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DeviceQueryForm;




@Controller
@Scope("prototype")
@RequestMapping(value = "/dm")
public class DeviceController extends CommonController {

	private static final Logger log = Logger.getLogger(DeviceController.class);
	@Autowired
	private DeviceService deviceService;

	/**
	 * 新增设备信息
	 * @author liukh
	 * @date 2017-7-18 下午2:44:24
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/device", method = { RequestMethod.POST })
	@ResponseBody
	public String addDevice(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDevice") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDevice", "addDevice");
			jsonStr = deviceService.addDevice(data);
		} catch (Exception e) {
			log.error("addDevice ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDevice", null);
		return jsonStr;
	}

/**
 * 获取设备信息
 * @author liukh
 * @date 2017-7-18 下午2:45:15
 * @param request
 * @param response
 * @param deviceId
 * @return
 */
	@RequestMapping(value = "/device/{deviceId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getDevice(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "deviceId") String deviceId) {
		String jsonStr = deviceService.getDevice(deviceId);
		return jsonStr;
	}

/**
 * 修改设备信息
 * @author liukh
 * @date 2017-7-18 下午2:45:33
 * @param deviceId
 * @param data
 * @param request
 * @param response
 * @return
 */
	@RequestMapping(value = "/device/{deviceId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDevice(
			@PathVariable(value = "deviceId") String deviceId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = deviceService.updateDevice(deviceId, data);
		return jsonStr;
	}
	/**
	 * 
	 * @author liukh
	 * @date 2017-8-28 下午9:14:41
	 * @param deviceId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/device/allot/{deviceId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDevieAllot(
			@PathVariable(value = "deviceId") String deviceId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = deviceService.updateDevieAllot(deviceId, data);
		return jsonStr;
	}

/**
 * 获取分页的设备信息列表
 * @author liukh
 * @date 2017-7-18 下午2:45:52
 * @param request
 * @param response
 * @param form
 * @return
 */
	@RequestMapping(value = "/device/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceList(HttpServletRequest request,
			HttpServletResponse response, DeviceQueryForm form) {
		BaseDto baseDto = new BaseDto();
		String jsonStr = JSON.toJSONString(baseDto);
	
		try {
			if(form == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数不能为空");
				return JSON.toJSONString(baseDto);
			}else if(form.getCompanyId() == null){
				
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数CompanyId不能为空");
				return JSON.toJSONString(baseDto);
			}
			jsonStr = deviceService.getDeviceList(form);
		} catch (Exception e) {
			log.error("getDeviceList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}

/**
 * 获取所有的设备信息列表
 * @author liukh
 * @date 2017-7-18 下午2:46:17
 * @param request
 * @param response
 * @param form
 * @return
 */
	@RequestMapping(value = "/device/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllDeviceList(HttpServletRequest request,
			HttpServletResponse response, DeviceQueryForm form) {

		BaseDto baseDto = new BaseDto();
		String jsonStr = JSON.toJSONString(baseDto);
		try {
			if(form == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数不能为空");
				return JSON.toJSONString(baseDto);
			}else if(form.getCompanyId() == null){
				
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数CompanyId不能为空");
				return JSON.toJSONString(baseDto);
			}
			jsonStr = deviceService.getAllDeviceList(form);
		} catch (Exception e) {
			log.error("getAllDeviceList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	/**
	 * 获取设备库存统计列表
	 * @author liukh
	 * @date 2017-7-18 下午2:51:58
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/device/statisticsList", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceStatisticsList(HttpServletRequest request,
			HttpServletResponse response, DeviceQueryForm form) {

		BaseDto baseDto = new BaseDto();
		String jsonStr = JSON.toJSONString(baseDto);
		try {
			if(form == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数不能为空");
				return JSON.toJSONString(baseDto);
			}else if(form.getCompanyId() == null){
				
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数CompanyId不能为空");
				return JSON.toJSONString(baseDto);
			}
			jsonStr = deviceService.getDeviceStatisticsList(form);
		} catch (Exception e) {
			log.error("getAllDeviceList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	/**
	 * 获取按照公司划分的设备库存统计列表
	 * @author liukh
	 * @date 2017-7-18 下午2:51:18
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/device/companyStatisticsList", method = { RequestMethod.GET })
	@ResponseBody
	public String getDeviceGroupByCompanyStatisticsList(HttpServletRequest request,
			HttpServletResponse response, DeviceQueryForm form) {
		
		BaseDto baseDto = new BaseDto();
		String jsonStr = JSON.toJSONString(baseDto);
		try {
			if(form == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数不能为空");
				return JSON.toJSONString(baseDto);
			}else if(form.getCompanyId() == null){
				
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数CompanyId不能为空");
				return JSON.toJSONString(baseDto);
			}
			jsonStr = deviceService.getDeviceGroupByCompanyStatisticsList(form);
		} catch (Exception e) {
			log.error("getAllDeviceList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	
	
}
