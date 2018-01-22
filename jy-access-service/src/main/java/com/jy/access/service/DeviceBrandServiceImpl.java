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
import com.jy.access.mapper.DeviceBrandMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DeviceBrandQueryForm;
import com.jy.entity.po.DeviceBrand;
import com.jy.entity.pojo.SuccessReturnPojo;

@Service("deviceBrandService")
@Scope("prototype")
public class DeviceBrandServiceImpl implements DeviceBrandService {
	private static final Logger log = Logger.getLogger(DeviceBrandServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;

	/**
	 * 增加品牌信息
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:37:20
	 * @param data
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceBrandService#addDeviceBrand(java.lang.String)
	 */
	@Override
	public String addDeviceBrand(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DeviceBrand deviceBrand = JSON.parseObject(data,
						DeviceBrand.class);
				if (deviceBrand == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (deviceBrand.getBrandName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("品牌名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				Date createDate = new Date();
				deviceBrand.setCreateTime(createDate);
				dto = baseDao.insertSelective(DeviceBrandMapper.class,
						deviceBrand);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDeviceBrand success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(deviceBrand.getDeviceBrand_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDeviceBrand failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDeviceBrand exception!");
				throw new RuntimeException("addDeviceBrand Exception!");
			}
		} else {
			log.error("---addDeviceBrand -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	/**
	 * 更新品牌信息
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:36:16
	 * @param deviceBrandId
	 * @param data
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceBrandService#updateDeviceBrand(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String updateDeviceBrand(String deviceBrandId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(deviceBrandId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数deviceBrandId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(deviceBrandId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DeviceBrand deviceBrand = JSON.parseObject(data,
						DeviceBrand.class);
				if (deviceBrand == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				deviceBrand.setDeviceBrand_Id(deviceBrandId);
				dto = baseDao.updateByPrimaryKeySelective(
						DeviceBrandMapper.class, deviceBrand);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateDeviceBrand success!");
				} else {
					log.error("updateDeviceBrand failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDeviceBrand exception!");
				throw new RuntimeException("updateDeviceBrand Exception!");
			}

		} else {
			log.error("---updateDeviceBrand -------- data or deviceBrandId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	/**
	 * 获取品牌信息
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:36:50
	 * @param deviceBrandId
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceBrandService#getDeviceBrand(java.lang.String)
	 */
	@Override
	public String getDeviceBrand(String deviceBrandId) {
		String jsonStr = "";
		BaseObjDto<DeviceBrand> dto = new BaseObjDto<DeviceBrand>();
		try {
			if (StringUtils.isBlank(deviceBrandId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数deviceBrandId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DeviceBrand> deviceBrandDto = baseDao
					.selectByPrimaryKey(DeviceBrandMapper.class,
							StringUtils.trim(deviceBrandId));
			if (FrameworkUtils.isSuccess(deviceBrandDto)) {
				DeviceBrand deviceBrand = deviceBrandDto.getData();
				dto.setData(deviceBrand);
				FrameworkUtils.setSuccess(dto);
				log.info("getDeviceBrand success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceBrand failure");
			}
		} catch (Exception e) {
			log.error("getDeviceBrand exception!");
			e.printStackTrace();
			throw new RuntimeException("getDeviceBrand Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	/**
	 * 获取分页的品牌信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-6 下午10:33:02
	 * @param form
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.DeviceBrandService#getDeviceBrandList(com.jy.entity.form.DeviceBrandQueryForm)
	 */
	@Override
	public String getDeviceBrandList(DeviceBrandQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DeviceBrand>> dto = new BaseObjDto<ItemPage<DeviceBrand>>();
		try {

			BaseObjDto<ItemPage<DeviceBrand>> pagesDto = baseDao.getPageList(
					DeviceBrandMapper.class, DeviceBrand.class, form,
					"getDeviceBrandPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDeviceBrandList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDeviceBrandList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDeviceBrandList Exception !");
			throw new RuntimeException("getDeviceBrandList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
/**
 * 获取所有的品牌信息
 * @author liukh
 * @date 2017-7-7 上午10:35:47 
 * @param form
 * @return
 * (non-Javadoc)
 * @see com.jy.access.service.DeviceBrandService#getAllDeviceBrandList(com.jy.entity.form.DeviceBrandQueryForm)
 */
	@Override
	public String getAllDeviceBrandList(DeviceBrandQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DeviceBrand>> dto = new BaseObjDto<List<DeviceBrand>>();
		try {
			BaseObjDto<List<DeviceBrand>> dataDto = baseDao.getList(
					DeviceBrandMapper.class, DeviceBrand.class,
					"getAllDeviceBrandList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDeviceBrandList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDeviceBrandList Exception");
			throw new RuntimeException("getAllDeviceBrandList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
