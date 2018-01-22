package com.jy.access.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.CompanyDeviceModelQueryForm;
import com.jy.entity.form.DeviceQueryForm;
import com.jy.entity.po.CompanyDeviceModel;
import com.jy.entity.po.Device;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.DeviceAmountVo;
import com.jy.entity.vo.DeviceVo;
import com.jy.entity.vo.DevieAllotVo;
@Service("deviceService")
@Scope("prototype")
public class DeviceServiceImpl implements DeviceService {
	private static final Logger log = Logger.getLogger(DeviceServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeviceModelService deviceModelService;
	@Autowired
	private CompanyDeviceModelService companyDeviceModelService;

	@Override
	public String addDevice(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				Device device = JSON.parseObject(data,Device.class);
				if (device == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (device.getCompanyId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("所属公司不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCompanyStr = companyService.getCompany(StringUtils.trim(device.getCompanyId()));
				if(!FrameworkUtils.isSuccess(returnCompanyStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数公司Id不正确 !");
					return JSON.toJSONString(dto);
				}
				if (device.getDeviceModelId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("型号不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceModelStr = deviceModelService.getDeviceModel(StringUtils.trim(device.getDeviceModelId()));	
				if(!FrameworkUtils.isSuccess(returnDeviceModelStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数设备型号Id不正确 !");
					return JSON.toJSONString(dto);
				}
				
				if (device.getSequenceNum() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备序列号不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (device.getStatus() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备状态不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (device.getIsRealDevice() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备来源不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				Date createDate = new Date();
				device.setCreateTime(createDate);
				dto = baseDao.insertSelective(DeviceMapper.class,device);
				
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDevice success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(device.getDevice_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDevice failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDevice exception!");
				throw new RuntimeException("addDevice Exception!");
			}
		} else {
			log.error("---addDevice -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateDevice(String deviceId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(deviceId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数deviceId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(deviceId)
				&& StringUtils.isNotBlank(data)) {
			try {
				Device device = JSON.parseObject(data,Device.class);
				if (device == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				device.setDevice_Id(deviceId);
						
				if(device.getCompanyId()!= null){
					String returnCompanyStr = companyService.getCompany(StringUtils.trim(device.getCompanyId()));
					if(!FrameworkUtils.isSuccess(returnCompanyStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数公司Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if(device.getDeviceModelId()!= null){
					String returnDeviceModelStr = deviceModelService.getDeviceModel(StringUtils.trim(device.getDeviceModelId()));	
					if(!FrameworkUtils.isSuccess(returnDeviceModelStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数设备型号Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				
				dto = baseDao.updateByPrimaryKeySelective(DeviceMapper.class, device);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateDevice success!");
				} else {
					log.error("updateDevice failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDevice exception!");
				throw new RuntimeException("updateDevice Exception!");
			}

		} else {
			log.error("---updateDevice -------- data or deviceId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

	@Override
	public String updateDevieAllot(String deviceId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(deviceId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数deviceId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(deviceId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DevieAllotVo devieAllotVo = JSON.parseObject(data,DevieAllotVo.class);
				if (devieAllotVo == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if(devieAllotVo.getAllotType() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数allotType不能为空 !");
					return JSON.toJSONString(dto);
					
				}
				
				if(!devieAllotVo.isRightAllotType()){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数allotType不正确 !");
					return JSON.toJSONString(dto);
					
				}
		
				if(devieAllotVo.getCompanyId() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数companyId不能空!");
					return JSON.toJSONString(dto);
					
				}
				String queryCompanyStr = companyService.getCompany(StringUtils.trim(devieAllotVo.getCompanyId()));
				if(!FrameworkUtils.isSuccess(queryCompanyStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数companyId不正确!");
					return JSON.toJSONString(dto);
				}
				
				if(devieAllotVo.isNonexistenceAllotType()){
					CompanyDeviceModel updateCompanyDeviceModel = new CompanyDeviceModel();
					if(devieAllotVo.getDeviceModelId() == null){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("传入的参数deviceModelId不能空!");
						return JSON.toJSONString(dto);
						
					}
					String returnDeviceModelStr = 
							deviceModelService.getDeviceModel(StringUtils.trim(devieAllotVo.getDeviceModelId()));	
					if(!FrameworkUtils.isSuccess(returnDeviceModelStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数设备型号Id不正确 !");
						return JSON.toJSONString(dto);
					}
					CompanyDeviceModelQueryForm queryForm = new CompanyDeviceModelQueryForm();
					queryForm.setCompanyId(StringUtils.trim(devieAllotVo.getCompanyId()));
					queryForm.setDeviceModelId(StringUtils.trim(devieAllotVo.getDeviceModelId()));
					
					
				String queryCDMStr =companyDeviceModelService.getCompanyDeviceModelByCompanyIdAndDeviceModelId(JSON.toJSONString(queryForm));
				if(FrameworkUtils.isSuccess(queryCDMStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数allotType匹配（根据companyId和deviceModelId查询存在该公司型号信息）!");
					return JSON.toJSONString(dto);
				}
					
					if(devieAllotVo.getRentPerDay() == null){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数设备型号rentPerDay不能为空 !");
						return JSON.toJSONString(dto);
					}
					
					if(devieAllotVo.getRentPerWeek() == null){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数设备型号rentPerWeek不能为空 !");
						return JSON.toJSONString(dto);
					}
					
					
					if(devieAllotVo.getRentPerDay() == null){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数设备型号rentPerDay不能为空 !");
						return JSON.toJSONString(dto);
					}
					

					updateCompanyDeviceModel.setDeviceModelId(devieAllotVo.getDeviceModelId());
					updateCompanyDeviceModel.setCompanyId(devieAllotVo.getCompanyId());
					updateCompanyDeviceModel.setRentPerMonth(devieAllotVo.getRentPerMonth());
					updateCompanyDeviceModel.setRentPerWeek(devieAllotVo.getRentPerWeek());
					updateCompanyDeviceModel.setRentPerDay(devieAllotVo.getRentPerDay());
					updateCompanyDeviceModel.setStatus(EntityConstant.STATUS_VALID);
					updateCompanyDeviceModel.setCreateTime(new Date());
					
					jsonStr = companyDeviceModelService.addCompanyDeviceModel(JSON.toJSONString(updateCompanyDeviceModel));
					if(!FrameworkUtils.isSuccess(jsonStr)){
						return jsonStr;
					}
					
				}	
				Device updateDevice = new Device();
				updateDevice.setCompanyId(devieAllotVo.getCompanyId());
				updateDevice.setDevice_Id(deviceId);
						
				dto = baseDao.updateByPrimaryKeySelective(DeviceMapper.class, updateDevice);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateDevice success!");
				} else {
					log.error("updateDevice failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDevice exception!");
				throw new RuntimeException("updateDevice Exception!");
			}

		} else {
			log.error("---updateDevice -------- data or deviceId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDevice(String deviceId) {
		String jsonStr = "";
		BaseObjDto<DeviceVo> dto = new BaseObjDto<DeviceVo>();
		try {
			if (StringUtils.isBlank(deviceId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数deviceId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DeviceVo> deviceVoDto = baseDao
					.selectByPrimaryKey(DeviceMapper.class,
							StringUtils.trim(deviceId));
			if (FrameworkUtils.isSuccess(deviceVoDto)) {
				DeviceVo deviceVo = deviceVoDto.getData();
				dto.setData(deviceVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getDevice success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDevice failure");
			}
		} catch (Exception e) {
			log.error("getDevice exception!");
			e.printStackTrace();
			throw new RuntimeException("getDevice Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeviceList(DeviceQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DeviceVo>> dto = new BaseObjDto<ItemPage<DeviceVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("设备所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyIdArray()== null || (form.getCompanyIdArray()!= null && form.getCompanyIdArray().length<0)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("设备所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<ItemPage<DeviceVo>> pagesDto = baseDao.getPageList(DeviceMapper.class, DeviceVo.class, form,"getDevicePageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDeviceList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceList Exception !");
			throw new RuntimeException("getDeviceList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllDeviceList(DeviceQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceVo>> dto = new BaseObjDto<List<DeviceVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("设备所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyIdArray()== null || (form.getCompanyIdArray()!= null && form.getCompanyIdArray().length<0)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("设备所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setCompanyId(null);
				form.setCompanyIdArray(null);
			}
			BaseObjDto<List<DeviceVo>> dataDto = baseDao.getList(DeviceMapper.class, DeviceVo.class,"getAllDeviceList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeviceList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeviceList Exception");
			throw new RuntimeException("getAllDeviceList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	

	@Override
	public String getDeviceStatisticsList(DeviceQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceAmountVo>> dto = new BaseObjDto<List<DeviceAmountVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("统计的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyIdArray()== null || (form.getCompanyIdArray()!= null && form.getCompanyIdArray().length<0)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("统计的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<List<DeviceAmountVo>> dataDto = baseDao.getList(DeviceMapper.class, DeviceAmountVo.class,"getDeviceStatisticsList", form);
			if (FrameworkUtils.isSuccess(dataDto)&& dataDto.getData() != null) {
				List<DeviceAmountVo> queryDAmoutVoList = dataDto.getData();
				int allDeviceStatusAmount = 0;
				for(DeviceAmountVo deviceAmountVo : queryDAmoutVoList){
					allDeviceStatusAmount += deviceAmountVo.getAmount();
				}
				DeviceAmountVo allDeviceAmountVo = new DeviceAmountVo();
				allDeviceAmountVo.setStatus("all");
				allDeviceAmountVo.setAmount(allDeviceStatusAmount);
				dto = dataDto;
				dto.getData().add(allDeviceAmountVo);
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceStatisticsList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceStatisticsList Exception");
			throw new RuntimeException("getDeviceStatisticsList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeviceGroupByCompanyStatisticsList(DeviceQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ArrayList<ArrayList<DeviceAmountVo>>> dto = new BaseObjDto<ArrayList<ArrayList<DeviceAmountVo>>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("统计的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyIdArray()== null || (form.getCompanyIdArray()!= null && form.getCompanyIdArray().length<0)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("统计的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<List<DeviceAmountVo>> dataDto = baseDao.getList(DeviceMapper.class, DeviceAmountVo.class,"getDeviceGroupByCompanyStatisticsList", form);
			if (FrameworkUtils.isSuccess(dataDto)&& dataDto.getData() != null) {
				List<DeviceAmountVo> queryDAmoutVoList = dataDto.getData();
				HashMap<String,ArrayList<DeviceAmountVo>> cachDeviceAmountMap = new HashMap<String,ArrayList<DeviceAmountVo>>();
				for(DeviceAmountVo deviceAmountVo : queryDAmoutVoList){
					if(deviceAmountVo.getCompanyId()!= null){
						if(cachDeviceAmountMap.get(deviceAmountVo.getCompanyId()) == null){
							cachDeviceAmountMap.put(deviceAmountVo.getCompanyId(), new ArrayList<DeviceAmountVo>());
						}
						cachDeviceAmountMap.get(deviceAmountVo.getCompanyId()).add(deviceAmountVo);
					}
					
				}
					if(dto.getData() == null){
						dto.setData(new ArrayList<ArrayList<DeviceAmountVo>>());
					}
				for(String keyStr:cachDeviceAmountMap.keySet()){
					ArrayList<DeviceAmountVo> deviceAmountVoValue = cachDeviceAmountMap.get(keyStr);
					dto.getData().add(deviceAmountVoValue);
					
					
				}
				FrameworkUtils.setSuccess(dto);
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceGroupByCompanyStatisticsList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceGroupByCompanyStatisticsList Exception");
			throw new RuntimeException("getDeviceGroupByCompanyStatisticsList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

}
