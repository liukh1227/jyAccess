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
import com.jy.access.mapper.DeviceInMakesureOrderMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DeviceInMakesureOrderQueryForm;
import com.jy.entity.po.Device;
import com.jy.entity.po.DeviceInMakesureOrder;
import com.jy.entity.pojo.DeviceInMakesureOrder4PdfPojo;
import com.jy.entity.pojo.DeviceInMakesureOrderPojo;
import com.jy.entity.pojo.DeviceMakesurePojo;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.DeviceInMakesureOrderVo;
@Service("deviceInMakesureOrderService")
@Scope("prototype")
public class DeviceInMakesureOrderServiceImpl implements DeviceInMakesureOrderService {
	private static final Logger log = Logger.getLogger(DeviceInMakesureOrderServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ContractOrderService contractOrderService;
	@Autowired
	private UserAccountServie userAccountServie;

	@Override
	public String addDeviceInMakesureOrder(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeviceInMakesureOrder deviceInMakesureOrder = JSON.parseObject(data,DeviceInMakesureOrder.class);
				if (deviceInMakesureOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrder.getCustomerName()== null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrder.getEnterTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("预计进场时间不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrder.getBillingType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("计费模式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrder.getWorkSite() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("施工地点不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrder.getLeaseTerm() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("预计工期不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrder.getContractOrderId()== null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单Id(contractOrderId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService.getContractOrder(StringUtils.trim(deviceInMakesureOrder.getContractOrderId()));
				if(!FrameworkUtils.isSuccess(returnContractOrdertr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrder.getDeviceId()== null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进场设备Id(deviceId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceStr = deviceService.getDevice(StringUtils.trim(deviceInMakesureOrder.getDeviceId()));
				if(!FrameworkUtils.isSuccess(returnDeviceStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进场设备Id(deviceId)不正确 !");
					return JSON.toJSONString(dto);
				}
			if(deviceInMakesureOrder.getStatus()== null){
				deviceInMakesureOrder.setStatus(EntityConstant.STATUS_VALID);
			}
			if(deviceInMakesureOrder.getCreator()==null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("创建者不能为空!");
				return JSON.toJSONString(dto);
			}
			if(deviceInMakesureOrder.getOperators()==null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("操作人Id不能为空!");
				return JSON.toJSONString(dto);
			}
			if(deviceInMakesureOrder.getCreator().equals(deviceInMakesureOrder.getOperators())){
				String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceInMakesureOrder.getOperators()));
				if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("操作人Id和创建者Id参数不正确!");
					return JSON.toJSONString(dto);
				}
				
			}else{
				String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceInMakesureOrder.getOperators()));
				if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("操作人Id参数不正确!");
					return JSON.toJSONString(dto);
				}
				String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceInMakesureOrder.getCreator()));
				if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者Id参数不正确!");
					return JSON.toJSONString(dto);
				}
				
			}
				
				Date createDate = new Date();
				deviceInMakesureOrder.setCreateTime(createDate);
				dto = baseDao.insertSelective(DeviceInMakesureOrderMapper.class,deviceInMakesureOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					//更改设备状态
					Device updateDevice = new Device();
					updateDevice.setStatus(EntityConstant.DEVICE_STATUS_WORKING);
					String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(deviceInMakesureOrder.getDeviceId()), JSON.toJSONString(updateDevice));
					if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
						return returnUpdateDeviceStr;
					}
					
					log.info("addDeviceInMakesureOrder success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deviceInMakesureOrder.getdInMakeSure_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDeviceInMakesureOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeviceInMakesureOrder exception!");
				throw new RuntimeException("addDeviceInMakesureOrder Exception!");
			}
		} else {
			log.error("---addDeviceInMakesureOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	
	
	@Override
	public String addBatchDeviceInMakesureOrder(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeviceInMakesureOrderPojo deviceInMakesureOrderPojo = JSON.parseObject(data,DeviceInMakesureOrderPojo.class);
				if (deviceInMakesureOrderPojo == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrderPojo.getCustomerName()== null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrderPojo.getEnterTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("预计进场时间不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrderPojo.getBillingType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("计费模式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrderPojo.getWorkSite() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("施工地点不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrderPojo.getLeaseTerm() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("预计工期不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceInMakesureOrderPojo.getContractOrderId()== null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单Id(contractOrderId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService.getContractOrder(StringUtils.trim(deviceInMakesureOrderPojo.getContractOrderId()));
				if(!FrameworkUtils.isSuccess(returnContractOrdertr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if(deviceInMakesureOrderPojo.getDeviceIdList()== null ||(deviceInMakesureOrderPojo.getDeviceIdList()!= null && deviceInMakesureOrderPojo.getDeviceIdList().size()<1)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("批量进场设备不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(deviceInMakesureOrderPojo.getStatus()== null){
					deviceInMakesureOrderPojo.setStatus(EntityConstant.STATUS_VALID);
				}
				if(deviceInMakesureOrderPojo.getCreator().equals(deviceInMakesureOrderPojo.getOperators())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceInMakesureOrderPojo.getOperators()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id和创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceInMakesureOrderPojo.getOperators()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceInMakesureOrderPojo.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}
				Date createDate = new Date();
				deviceInMakesureOrderPojo.setCreateTime(createDate);
				List<DeviceInMakesureOrderPojo> cachDeviceInMakesureOrderPojoList = new ArrayList<DeviceInMakesureOrderPojo>();
				
				for(DeviceMakesurePojo deviceMakesurePojo:deviceInMakesureOrderPojo.getDeviceIdList()){
					if(deviceMakesurePojo== null||(deviceMakesurePojo!= null && deviceMakesurePojo.getDeviceId()== null)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("批量进场设备不能为空 !");
						return JSON.toJSONString(dto);

					}
					String returnDeviceStr = deviceService.getDevice(StringUtils.trim(deviceMakesurePojo.getDeviceId()));
					if(!FrameworkUtils.isSuccess(returnDeviceStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("批量进场设备Id(deviceId)："+StringUtils.trim(deviceMakesurePojo.getDeviceId())+"不正确 !");
						return JSON.toJSONString(dto);
					}
					DeviceInMakesureOrderPojo addDeviceInMakesureOrderPojo = new DeviceInMakesureOrderPojo();
					addDeviceInMakesureOrderPojo.copyFrom(deviceInMakesureOrderPojo);
					addDeviceInMakesureOrderPojo.setDeviceId(StringUtils.trim(deviceMakesurePojo.getDeviceId()));
					cachDeviceInMakesureOrderPojoList.add(addDeviceInMakesureOrderPojo);
				}
			
		for(DeviceInMakesureOrderPojo addDeviceInMakesureOrderPojo:cachDeviceInMakesureOrderPojoList){
			dto = baseDao.insertSelective(DeviceInMakesureOrderMapper.class,addDeviceInMakesureOrderPojo);
			if (FrameworkUtils.isSuccess(dto)) {
				//更改设备状态
				Device updateDevice = new Device();
				updateDevice.setStatus(EntityConstant.DEVICE_STATUS_WORKING);
				String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(addDeviceInMakesureOrderPojo.getDeviceId()), JSON.toJSONString(updateDevice));
				if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
					return returnUpdateDeviceStr;
				}
				log.info("addBatchDeviceInMakesureOrder ---- addDeviceInMakesureOrder success!");
			} else {
				log.error("addBatchDeviceInMakesureOrder---- addDeviceInMakesureOrder failure!");
			}
		}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addBatchDeviceInMakesureOrder exception!");
				throw new RuntimeException("addBatchDeviceInMakesureOrder Exception!");
			}
		} else {
			log.error("---addBatchDeviceInMakesureOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}



	@Override
	public String updateDeviceInMakesureOrder(String dInMakeSureId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(dInMakeSureId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数dInMakeSureId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(dInMakeSureId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DeviceInMakesureOrder deviceInMakesureOrder = JSON.parseObject(data,DeviceInMakesureOrder.class);
				if (deviceInMakesureOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				deviceInMakesureOrder.setdInMakeSure_Id(dInMakeSureId);
				
				if (deviceInMakesureOrder.getContractOrderId()!= null) {
					String returnContractOrdertr = contractOrderService.getContractOrder(StringUtils.trim(deviceInMakesureOrder.getContractOrderId()));
					if(!FrameworkUtils.isSuccess(returnContractOrdertr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}
			
				if (deviceInMakesureOrder.getDeviceId()!= null) {
					String returnDeviceStr = deviceService.getDevice(StringUtils.trim(deviceInMakesureOrder.getDeviceId()));
					if(!FrameworkUtils.isSuccess(returnDeviceStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("进场设备Id(deviceId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if(deviceInMakesureOrder.getOperators()!= null){
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceInMakesureOrder.getOperators()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("操作人Id参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				if(deviceInMakesureOrder.getCreator()!= null){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(deviceInMakesureOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				
				
				dto = baseDao.updateByPrimaryKeySelective(DeviceInMakesureOrderMapper.class, deviceInMakesureOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateDeviceInMakesureOrder success!");
				} else {
					log.error("updateDeviceInMakesureOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDeviceInMakesureOrder exception!");
				throw new RuntimeException("updateDeviceInMakesureOrder Exception!");
			}

		} else {
			log.error("---updateDeviceInMakesureOrder -------- data or dInMakeSureId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeviceInMakesureOrder(String dInMakeSureId) {
		String jsonStr = "";
		BaseObjDto<DeviceInMakesureOrderVo> dto = new BaseObjDto<DeviceInMakesureOrderVo>();
		try {
			if (StringUtils.isBlank(dInMakeSureId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数dInMakeSureId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DeviceInMakesureOrderVo> dInMakesureOrderlDto = baseDao
					.selectByPrimaryKey(DeviceInMakesureOrderMapper.class,
							StringUtils.trim(dInMakeSureId));
			if (FrameworkUtils.isSuccess(dInMakesureOrderlDto)) {
				DeviceInMakesureOrderVo deviceInMakesureOrderVo = dInMakesureOrderlDto.getData();
				dto.setData(deviceInMakesureOrderVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getDeviceInMakesureOrder success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceInMakesureOrder failure");
			}
		} catch (Exception e) {
			log.error("getDeviceInMakesureOrder exception!");
			e.printStackTrace();
			throw new RuntimeException("getDeviceInMakesureOrder Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	
	
	@Override
	public DeviceInMakesureOrder4PdfPojo getDInMakesureOrderDetailById(
			String dInMakeSureId) {
		DeviceInMakesureOrder4PdfPojo dInMakesureOrder4PdfPojo = null;
		try {
			if(StringUtils.isNotBlank(dInMakeSureId)){
				Map<String, Object> queryParamas = new HashMap<String, Object>();
				
				queryParamas.put("dInMakeSure_Id", dInMakeSureId);
				BaseObjDto<DeviceInMakesureOrder4PdfPojo> queryDto = baseDao
						.getObjProperty(DeviceInMakesureOrderMapper.class,
								"getDInMakesureOrderDetailById", queryParamas);

				if (FrameworkUtils.isSuccess(queryDto)) {
					dInMakesureOrder4PdfPojo = queryDto.getData();
					log.info("getDInMakesureOrderDetailById success!");
				} else {
					log.error("getDInMakesureOrderDetailById failure");
				}
			}
			
		} catch (Exception e) {
			log.error("getDInMakesureOrderDetailById exception!");
			e.printStackTrace();
			throw new RuntimeException("getDInMakesureOrderDetailById Exception!");
		}
		
		return dInMakesureOrder4PdfPojo;
	}



	@Override
	public String getDeviceInMakesureOrderList(
			DeviceInMakesureOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DeviceInMakesureOrderVo>> dto = new BaseObjDto<ItemPage<DeviceInMakesureOrderVo>>();
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

			BaseObjDto<ItemPage<DeviceInMakesureOrderVo>> pagesDto = baseDao.getPageList(DeviceInMakesureOrderMapper.class, DeviceInMakesureOrderVo.class, form,"getDeviceInMakesureOrderPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDeviceInMakesureOrderList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceInMakesureOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceInMakesureOrderList Exception !");
			throw new RuntimeException("getDeviceInMakesureOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllDeviceInMakesureOrderList(
			DeviceInMakesureOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceInMakesureOrderVo>> dto = new BaseObjDto<List<DeviceInMakesureOrderVo>>();
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
			BaseObjDto<List<DeviceInMakesureOrderVo>> dataDto = baseDao.getList(DeviceInMakesureOrderMapper.class, DeviceInMakesureOrderVo.class,"getAllDeviceInMakesureOrderList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeviceInMakesureOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeviceInMakesureOrderList Exception");
			throw new RuntimeException("getAllDeviceInMakesureOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}



	@Override
	public String getDeviceInMakesureOrder4DOutMOrderPageList(
			DeviceInMakesureOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DeviceInMakesureOrderVo>> dto = new BaseObjDto<ItemPage<DeviceInMakesureOrderVo>>();
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

			BaseObjDto<ItemPage<DeviceInMakesureOrderVo>> pagesDto = baseDao.getPageList(DeviceInMakesureOrderMapper.class, DeviceInMakesureOrderVo.class, form,"getDeviceInMakesureOrder4DOutMOrderPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDeviceInMakesureOrder4DOutMOrderPageList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceInMakesureOrder4DOutMOrderPageList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceInMakesureOrder4DOutMOrderPageList Exception !");
			throw new RuntimeException("getDeviceInMakesureOrder4DOutMOrderPageList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}



	@Override
	public String getAllDeviceInMakesureOrder4DOutMOrderList(
			DeviceInMakesureOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceInMakesureOrderVo>> dto = new BaseObjDto<List<DeviceInMakesureOrderVo>>();
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
			BaseObjDto<List<DeviceInMakesureOrderVo>> dataDto = baseDao.getList(DeviceInMakesureOrderMapper.class, DeviceInMakesureOrderVo.class,"getAllDeviceInMakesureOrder4DOutMOrderList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeviceInMakesureOrder4DOutMOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeviceInMakesureOrder4DOutMOrderList Exception");
			throw new RuntimeException("getAllDeviceInMakesureOrder4DOutMOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

}
