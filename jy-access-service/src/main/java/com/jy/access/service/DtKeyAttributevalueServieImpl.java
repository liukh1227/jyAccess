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
import com.jy.access.mapper.DtKeyAttributevalueMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DtKeyAttributeValueQueryForm;
import com.jy.entity.po.DtKeyAttributevalue;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.DtKeyAttributevalueVo;
@Service("dtKeyAttributevalueServie")
@Scope("prototype")
public class DtKeyAttributevalueServieImpl implements DtKeyAttributevalueServie {
	private static final Logger log = Logger.getLogger(DtKeyAttributevalueServieImpl.class);
	@Autowired
	private IBaseDao baseDao;
	
	@Override
	public String addDtKeyAttributevalue(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				DtKeyAttributevalue dtKeyAttributevalue = JSON.parseObject(data, DtKeyAttributevalue.class);
				if (dtKeyAttributevalue == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数不正确!");
					return JSON.toJSONString(dto);
				}
				if (dtKeyAttributevalue.getAttributeValue() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("关键属性值不能为空!");
					return JSON.toJSONString(dto);
				}
				if (dtKeyAttributevalue.getDeviceTypeId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("设备类型不能为空!");
					return JSON.toJSONString(dto);
				}
				Date createDate = new Date();
				dtKeyAttributevalue.setCreateTime(createDate);
				dtKeyAttributevalue.setStatus(EntityConstant.STATUS_VALID);

				dto = baseDao.insertSelective(DtKeyAttributevalueMapper.class,dtKeyAttributevalue);
				if(FrameworkUtils.isSuccess(dto)){
					log.info("addDtKeyAttributevalue success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(dtKeyAttributevalue.getdTKeyAttributeValue_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				}else{
					log.error("addDtKeyAttributevalue failure!");
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error("addDtKeyAttributevalue exception!");
				throw new RuntimeException("addDtKeyAttributevalue Exception!");
			}
		} else {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数为空!");
			log.error("---addDtKeyAttributevalue -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateDtKeyAttributevalue(String dTKeyAttributeValueId,
			String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(dTKeyAttributeValueId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入参数dTKeyAttributeValueId为空!");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(dTKeyAttributeValueId)
				&& StringUtils.isNotBlank(data)) {
			try {
				DtKeyAttributevalue dtKeyAttributevalue = JSON.parseObject(data, DtKeyAttributevalue.class);
				if (dtKeyAttributevalue == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数不正确 !");
					return JSON.toJSONString(dto);
				}
				dtKeyAttributevalue.setdTKeyAttributeValue_Id(dTKeyAttributeValueId);			
				dto = baseDao.updateByPrimaryKeySelective(DtKeyAttributevalueMapper.class, dtKeyAttributevalue);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateDtKeyAttributevalue exception!");
				throw new RuntimeException(
						"updateDtKeyAttributevalue Exception!");
			}

		} else {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入参数为空!");
			log.error("---updateDtKeyAttributevalue -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDtKeyAttributevalue(String dTKeyAttributeValueId) {
		String jsonStr = "";
		BaseObjDto<DtKeyAttributevalueVo> dto = new BaseObjDto<DtKeyAttributevalueVo>();
		try {
			if (StringUtils.isBlank(dTKeyAttributeValueId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数dTKeyAttributeValueId为空");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<DtKeyAttributevalueVo> dtKeyAttributevalueDto = baseDao
					.selectByPrimaryKey(DtKeyAttributevalueMapper.class,
							StringUtils.trim(dTKeyAttributeValueId));
			if (FrameworkUtils.isSuccess(dtKeyAttributevalueDto)) {
				DtKeyAttributevalueVo dtKeyAttributevalueVo = dtKeyAttributevalueDto.getData();
				dto.setData(dtKeyAttributevalueVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getDtKeyAttributevalue success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDtKeyAttributevalue failure");
			}
		} catch (Exception e) {
			log.error("getDtKeyAttributevalue exception!");
			e.printStackTrace();
			throw new RuntimeException("getDtKeyAttributevalue Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getDtKeyAttributevalueList(DtKeyAttributeValueQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<DtKeyAttributevalueVo>> dto = new BaseObjDto<ItemPage<DtKeyAttributevalueVo>>();
		try {

			BaseObjDto<ItemPage<DtKeyAttributevalueVo>> pagesDto = baseDao
					.getPageList(DtKeyAttributevalueMapper.class,
							DtKeyAttributevalueVo.class, form,
							"getDtKeyAttributevaluePageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDtKeyAttributevalueList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDtKeyAttributevalueList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDtKeyAttributevalueList Exception !");
			throw new RuntimeException("getDtKeyAttributevalueList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllDtKeyAttributevalueList(
			DtKeyAttributeValueQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<DtKeyAttributevalueVo>> dto = new BaseObjDto<List<DtKeyAttributevalueVo>>();
		try {
			BaseObjDto<List<DtKeyAttributevalueVo>> dataDto = baseDao.getList(
					DtKeyAttributevalueMapper.class, DtKeyAttributevalueVo.class,
					"getAllDtKeyAttributevalueList", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDtKeyAttributevalueList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDtKeyAttributevalueList Exception");
			throw new RuntimeException(
					"getAllDtKeyAttributevalueList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
