package com.jy.access.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.DeviceSpecificationMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DeviceSpecificationQueryForm;
import com.jy.entity.po.DeviceSpecification;
import com.jy.entity.pojo.SuccessReturnPojo;
@Service("deviceSpecificationService")
@Scope("prototype")
public class DeviceSpecificationServiceImpl implements
		DeviceSpecificationService {
	private static final Logger log = Logger.getLogger(DeviceSpecificationServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;

	/**
	 * 增加规格信息
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午4:05:41
	 * @param data
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceSpecificationService#addDeviceSpecification(java.lang.String)
	 */
	@Override
	public String addDeviceSpecification(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeviceSpecification deviceSpecification = JSON.parseObject(
						data, DeviceSpecification.class);
				if (deviceSpecification == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数不正确!");
					return JSON.toJSONString(dto);
				}
				if (deviceSpecification.getdSpecificationName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("规格名称不能为空!");
					return JSON.toJSONString(dto);
				}
				if (deviceSpecification.getStatus() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("状态不能为空!");
					return JSON.toJSONString(dto);
				}
				Date createDate = new Date();
				deviceSpecification.setCreateTime(createDate);

				dto = baseDao.insertSelective(DeviceSpecificationMapper.class,
						deviceSpecification);
				if(FrameworkUtils.isSuccess(dto)){
					log.info("addDeviceSpecification success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deviceSpecification.getdSpecification_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				}else{
					log.error("addDeviceSpecification failure!");
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeviceSpecification exception!");
				throw new RuntimeException("addDeviceSpecification Exception!");
			}
		} else {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数为空!");
			log.error("---addDeviceSpecification -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	/**
	 * 修改规格信息
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午4:05:55
	 * @param dSpecificationId
	 * @param data
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceSpecificationService#updateDeviceSpecification(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String updateDeviceSpecification(String dSpecificationId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(dSpecificationId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入参数dSpecificationId为空!");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(dSpecificationId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DeviceSpecification deviceSpecification = JSON.parseObject(
						data, DeviceSpecification.class);
				if (deviceSpecification == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数不正确 !");
					return JSON.toJSONString(dto);
				}
				deviceSpecification.setdSpecification_Id(dSpecificationId);
				dto = baseDao.updateByPrimaryKeySelective(
						DeviceSpecificationMapper.class, deviceSpecification);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDeviceSpecification exception!");
				throw new RuntimeException(
						"updateDeviceSpecification Exception!");
			}

		} else {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入参数为空!");
			log.error("---updateDeviceSpecification -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	/**
	 * 获取规格信息
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午4:06:09
	 * @param dSpecificationId
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceSpecificationService#getDeviceSpecification(java.lang.String)
	 */
	@Override
	public String getDeviceSpecification(String dSpecificationId) {
		String jsonStr = "";
		BaseObjDto<DeviceSpecification> dto = new BaseObjDto<DeviceSpecification>();
		try {
			if (StringUtils.isBlank(dSpecificationId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数dSpecificationId为空");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DeviceSpecification> deviceSpecificationDto = baseDao
					.selectByPrimaryKey(DeviceSpecificationMapper.class,
							StringUtils.trim(dSpecificationId));
			if (FrameworkUtils.isSuccess(deviceSpecificationDto)) {
				DeviceSpecification deviceSpecification = deviceSpecificationDto
						.getData();
				dto.setData(deviceSpecification);
				FrameworkUtils.setSuccess(dto);
				log.info("getDeviceSpecification success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceSpecification failure");
			}
		} catch (Exception e) {
			log.error("getDeviceSpecification exception!");
			e.printStackTrace();
			throw new RuntimeException("getDeviceSpecification Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	/**
	 * 获取分页的设备规格列表
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午4:02:16
	 * @param form
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceSpecificationService#getDeviceSpecificationList(com.jy.entity.form.DeviceSpecificationQueryForm)
	 */
	@Override
	public String getDeviceSpecificationList(DeviceSpecificationQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DeviceSpecification>> dto = new BaseObjDto<ItemPage<DeviceSpecification>>();
		try {

			BaseObjDto<ItemPage<DeviceSpecification>> pagesDto = baseDao
					.getPageList(DeviceSpecificationMapper.class,
							DeviceSpecification.class, form,
							"getDeviceSpecificationPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDeviceSpecificationList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceSpecificationList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceSpecificationList Exception !");
			throw new RuntimeException("getDeviceSpecificationList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	/**
	 * 获取所以规格信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午4:03:49
	 * @param form
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceSpecificationService#getAllDeviceSpecificationList(com.jy.entity.form.DeviceSpecificationQueryForm)
	 */
	@Override
	public String getAllDeviceSpecificationList(
			DeviceSpecificationQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceSpecification>> dto = new BaseObjDto<List<DeviceSpecification>>();
		try {
			BaseObjDto<List<DeviceSpecification>> dataDto = baseDao.getList(
					DeviceSpecificationMapper.class, DeviceSpecification.class,
					"getAllDeviceSpecificationList", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeviceSpecificationList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeviceSpecificationList Exception");
			throw new RuntimeException(
					"getAllDeviceSpecificationList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	/**
	 * 根据设备类型Id查询所有规格信息
	 * 
	 * @author liukh
	 * @date 2017-7-10 下午4:04:45
	 * @param form
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceSpecificationService#getAllDeviceSpecificationListByDeviceTypeId(com.jy.entity.form.DeviceSpecificationQueryForm)
	 */
	@Override
	public String getAllDeviceSpecificationListByDeviceTypeId(
			DeviceSpecificationQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceSpecification>> dto = new BaseObjDto<List<DeviceSpecification>>();
		try {
			BaseObjDto<List<DeviceSpecification>> dataDto = baseDao.getList(
					DeviceSpecificationMapper.class, DeviceSpecification.class,
					"getAllDeviceSpecificationListByDeviceTypeId", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;
				log.error("getAllDeviceSpecificationListByDeviceTypeId success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeviceSpecificationListByDeviceTypeId failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeviceSpecificationListByDeviceTypeId Exception");
			throw new RuntimeException(
					"getAllDeviceSpecificationListByDeviceTypeId Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
