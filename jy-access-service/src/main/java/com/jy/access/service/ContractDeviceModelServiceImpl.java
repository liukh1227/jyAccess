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
import com.jy.access.mapper.ContractDeviceModelMapper;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.ContractDeviceModelQueryForm;
import com.jy.entity.po.ContractDeviceModel;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.ContractDeviceModelVo;

@Service("contractDeviceModelService")
@Scope("prototype")
public class ContractDeviceModelServiceImpl implements ContractDeviceModelService {
	private static final Logger log = Logger
			.getLogger(ContractDeviceModelServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private ContractOrderService contractOrderService;
	@Autowired
	private DeviceModelService deviceModelService;


	@Override
	public String addContractDeviceModel(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				ContractDeviceModel contractDeviceModel = JSON.parseObject(
						data, ContractDeviceModel.class);
				if (contractDeviceModel == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数格式不正确，解析成ContractDeviceModel为空 !");
					return JSON.toJSONString(dto);
				}

				if (contractDeviceModel.getQuantity() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约设备数量不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractDeviceModel.getPlanStartTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约设备预计起租日不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractDeviceModel.getBillingType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约设备计费模式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractDeviceModel.getBillingType() != null
						&& !contractDeviceModel.isRightBillType()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约设备计费模式参数值不正确!");
					return JSON.toJSONString(dto);
				}
				if (contractDeviceModel.getLeaseTerm() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价设备预计工期不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractDeviceModel.getUnitPrice() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价设备价格说明不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractDeviceModel.getPlanStartTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("预计起租日不能为空 !");
					return JSON.toJSONString(dto);
				}

				if (contractDeviceModel.getDeviceModelId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约设备型号Id不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceModelStr = deviceModelService
						.getDeviceModel(StringUtils.trim(contractDeviceModel
								.getDeviceModelId()));
				if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约设备型号Id不正确 !");
					return JSON.toJSONString(dto);
				}
				if (contractDeviceModel.getContractOrderId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单Id(contractOrderId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnContractOrderStr = contractOrderService.getContractOrder(StringUtils.trim(contractDeviceModel.getContractOrderId()));
				if (!FrameworkUtils.isSuccess(returnContractOrderStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if(contractDeviceModel.getStatus() == null){
					contractDeviceModel.setStatus(EntityConstant.STATUS_VALID);
				}
				if(contractDeviceModel.getChangeTye() == null){
					contractDeviceModel.setChangeTye(EntityConstant.TM_CHANGORDER_CHANGETYPE_ZHENGCHANG);
				}
				dto = baseDao.insertSelective(ContractDeviceModelMapper.class,
						contractDeviceModel);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addContractDeviceModel success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(contractDeviceModel.getContractDeviceModel_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addContractDeviceModel failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addContractDeviceModel exception!");
				throw new RuntimeException("addContractDeviceModel Exception!");
			}
		} else {
			log.error("---addContractDeviceModel -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteContractDeviceModel(String contractDeviceModelId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(contractDeviceModelId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数contractDeviceModelId为空");
			log.error("---deleteContractDeviceModel -------- contractDeviceModelId is null ==== ");
			return JSON.toJSONString(dto);
		}

		try {
			dto = baseDao.deleteByPrimaryKey(ContractDeviceModelMapper.class,
					contractDeviceModelId);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("deleteContractDeviceModel exception!");
			throw new RuntimeException("deleteContractDeviceModel Exception!");
		}

		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateContractDeviceModel(String contractDeviceModelId,
			String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(contractDeviceModelId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数contractDeviceModelId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(contractDeviceModelId)
				&& StringUtils.isNotBlank(data)) {
			try {
				ContractDeviceModel contractDeviceModel = JSON.parseObject(
						data, ContractDeviceModel.class);
				if (contractDeviceModel == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数格式不正确,解析成ContractDeviceModel为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractDeviceModel.getDeviceModelId() != null) {
					String returnDeviceModelStr = deviceModelService
							.getDeviceModel(StringUtils.trim(contractDeviceModel
									.getDeviceModelId()));
					if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("签约设备型号Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
			
				if (contractDeviceModel.getContractOrderId() != null) {
					String returnContractOrderStr = contractOrderService.getContractOrder(StringUtils.trim(contractDeviceModel.getContractOrderId()));
					if (!FrameworkUtils.isSuccess(returnContractOrderStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}
			

				contractDeviceModel
						.setContractDeviceModel_Id(contractDeviceModelId);
				dto = baseDao.updateByPrimaryKeySelective(
						ContractDeviceModelMapper.class, contractDeviceModel);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateContractDeviceModel exception!");
				throw new RuntimeException(
						"updateContractDeviceModel Exception!");
			}

		} else {
			log.error("---updateContractDeviceModel -------- data or contractDeviceModelId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getContractDeviceModel(String contractDeviceModelId) {
		String jsonStr = "";
		BaseObjDto<ContractDeviceModelVo> dto = new BaseObjDto<ContractDeviceModelVo>();
		try {
			if (StringUtils.isBlank(contractDeviceModelId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数contractDeviceModelId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<ContractDeviceModelVo> contractDeviceModelDto = baseDao
					.selectByPrimaryKey(ContractDeviceModelMapper.class,
							StringUtils.trim(contractDeviceModelId));
			if (FrameworkUtils.isSuccess(contractDeviceModelDto)) {
				ContractDeviceModelVo contractDeviceModelVo = contractDeviceModelDto
						.getData();
				dto.setData(contractDeviceModelVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getContractDeviceModel success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getContractDeviceModel failure");
			}
		} catch (Exception e) {
			log.error("getContractDeviceModel exception!");
			e.printStackTrace();
			throw new RuntimeException("getContractDeviceModel Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	@Override
	public String getAllContractDeviceModelList(
			ContractDeviceModelQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<ContractDeviceModelVo>> dto = new BaseObjDto<List<ContractDeviceModelVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空");
				return JSON.toJSONString(dto);
			}
			if(form.getContractOrderId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数签约单Id(contractOrderId)不能为空");
				return JSON.toJSONString(dto);
			}

			BaseObjDto<List<ContractDeviceModelVo>> pagesDto = baseDao.getList(
					ContractDeviceModelMapper.class,
					ContractDeviceModelVo.class,
					"getContractDeviceModelAllList", form);
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getAllContractDeviceModelList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllContractDeviceModelList failure");
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
