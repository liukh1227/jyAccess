package com.jy.access.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jy.EntityConstant;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.DeliveryCarMapper;
import com.jy.access.mapper.DeliveryDeviceMapper;
import com.jy.access.mapper.DeliveryOrderMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.bo.DeliveryOrderBo;
import com.jy.entity.form.DeliveryOrderQueryForm;
import com.jy.entity.po.DeliveryCar;
import com.jy.entity.po.DeliveryDevice;
import com.jy.entity.po.DeliveryOrder;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.DeliveryDeviceVo;
import com.jy.entity.vo.DeliveryOrderVo;

@Service("deliveryOrderService")
@Scope("prototype")
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
	private static final Logger log = Logger.getLogger(DeliveryOrderServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private UserAccountServie userAccountServie;
	@Autowired
	private ContractOrderService contractOrderService;
	
	@Override
	public String addDeliveryOrder(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeliveryOrder deliveryOrder = JSON.parseObject(data,DeliveryOrder.class);
				if (deliveryOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrder.getDeliveryType() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库类型不能空 !");
					return JSON.toJSONString(dto);
				}
				if(!deliveryOrder.isRightDeliveryType()){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库类型参数不正确 !");
					return JSON.toJSONString(dto);
				}
				
				if(deliveryOrder.getConsignorName()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("发货单位名称不能为空!");
					return JSON.toJSONString(dto);
				}
				
				if(deliveryOrder.getConsignorAddress()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("发货地址不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrder.getSentTime()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("发货时间不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrder.getBuyerName()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("接货单位名称不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrder.getBuyerAddress()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("使用地址不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrder.getContractOrderId() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单Id(contractOrderId)不能为空!");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService
						.getContractOrder(StringUtils
								.trim(deliveryOrder
										.getContractOrderId()));
				if (!FrameworkUtils.isSuccess(returnContractOrdertr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrder.getCreator()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrder.getOperators()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("操作人不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrder.getCreator().equals(deliveryOrder.getOperators())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deliveryOrder.getOperators()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id和创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deliveryOrder.getOperators()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deliveryOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}
							
				Date createDate = new Date();
				deliveryOrder.setCreateTime(createDate);
				deliveryOrder.setStatus(EntityConstant.STATUS_VALID);
				dto = baseDao.insertSelective(DeliveryOrderMapper.class,deliveryOrder);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDeliveryOrder success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deliveryOrder.getDeliveryOrder_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDeliveryOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeliveryOrder exception!");
				throw new RuntimeException("addDeliveryOrder Exception!");
			}
		} else {
			log.error("---addDeliveryOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String addDeliveryOrderAndCarAndDevice(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeliveryOrderBo deliveryOrderBo = JSON.parseObject(data,DeliveryOrderBo.class);
				if (deliveryOrderBo == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrderBo.getDeliveryType() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库类型不能空 !");
					return JSON.toJSONString(dto);
				}
				if(!deliveryOrderBo.isRightDeliveryType()){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进出库类型参数不正确 !");
					return JSON.toJSONString(dto);
				}
				
				if(deliveryOrderBo.getConsignorName()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("发货单位名称不能为空!");
					return JSON.toJSONString(dto);
				}
				
				if(deliveryOrderBo.getConsignorAddress()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("发货地址不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrderBo.getSentTime()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("发货时间不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrderBo.getBuyerName()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("接货单位名称不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrderBo.getBuyerAddress()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("使用地址不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrderBo.getContractOrderId() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单Id(contractOrderId)不能为空!");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService
						.getContractOrder(StringUtils
								.trim(deliveryOrderBo
										.getContractOrderId()));
				if (!FrameworkUtils.isSuccess(returnContractOrdertr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrderBo.getCreator()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrderBo.getOperators()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("操作人不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrderBo.getCreator().equals(deliveryOrderBo.getOperators())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deliveryOrderBo.getOperators()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id和创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deliveryOrderBo.getOperators()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deliveryOrderBo.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}
				
				
				if(deliveryOrderBo.getDeliveryCarLists()!= null && deliveryOrderBo.getDeliveryCarLists().size()>0){
					for(DeliveryCar deliveryCar:deliveryOrderBo.getDeliveryCarLists()){
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
					
					}
				}
				if(deliveryOrderBo.getDeliveryDeviceLists()!= null && deliveryOrderBo.getDeliveryDeviceLists().size()>0){
					for(DeliveryDeviceVo deliveryDeviceVo:deliveryOrderBo.getDeliveryDeviceLists()){
						
						if(deliveryDeviceVo.getDeviceId()== null){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("进出库设备Id不能为空!");
							return JSON.toJSONString(dto);
						}
						String returnDeviceStr = deviceService.getDevice(StringUtils.trim(deliveryDeviceVo.getDeviceId()));	
						if(!FrameworkUtils.isSuccess(returnDeviceStr)){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("进出库设备Id不正确 !");
							return JSON.toJSONString(dto);
						}
						
					}
				}
				
				
							
				Date createDate = new Date();
				deliveryOrderBo.setCreateTime(createDate);
				deliveryOrderBo.setStatus(EntityConstant.STATUS_VALID);
				dto = baseDao.insertSelective(DeliveryOrderMapper.class,deliveryOrderBo);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDeliveryOrderAndCarAndDevice when -----addDeliveryOrder success!");
					
					if(deliveryOrderBo.getDeliveryCarLists()!= null && deliveryOrderBo.getDeliveryCarLists().size()>0){
						for(DeliveryCar deliveryCar:deliveryOrderBo.getDeliveryCarLists()){
							deliveryCar.setDeliveryOrderId(deliveryOrderBo.getDeliveryOrder_Id());
							dto = baseDao.insertSelective(DeliveryCarMapper.class,deliveryCar);
							if(!FrameworkUtils.isSuccess(dto)){
								log.error("addDeliveryOrderAndCarAndDevice when --- addDeliveryCat failure!");
								JSON.toJSONString(dto);
							}
						
						}
					}
					if(deliveryOrderBo.getDeliveryDeviceLists()!= null && deliveryOrderBo.getDeliveryDeviceLists().size()>0){
						for(DeliveryDeviceVo deliveryDeviceVo:deliveryOrderBo.getDeliveryDeviceLists()){
							DeliveryDevice deliveryDevice = new DeliveryDevice();
							deliveryDevice.setDeliveryOrderId(deliveryOrderBo.getDeliveryOrder_Id());
							deliveryDevice.setDeviceId(deliveryDeviceVo.getDeviceId());
							deliveryDevice.setRemark(deliveryDeviceVo.getRemark());
							dto = baseDao.insertSelective(DeliveryDeviceMapper.class,deliveryDevice);
							if(!FrameworkUtils.isSuccess(dto)){
								log.error("addDeliveryOrderAndCarAndDevice when --- AddDeliveryDevice failure!");
								JSON.toJSONString(dto);
							}
						}
					}
					
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deliveryOrderBo.getDeliveryOrder_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDeliveryOrderAndCarAndDevice when -----addDeliveryOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeliveryOrderAndCarAndDevice exception!");
				throw new RuntimeException("addDeliveryOrderAndCarAndDevice Exception!");
			}
		} else {
			log.error("---addDeliveryOrderAndCarAndDevice -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateDeliveryOrder(String deliveryOrderId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(deliveryOrderId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数deliveryOrderId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(deliveryOrderId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DeliveryOrder deliveryOrder = JSON.parseObject(data,DeliveryOrder.class);
				if (deliveryOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if(deliveryOrder.getContractOrderId() != null){
					String returnContractOrdertr = contractOrderService
							.getContractOrder(StringUtils
									.trim(deliveryOrder
											.getContractOrderId()));
					if (!FrameworkUtils.isSuccess(returnContractOrdertr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}
			
				if(deliveryOrder.getOperators()!= null){
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deliveryOrder.getOperators()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				if(deliveryOrder.getCreator()!= null){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deliveryOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				
			
				deliveryOrder.setDeliveryOrder_Id(deliveryOrderId);
			
				dto = baseDao.updateByPrimaryKeySelective(DeliveryOrderMapper.class, deliveryOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateDeliveryOrder success!");
				} else {
					log.error("updateDeliveryOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDeliveryOrder exception!");
				throw new RuntimeException("updateDeliveryOrder Exception!");
			}

		} else {
			log.error("---updateCustomer -------- data or customerId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeliveryOrder(String deliveryOrderId) {
		String jsonStr = "";
		BaseObjDto<DeliveryOrderBo> dto = new BaseObjDto<DeliveryOrderBo>();
		try {
			if (StringUtils.isBlank(deliveryOrderId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数deliveryOrderId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DeliveryOrderBo> deliveryOrderDto = baseDao.selectByPrimaryKey(DeliveryOrderMapper.class,StringUtils.trim(deliveryOrderId));
			if (FrameworkUtils.isSuccess(deliveryOrderDto)) {
				DeliveryOrderBo deliveryOrderBo = deliveryOrderDto.getData();
				dto.setData(deliveryOrderBo);
				FrameworkUtils.setSuccess(dto);
				log.info("getDeliveryOrder success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeliveryOrder failure");
			}
		} catch (Exception e) {
			log.error("getDeliveryOrder exception!");
			e.printStackTrace();
			throw new RuntimeException("getDeliveryOrder Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeliveryOrderList(DeliveryOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DeliveryOrderVo>> dto = new BaseObjDto<ItemPage<DeliveryOrderVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getStatus() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("进出库单状态不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getDeliveryType() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("进出库类型不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(!form.isRightDeliveryType()){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("进出库类型参数不正确 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setStatus(null);
				form.setStatusArray(null);
				}
			BaseObjDto<ItemPage<DeliveryOrderVo>> pagesDto = baseDao.getPageList(
					DeliveryOrderMapper.class, DeliveryOrderVo.class, form,
					"getDeliveryOrderPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDeliveryOrderList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeliveryOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeliveryOrderList Exception !");
			throw new RuntimeException("getDeliveryOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllDeliveryOrderList(DeliveryOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeliveryOrderVo>> dto = new BaseObjDto<List<DeliveryOrderVo>>();

		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getStatus() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("进出库单状态不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getDeliveryType() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("进出库类型不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(!form.isRightDeliveryType()){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("进出库类型参数不正确 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setStatus(null);
				form.setStatusArray(null);
			}
			BaseObjDto<List<DeliveryOrderVo>> listDto = baseDao.getList(DeliveryOrderMapper.class, DeliveryOrderVo.class,"getDeliveryOrderPageList", form);
					
				
			if (FrameworkUtils.isSuccess(listDto)) {
				dto = listDto;
				log.info("getAllDeliveryOrderList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeliveryOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeliveryOrderList Exception !");
			throw new RuntimeException("getAllDeliveryOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
