package com.jy.access.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.DeviceModelMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DeviceModelQueryForm;
import com.jy.entity.po.DeviceModel;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.DeviceModelVo;
@Service("deviceModelService")
@Scope("prototype")
public class DeviceModelServiceImpl implements DeviceModelService {
	private static final Logger log = Logger.getLogger(DeviceTypeServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceBrandService deviceBrandService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	@Autowired
	private DtKeyAttributevalueServie dtKeyAttributevalueServie;

	@Override
	public String addDeviceModel(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeviceModel deviceModel = JSON.parseObject(data,DeviceModel.class);
				if (deviceModel == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceModel.getModelName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备型号名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceModel.getIsDisplay() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("状态不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceModel.getDeviceBrandId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("品牌不能为空 !");
					return JSON.toJSONString(dto);
				}
			String returnBrandStr = deviceBrandService.getDeviceBrand(StringUtils.trim(deviceModel.getDeviceBrandId()));
			if(!FrameworkUtils.isSuccess(returnBrandStr)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数品牌Id不正确 !");
				return JSON.toJSONString(dto);
			}
				if (deviceModel.getDeviceTypeId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备类型不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnTypeStr = deviceTypeService.getDeviceType(StringUtils.trim(deviceModel.getDeviceTypeId()));
				if(!FrameworkUtils.isSuccess(returnTypeStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备类型Id不正确 !");
					return JSON.toJSONString(dto);
				}
				if(deviceModel.getKeyAttributeValueId()!= null){
					String returnDtkveStr = dtKeyAttributevalueServie.getDtKeyAttributevalue(StringUtils.trim(deviceModel.getKeyAttributeValueId()));
					if(!FrameworkUtils.isSuccess(returnDtkveStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("关键参数值Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				
				Date createDate = new Date();
				deviceModel.setCreateTime(createDate);
				dto = baseDao.insertSelective(DeviceModelMapper.class,deviceModel);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDeviceModel success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deviceModel.getDeviceModel_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDeviceModel failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeviceModel exception!");
				throw new RuntimeException("addDeviceModel Exception!");
			}
		} else {
			log.error("---addDeviceModel -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateDeviceModel(String deviceModelId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(deviceModelId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数deviceModelId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(deviceModelId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DeviceModel deviceModel = JSON.parseObject(data,DeviceModel.class);
				if (deviceModel == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				deviceModel.setDeviceModel_Id(deviceModelId);
				if(deviceModel.getDeviceBrandId()!= null){
					String returnBrandStr = deviceBrandService.getDeviceBrand(StringUtils.trim(deviceModel.getDeviceBrandId()));
					if(!FrameworkUtils.isSuccess(returnBrandStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数品牌Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if(deviceModel.getDeviceTypeId()!= null){
					String returnTypeStr = deviceTypeService.getDeviceType(StringUtils.trim(deviceModel.getDeviceTypeId()));
					if(!FrameworkUtils.isSuccess(returnTypeStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("设备类型Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				
				dto = baseDao.updateByPrimaryKeySelective(DeviceModelMapper.class, deviceModel);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateDeviceModel success!");
				} else {
					log.error("updateDeviceModel failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDeviceModel exception!");
				throw new RuntimeException("updateDeviceModel Exception!");
			}

		} else {
			log.error("---updateDeviceModel -------- data or deviceModelId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeviceModel(String deviceModelId) {
		String jsonStr = "";
		BaseObjDto<DeviceModelVo> dto = new BaseObjDto<DeviceModelVo>();
		try {
			if (StringUtils.isBlank(deviceModelId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数deviceTypeId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DeviceModelVo> deviceModelDto = baseDao
					.selectByPrimaryKey(DeviceModelMapper.class,
							StringUtils.trim(deviceModelId));
			if (FrameworkUtils.isSuccess(deviceModelDto)) {
				DeviceModelVo deviceModel = deviceModelDto.getData();
				dto.setData(deviceModel);
				FrameworkUtils.setSuccess(dto);
				log.info("getDeviceModel success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceModel failure");
			}
		} catch (Exception e) {
			log.error("getDeviceModel exception!");
			e.printStackTrace();
			throw new RuntimeException("getDeviceModel Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeviceModelList(DeviceModelQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DeviceModelVo>> dto = new BaseObjDto<ItemPage<DeviceModelVo>>();
		try {

			BaseObjDto<ItemPage<DeviceModelVo>> pagesDto = baseDao.getPageList(DeviceModelMapper.class, DeviceModelVo.class, form,"getDeviceModelPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDeviceModelList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceModelList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceModelList Exception !");
			throw new RuntimeException("getDeviceModelList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllDeviceModelList(DeviceModelQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceModel>> dto = new BaseObjDto<List<DeviceModel>>();
		try {
			BaseObjDto<List<DeviceModel>> dataDto = baseDao.getList(DeviceModelMapper.class, DeviceModel.class,"getAllDeviceModelList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeviceModelList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeviceModelList Exception");
			throw new RuntimeException("getAllDeviceModelList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
