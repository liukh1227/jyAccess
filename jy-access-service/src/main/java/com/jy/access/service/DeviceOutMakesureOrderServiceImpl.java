package com.jy.access.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jy.EntityConstant;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.DeviceOutMakesureOrderMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DeviceOutMakesureOrderQueryForm;
import com.jy.entity.po.Device;
import com.jy.entity.po.DeviceOutMakesureOrder;
import com.jy.entity.po.DeviceWorktrace;
import com.jy.entity.pojo.DeviceOutMakesureOrder4PdfPojo;
import com.jy.entity.pojo.DeviceOutMakesureOrderPojo;
import com.jy.entity.pojo.DeviceOutMakesurePojo;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.DeviceInMakesureOrderVo;
import com.jy.entity.vo.DeviceOutMakesureOrderVo;

@Service("deviceOutMakesureOrderService")
@Scope("prototype")
public class DeviceOutMakesureOrderServiceImpl implements
		DeviceOutMakesureOrderService {
	private static final Logger log = Logger
			.getLogger(DeviceOutMakesureOrderServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ContractOrderService contractOrderService;
	@Autowired
	private DeviceInMakesureOrderService deviceInMakesureOrderService;
	@Autowired
	private DeviceWorktraceService deviceWorktraceService;
	@Autowired
	private UserAccountServie userAccountServie;

	@SuppressWarnings("unchecked")
	@Override
	public String addDeviceOutMakesureOrder(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeviceOutMakesureOrder deviceOutMakesureOrder = JSON
						.parseObject(data, DeviceOutMakesureOrder.class);
				if (deviceOutMakesureOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrder.getCustomerName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrder.getExitTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("退租返场时间不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrder.getBillingType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("计费模式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrder.getWorkSite() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("施工地点不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrder.getLeaseTerm() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("预计工期不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrder.getContractOrderId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单Id(contractOrderId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService
						.getContractOrder(StringUtils
								.trim(deviceOutMakesureOrder
										.getContractOrderId()));
				if (!FrameworkUtils.isSuccess(returnContractOrdertr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrder.getDeviceId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进场设备Id(deviceId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceStr = deviceService.getDevice(StringUtils
						.trim(deviceOutMakesureOrder.getDeviceId()));
				if (!FrameworkUtils.isSuccess(returnDeviceStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进场设备Id(deviceId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrder.getdInMakeSureId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进场确认单Id(dInMakeSureId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDInMakeSureIdStr = deviceInMakesureOrderService
						.getDeviceInMakesureOrder(StringUtils
								.trim(deviceOutMakesureOrder.getdInMakeSureId()));
				if (!FrameworkUtils.isSuccess(returnDInMakeSureIdStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进场确认单Id(dInMakeSureId)不正确 !");
					return JSON.toJSONString(dto);
				}else{
					BaseObjDto<DeviceInMakesureOrderVo> queryDInMakesureOrderDto = JSON.parseObject(returnDInMakeSureIdStr, BaseObjDto.class);
					if(queryDInMakesureOrderDto != null && queryDInMakesureOrderDto.getData()!= null ){
						DeviceInMakesureOrderVo queryDeviceInMakesureOrderVo = JSON.parseObject(JSON.toJSONString(queryDInMakesureOrderDto.getData()), DeviceInMakesureOrderVo.class);
						if(queryDeviceInMakesureOrderVo!= null && queryDeviceInMakesureOrderVo.getDeviceId()!= null && !queryDeviceInMakesureOrderVo.getDeviceId().equals(StringUtils.trim(deviceOutMakesureOrder.getDeviceId()))){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("参数中 dInMakeSureId 和 deviceId 不匹配!");
							return JSON.toJSONString(dto);
						}
					}
					
				
				}

				if (deviceOutMakesureOrder.getStatus() == null) {
					deviceOutMakesureOrder
							.setStatus(EntityConstant.STATUS_VALID);
				}
				if(deviceOutMakesureOrder.getCreator()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deviceOutMakesureOrder.getOperators()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("操作人Id不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deviceOutMakesureOrder.getCreator().equals(deviceOutMakesureOrder.getOperators())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceOutMakesureOrder.getOperators()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id和创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceOutMakesureOrder.getOperators()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceOutMakesureOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}

				Date createDate = new Date();
				deviceOutMakesureOrder.setCreateTime(createDate);
				dto = baseDao.insertSelective(
						DeviceOutMakesureOrderMapper.class,
						deviceOutMakesureOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					//更改设备状态
					Device updateDevice = new Device();
					updateDevice.setStatus(EntityConstant.DEVICE_STATUS_WAITWORK);
					String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(deviceOutMakesureOrder.getDeviceId()), JSON.toJSONString(updateDevice));
					if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
						return returnUpdateDeviceStr;
					}
					DeviceWorktrace  addDeviceWorktrace= new DeviceWorktrace();
					addDeviceWorktrace.copyFrom(deviceOutMakesureOrder);
					
					jsonStr =  deviceWorktraceService.addDeviceWorktraceUseProcess(addDeviceWorktrace);
					if(!FrameworkUtils.isSuccess(jsonStr)){
						return jsonStr;
					}
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deviceOutMakesureOrder.getdOutMakeSure_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
					
					
				} else {
					log.error("addDeviceOutMakesureOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeviceOutMakesureOrder exception!");
				throw new RuntimeException(
						"addDeviceOutMakesureOrder Exception!");
			}
		} else {
			log.error("---addDeviceOutMakesureOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public String addBatchDeviceOutMakesureOrder(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeviceOutMakesureOrderPojo deviceOutMakesureOrderPojo = JSON.parseObject(data, DeviceOutMakesureOrderPojo.class);
				if (deviceOutMakesureOrderPojo == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrderPojo.getCustomerName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrderPojo.getExitTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("退租返场时间不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrderPojo.getBillingType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("计费模式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrderPojo.getWorkSite() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("施工地点不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrderPojo.getLeaseTerm() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("预计工期不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrderPojo.getContractOrderId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单Id(contractOrderId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService
						.getContractOrder(StringUtils
								.trim(deviceOutMakesureOrderPojo
										.getContractOrderId()));
				if (!FrameworkUtils.isSuccess(returnContractOrdertr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if (deviceOutMakesureOrderPojo.getStatus() == null) {
					deviceOutMakesureOrderPojo
							.setStatus(EntityConstant.STATUS_VALID);
				}
				if(deviceOutMakesureOrderPojo.getCreator()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deviceOutMakesureOrderPojo.getOperators()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("操作人Id不能为空!");
					return JSON.toJSONString(dto);
				}
				if(deviceOutMakesureOrderPojo.getCreator().equals(deviceOutMakesureOrderPojo.getOperators())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceOutMakesureOrderPojo.getOperators()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id和创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceOutMakesureOrderPojo.getOperators()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceOutMakesureOrderPojo.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}

				Date createDate = new Date();
				deviceOutMakesureOrderPojo.setCreateTime(createDate);
			
	     List<DeviceOutMakesureOrderPojo> cachDeviceOutMakesureOrderPojoList = new ArrayList<DeviceOutMakesureOrderPojo>();
				for(DeviceOutMakesurePojo deviceOutMakesurePojo:deviceOutMakesureOrderPojo.getDeviceIdList()){
					if(deviceOutMakesurePojo== null||(deviceOutMakesurePojo!= null && deviceOutMakesurePojo.getDeviceId()== null)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("批量进场设备Id不能为空 !");
						return JSON.toJSONString(dto);

					}
					String returnDeviceStr = deviceService.getDevice(StringUtils.trim(deviceOutMakesurePojo.getDeviceId()));
					if(!FrameworkUtils.isSuccess(returnDeviceStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("批量进场设备Id(deviceId)："+StringUtils.trim(deviceOutMakesurePojo.getDeviceId())+"不正确 !");
						return JSON.toJSONString(dto);
					}
					if(deviceOutMakesurePojo== null||(deviceOutMakesurePojo!= null && deviceOutMakesurePojo.getdInMakeSureId()== null)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("批量进场确认单Id(dInMakeSureId)不能为空 !");
						return JSON.toJSONString(dto);
						
					}
					String returnDInMakeSureIdStr = deviceInMakesureOrderService
							.getDeviceInMakesureOrder(StringUtils
									.trim(deviceOutMakesurePojo.getdInMakeSureId()));
					if (!FrameworkUtils.isSuccess(returnDInMakeSureIdStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("进场确认单Id(dInMakeSureId)不正确 !");
						return JSON.toJSONString(dto);
					}else{
						BaseObjDto<DeviceInMakesureOrderVo> queryDInMakesureOrderDto = JSON.parseObject(returnDInMakeSureIdStr, BaseObjDto.class);
						if(queryDInMakesureOrderDto != null && queryDInMakesureOrderDto.getData()!= null ){
							DeviceInMakesureOrderVo queryDeviceInMakesureOrderVo = JSON.parseObject(JSON.toJSONString(queryDInMakesureOrderDto.getData()), DeviceInMakesureOrderVo.class);
							if(queryDeviceInMakesureOrderVo!= null && queryDeviceInMakesureOrderVo.getDeviceId()!= null && !queryDeviceInMakesureOrderVo.getDeviceId().equals(StringUtils.trim(deviceOutMakesurePojo.getDeviceId()))){
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("参数中 dInMakeSureId 和 deviceId 不匹配!");
								return JSON.toJSONString(dto);
							}
						}
						////
						DeviceOutMakesureOrderPojo addDeviceOutMakesureOrderPojo = new DeviceOutMakesureOrderPojo();
						addDeviceOutMakesureOrderPojo.copyFrom(deviceOutMakesureOrderPojo);
						addDeviceOutMakesureOrderPojo.setDeviceId(StringUtils.trim(deviceOutMakesurePojo.getDeviceId()));
						addDeviceOutMakesureOrderPojo.setdInMakeSureId(StringUtils.trim(deviceOutMakesurePojo.getdInMakeSureId()));
					cachDeviceOutMakesureOrderPojoList.add(addDeviceOutMakesureOrderPojo);
				}
				
				}
				for(DeviceOutMakesureOrderPojo adDeviceOutMakesureOrderPojo:cachDeviceOutMakesureOrderPojoList){
					dto = baseDao.insertSelective(DeviceOutMakesureOrderMapper.class,adDeviceOutMakesureOrderPojo);
					if (FrameworkUtils.isSuccess(dto)) {
						//更改设备状态
						Device updateDevice = new Device();
						updateDevice.setStatus(EntityConstant.DEVICE_STATUS_WAITWORK);
						String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(adDeviceOutMakesureOrderPojo.getDeviceId()), JSON.toJSONString(updateDevice));
						if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
							return returnUpdateDeviceStr;
						}
						DeviceWorktrace addDeviceWorktrace = new DeviceWorktrace();
						addDeviceWorktrace.copyFrom(adDeviceOutMakesureOrderPojo);
						String returnAddDworktraceStr = deviceWorktraceService.addDeviceWorktraceUseProcess(addDeviceWorktrace);
						if(!FrameworkUtils.isSuccess(returnAddDworktraceStr)){
							return returnAddDworktraceStr;
						}
						
						log.info("addBatchDeviceOutMakesureOrder ---- addDeviceOutMakesureOrder success!");
					} else {
						log.error("addBatchDeviceOutMakesureOrder---- addDeviceOutMakesureOrder failure!");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error("addBatchDeviceOutMakesureOrder exception!");
				throw new RuntimeException(
						"addBatchDeviceOutMakesureOrder Exception!");
			}
		} else {
			log.error("---addBatchDeviceOutMakesureOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}



	@Override
	public String updateDeviceOutMakesureOrder(String dOutMakeSureId,
			String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(dOutMakeSureId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数dOutMakeSureId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(dOutMakeSureId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DeviceOutMakesureOrder deviceOutMakesureOrder = JSON
						.parseObject(data, DeviceOutMakesureOrder.class);
				if (deviceOutMakesureOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				deviceOutMakesureOrder.setdOutMakeSure_Id(dOutMakeSureId);

				if (deviceOutMakesureOrder.getContractOrderId() != null) {
					String returnContractOrdertr = contractOrderService
							.getContractOrder(StringUtils
									.trim(deviceOutMakesureOrder
											.getContractOrderId()));
					if (!FrameworkUtils.isSuccess(returnContractOrdertr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}

				if (deviceOutMakesureOrder.getDeviceId() != null) {
					String returnDeviceStr = deviceService
							.getDevice(StringUtils.trim(deviceOutMakesureOrder
									.getDeviceId()));
					if (!FrameworkUtils.isSuccess(returnDeviceStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("进场设备Id(deviceId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}

				if (deviceOutMakesureOrder.getdInMakeSureId() != null) {
					String returnDInMakeSureIdStr = deviceInMakesureOrderService
							.getDeviceInMakesureOrder(StringUtils
									.trim(deviceOutMakesureOrder
											.getdInMakeSureId()));
					if (!FrameworkUtils.isSuccess(returnDInMakeSureIdStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("进场确认单Id(dInMakeSureId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if(deviceOutMakesureOrder.getOperators()!= null){
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceOutMakesureOrder.getOperators()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				if(deviceOutMakesureOrder.getCreator()!= null){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceOutMakesureOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				dto = baseDao.updateByPrimaryKeySelective(
						DeviceOutMakesureOrderMapper.class,
						deviceOutMakesureOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateDeviceOutMakesureOrder success!");
				} else {
					log.error("updateDeviceOutMakesureOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDeviceOutMakesureOrder exception!");
				throw new RuntimeException(
						"updateDeviceOutMakesureOrder Exception!");
			}

		} else {
			log.error("---updateDeviceOutMakesureOrder -------- data or dOutMakeSureId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeviceOutMakesureOrder(String dOutMakeSureId) {
		String jsonStr = "";
		BaseObjDto<DeviceOutMakesureOrderVo> dto = new BaseObjDto<DeviceOutMakesureOrderVo>();
		try {
			if (StringUtils.isBlank(dOutMakeSureId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数dOutMakeSureId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DeviceOutMakesureOrderVo> dOutMakesureOrderDto = baseDao
					.selectByPrimaryKey(DeviceOutMakesureOrderMapper.class,
							StringUtils.trim(dOutMakeSureId));
			if (FrameworkUtils.isSuccess(dOutMakesureOrderDto)) {
				DeviceOutMakesureOrderVo deviceOutMakesureOrderVo = dOutMakesureOrderDto
						.getData();
				dto.setData(deviceOutMakesureOrderVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getDeviceOutMakesureOrder success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceOutMakesureOrder failure");
			}
		} catch (Exception e) {
			log.error("getDeviceOutMakesureOrder exception!");
			e.printStackTrace();
			throw new RuntimeException("getDeviceOutMakesureOrder Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

	@Override
	public DeviceOutMakesureOrder4PdfPojo getDOutMakesureOrderDetailById(
			String dOutMakeSureId) {
		DeviceOutMakesureOrder4PdfPojo dOutMakesureOrder4PdfPojo = null;
		try {
			if(StringUtils.isNotBlank(dOutMakeSureId)){
				Map<String, Object> queryParamas = new HashMap<String, Object>();
				
				queryParamas.put("dOutMakeSure_Id", dOutMakeSureId);
				BaseObjDto<DeviceOutMakesureOrder4PdfPojo> queryDto = baseDao
						.getObjProperty(DeviceOutMakesureOrderMapper.class,
								"getDOutMakesureOrderDetailById", queryParamas);

				if (FrameworkUtils.isSuccess(queryDto)) {
					dOutMakesureOrder4PdfPojo = queryDto.getData();
					log.info("getDOutMakesureOrderDetailById success!");
				} else {
					log.error("getDOutMakesureOrderDetailById failure");
				}
			}
			
		} catch (Exception e) {
			log.error("getDOutMakesureOrderDetailById exception!");
			e.printStackTrace();
			throw new RuntimeException("getDOutMakesureOrderDetailById Exception!");
		}
		
		return dOutMakesureOrder4PdfPojo;
	}



	@Override
	public String getDeviceOutMakesureOrderList(
			DeviceOutMakesureOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DeviceOutMakesureOrderVo>> dto = new BaseObjDto<ItemPage<DeviceOutMakesureOrderVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空");
				return JSON.toJSONString(dto);
			}
			if(form.getContractOrderId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数签约单Id(contractOrderId)不能为空");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<ItemPage<DeviceOutMakesureOrderVo>> pagesDto = baseDao
					.getPageList(DeviceOutMakesureOrderMapper.class,
							DeviceOutMakesureOrderVo.class, form,
							"getDeviceOutMakesureOrderPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDeviceOutMakesureOrderList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceOutMakesureOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceOutMakesureOrderList Exception !");
			throw new RuntimeException(
					"getDeviceOutMakesureOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllDeviceOutMakesureOrderList(
			DeviceOutMakesureOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceOutMakesureOrderVo>> dto = new BaseObjDto<List<DeviceOutMakesureOrderVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空");
				return JSON.toJSONString(dto);
			}
			if(form.getContractOrderId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数签约单Id(contractOrderId)不能为空");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<List<DeviceOutMakesureOrderVo>> dataDto = baseDao
					.getList(DeviceOutMakesureOrderMapper.class,
							DeviceOutMakesureOrderVo.class,
							"getAllDeviceOutMakesureOrderList", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeviceOutMakesureOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeviceOutMakesureOrderList Exception");
			throw new RuntimeException(
					"getAllDeviceOutMakesureOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
