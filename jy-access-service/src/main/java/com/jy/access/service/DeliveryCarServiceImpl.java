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
import com.jy.access.mapper.DeliveryCarMapper;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.po.DeliveryCar;
import com.jy.entity.pojo.SuccessReturnPojo;

@Service("deliveryCarService")
@Scope("prototype")
public class DeliveryCarServiceImpl implements DeliveryCarService {
	private static final Logger log = Logger.getLogger(DeliveryCarServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeliveryOrderService deliveryOrderService;
	
	@Override
	public String addDeliveryCar(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeliveryCar deliveryCar = JSON.parseObject(data,DeliveryCar.class);
				if(deliveryCar.getDeliveryOrderId()== null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库单Id(deliveryOrderId)不能为空!");
					return JSON.toJSONString(dto);
				}
				String returnDeliveryOrderStr = deliveryOrderService.getDeliveryOrder(StringUtils.trim(deliveryCar.getDeliveryOrderId()));	
				if(!FrameworkUtils.isSuccess(returnDeliveryOrderStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库单Id(deliveryOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if(deliveryCar.getPlateNumber()== null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("送货车牌不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryCar.getDriver()== null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("送货司机不能为空!");
					return JSON.toJSONString(dto);
				}
							
				dto = baseDao.insertSelective(DeliveryCarMapper.class,deliveryCar);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDeliveryCar success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deliveryCar.getDeliveryCar_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDeliveryCar failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeliveryCar exception!");
				throw new RuntimeException("addDeliveryCar Exception!");
			}
		} else {
			log.error("---addDeliveryCar -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteDeliveryCar(String deliveryCarId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(deliveryCarId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数deliveryCarId为空");
			log.error("---deleteDeliveryCar -------- deliveryCarId is null ==== ");
			return JSON.toJSONString(dto);
		}

		try {
			dto = baseDao.deleteByPrimaryKey(DeliveryCarMapper.class,deliveryCarId);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("deleteDeliveryCar exception!");
			throw new RuntimeException("deleteDeliveryCar Exception!");
		}

		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
