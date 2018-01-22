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
import com.jy.access.mapper.QuotationDeviceMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.QuotationDeviceQueryForm;
import com.jy.entity.po.QuotationDevice;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.QuotationDeviceVo;
@Service("quotationDeviceService")
@Scope("prototype")
public class QuotationDeviceServiceImpl implements QuotationDeviceService {
	
	private static final Logger log = Logger.getLogger(QuotationDeviceServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	
	@Autowired
	private DeviceModelService deviceModelService;
	
	@Autowired
	private QuotationService quotationService;

	@Override
	public String addQuotationDevice(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				QuotationDevice quotationDevice = JSON.parseObject(data, QuotationDevice.class);
				if (quotationDevice == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotationDevice.getDeviceModelId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价型号不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceModelStr = deviceModelService
						.getDeviceModel(StringUtils.trim(quotationDevice
								.getDeviceModelId()));
				if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价设备中型号Id不正确 !");
					return JSON.toJSONString(dto);
				}
				if(quotationDevice.getQuotationId() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价单Id(quotaionId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnQuotationStr = quotationService.getQuotation(StringUtils.trim(quotationDevice.getQuotationId()));	
				if (!FrameworkUtils.isSuccess(returnQuotationStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价单Id(quotaionId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if(quotationDevice.getQuantity()== null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价设备数量不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(quotationDevice.getRentPerMonth() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价设备月租价不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(quotationDevice.getRentPerWeek() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价设备周租价不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(quotationDevice.getRentPerDay() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价设备日租价不能为空 !");
					return JSON.toJSONString(dto);
				}
				
	
				dto = baseDao.insertSelective(QuotationDeviceMapper.class, quotationDevice);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addQuotationDevice success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(quotationDevice.getQuodevice_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addQuotationDevice failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addQuotationDevice exception!");
				throw new RuntimeException("addQuotationDevice Exception!");
			}
		} else {
			log.error("---addQuotationDevice -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteQuotationDevice(String quodeviceId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(quodeviceId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数quodeviceId为空");
			log.error("---deleteQuotationDevice -------- quodeviceId is null ==== ");
			return JSON.toJSONString(dto);
		}

		try {
			dto = baseDao.deleteByPrimaryKey(QuotationDeviceMapper.class,
					quodeviceId);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("deleteQuotationDevice exception!");
			throw new RuntimeException("deleteQuotationDevice Exception!");
		}

		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	@Override
	public String updateQuotationDevice(String quodeviceId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(quodeviceId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数quodeviceId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(quodeviceId)
				&& StringUtils.isNotBlank(data)) {
			try {
				QuotationDevice quotationDevice = JSON.parseObject(data,QuotationDevice.class);
				if (quotationDevice == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				quotationDevice.setQuodevice_Id(quodeviceId);
				
				if (quotationDevice.getQuotationId()!= null) {
					String returnQuotationStr = quotationService.getQuotation(StringUtils.trim(quotationDevice.getQuotationId()));	
					if (!FrameworkUtils.isSuccess(returnQuotationStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价单Id(quotaionId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}
			
				if (quotationDevice.getDeviceModelId()!= null) {
					String returnDeviceModelStr = deviceModelService
							.getDeviceModel(StringUtils.trim(quotationDevice
									.getDeviceModelId()));
					if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价设备中型号Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				
				dto = baseDao.updateByPrimaryKeySelective(QuotationDeviceMapper.class, quotationDevice);
				if (FrameworkUtils.isSuccess(dto)) {

			
					log.info("updateQuotationDevice success!");
				} else {
					log.error("updateQuotationDevice failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateQuotationDevice exception!");
				throw new RuntimeException("updateQuotationDevice Exception!");
			}

		} else {
			log.error("---updateQuotationDevice -------- data or quodeviceId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getQuotationDevice(String quodeviceId) {
		String jsonStr = "";
		BaseObjDto<QuotationDeviceVo> dto = new BaseObjDto<QuotationDeviceVo>();
		try {
			if (StringUtils.isBlank(quodeviceId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数quodeviceId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<QuotationDeviceVo> quotationDeviceDto = baseDao.selectByPrimaryKey(QuotationDeviceMapper.class,StringUtils.trim(quodeviceId));
			if (FrameworkUtils.isSuccess(quotationDeviceDto)) {
				QuotationDeviceVo quotationDeviceVo = quotationDeviceDto.getData();
				dto.setData(quotationDeviceVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getQuotationDevice success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getQuotationDevice failure");
			}
		} catch (Exception e) {
			log.error("getQuotationDevice exception!");
			e.printStackTrace();
			throw new RuntimeException("getQuotationDevice Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getQuotationDeviceList(QuotationDeviceQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<QuotationDeviceVo>> dto = new BaseObjDto<ItemPage<QuotationDeviceVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getQuotationId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("报价单Id(quotationId)不能为空 !");
				return JSON.toJSONString(dto);
			}

			BaseObjDto<ItemPage<QuotationDeviceVo>> pagesDto = baseDao.getPageList(QuotationDeviceMapper.class, QuotationDeviceVo.class, form,"getQuotationDevicePageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getQuotationDeviceList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getQuotationDeviceList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getQuotationDeviceList Exception !");
			throw new RuntimeException("getQuotationDeviceList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	@Override
	public String getAllQuotationDeviceList(QuotationDeviceQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<QuotationDeviceVo>> dto = new BaseObjDto<List<QuotationDeviceVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getQuotationId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("报价单Id(quotationId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<List<QuotationDeviceVo>> pagesDto = baseDao.getList(
					QuotationDeviceMapper.class, QuotationDeviceVo.class,
					"getAllQuotationDeviceList", form);
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getAllQuotationDeviceList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllQuotationDeviceList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllQuotationDeviceList Exception !");
			throw new RuntimeException("getAllQuotationDeviceList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}



}
