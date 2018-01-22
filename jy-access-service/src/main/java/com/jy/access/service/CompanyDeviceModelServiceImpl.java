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
import com.jy.access.mapper.CompanyDeviceModelMapper;
import com.jy.base.common.Constant;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.CompanyDeviceModel4RentPerQueryForm;
import com.jy.entity.form.CompanyDeviceModelQueryForm;
import com.jy.entity.po.CompanyDeviceModel;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.CompanyDeviceModelVo;

@Service("companyDeviceModelService")
@Scope("prototype")
public class CompanyDeviceModelServiceImpl implements CompanyDeviceModelService {
	private static final Logger log = Logger
			.getLogger(DeviceTypeServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeviceModelService deviceModelService;

	@Override
	public String addCompanyDeviceModel(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				CompanyDeviceModel companyDeviceModel = JSON.parseObject(data,
						CompanyDeviceModel.class);
				if (companyDeviceModel == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (companyDeviceModel.getCompanyId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("所属公司不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCompanyStr = companyService.getCompany(StringUtils
						.trim(companyDeviceModel.getCompanyId()));
				if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数公司Id不正确 !");
					return JSON.toJSONString(dto);
				}
				if (companyDeviceModel.getDeviceModelId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("型号不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceModelStr = deviceModelService
						.getDeviceModel(StringUtils.trim(companyDeviceModel
								.getDeviceModelId()));
				if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数设备型号Id不正确 !");
					return JSON.toJSONString(dto);
				}

				if (companyDeviceModel.getRentPerMonth() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("月租金不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (companyDeviceModel.getRentPerWeek() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("周租金不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (companyDeviceModel.getRentPerDay() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("日租金不能为空 !");
					return JSON.toJSONString(dto);
				}
				CompanyDeviceModelQueryForm form = new CompanyDeviceModelQueryForm();
				form.setCompanyId(StringUtils.trim(companyDeviceModel
						.getCompanyId()));
				form.setDeviceModelId(StringUtils.trim(companyDeviceModel
						.getDeviceModelId()));
				form.setStatus(EntityConstant.STATUS_VALID);
				BaseObjDto<List<CompanyDeviceModelVo>> dataDto = baseDao
						.getList(CompanyDeviceModelMapper.class,
								CompanyDeviceModelVo.class,
								"getAllCompanyDeviceModelList", form);
				if (FrameworkUtils.isSuccess(dataDto)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("此设备型号已添加，请勿重复添加 !");
					return JSON.toJSONString(dto);
				}
				Date createDate = new Date();
				companyDeviceModel.setCreateTime(createDate);
				if (companyDeviceModel.getStatus() == null) {
					companyDeviceModel.setStatus(EntityConstant.STATUS_VALID);
				}

				dto = baseDao.insertSelective(CompanyDeviceModelMapper.class,
						companyDeviceModel);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addCompanyDeviceModel success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(companyDeviceModel.getCompanyDeviveModel_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addCompanyDeviceModel failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addCompanyDeviceModel exception!");
				throw new RuntimeException("addCompanyDeviceModel Exception!");
			}
		} else {
			log.error("---addCompanyDeviceModel -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateCompanyDeviceModel(String companyDeviceModelId,
			String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(companyDeviceModelId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数companyDeviceModelId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(companyDeviceModelId)
				&& StringUtils.isNotBlank(data)) {
			try {
				CompanyDeviceModel companyDeviceModel = JSON.parseObject(data,
						CompanyDeviceModel.class);
				if (companyDeviceModel == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				companyDeviceModel
						.setCompanyDeviveModel_Id(companyDeviceModelId);

				if (companyDeviceModel.getCompanyId() != null) {
					String returnCompanyStr = companyService
							.getCompany(StringUtils.trim(companyDeviceModel
									.getCompanyId()));
					if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数公司Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if (companyDeviceModel.getDeviceModelId() != null) {
					String returnDeviceModelStr = deviceModelService
							.getDeviceModel(StringUtils.trim(companyDeviceModel
									.getDeviceModelId()));
					if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数设备型号Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}

				dto = baseDao.updateByPrimaryKeySelective(
						CompanyDeviceModelMapper.class, companyDeviceModel);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateCompanyDeviceModel success!");
				} else {
					log.error("updateCompanyDeviceModel failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateCompanyDeviceModel exception!");
				throw new RuntimeException(
						"updateCompanyDeviceModel Exception!");
			}

		} else {
			log.error("---updateCompanyDeviceModel -------- data or companyDeviceModelId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getCompanyDeviceModel(String companyDeviceModelId) {
		String jsonStr = "";
		BaseObjDto<CompanyDeviceModelVo> dto = new BaseObjDto<CompanyDeviceModelVo>();
		try {
			if (StringUtils.isBlank(companyDeviceModelId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数companyDeviceModelId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<CompanyDeviceModelVo> companyDeviceModelVoDto = baseDao
					.selectByPrimaryKey(CompanyDeviceModelMapper.class,
							StringUtils.trim(companyDeviceModelId));
			if (FrameworkUtils.isSuccess(companyDeviceModelVoDto)) {
				CompanyDeviceModelVo companyDeviceModelVo = companyDeviceModelVoDto
						.getData();
				dto.setData(companyDeviceModelVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getCompanyDeviceModel success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCompanyDeviceModel failure");
			}
		} catch (Exception e) {
			log.error("getCompanyDeviceModel exception!");
			e.printStackTrace();
			throw new RuntimeException("getCompanyDeviceModel Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	
	
	@Override
	public String getCompanyDeviceModelByCompanyIdAndDeviceModelId(String data) {
		String jsonStr = "";
		BaseObjDto<CompanyDeviceModelVo> dto = new BaseObjDto<CompanyDeviceModelVo>();
		try {
			
			CompanyDeviceModelQueryForm parseForm = JSON.parseObject(data, CompanyDeviceModelQueryForm.class);
			CompanyDeviceModelQueryForm form = new CompanyDeviceModelQueryForm();
			if (parseForm == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (parseForm.getCompanyId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (parseForm.getCompanyIdArray() == null
					|| (parseForm.getCompanyIdArray() != null && parseForm
							.getCompanyIdArray().length < 0)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(parseForm.getDeviceModelId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数设备型号Id(deviceModelId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			form.setCompanyId(parseForm.getCompanyId());
			form.setDeviceModelId(parseForm.getDeviceModelId());
			form.setStatus(EntityConstant.STATUS_VALID);
			form.setPageSize(parseForm.getPageSize());
			form.setPageIndex(parseForm.getPageIndex());
			
			BaseObjDto<ItemPage<CompanyDeviceModelVo>> pagesDto = baseDao
					.getPageList(CompanyDeviceModelMapper.class,
							CompanyDeviceModelVo.class, form,
							"getCompanyDeviceModelPageList");
			if (FrameworkUtils.isSuccess(pagesDto) && pagesDto.getData()!= null && pagesDto.getData().getItems()!= null && pagesDto.getData().getItems().size()>0) {
				CompanyDeviceModelVo queryCompanyDeviceModelVo = pagesDto.getData().getItems().get(0);
				FrameworkUtils.setSuccess(dto);
				dto.setData(queryCompanyDeviceModelVo);
				log.info("getCompanyDeviceModelByCompanyIdAndDeviceModelId success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCompanyDeviceModelByCompanyIdAndDeviceModelId failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCompanyDeviceModelByCompanyIdAndDeviceModelId Exception !");
			throw new RuntimeException("getCompanyDeviceModelByCompanyIdAndDeviceModelId Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	@Override
	public String getCompanyDeviceModelByOriginalCompanyIdOrCompanyIdAndDeviceModelId(
			String data) {
		String jsonStr = "";
		BaseObjDto<CompanyDeviceModelVo> dto = new BaseObjDto<CompanyDeviceModelVo>();
		try {
			CompanyDeviceModel4RentPerQueryForm form = JSON.parseObject(data, CompanyDeviceModel4RentPerQueryForm.class);
			CompanyDeviceModelQueryForm currentForm = new CompanyDeviceModelQueryForm();
			CompanyDeviceModelQueryForm originalForm = new CompanyDeviceModelQueryForm();
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getDeviceModelId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数设备型号Id(deviceModelId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getOriginalCompanyId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数原设备所属公司Id(originalCompanyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			
			currentForm.setDeviceModelId(form.getDeviceModelId());
			currentForm.setCompanyId(form.getCompanyId());
			currentForm.setStatus(EntityConstant.STATUS_VALID);
			
		
			BaseObjDto<ItemPage<CompanyDeviceModelVo>> currentPagesDto = baseDao
					.getPageList(CompanyDeviceModelMapper.class,
							CompanyDeviceModelVo.class, currentForm,
							"getCompanyDeviceModelPageList");
			if (FrameworkUtils.isSuccess(currentPagesDto)) {
				FrameworkUtils.setSuccess(dto);
				log.info("getCompanyDeviceModelByCompanyIdAndDeviceModelId current success");
				return JSON.toJSONString(dto);
			} else {
				originalForm.setDeviceModelId(form.getDeviceModelId());
				originalForm.setCompanyId(form.getOriginalCompanyId());
				originalForm.setStatus(EntityConstant.STATUS_VALID);
				BaseObjDto<ItemPage<CompanyDeviceModelVo>> originalPagesDto = baseDao
						.getPageList(CompanyDeviceModelMapper.class,
								CompanyDeviceModelVo.class, originalForm,
								"getCompanyDeviceModelPageList");
				if (FrameworkUtils.isSuccess(originalPagesDto) && originalPagesDto.getData()!= null && originalPagesDto.getData().getItems()!= null && originalPagesDto.getData().getItems().size()>0) {
					CompanyDeviceModelVo queryCompanyDeviceModelVo = originalPagesDto.getData().getItems().get(0);
					dto.setRcode(Constant.NO_DATA_RCODE);
					dto.setRinfo("请求原公司的设备型号信息!");
					dto.setData(queryCompanyDeviceModelVo);
					log.info("getCompanyDeviceModelByCompanyIdAndDeviceModelId original  success");
				} else {
					FrameworkUtils.setNoData(dto);
					log.error("getCompanyDeviceModelByCompanyIdAndDeviceModelId original  failure");
				}
				
				log.error("getCompanyDeviceModelByCompanyIdAndDeviceModelId current  failure");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCompanyDeviceModelByCompanyIdAndDeviceModelId Exception !");
			throw new RuntimeException("getCompanyDeviceModelByCompanyIdAndDeviceModelId Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getCompanyDeviceModelList(CompanyDeviceModelQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<CompanyDeviceModelVo>> dto = new BaseObjDto<ItemPage<CompanyDeviceModelVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyIdArray() == null
					|| (form.getCompanyIdArray() != null && form
							.getCompanyIdArray().length < 0)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.isQueryAll()) {
				form.setCompanyIdArray(null);
			}
			BaseObjDto<ItemPage<CompanyDeviceModelVo>> pagesDto = baseDao
					.getPageList(CompanyDeviceModelMapper.class,
							CompanyDeviceModelVo.class, form,
							"getCompanyDeviceModelPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getCompanyDeviceModelList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCompanyDeviceModelList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCompanyDeviceModelList Exception !");
			throw new RuntimeException("getCompanyDeviceModelList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllCompanyDeviceModelList(CompanyDeviceModelQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<CompanyDeviceModelVo>> dto = new BaseObjDto<List<CompanyDeviceModelVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyIdArray() == null
					|| (form.getCompanyIdArray() != null && form
							.getCompanyIdArray().length < 0)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.isQueryAll()) {
				form.setCompanyIdArray(null);
			}
			BaseObjDto<List<CompanyDeviceModelVo>> dataDto = baseDao.getList(
					CompanyDeviceModelMapper.class, CompanyDeviceModelVo.class,
					"getAllCompanyDeviceModelList", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllCompanyDeviceModelList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllCompanyDeviceModelList Exception");
			throw new RuntimeException(
					"getAllCompanyDeviceModelList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
