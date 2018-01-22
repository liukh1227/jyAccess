package com.jy.access.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.DtypeSpecificationMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DtypeSpecificationQueryForm;
import com.jy.entity.po.DtypeSpecification;
import com.jy.entity.pojo.SuccessReturnPojo;
@Service("dtypeSpecificationService")
@Scope("prototype")
public class DtypeSpecificationServiceImpl implements DtypeSpecificationService {
	private static final Logger log = Logger.getLogger(DtypeSpecificationServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	/**
	 * 增加设备类型和设备规格关联
	 * @author liukh
	 * @date 2017-7-10 下午4:18:37 
	 * @param data
	 * @return
	 * (non-Javadoc)
	 * @see com.jy.access.service.DtypeSpecificationService#addDtypeSpecification(java.lang.String)
	 */
	@Override
	public String addDtypeSpecification(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DtypeSpecification dtypeSpecification = JSON.parseObject(data, DtypeSpecification.class);
				if (dtypeSpecification == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数不正确!");
					return JSON.toJSONString(dto);
				}
				if (dtypeSpecification.getDeviceTypeId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数设备类型Id不能为空!");
					return JSON.toJSONString(dto);
				}
				if (dtypeSpecification.getdSpecificationId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数设备规格Id不能为空!");
					return JSON.toJSONString(dto);
				}
				DtypeSpecificationQueryForm form = new DtypeSpecificationQueryForm();
				form.setDeviceTypeId(dtypeSpecification.getDeviceTypeId());
				form.setdSpecificationId(dtypeSpecification.getdSpecificationId());
				BaseObjDto<ItemPage<DtypeSpecification>> pagesDto = baseDao
						.getPageList(DtypeSpecificationMapper.class,DtypeSpecification.class, form,"getDtypeSpecificationPageList");
				if (FrameworkUtils.isSuccess(pagesDto)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("此规格已添加，请勿重复添加!");
					return JSON.toJSONString(dto);
				}
			
				dto = baseDao.insertSelective(DtypeSpecificationMapper.class,dtypeSpecification);
				if(FrameworkUtils.isSuccess(dto)){
					log.info("addDtypeSpecification success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(dtypeSpecification.getdTSpec_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				}else{
					log.error("addDtypeSpecification failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDtypeSpecification exception!");
				throw new RuntimeException("addDtypeSpecification Exception!");
			}
		} else {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数为空!");
			log.error("---addDtypeSpecification -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
/**
 * 删除设备类型和设备规格关联
 * @author liukh
 * @date 2017-7-10 下午4:19:17 
 * @param data
 * @return
 * (non-Javadoc)
 * @see com.jy.access.service.DtypeSpecificationService#deleteDtypeSpecification(java.lang.String)
 */
	@Override
	public String deleteDtypeSpecification(String dTSpecId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(dTSpecId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数dTSpecId为空");
			log.error("---deleteDtypeSpecification -------- dTSpecId is null ==== ");
			return JSON.toJSONString(dto);
		}

			try {
				dto = baseDao.deleteByPrimaryKey(DtypeSpecificationMapper.class,dTSpecId);

			} catch (Exception e) {
				e.printStackTrace();
				log.error("deleteDtypeSpecification exception!");
				throw new RuntimeException("deleteDtypeSpecification Exception!");
			}
		
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
