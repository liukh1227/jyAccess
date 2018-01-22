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
import com.jy.access.mapper.DtKeyAttributespecificationMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DtKeyAttributeSpecificationQueryForm;
import com.jy.entity.po.DtKeyAttributespecification;
import com.jy.entity.pojo.SuccessReturnPojo;
@Service("dtKeyAttributespecificationService")
@Scope("prototype")
public class DtKeyAttributespecificationServiceImpl implements DtKeyAttributespecificationService {
	private static final Logger log = Logger.getLogger(DtKeyAttributespecificationServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceTypeService deviceTypeService;
/**
 * 增加关键规格属性信息列表
 * @author liukh
 * @date 2017-7-10 下午5:45:43 
 * @param data
 * @return
 * (non-Javadoc)
 * @see com.jy.access.service.DtKeyAttributespecificationService#addDtKeyAttributespecification(java.lang.String)
 */
	@Override
	public String addDtKeyAttributespecification(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DtKeyAttributespecification dtKeyAttributespecification = JSON.parseObject(data, DtKeyAttributespecification.class);
				if (dtKeyAttributespecification == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数不正确!");
					return JSON.toJSONString(dto);
				}
				if (dtKeyAttributespecification.getdTKeyAttributeSpecName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo(" 关键属性名称不能为空!");
					return JSON.toJSONString(dto);
				}
				if (dtKeyAttributespecification.getStatus() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("状态不能为空!");
					return JSON.toJSONString(dto);
				}
				if (dtKeyAttributespecification.getDeviceTypeId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备类型不能为空!");
					return JSON.toJSONString(dto);
				}
			
				String returnTypeStr = deviceTypeService.getDeviceType(StringUtils.trim(dtKeyAttributespecification.getDeviceTypeId()));
				if(!FrameworkUtils.isSuccess(returnTypeStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备类型Id不正确 !");
					return JSON.toJSONString(dto);
				}
					Date createDate = new Date();
					dtKeyAttributespecification.setCreateTime(createDate);

					dto = baseDao.insertSelective(DtKeyAttributespecificationMapper.class,
							dtKeyAttributespecification);
					if(FrameworkUtils.isSuccess(dto)){
						log.info("addDtKeyAttributespecification success!");
						BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
						SuccessReturnPojo successPojo = new SuccessReturnPojo();
						successPojo.setId(dtKeyAttributespecification.getdTKeyAttributeSpec_Id());
						successPojo.setCreateTime(createDate);
						returnDto.setData(successPojo);
						FrameworkUtils.setSuccess(returnDto);
						return JSON.toJSONString(returnDto);
					}else{
						log.error("addDtKeyAttributespecification failure!");
					}
				
					
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDtKeyAttributespecification exception!");
				throw new RuntimeException("addDtKeyAttributespecification Exception!");
			}
		} else {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数为空!");
			log.error("---addDtKeyAttributespecification -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
/**
 * 更新关键规格属性信息列表
 * @author liukh
 * @date 2017-7-10 下午5:45:25 
 * @param dTKeyAttributeSpecId
 * @param data
 * @return
 * (non-Javadoc)
 * @see com.jy.access.service.DtKeyAttributespecificationService#updateDtKeyAttributespecification(java.lang.String, java.lang.String)
 */
	@Override
	public String updateDtKeyAttributespecification(
			String dTKeyAttributeSpecId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(dTKeyAttributeSpecId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入参数dTKeyAttributeSpecId为空!");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(dTKeyAttributeSpecId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DtKeyAttributespecification dtKeyAttributespecification = JSON.parseObject(
						data, DtKeyAttributespecification.class);
				if (dtKeyAttributespecification == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数不正确 !");
					return JSON.toJSONString(dto);
				}
				dtKeyAttributespecification.setdTKeyAttributeSpec_Id(dTKeyAttributeSpecId);
			
				dto = baseDao.updateByPrimaryKeySelective(
						DtKeyAttributespecificationMapper.class, dtKeyAttributespecification);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDtKeyAttributespecification exception!");
				throw new RuntimeException(
						"updateDtKeyAttributespecification Exception!");
			}

		} else {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入参数为空!");
			log.error("---updateDtKeyAttributespecification -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
/**
 * 获取关键规格属性信息
 * @author liukh
 * @date 2017-7-10 下午5:45:00 
 * @param dTKeyAttributeSpecId
 * @return
 * (non-Javadoc)
 * @see com.jy.access.service.DtKeyAttributespecificationService#getDtKeyAttributespecification(java.lang.String)
 */
	@Override
	public String getDtKeyAttributespecification(String dTKeyAttributeSpecId) {
		String jsonStr = "";
		BaseObjDto<DtKeyAttributespecification> dto = new BaseObjDto<DtKeyAttributespecification>();
		try {
			if (StringUtils.isBlank(dTKeyAttributeSpecId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数dTKeyAttributeSpecId为空");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DtKeyAttributespecification> dtKeyAttributespecificationDto = baseDao
					.selectByPrimaryKey(DtKeyAttributespecificationMapper.class,
							StringUtils.trim(dTKeyAttributeSpecId));
			if (FrameworkUtils.isSuccess(dtKeyAttributespecificationDto)) {
				DtKeyAttributespecification dtKeyAttributespecification = dtKeyAttributespecificationDto
						.getData();
				dto.setData(dtKeyAttributespecification);
				FrameworkUtils.setSuccess(dto);
				log.info("getDtKeyAttributespecification success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDtKeyAttributespecification failure");
			}
		} catch (Exception e) {
			log.error("getDtKeyAttributespecification exception!");
			e.printStackTrace();
			throw new RuntimeException("getDtKeyAttributespecification Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
/**
 * 获取分页的关键规格属性信息列表
 * @author liukh
 * @date 2017-7-10 下午5:44:43 
 * @param form
 * @return
 * (non-Javadoc)
 * @see com.jy.access.service.DtKeyAttributespecificationService#getDtKeyAttributespecificationList(com.jy.entity.form.DtKeyAttributeSpecificationQueryForm)
 */
	@Override
	public String getDtKeyAttributespecificationList(
			DtKeyAttributeSpecificationQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DtKeyAttributespecification>> dto = new BaseObjDto<ItemPage<DtKeyAttributespecification>>();
		try {

			BaseObjDto<ItemPage<DtKeyAttributespecification>> pagesDto = baseDao
					.getPageList(DtKeyAttributespecificationMapper.class,
							DtKeyAttributespecification.class, form,
							"getDtKeyAttributespecificationPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDtKeyAttributespecificationList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDtKeyAttributespecificationList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDtKeyAttributespecificationList Exception !");
			throw new RuntimeException("getDtKeyAttributespecificationList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
/**
 * 获取所有的关键规格属性信息列表
 * @author liukh
 * @date 2017-7-10 下午5:44:21 
 * @param form
 * @return
 * (non-Javadoc)
 * @see com.jy.access.service.DtKeyAttributespecificationService#getAllDtKeyAttributespecificationList(com.jy.entity.form.DtKeyAttributeSpecificationQueryForm)
 */
	@Override
	public String getAllDtKeyAttributespecificationList(
			DtKeyAttributeSpecificationQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DtKeyAttributespecification>> dto = new BaseObjDto<List<DtKeyAttributespecification>>();
		try {
			BaseObjDto<List<DtKeyAttributespecification>> dataDto = baseDao.getList(
					DtKeyAttributespecificationMapper.class, DtKeyAttributespecification.class,
					"getAllDtKeyAttributespecificationList", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDtKeyAttributespecificationList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDtKeyAttributespecificationList Exception");
			throw new RuntimeException(
					"getAllDtKeyAttributespecificationList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
