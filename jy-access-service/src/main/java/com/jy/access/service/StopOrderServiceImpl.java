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
import com.jy.access.mapper.StopOrderMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.StopOrderQueryForm;
import com.jy.entity.po.Device;
import com.jy.entity.po.StopOrder;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.StopOrderVo;
@Service("stopOrderService")
@Scope("prototype")
public class StopOrderServiceImpl implements StopOrderService {
	private static final Logger log = Logger.getLogger(StopOrderServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ContractOrderService contractOrderService;
	@Autowired
	private UserAccountServie userAccountServie;

	@Override
	public String addStopOrder(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try{
				StopOrder stopOrder = JSON.parseObject(data,StopOrder.class);
				if (stopOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (stopOrder.getCustomerName()== null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (stopOrder.getLeaserPhone() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("联系电话不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (stopOrder.getWorkSite() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("施工地点不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (stopOrder.getRemark() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报停原因不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (stopOrder.getStopStartTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报停开始时间不能为空 !");
					return JSON.toJSONString(dto);
				}	
				if (stopOrder.getSalesMan() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报停员不能为空 !");
					return JSON.toJSONString(dto);
				}	
				if (stopOrder.getContractOrderId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单Id(contractOrderId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService.getContractOrder(StringUtils.trim(stopOrder.getContractOrderId()));
				if(!FrameworkUtils.isSuccess(returnContractOrdertr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				
				if (stopOrder.getDeviceId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报停设备不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceStr = deviceService.getDevice(StringUtils.trim(stopOrder.getDeviceId()));
				if(!FrameworkUtils.isSuccess(returnDeviceStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报停设备Id(deviceId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if(stopOrder.getCreator()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空!");
					return JSON.toJSONString(dto);
				}
				if(stopOrder.getSalesMan()==null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报停员Id不能为空!");
					return JSON.toJSONString(dto);
				}
				if(stopOrder.getCreator().equals(stopOrder.getSalesMan())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(stopOrder.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报停员Id和创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(stopOrder.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报停员Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(stopOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}
				
				if(stopOrder.getStatus()== null){
					stopOrder.setStatus(EntityConstant.STATUS_VALID);
				}
				if(stopOrder.getStopEndTime() != null && stopOrder.getStopStatus()== null){
					stopOrder.setStopStatus(EntityConstant.TM_STOPORDER_STOPSTATUS_RESET);
				}else if(stopOrder.getStopStatus()== null){
					stopOrder.setStatus(EntityConstant.TM_STOPORDER_STOPSTATUS_STOP);
				}
				dto = baseDao.insertSelective(StopOrderMapper.class,stopOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					if(stopOrder.isStopStopOrder()){
						Device updateDevice = new Device();
						updateDevice.setStatus(EntityConstant.DEVICE_STATUS_STOPING);
						String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(stopOrder.getDeviceId()), JSON.toJSONString(updateDevice));
						if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
							return returnUpdateDeviceStr;
						}
					}
					log.info("addStopOrder success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(stopOrder.getStopOrder_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addStopOrder failure!");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				log.error("addStopOrder exception!");
				throw new RuntimeException("addStopOrder Exception!");
			}
				
		}else {
			log.error("---addStopOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;	
	}

	@Override
	public String updateStopOrder(String stopOrderId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(stopOrderId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数stopOrderId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(stopOrderId)
				&& StringUtils.isNotBlank(data)) {
			try {
				StopOrder stopOrder = JSON.parseObject(data,StopOrder.class);
				if (stopOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				stopOrder.setStopOrder_Id(stopOrderId);
				
				if (stopOrder.getContractOrderId()!= null) {
					String returnContractOrdertr = contractOrderService.getContractOrder(StringUtils.trim(stopOrder.getContractOrderId()));
					if(!FrameworkUtils.isSuccess(returnContractOrdertr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if (stopOrder.getDeviceId()!= null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("修改报停单不支持更改报停设备(deviceId)更改,如果添加报修设备错误，请删除,重新添加报停单 !");
					return JSON.toJSONString(dto);
				}
				if(stopOrder.getStopStatus()!= null && stopOrder.isResetStopOrder() && stopOrder.getStopEndTime() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报停单恢复工作时,报停结束时间不能为空 !");
					return JSON.toJSONString(dto);
				}	
				if(stopOrder.getStopEndTime() != null){
					if(stopOrder.getStopStatus()== null){
						stopOrder.setStopStatus(EntityConstant.TM_STOPORDER_STOPSTATUS_RESET);
					}
					
				}
				if(stopOrder.getCreator()!=null){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(stopOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				if(stopOrder.getSalesMan()!=null){
					String returnOperatorStr = userAccountServie.getUserAccountById(StringUtils.trim(stopOrder.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnOperatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报停员Id参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				dto = baseDao.updateByPrimaryKeySelective(StopOrderMapper.class, stopOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					if(stopOrder.getStopStatus()!= null && stopOrder.isResetStopOrder()){
						BaseObjDto<StopOrderVo> queryStopOrderDto = baseDao.selectByPrimaryKey(StopOrderMapper.class,StringUtils.trim(stopOrderId));
						if (!FrameworkUtils.isSuccess(queryStopOrderDto)) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("传入的报停单Id(stopOrderId)不正确 !");
							return JSON.toJSONString(dto);
						}
						StopOrderVo queryStopOrderVo = queryStopOrderDto.getData();
						if(queryStopOrderVo== null ||(queryStopOrderVo!= null && queryStopOrderVo.getDeviceId()==null)){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("查询的报停单Id,数据为空 !");
							return JSON.toJSONString(dto);
						}
						Device updateDevice = new Device();
						updateDevice.setStatus(EntityConstant.DEVICE_STATUS_WORKING);
						String returnUpdateDeviceStr = deviceService.updateDevice(StringUtils.trim(queryStopOrderVo.getDeviceId()), JSON.toJSONString(updateDevice));
						if(!FrameworkUtils.isSuccess(returnUpdateDeviceStr)){
							return returnUpdateDeviceStr;
						}
						
					}
					log.error("updateStopOrder success!");
				} else {
					log.error("updateStopOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateStopOrder exception!");
				throw new RuntimeException("updateStopOrder Exception!");
			}

		} else {
			log.error("---updateStopOrder -------- data or dInMakeSureId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteStopOrder(String stopOrderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStopOrder(String stopOrderId) {
		String jsonStr = "";
		BaseObjDto<StopOrderVo> dto = new BaseObjDto<StopOrderVo>();
		try {
			if (StringUtils.isBlank(stopOrderId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数stopOrderId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<StopOrderVo> stopOrderDto = baseDao.selectByPrimaryKey(StopOrderMapper.class,StringUtils.trim(stopOrderId));
			if (FrameworkUtils.isSuccess(stopOrderDto)) {
				StopOrderVo stopOrderVo = stopOrderDto.getData();
				dto.setData(stopOrderVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getStopOrder success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getStopOrder failure");
			}
		} catch (Exception e) {
			log.error("getStopOrder exception!");
			e.printStackTrace();
			throw new RuntimeException("getStopOrder Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getStopOrderList(StopOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<StopOrderVo>> dto = new BaseObjDto<ItemPage<StopOrderVo>>();
		try {

			BaseObjDto<ItemPage<StopOrderVo>> pagesDto = baseDao.getPageList(StopOrderMapper.class, StopOrderVo.class, form,"getStopOrderPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getStopOrderList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getStopOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getStopOrderList Exception !");
			throw new RuntimeException("getStopOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllStopOrderList(StopOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<StopOrderVo>> dto = new BaseObjDto<List<StopOrderVo>>();
		try {
			BaseObjDto<List<StopOrderVo>> dataDto = baseDao.getList(StopOrderMapper.class, StopOrderVo.class,"getAllStopOrderList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllStopOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllStopOrderList Exception");
			throw new RuntimeException("getAllStopOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
