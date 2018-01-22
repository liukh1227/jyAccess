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
import com.jy.access.service.DeliveryCarService;
import com.jy.access.service.DeliveryDeviceService;
import com.jy.access.service.DeliveryOrderService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.DeliveryOrderQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/tm")
public class DeliveryOrderController extends CommonController {

	private static final Logger log = Logger.getLogger(DeliveryOrderController.class);
	@Autowired
	private DeliveryOrderService deliveryOrderService;
	@Autowired
	private DeliveryCarService deliveryCarService;
	@Autowired
	private DeliveryDeviceService deliveryDeviceService;

	/**
	 * 新增进出库信息
	 * deliveryType I 进库 O 出库
	 * @author liukh
	 * @date 2017-7-24 下午3:11:13
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/deliveryOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String addDeliveryOrder(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDeliveryOrder") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDeliveryOrder", "addDeliveryOrder");
			jsonStr = deliveryOrderService.addDeliveryOrder(data);
		} catch (Exception e) {
			log.error("addDeliveryOrder ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDeliveryOrder", null);
		return jsonStr;
	}
	/**
	 * 新增进出库信息及送货车辆，进出库设备信息
	 * @author liukh
	 * @date 2017-7-24 下午3:11:50
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/deliveryOrderAndCarAndDevice", method = { RequestMethod.POST })
	@ResponseBody
	public String addDeliveryOrderAndCarAndDevice(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addDeliveryOrderAndCarAndDevice") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addDeliveryOrderAndCarAndDevice", "addDeliveryOrderAndCarAndDevice");
			jsonStr = deliveryOrderService.addDeliveryOrderAndCarAndDevice(data);
		} catch (Exception e) {
			log.error("addDeliveryOrderAndCarAndDevice ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addDeliveryOrderAndCarAndDevice", null);
		return jsonStr;
	}

/**
 * 修改进出库信息
 * @author liukh
 * @date 2017-7-24 下午3:12:39
 * @param deliveryOrderId
 * @param data
 * @param request
 * @param response
 * @return
 */
	@RequestMapping(value = "/deliveryOrder/{deliveryOrderId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateDeliveryOrder(
			
			@PathVariable(value = "deliveryOrderId") String deliveryOrderId, 
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr =  deliveryOrderService.updateDeliveryOrder(deliveryOrderId, data);
		} catch (Exception e) {
			log.error("updateDeliveryOrder ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}
	
/**
 * 获取进出库信息及送货车辆，进出库设备信息
 * @author liukh
 * @date 2017-7-24 下午3:12:57
 * @param request
 * @param response
 * @param deliveryOrderId
 * @return
 */
		
		@RequestMapping(value = "/deliveryOrder/{deliveryOrderId}", method = { RequestMethod.GET })
		@ResponseBody
		public String getDeliveryOrder(HttpServletRequest request,
				HttpServletResponse response,
				@PathVariable(value = "deliveryOrderId") String deliveryOrderId) {
			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				jsonStr =  deliveryOrderService.getDeliveryOrder(deliveryOrderId);
			} catch (Exception e) {
				log.error("getDeliveryOrder ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			return jsonStr;
		}

	/**
	 * 获取分页的进出库信息列表
	 * @author liukh
	 * @date 2017-7-24 下午3:13:19
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
		@RequestMapping(value = "/deliveryOrder/list", method = { RequestMethod.GET })
		@ResponseBody
		public String getDeliveryOrderList(HttpServletRequest request,
				HttpServletResponse response, DeliveryOrderQueryForm  form) {
			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				jsonStr = deliveryOrderService.getDeliveryOrderList(form);
			} catch (Exception e) {
				log.error("getDeliveryOrderList ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			return jsonStr;

		}

/**
 * 获取所有的进出库信息列表
 * @author liukh
 * @date 2017-7-24 下午3:13:33
 * @param request
 * @param response
 * @param form
 * @return
 */
		@RequestMapping(value = "/deliveryOrder/allList", method = { RequestMethod.GET })
		@ResponseBody
		public String getAllDeliveryOrderList(HttpServletRequest request,
				HttpServletResponse response, DeliveryOrderQueryForm form) {

			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				jsonStr = deliveryOrderService.getAllDeliveryOrderList(form);
			} catch (Exception e) {
				log.error("getAllDeliveryOrderList ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			return jsonStr;

		}
		
		
		/**
		 * 新增进出库设备信息
		 * @author liukh
		 * @date 2017-7-24 下午3:13:51
		 * @param request
		 * @param response
		 * @param data
		 * @return
		 */
		@RequestMapping(value = "/deliveryDevice", method = { RequestMethod.POST })
		@ResponseBody
		public String addDeliveryDevice(
				HttpServletRequest request,HttpSession session,
				HttpServletResponse response,
				@RequestParam(value = "data", required = true) String data) {
			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				if (session.getAttribute("addDeliveryDevice") != null) {
					BaseDto dto = new BaseDto();
					dto.setRcode(BaseDto.EXIST_RCODE);
					dto.setRinfo("请求已提交，请耐心等待!");
					return JSON.toJSONString(dto);
				}
				session.setAttribute("addDeliveryDevice", "addDeliveryDevice");
				jsonStr = deliveryDeviceService.addDeliveryDevice(data);
			} catch (Exception e) {
				log.error("addDeliveryOrder ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			session.setAttribute("addDeliveryDevice", null);
			return jsonStr;
		}

		/**
		 * 删除进出库设备信息
		 * @author liukh
		 * @date 2017-7-24 下午3:14:08
		 * @param request
		 * @param response
		 * @param deliveryDeviceId
		 * @return
		 */
		@RequestMapping(value = "/delete/deliveryDevice/{deliveryDeviceId}", method = { RequestMethod.GET })
		@ResponseBody
		public String deleteDeliveryDevice(
				HttpServletRequest request,
				HttpServletResponse response,
				@PathVariable(value = "deliveryDeviceId") String deliveryDeviceId) {
			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				jsonStr =  deliveryDeviceService.deleteDeliveryDevice(deliveryDeviceId);
			} catch (Exception e) {
				log.error("deleteDeliveryDevice ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			return jsonStr;
			
		}
		
		/**
		 * 新增进出库送货车辆信息
		 * @author liukh
		 * @date 2017-7-24 下午3:14:20
		 * @param request
		 * @param response
		 * @param data
		 * @return
		 */
		@RequestMapping(value = "/deliveryCar", method = { RequestMethod.POST })
		@ResponseBody
		public String addDeliveryCar(
				HttpServletRequest request,
				HttpServletResponse response,HttpSession session,
				@RequestParam(value = "data", required = true) String data) {
			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				if (session.getAttribute("addDeliveryCar") != null) {
					BaseDto dto = new BaseDto();
					dto.setRcode(BaseDto.EXIST_RCODE);
					dto.setRinfo("请求已提交，请耐心等待!");
					return JSON.toJSONString(dto);
				}
				session.setAttribute("addDeliveryCar", "addDeliveryCar");
				jsonStr = deliveryCarService.addDeliveryCar(data);
			} catch (Exception e) {
				log.error("addDeliveryCar ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			session.setAttribute("addDeliveryCar", null);
			return jsonStr;
		}
		/**
		 * 删除进出库送货车辆信息
		 * @author liukh
		 * @date 2017-7-24 下午3:14:36
		 * @param request
		 * @param response
		 * @param deliveryCarId
		 * @return
		 */
		@RequestMapping(value = "/delete/deliveryCar/{deliveryCarId}", method = { RequestMethod.GET })
		@ResponseBody
		public String deleteDeliveryCar(
				HttpServletRequest request,
				HttpServletResponse response,
				@PathVariable(value = "deliveryCarId") String deliveryCarId) {
			String jsonStr = JSON.toJSONString(new BaseDto());
			try {
				jsonStr =  deliveryCarService.deleteDeliveryCar(deliveryCarId);
			} catch (Exception e) {
				log.error("deleteDeliveryCar ---- 异常,message = " + e.getMessage());
				e.printStackTrace();
			}
			return jsonStr;
			
		}
	

}


