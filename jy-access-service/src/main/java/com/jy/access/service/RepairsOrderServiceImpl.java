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
import com.jy.access.mapper.RepairsOrderMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.RepairsOrderQueryForm;
import com.jy.entity.po.Device;
import com.jy.entity.po.RepairsOrder;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.RepairsOrderVo;
@Service("repairsOrderService")
@Scope("prototype")
public class RepairsOrderServiceImpl implements RepairsOrderService {
	private static final Logger log = Logger.getLogger(RepairsOrderServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ContractOrderService contractOrderService;
	
	@Override
	public String addRepairsOrder(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try{
				RepairsOrder repairsOrder = JSON.parseObject(data,RepairsOrder.class);
				if (repairsOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (repairsOrder.getCustomerName()== null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (repairsOrder.getLeaserPhone() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("联系电话不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (repairsOrder.getWorkSite() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("施工地点不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (repairsOrder.getRemark() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("故障描述不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (repairsOrder.getCreateTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报修日期不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (repairsOrder.getRepairsType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("处理方式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(!(repairsOrder.isUndoDeviceType()||repairsOrder.isDoneDeviceType()||repairsOrder.isReplaceDeviceType())){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("处理方式参数不正确 !");
					return JSON.toJSONString(dto);
				}
				
				if (repairsOrder.getContractOrderId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单Id(contractOrderId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService.getContractOrder(StringUtils.trim(repairsOrder.getContractOrderId()));
				if(!FrameworkUtils.isSuccess(returnContractOrdertr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				
				if (repairsOrder.getDeviceId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报修设备不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceStr = deviceService.getDevice(StringUtils.trim(repairsOrder.getDeviceId()));
				if(!FrameworkUtils.isSuccess(returnDeviceStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("进场设备Id(deviceId)不正确 !");
					return JSON.toJSONString(dto);
				}
				
				if(repairsOrder.getRepairsType()!= null && !repairsOrder.isReplaceDeviceType() && repairsOrder.getReplaceDeviceId()!= null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("处理方式不为更换设备时，替换设备的Id(replaceDeviceId)应为空 !");
					return JSON.toJSONString(dto);
				}
				
				if(repairsOrder.getRepairsType()!= null && repairsOrder.isReplaceDeviceType()){
					if(repairsOrder.getReplaceDeviceId()== null){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("处理方式为更换设备时，替换设备的Id(replaceDeviceId)不能为空 !");
						return JSON.toJSONString(dto);
					}else{
						if(repairsOrder.getDeviceId().equals(repairsOrder.getReplaceDeviceId())){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("处理方式为更换设备时，替换设备的Id(replaceDeviceId)和报修的设备Id(deviceId)不能相同!");
							return JSON.toJSONString(dto);
						}
						String returnReplaceDeviceStr = deviceService.getDevice(StringUtils.trim(repairsOrder.getReplaceDeviceId()));
						if(!FrameworkUtils.isSuccess(returnReplaceDeviceStr)){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("处理方式为更换设备时，替换设备的Id(replaceDeviceId)不正确 !");
							return JSON.toJSONString(dto);
						}
						
					}
					
				}
				if(repairsOrder.getStatus()== null){
					repairsOrder.setStatus(EntityConstant.STATUS_VALID);
				}
				dto = baseDao.insertSelective(RepairsOrderMapper.class,repairsOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					if(!repairsOrder.isDoneDeviceType()){
						if(repairsOrder.isReplaceDeviceType()){
							Device updateDevice = new Device();
							updateDevice.setStatus(EntityConstant.DEVICE_STATUS_FAULTING);
							String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(repairsOrder.getDeviceId()), JSON.toJSONString(updateDevice));
							if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
								return returnUpdateDeviceStr;
							}
							Device updateReplaceDevice = new Device();
							updateReplaceDevice.setStatus(EntityConstant.DEVICE_STATUS_WORKING);
							String returnUpdateReplaceDeviceStr = deviceService.updateDevice(StringUtils.trim(repairsOrder.getReplaceDeviceId()), JSON.toJSONString(updateReplaceDevice));
							if(!FrameworkUtils.isSuccess(returnUpdateReplaceDeviceStr)){
								return returnUpdateReplaceDeviceStr;
							}
							
						}else if(repairsOrder.isUndoDeviceType()){
							Device updateDevice = new Device();
							updateDevice.setStatus(EntityConstant.DEVICE_STATUS_REPARIRSING);
							String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(repairsOrder.getDeviceId()), JSON.toJSONString(updateDevice));
							if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
								return returnUpdateDeviceStr;
							}
						}
						
					}
					log.info("addRepairsOrder success!");
					
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(repairsOrder.getRepairsOrder_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
					
				} else {
					log.error("addRepairsOrder failure!");
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
				log.error("addRepairsOrder exception!");
				throw new RuntimeException("addRepairsOrder Exception!");
			}
				
		}else {
			log.error("---addRepairsOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;	
			
	}

	@Override
	public String updateRepairsOrder(String repairsOrderId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(repairsOrderId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数dInMakeSureId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(repairsOrderId)
				&& StringUtils.isNotBlank(data)) {
			try {
				RepairsOrder repairsOrder = JSON.parseObject(data,RepairsOrder.class);
				if (repairsOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				repairsOrder.setRepairsOrder_Id(repairsOrderId);
				
				if (repairsOrder.getContractOrderId()!= null) {
					String returnContractOrdertr = contractOrderService.getContractOrder(StringUtils.trim(repairsOrder.getContractOrderId()));
					if(!FrameworkUtils.isSuccess(returnContractOrdertr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}
			
				if (repairsOrder.getDeviceId()!= null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("修改报修单不支持更改报修设备(deviceId)更改,如果添加报修设备错误，请删除,重新添加报修单 !");
					return JSON.toJSONString(dto);
				}
				
				if(repairsOrder.getRepairsType()!= null && !repairsOrder.isReplaceDeviceType() && repairsOrder.getReplaceDeviceId()!= null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("处理方式不为更换设备时，替换设备的Id(replaceDeviceId)应为空 !");
					return JSON.toJSONString(dto);
				}
				BaseObjDto<RepairsOrderVo> queryRepairsOrderDto = baseDao.selectByPrimaryKey(RepairsOrderMapper.class,StringUtils.trim(repairsOrderId));
				if (!FrameworkUtils.isSuccess(queryRepairsOrderDto)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的报修单Id(repairsOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				RepairsOrderVo queryRepairsOrderVo = queryRepairsOrderDto.getData();
				if(queryRepairsOrderVo== null ||(queryRepairsOrderVo!= null && queryRepairsOrderVo.getDeviceId()==null)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("查询的报修单Id,数据为空 !");
					return JSON.toJSONString(dto);
				}
				
				if(repairsOrder.getRepairsType()!= null && repairsOrder.isReplaceDeviceType()){
					if(repairsOrder.getReplaceDeviceId()== null){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("处理方式为更换设备时，替换设备的Id(replaceDeviceId)不能为空 !");
						return JSON.toJSONString(dto);
					}else{
						if(queryRepairsOrderVo!= null && queryRepairsOrderVo.getDeviceId().equals(repairsOrder.getReplaceDeviceId())){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("处理方式为更换设备时，替换设备的Id(replaceDeviceId)和报修的设备Id(deviceId)不能相同!");
							return JSON.toJSONString(dto);
						}
						String returnReplaceDeviceStr = deviceService.getDevice(StringUtils.trim(repairsOrder.getReplaceDeviceId()));
						if(!FrameworkUtils.isSuccess(returnReplaceDeviceStr)){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("处理方式为更换设备时，替换设备的Id(replaceDeviceId)不正确 !");
							return JSON.toJSONString(dto);
						}
						
					}
					
				}
				
				dto = baseDao.updateByPrimaryKeySelective(RepairsOrderMapper.class, repairsOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					if(repairsOrder.getRepairsType()!= null ){
						if(repairsOrder.isDoneDeviceType()){
							Device updateDevice = new Device();
							updateDevice.setStatus(EntityConstant.DEVICE_STATUS_WORKING);
							String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(queryRepairsOrderVo.getDeviceId()), JSON.toJSONString(updateDevice));
							if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
								return returnUpdateDeviceStr;
							}
						}else if(repairsOrder.isReplaceDeviceType()){
							Device updateDevice = new Device();
							updateDevice.setStatus(EntityConstant.DEVICE_STATUS_FAULTING);
							String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(queryRepairsOrderVo.getDeviceId()), JSON.toJSONString(updateDevice));
							if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
								return returnUpdateDeviceStr;
							}
							Device updateReplaceDevice = new Device();
							updateReplaceDevice.setStatus(EntityConstant.DEVICE_STATUS_WORKING);
							String returnUpdateReplaceDeviceStr = deviceService.updateDevice(StringUtils.trim(repairsOrder.getReplaceDeviceId()), JSON.toJSONString(updateReplaceDevice));
							if(!FrameworkUtils.isSuccess(returnUpdateReplaceDeviceStr)){
								return returnUpdateReplaceDeviceStr;
							}
						}
						
					}
			
					log.info("updateRepairsOrder success!");
				} else {
					log.error("updateRepairsOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateRepairsOrder exception!");
				throw new RuntimeException("updateRepairsOrder Exception!");
			}

		} else {
			log.error("---updateRepairsOrder -------- data or repairsOrderId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteRepairsOrder(String repairsOrderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRepairsOrder(String repairsOrderId) {
		String jsonStr = "";
		BaseObjDto<RepairsOrderVo> dto = new BaseObjDto<RepairsOrderVo>();
		try {
			if (StringUtils.isBlank(repairsOrderId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数repairsOrderId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<RepairsOrderVo> repairsOrderDto = baseDao.selectByPrimaryKey(RepairsOrderMapper.class,StringUtils.trim(repairsOrderId));
			if (FrameworkUtils.isSuccess(repairsOrderDto)) {
				RepairsOrderVo repairsOrderVo = repairsOrderDto.getData();
				dto.setData(repairsOrderVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getRepairsOrder success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getRepairsOrder failure");
			}
		} catch (Exception e) {
			log.error("getRepairsOrder exception!");
			e.printStackTrace();
			throw new RuntimeException("getRepairsOrder Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getRepairsOrderList(RepairsOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<RepairsOrder>> dto = new BaseObjDto<ItemPage<RepairsOrder>>();
		try {

			BaseObjDto<ItemPage<RepairsOrder>> pagesDto = baseDao.getPageList(RepairsOrderMapper.class, RepairsOrder.class, form,"getRepairsOrderPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getRepairsOrderList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getRepairsOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getRepairsOrderList Exception !");
			throw new RuntimeException("getRepairsOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllRepairsOrderList(RepairsOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<RepairsOrder>> dto = new BaseObjDto<List<RepairsOrder>>();
		try {
			BaseObjDto<List<RepairsOrder>> dataDto = baseDao.getList(RepairsOrderMapper.class, RepairsOrder.class,"getAllRepairsOrderList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllRepairsOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllRepairsOrderList Exception");
			throw new RuntimeException("getAllRepairsOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
