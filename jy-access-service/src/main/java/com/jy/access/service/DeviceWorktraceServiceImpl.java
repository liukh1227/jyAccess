package com.jy.access.service;

import java.util.ArrayList;
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
import com.jy.access.mapper.DeviceMapper;
import com.jy.access.mapper.DeviceWorktraceMapper;
import com.jy.access.util.TimeUtils;
import com.jy.base.common.utils.CommonUtils;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DeviceQueryForm;
import com.jy.entity.po.DeviceWorktrace;
import com.jy.entity.po.DeviceWorktraceMonthDate;
import com.jy.entity.vo.DeviceAmountVo;
import com.jy.entity.vo.DeviceInMakesureOrderVo;
import com.jy.entity.vo.DeviceModelVo;
import com.jy.entity.vo.DeviceOutMakesureOrderVo;
import com.jy.entity.vo.DeviceVo;

@Service("deviceWorktraceService")
@Scope("prototype")
public class DeviceWorktraceServiceImpl implements DeviceWorktraceService {
	private static final Logger log = Logger.getLogger(DeviceWorktraceServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeviceModelService deviceModelService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ContractOrderService contractOrderService;
	@Autowired
	private DeviceInMakesureOrderService deviceInMakesureOrderService;
	@Autowired
	private DeviceOutMakesureOrderService deviceOutMakesureOrderService;
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String addDeviceWorktraceUseProcess(DeviceWorktrace deviceWorktrace) {
		BaseDto dto = new BaseDto();
		if (deviceWorktrace != null) {
			try {
				 
				if (deviceWorktrace.getContractOrderId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数签约单Id(contractOrderId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService
						.getContractOrder(StringUtils
								.trim(deviceWorktrace
										.getContractOrderId()));
				if (!FrameworkUtils.isSuccess(returnContractOrdertr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getDeviceId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备Id(deviceId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				String returnDeviceStr = deviceService.getDevice(StringUtils.trim(deviceWorktrace.getDeviceId()));
				if (!FrameworkUtils.isSuccess(returnDeviceStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备Id(deviceId)不正确 !");
					return JSON.toJSONString(dto);
				}
				
				BaseObjDto<DeviceVo> deviceVoDto = JSON.parseObject(returnDeviceStr, BaseObjDto.class);
				if(deviceVoDto != null && deviceVoDto.getData()!= null ){
					DeviceVo deviceVo = JSON.parseObject(JSON.toJSONString(deviceVoDto.getData()), DeviceVo.class);
					if(deviceVo != null){
						//填充设备型号
						deviceWorktrace.setDeviceModelId(deviceVo.getDeviceModelId());
						deviceWorktrace.setCompanyId(deviceVo.getCompanyId());	
						//填充设备类型
						if(deviceVo.getDeviceModelId()!= null){
							String returnModelStr = deviceModelService.getDeviceModel(StringUtils.trim(deviceVo.getDeviceModelId()));
							BaseObjDto<DeviceModelVo> queryModelDto = JSON.parseObject(returnModelStr, BaseObjDto.class);
							if(queryModelDto != null && queryModelDto.getData()!= null ){
								DeviceModelVo deviceModelVo = JSON.parseObject(JSON.toJSONString(queryModelDto.getData()), DeviceModelVo.class);
								if(deviceModelVo!= null){
									deviceWorktrace.setDeviceTypeId(deviceModelVo.getDeviceTypeId());
								}
							}
						}
						
								
						//公司总设备数量
						DeviceQueryForm deviceCompanyQueryForm = new DeviceQueryForm();
						deviceCompanyQueryForm.setCompanyId(deviceWorktrace.getCompanyId());
						BaseObjDto<List<DeviceAmountVo>> companyAmountDataDto = baseDao.getList(DeviceMapper.class, DeviceAmountVo.class,"getDeviceGroupByCompanyStatisticsList", deviceCompanyQueryForm);
						int deviceCompanyAmount = 0;
						if (FrameworkUtils.isSuccess(companyAmountDataDto)&& companyAmountDataDto.getData() != null) {
							List<DeviceAmountVo> queryCompanyAmoutVoList = companyAmountDataDto.getData();
							for(DeviceAmountVo deviceAmountVo : queryCompanyAmoutVoList){
								if(deviceAmountVo!= null && deviceAmountVo.isNotDiscard()){
									deviceCompanyAmount += deviceAmountVo.getAmount();
								}
							}
							}
						
						deviceWorktrace.setAvaliableDeviceCount(deviceCompanyAmount);
						
						//公司某型号设备数量
						DeviceQueryForm deviceModelQueryForm = new DeviceQueryForm();
						deviceModelQueryForm.setCompanyId(deviceWorktrace.getCompanyId());
						deviceModelQueryForm.setDeviceModelId(deviceWorktrace.getDeviceModelId());
						BaseObjDto<List<DeviceAmountVo>> modelAmountDataDto = baseDao.getList(DeviceMapper.class, DeviceAmountVo.class,"getDeviceGroupByCompanyStatisticsList", deviceModelQueryForm);
						int deviceModelAmount = 0;
						if (FrameworkUtils.isSuccess(modelAmountDataDto)&& modelAmountDataDto.getData() != null) {
							List<DeviceAmountVo> queryModelAmoutVoList = modelAmountDataDto.getData();
							for(DeviceAmountVo deviceAmountVo : queryModelAmoutVoList){
								if(deviceAmountVo!= null && deviceAmountVo.isNotDiscard()){
									deviceModelAmount += deviceAmountVo.getAmount();
								}
							}
						}
						deviceWorktrace.setModelAvaliableDeviceCount(deviceModelAmount);
						
						//公司某类型设备数量
						DeviceQueryForm deviceTypeQueryForm = new DeviceQueryForm();
						deviceTypeQueryForm.setCompanyId(deviceWorktrace.getCompanyId());
						deviceTypeQueryForm.setDeviceTypeId(deviceWorktrace.getDeviceTypeId());
						BaseObjDto<List<DeviceAmountVo>> typeAmountDataDto = baseDao.getList(DeviceMapper.class, DeviceAmountVo.class,"getDeviceGroupByCompanyStatisticsList", deviceTypeQueryForm);
						int deviceTypeAmount = 0;
						if (FrameworkUtils.isSuccess(typeAmountDataDto)&& typeAmountDataDto.getData() != null) {
							List<DeviceAmountVo> queryTypeAmoutVoList = typeAmountDataDto.getData();
							for(DeviceAmountVo deviceAmountVo : queryTypeAmoutVoList){
								if(deviceAmountVo!= null && deviceAmountVo.isNotDiscard()){
									deviceTypeAmount += deviceAmountVo.getAmount();
								}
							}
						}
						deviceWorktrace.setTypeAvaliableDeviceCount(deviceTypeAmount);
						
					}
					
				}
				
				
				if (deviceWorktrace.getdInMakeSureId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数进场确认单Id(dInMakeSureId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDInMakeSureIdStr = deviceInMakesureOrderService
						.getDeviceInMakesureOrder(StringUtils
								.trim(deviceWorktrace.getdInMakeSureId()));
				if (!FrameworkUtils.isSuccess(returnDInMakeSureIdStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数进场确认单Id(dInMakeSureId)不正确 !");
					return JSON.toJSONString(dto);
				}else{
					BaseObjDto<DeviceInMakesureOrderVo> queryDInMakesureOrderDto = JSON.parseObject(returnDInMakeSureIdStr, BaseObjDto.class);
					if(queryDInMakesureOrderDto != null && queryDInMakesureOrderDto.getData()!= null ){
						DeviceInMakesureOrderVo queryDeviceInMakesureOrderVo = JSON.parseObject(JSON.toJSONString(queryDInMakesureOrderDto.getData()), DeviceInMakesureOrderVo.class);
						if(queryDeviceInMakesureOrderVo!= null && queryDeviceInMakesureOrderVo.getEnterTime()!= null){
							deviceWorktrace.setdEnterTime(queryDeviceInMakesureOrderVo.getEnterTime());
						}
						if(queryDeviceInMakesureOrderVo!= null && queryDeviceInMakesureOrderVo.getDeviceId()!= null && !queryDeviceInMakesureOrderVo.getDeviceId().equals(StringUtils.trim(deviceWorktrace.getDeviceId()))){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo(".新增工作设备跟踪时传入的参数进场确认单 dInMakeSureId 和 deviceId 不匹配!");
							return JSON.toJSONString(dto);
						}
					}
					
				
				}
				if (deviceWorktrace.getdOutMakeSureId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数退场确认单Id(dOutMakeSureId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				String returnDOutMakeSureIdStr = deviceOutMakesureOrderService
						.getDeviceOutMakesureOrder(StringUtils
								.trim(deviceWorktrace.getdOutMakeSureId()));
				if (!FrameworkUtils.isSuccess(returnDOutMakeSureIdStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数退场确认单Id(dOutMakeSureId)不正确 !");
					return JSON.toJSONString(dto);
				}else{
					BaseObjDto<DeviceOutMakesureOrderVo> queryDOutMakesureOrderDto = JSON.parseObject(returnDOutMakeSureIdStr, BaseObjDto.class);
					if(queryDOutMakesureOrderDto != null && queryDOutMakesureOrderDto.getData()!= null ){
						DeviceOutMakesureOrderVo queryDeviceOutMakesureOrderVo = JSON.parseObject(JSON.toJSONString(queryDOutMakesureOrderDto.getData()), DeviceOutMakesureOrderVo.class);
						if(queryDeviceOutMakesureOrderVo!= null && queryDeviceOutMakesureOrderVo.getDeviceId()!= null && !queryDeviceOutMakesureOrderVo.getDeviceId().equals(StringUtils.trim(deviceWorktrace.getDeviceId()))){
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo(".新增工作设备跟踪时传入的参数退场确认单 dOutMakeSureId 和 deviceId 不匹配!");
							return JSON.toJSONString(dto);
						}
					}
				}
				if (deviceWorktrace.getDeviceModelId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备型号Id(deviceModelId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceModelStr = deviceModelService
						.getDeviceModel(StringUtils.trim(deviceWorktrace
								.getDeviceModelId()));
				if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备型号Id(deviceModelId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getModelAvaliableDeviceCount() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备型号数量(modelAvaliableDeviceCount)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getDeviceTypeId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备类型Id(deviceTypeId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnTypeStr = deviceTypeService.getDeviceType(StringUtils.trim(deviceWorktrace.getDeviceTypeId()));
				if(!FrameworkUtils.isSuccess(returnTypeStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备型号Id(deviceModelId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getTypeAvaliableDeviceCount() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备类型数量(typeAvaliableDeviceCount)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getCompanyId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数公司Id(companyId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCompanyStr = companyService.getCompany(StringUtils.trim(deviceWorktrace.getCompanyId()));
				if(!FrameworkUtils.isSuccess(returnCompanyStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数公司Id(companyId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getAvaliableDeviceCount() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备数量(avaliableDeviceCount)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getdEnterTime()== null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备进场时间(dEnterTime)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getdExitTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备退场时间(dExitTime)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if(deviceWorktrace.getdEnterTime().getTime()>deviceWorktrace.getdExitTime().getTime()){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(".新增工作设备跟踪时传入的参数设备退场时间(dExitTime)不能小于进场时间(dEnterTime) !");
					return JSON.toJSONString(dto);
				}
				//处理时间(增加每月发的天数，开始时间，结束时间，月份什么的
				 String pattern = "yyyy-MM-dd";
			     String	beginDate = CommonUtils.getDateString(deviceWorktrace.getdEnterTime(), pattern);
			    String	endDate = CommonUtils.getDateString(deviceWorktrace.getdExitTime(), pattern);
				 List<DeviceWorktraceMonthDate> processDWorkMonthDateList = TimeUtils.getDeviceWorktraceMonthDateList(beginDate, endDate);
				List<DeviceWorktrace> processDWorktraceList = new ArrayList<DeviceWorktrace>();
				for(DeviceWorktraceMonthDate dWorktraceMonthDate:processDWorkMonthDateList){
					DeviceWorktrace newDeviceWorktrace = new DeviceWorktrace();
					newDeviceWorktrace.copyFromOther(deviceWorktrace, dWorktraceMonthDate);
					processDWorktraceList.add(newDeviceWorktrace);
				}
				
				for(int index = 0;index<processDWorktraceList.size();index++){
					DeviceWorktrace dWorktrace = processDWorktraceList.get(index);
					if (dWorktrace.getWorkDay() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo(".新增工作设备跟踪时传入的参数设备工作天数(workDay)不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (dWorktrace.getInMonth() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo(".新增工作设备跟踪时传入的参数设备工作所在月份(inMonth)不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (dWorktrace.getInYear() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo(".新增工作设备跟踪时传入的参数设备工作所在年份(inYear)不能为空 !");
						return JSON.toJSONString(dto);
					}
					
					if (dWorktrace.getMonthStartDate() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo(".新增工作设备跟踪时传入的参数设备工作所在月份开始时间(monthStartDate)不能为空 !");
						return JSON.toJSONString(dto);
					}
					
					if (dWorktrace.getMonthEndDate() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo(".新增工作设备跟踪时传入的参数设备工作所在月份结束(monthEndDate)不能为空 !");
						return JSON.toJSONString(dto);
					}
					
				
					if(dWorktrace.getStatus() == null){
						dWorktrace.setStatus(EntityConstant.STATUS_VALID);
					}
					String returJsonStr =addDeviceWorktrace(JSON.toJSONString(dWorktrace));
					if(!FrameworkUtils.isSuccess(returJsonStr)){
						return returJsonStr;
					}
					if(processDWorktraceList.size() ==( index+1)){
						return returJsonStr;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeviceWorktrace exception!");
				throw new RuntimeException(
						"addDeviceWorktrace Exception!");
			}
		} else {
			log.error("---addDeviceWorktrace -------- data is null ==== ");
		}
		
		return JSON.toJSONString(dto);
	}

	@Override
	public String addDeviceWorktrace(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeviceWorktrace deviceWorktrace = JSON.parseObject(data, DeviceWorktrace.class);
				if (deviceWorktrace == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getContractOrderId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数签约单Id(contractOrderId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getDeviceId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备Id(deviceId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getdInMakeSureId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数进场确认单Id(dInMakeSureId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getdOutMakeSureId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数退场确认单Id(dOutMakeSureId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getDeviceModelId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备型号Id(deviceModelId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getModelAvaliableDeviceCount() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备型号数量(modelAvaliableDeviceCount)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getDeviceTypeId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备类型Id(deviceTypeId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getTypeAvaliableDeviceCount() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备类型数量(typeAvaliableDeviceCount)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getCompanyId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数公司Id(companyId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getAvaliableDeviceCount() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备数量(avaliableDeviceCount)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getWorkDay() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备工作天数(workDay)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getInMonth() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备工作所在月份(inMonth)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceWorktrace.getInYear() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备工作所在年份(inYear)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getMonthStartDate() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备工作所在月份开始时间(monthStartDate)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getMonthEndDate() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备工作所在月份结束(monthEndDate)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getdEnterTime()== null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备进场时间(dEnterTime)不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if (deviceWorktrace.getdExitTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新增工作设备跟踪时传入的参数设备退场时间(dExitTime)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(deviceWorktrace.getStatus() == null){
					deviceWorktrace.setStatus(EntityConstant.STATUS_VALID);
				}
				
				dto = baseDao.insertSelective(DeviceWorktraceMapper.class,deviceWorktrace);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDeviceWorktrace success!");
				} else {
					log.error("addDeviceWorktrace failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeviceWorktrace exception!");
				throw new RuntimeException(
						"addDeviceWorktrace Exception!");
			}
		} else {
			log.error("---addDeviceWorktrace -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeviceWorktraceService(String dWorkTraceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
