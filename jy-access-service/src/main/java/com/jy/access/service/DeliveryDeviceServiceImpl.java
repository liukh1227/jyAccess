package com.jy.access.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.DeliveryDeviceMapper;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.po.DeliveryDevice;
import com.jy.entity.pojo.SuccessReturnPojo;

@Service("deliveryDeviceService")
@Scope("prototype")
public class DeliveryDeviceServiceImpl implements DeliveryDeviceService {
	private static final Logger log = Logger.getLogger(DeliveryDeviceServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeliveryOrderService deliveryOrderService;
	
	@Override
	public String addDeliveryDevice(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeliveryDevice deliveryDevice = JSON.parseObject(data,DeliveryDevice.class);
				if(deliveryDevice.getDeliveryOrderId()== null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库单Id(deliveryOrderId)不能为空!");
					return JSON.toJSONString(dto);
				}
				String returnDeliveryOrderStr = deliveryOrderService.getDeliveryOrder(StringUtils.trim(deliveryDevice.getDeliveryOrderId()));	
				if(!FrameworkUtils.isSuccess(returnDeliveryOrderStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库单Id(deliveryOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				
				if(deliveryDevice.getDeviceId()== null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库设备Id(deviceId)不能为空!");
					return JSON.toJSONString(dto);
				}
				String returnDeviceStr = deviceService.getDevice(StringUtils.trim(deliveryDevice.getDeviceId()));	
				if(!FrameworkUtils.isSuccess(returnDeviceStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库设备Id不正确 !");
					return JSON.toJSONString(dto);
				}
				
				dto = baseDao.insertSelective(DeliveryDeviceMapper.class,deliveryDevice);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDeliveryDevice success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deliveryDevice.getDeliveryDevice_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDeliveryDevice failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeliveryDevice exception!");
				throw new RuntimeException("addDeliveryDevice Exception!");
			}
		} else {
			log.error("---addDeliveryDevice -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteDeliveryDevice(String deliveryDeviceId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(deliveryDeviceId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数deliveryDeviceId为空");
			log.error("---deleteDeliveryDevice -------- deliveryDeviceId is null ==== ");
			return JSON.toJSONString(dto);
		}

		try {
			dto = baseDao.deleteByPrimaryKey(DeliveryDeviceMapper.class,deliveryDeviceId);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("deleteDeliveryDevice exception!");
			throw new RuntimeException("deleteDeliveryDevice Exception!");
		}

		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
