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
import com.jy.access.mapper.DmAttributevalueMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DmAttributeValueQueryForm;
import com.jy.entity.po.DmAttributevalue;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.DmAttributevalueVo;
@Service("dmAttributevalueService")
@Scope("prototype")
public class DmAttributevalueServiceImpl implements DmAttributevalueService {
	private static final Logger log = Logger.getLogger(DmAttributevalueServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceModelService deviceModelService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	@Autowired
	private DeviceSpecificationService deviceSpecificationService;
	
	@Override
	public String addDmAttributevalue(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DmAttributevalue dmAttributevalue = JSON.parseObject(data,DmAttributevalue.class);
				if (dmAttributevalue == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (dmAttributevalue.getAttributeValue() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("属性值不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (dmAttributevalue.getdSpecificationId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备规格Id不能为空为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDSpStr = deviceSpecificationService.getDeviceSpecification(StringUtils.trim(dmAttributevalue.getdSpecificationId()));
				if(!FrameworkUtils.isSuccess(returnDSpStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备规格Id不正确 !");
					return JSON.toJSONString(dto);
				}
				if (dmAttributevalue.getDeviceModelId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备型号Id不能为空为空 !");
					return JSON.toJSONString(dto);
				}
				String returnModelStr = deviceModelService.getDeviceModel(StringUtils.trim(dmAttributevalue.getDeviceModelId()));
				if(!FrameworkUtils.isSuccess(returnModelStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备型号Id不正确 !");
					return JSON.toJSONString(dto);
				}
				if(dmAttributevalue.getDeviceTypeId()!= null){
					String returnTypeStr = deviceTypeService.getDeviceType(StringUtils.trim(dmAttributevalue.getDeviceTypeId()));
					if(!FrameworkUtils.isSuccess(returnTypeStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("设备类型Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				
				Date createDate = new Date();
				dmAttributevalue.setCreateTime(createDate);
				dmAttributevalue.setStatus(EntityConstant.STATUS_VALID);
				dto = baseDao.insertSelective(DmAttributevalueMapper.class,dmAttributevalue);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addDmAttributevalue success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(dmAttributevalue.getdMAttributeValue_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addDmAttributevalue failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDmAttributevalue exception!");
				throw new RuntimeException("addDmAttributevalue Exception!");
			}
		} else {
			log.error("---addDmAttributevalue -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteDmAttributevalue(String dMAttributeValueId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(dMAttributeValueId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数dTSpecId为空");
			log.error("---deleteDtypeSpecification -------- dMAttributeValueId is null ==== ");
			return JSON.toJSONString(dto);
		}

			try {
				dto = baseDao.deleteByPrimaryKey(DmAttributevalueMapper.class,dMAttributeValueId);

			} catch (Exception e) {
				e.printStackTrace();
				log.error("deleteDmAttributevalue exception!");
				throw new RuntimeException("deleteDmAttributevalue Exception!");
			}
		
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDmAttributevalueList(DmAttributeValueQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DmAttributevalueVo>> dto = new BaseObjDto<ItemPage<DmAttributevalueVo>>();
		try {

			BaseObjDto<ItemPage<DmAttributevalueVo>> pagesDto = baseDao.getPageList(DmAttributevalueMapper.class, DmAttributevalueVo.class, form,"getDmAttributevaluePageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDmAttributevalueList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDmAttributevalueList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDmAttributevalueList Exception !");
			throw new RuntimeException("getDmAttributevalueList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllDmAttributevalueList(DmAttributeValueQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DmAttributevalueVo>> dto = new BaseObjDto<List<DmAttributevalueVo>>();
		try {
			BaseObjDto<List<DmAttributevalueVo>> dataDto = baseDao.getList(DmAttributevalueMapper.class, DmAttributevalueVo.class,"getAllDmAttributevalueList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDmAttributevalueList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDmAttributevalueList Exception");
			throw new RuntimeException("getAllDmAttributevalueList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
