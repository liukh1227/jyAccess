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
import com.jy.access.mapper.DeviceTypeMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DeviceTypeQueryForm;
import com.jy.entity.po.DeviceType;
import com.jy.entity.pojo.DeviceTypePojo;
import com.jy.entity.pojo.SuccessReturnPojo;

@Service("deviceTypeService")
@Scope("prototype")
public class DeviceTypeServiceImpl implements DeviceTypeService {
	private static final Logger log = Logger.getLogger(DeviceTypeServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;

	@Override
	public String addDeviceType(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeviceType deviceType = JSON.parseObject(data,DeviceType.class);
				if (deviceType == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceType.getTypeName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备类型名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceType.getIsDisplay() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("状态不能为空 !");
					return JSON.toJSONString(dto);
				}
				Date createDate = new Date();
				deviceType.setCreateTime(createDate);
				dto = baseDao.insertSelective(DeviceTypeMapper.class,deviceType);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDeviceType success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deviceType.getDeviceType_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDeviceType failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeviceType exception!");
				throw new RuntimeException("addDeviceType Exception!");
			}
		} else {
			log.error("---addDeviceType -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateDeviceType(String deviceTypeId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(deviceTypeId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数deviceTypeId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(deviceTypeId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DeviceType deviceType = JSON.parseObject(data,
						DeviceType.class);
				if (deviceType == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				deviceType.setDeviceType_Id(deviceTypeId);
				dto = baseDao.updateByPrimaryKeySelective(DeviceTypeMapper.class, deviceType);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateDeviceType success!");
				} else {
					log.error("updateDeviceType failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDeviceType exception!");
				throw new RuntimeException("updateDeviceType Exception!");
			}

		} else {
			log.error("---updateDeviceType -------- data or deviceTypeId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeviceType(String deviceTypeId) {
		String jsonStr = "";
		BaseObjDto<DeviceType> dto = new BaseObjDto<DeviceType>();
		try {
			if (StringUtils.isBlank(deviceTypeId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数deviceTypeId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DeviceType> deviceTypeDto = baseDao
					.selectByPrimaryKey(DeviceTypeMapper.class,
							StringUtils.trim(deviceTypeId));
			if (FrameworkUtils.isSuccess(deviceTypeDto)) {
				DeviceType deviceType = deviceTypeDto.getData();
				dto.setData(deviceType);
				FrameworkUtils.setSuccess(dto);
				log.info("getDeviceType success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceType failure");
			}
		} catch (Exception e) {
			log.error("getDeviceType exception!");
			e.printStackTrace();
			throw new RuntimeException("getDeviceType Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDeviceTypeList(DeviceTypeQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DeviceType>> dto = new BaseObjDto<ItemPage<DeviceType>>();
		try {

			BaseObjDto<ItemPage<DeviceType>> pagesDto = baseDao.getPageList(DeviceTypeMapper.class, DeviceType.class, form,"getDeviceTypePageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDeviceTypeList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceTypeList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceTypeList Exception !");
			throw new RuntimeException("getDeviceTypeList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllDeviceTypeList(DeviceTypeQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceType>> dto = new BaseObjDto<List<DeviceType>>();
		try {
			BaseObjDto<List<DeviceType>> dataDto = baseDao.getList(DeviceTypeMapper.class, DeviceType.class,"getAllDeviceTypeList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeviceTypeList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeviceTypeList Exception");
			throw new RuntimeException("getAllDeviceTypeList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllLeveDeviceTypeList(DeviceTypeQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceTypePojo>> dto = new BaseObjDto<List<DeviceTypePojo>>();
		try {
			BaseObjDto<List<DeviceTypePojo>> dataDto = baseDao.getList(DeviceTypeMapper.class, DeviceTypePojo.class,"getAllLeveDeviceTypeList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllLeveDeviceTypeList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllLeveDeviceTypeList Exception");
			throw new RuntimeException("getAllLeveDeviceTypeList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	

}
