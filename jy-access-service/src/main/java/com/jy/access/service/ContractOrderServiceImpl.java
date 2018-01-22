package com.jy.access.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jy.access.mapper.ContractOrderMapper;
import com.jy.access.mapper.QuotationMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.bo.ContractOrderBo;
import com.jy.entity.bo.QuotationBo;
import com.jy.entity.form.ContractDeviceModelQueryForm;
import com.jy.entity.form.ContractOrderQueryForm;
import com.jy.entity.form.DeliveryOrderQueryForm;
import com.jy.entity.po.BankAccount;
import com.jy.entity.po.ContractDeviceModel;
import com.jy.entity.po.ContractOrder;
import com.jy.entity.po.DeliveryOrder;
import com.jy.entity.pojo.ContractOrder4PdfPojo;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.ContractDeviceModelVo;
import com.jy.entity.vo.ContractOrderVo;
import com.jy.entity.vo.QuotationDeviceVo;

@Service("contractOrderService")
@Scope("prototype")
public class ContractOrderServiceImpl implements ContractOrderService {
	private static final Logger log = Logger
			.getLogger(ContractOrderServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private DeviceModelService deviceModelService;
	@Autowired
	private DeliveryOrderService deliveryOrderService;
	@Autowired
	private UserAccountServie userAccountServie;

	@SuppressWarnings("unchecked")
	@Override
	public String addContractOrder(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				ContractOrder contractOrder = JSON.parseObject(data,
						ContractOrder.class);
				if (contractOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getContractOrderNum() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单合同编号不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getCustomerId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("承租方(乙方)信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCustomerStr = customerService
						.getCustomer(StringUtils.trim(contractOrder
								.getCustomerId()));
				if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("承租方(乙方)Id信息不正确!");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getCusBankAccountId() != null) {
					String returnCustomerBankAccountStr = bankAccountService
							.getBankAccount(StringUtils.trim(contractOrder
									.getCusBankAccountId()));
					if (!FrameworkUtils.isSuccess(returnCustomerBankAccountStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("承租方(乙方)银行账户信息不正确!");
						return JSON.toJSONString(dto);
					}
					BaseObjDto<BankAccount> queryBankAccountDto = JSON
							.parseObject(returnCustomerBankAccountStr,
									BaseObjDto.class);
					if (queryBankAccountDto != null
							&& queryBankAccountDto.getData() != null) {
						BankAccount queryBankAccount = JSON.parseObject(JSON
								.toJSONString(queryBankAccountDto.getData()),
								BankAccount.class);
						if (queryBankAccount != null
								&& queryBankAccount.getCustomerId() != null
								&& !queryBankAccount.getCustomerId().equals(
										contractOrder.getCustomerId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("承租方(乙方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					}
				}

				if (contractOrder.getLessorId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("出租方(甲方)信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCompanyStr = companyService.getCompany(StringUtils
						.trim(contractOrder.getLessorId()));
				if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("出租方(甲方)Id信息不正确 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getLesBankAccountId() != null) {
					String returnLesBankAccountStr = bankAccountService
							.getBankAccount(StringUtils.trim(contractOrder
									.getLesBankAccountId()));
					if (!FrameworkUtils.isSuccess(returnLesBankAccountStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("出租方(甲方)银行账户信息不正确!");
						return JSON.toJSONString(dto);
					}
					BaseObjDto<BankAccount> queryBankAccountDto = JSON
							.parseObject(returnLesBankAccountStr,
									BaseObjDto.class);
					if (queryBankAccountDto != null
							&& queryBankAccountDto.getData() != null) {
						BankAccount queryBankAccount = JSON.parseObject(JSON
								.toJSONString(queryBankAccountDto.getData()),
								BankAccount.class);
						if (queryBankAccount != null
								&& queryBankAccount.getCompanyId() != null
								&& !queryBankAccount.getCompanyId().equals(
										contractOrder.getLessorId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("出租方(甲方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					}
				}

				if (contractOrder.getIncludeShippingFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含运费不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (!contractOrder.getIncludeShippingFee()
						&& contractOrder.getShippingFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("合同条款中不含运费时，运费不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getIncludeInvoice() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含发票不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getIncludeJiShou() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含机手不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getIncludeFue() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含燃油不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getIncludeInsurance() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含保险不能为空 !");
					return JSON.toJSONString(dto);
				}

				if (contractOrder.getPayMethod() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款款方式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getPayMethod() != null
						&& contractOrder.isPayMethodYuFu()
						&& contractOrder.getPrepayFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款款方式为预付时，预付款不能为空!");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getSourceType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单来源类型不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrder.getStatus() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("状态不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(contractOrder.getCreator() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(contractOrder.getSalesMan() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("所属业务员不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(contractOrder.getCreator().equals(contractOrder.getSalesMan())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者和所属业务员参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnSalesManStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrder.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnSalesManStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("所属业务员参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}
				Date createDate = new Date();
				contractOrder.setCreateTime(createDate);
				dto = baseDao.insertSelective(ContractOrderMapper.class,
						contractOrder);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addContractOrder success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(contractOrder.getContractOrder_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addContractOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addContractOrder exception!");
				throw new RuntimeException("addContractOrder Exception!");
			}
		} else {
			log.error("---addContractOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String addContractOrderAndContractDeviceModel(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				ContractOrderBo contractOrderBo = JSON.parseObject(data,
						ContractOrderBo.class);
				if (contractOrderBo == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getContractOrderNum() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单合同编号不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getCustomerId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("承租方(乙方)信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCustomerStr = customerService
						.getCustomer(StringUtils.trim(contractOrderBo
								.getCustomerId()));
				if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("承租方(乙方)Id信息不正确!");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getCusBankAccountId() != null) {
					String returnCustomerBankAccountStr = bankAccountService
							.getBankAccount(StringUtils.trim(contractOrderBo
									.getCusBankAccountId()));
					if (!FrameworkUtils.isSuccess(returnCustomerBankAccountStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("承租方(乙方)银行账户信息不正确!");
						return JSON.toJSONString(dto);
					}
					BaseObjDto<BankAccount> queryBankAccountDto = JSON
							.parseObject(returnCustomerBankAccountStr,
									BaseObjDto.class);
					if (queryBankAccountDto != null
							&& queryBankAccountDto.getData() != null) {
						BankAccount queryBankAccount = JSON.parseObject(JSON
								.toJSONString(queryBankAccountDto.getData()),
								BankAccount.class);
						if (queryBankAccount != null
								&& queryBankAccount.getCustomerId() != null
								&& !queryBankAccount.getCustomerId().equals(
										contractOrderBo.getCustomerId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("承租方(乙方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					}
				}

				if (contractOrderBo.getLessorId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("出租方(甲方)信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCompanyStr = companyService.getCompany(StringUtils
						.trim(contractOrderBo.getLessorId()));
				if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("出租方(甲方)Id信息不正确 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getLesBankAccountId() != null) {
					String returnLesBankAccountStr = bankAccountService
							.getBankAccount(StringUtils.trim(contractOrderBo
									.getLesBankAccountId()));
					if (!FrameworkUtils.isSuccess(returnLesBankAccountStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("出租方(甲方)银行账户信息不正确!");
						return JSON.toJSONString(dto);
					}
					BaseObjDto<BankAccount> queryBankAccountDto = JSON
							.parseObject(returnLesBankAccountStr,
									BaseObjDto.class);
					if (queryBankAccountDto != null
							&& queryBankAccountDto.getData() != null) {
						BankAccount queryBankAccount = JSON.parseObject(JSON
								.toJSONString(queryBankAccountDto.getData()),
								BankAccount.class);
						if (queryBankAccount != null
								&& queryBankAccount.getCompanyId() != null
								&& !queryBankAccount.getCompanyId().equals(
										contractOrderBo.getLessorId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("出租方(甲方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					}
				}

				if (contractOrderBo.getIncludeShippingFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含运费不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (!contractOrderBo.getIncludeShippingFee()
						&& contractOrderBo.getShippingFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("合同条款中不含运费时，运费不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getIncludeInvoice() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含发票不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getIncludeJiShou() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含机手不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getIncludeFue() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含燃油不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getIncludeInsurance() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含保险不能为空 !");
					return JSON.toJSONString(dto);
				}

				if (contractOrderBo.getPayMethod() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款款方式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getPayMethod() != null
						&& contractOrderBo.isPayMethodYuFu()
						&& contractOrderBo.getPrepayFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款款方式为预付时，预付款不能为空!");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getSourceType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约单来源类型不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (contractOrderBo.getStatus() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("状态不能为空 !");
					return JSON.toJSONString(dto);
				}
				
				if(contractOrderBo.getCreator() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(contractOrderBo.getSalesMan() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("所属业务员不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(contractOrderBo.getCreator().equals(contractOrderBo.getSalesMan())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrderBo.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者和所属业务员参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrderBo.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnSalesManStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrderBo.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnSalesManStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("所属业务员参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}
				
				// 签约设备型号信息
				if (contractOrderBo.getContractDeviceModels() == null
						|| (contractOrderBo.getContractDeviceModels() != null && contractOrderBo
								.getContractDeviceModels().size() < 1)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("签约设备型号信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				ArrayList<ContractDeviceModel> cachContractDeviceModelList = new ArrayList<ContractDeviceModel>();
				for (ContractDeviceModelVo contractDeviceModelVo : contractOrderBo
						.getContractDeviceModels()) {

					if (contractDeviceModelVo.getDeviceModelId() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("签约设备型号Id不能为空 !");
						return JSON.toJSONString(dto);
					}
					String returnDeviceModelStr = deviceModelService
							.getDeviceModel(StringUtils
									.trim(contractDeviceModelVo
											.getDeviceModelId()));
					if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("签约设备型号Id不正确 !");
						return JSON.toJSONString(dto);
					}
					if (contractDeviceModelVo.getQuantity() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("签约设备数量不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (contractDeviceModelVo.getPlanStartTime() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("签约设备预计起租日不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (contractDeviceModelVo.getBillingType() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("签约设备计费模式不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (contractDeviceModelVo.getBillingType() != null
							&& !contractDeviceModelVo.isRightBillType()) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("签约设备计费模式参数值不正确!");
						return JSON.toJSONString(dto);
					}
					if (contractDeviceModelVo.getLeaseTerm() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价设备预计工期不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (contractDeviceModelVo.getUnitPrice() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价设备价格说明不能为空 !");
						return JSON.toJSONString(dto);
					}
					ContractDeviceModel contractDeviceModel = new ContractDeviceModel();
					contractDeviceModel.setDeviceModelId(contractDeviceModelVo
							.getDeviceModelId());
					contractDeviceModel.setQuantity(contractDeviceModelVo
							.getQuantity());
					contractDeviceModel.setPlanStartTime(contractDeviceModelVo
							.getPlanStartTime());
					contractDeviceModel.setLeaseTerm(contractDeviceModelVo
							.getLeaseTerm());
					contractDeviceModel.setBillingType(contractDeviceModelVo
							.getBillingType());
					contractDeviceModel.setUnitPrice(contractDeviceModelVo
							.getUnitPrice());
					if(contractDeviceModelVo.getStatus() == null){
						contractDeviceModel.setStatus(EntityConstant.STATUS_VALID);
					}
					if(contractDeviceModelVo.getChangeTye() == null){
						contractDeviceModel.setChangeTye(EntityConstant.TM_CHANGORDER_CHANGETYPE_ZHENGCHANG);
					}
					cachContractDeviceModelList.add(contractDeviceModel);

				}

				Date createDate = new Date();
				contractOrderBo.setCreateTime(createDate);
				dto = baseDao.insertSelective(ContractOrderMapper.class,
						contractOrderBo);
				if (!FrameworkUtils.isSuccess(dto)) {
					log.error("adContractOrderAndContractDeviceModel........when adContractOrder failure!");
					return JSON.toJSONString(dto);
				}
				for (ContractDeviceModel contractDeviceModel : cachContractDeviceModelList) {
					contractDeviceModel.setContractOrderId(contractOrderBo
							.getContractOrder_Id());
					dto = baseDao.insertSelective(
							ContractDeviceModelMapper.class,
							contractDeviceModel);
				}
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("adContractOrderAndContractDeviceModel success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(contractOrderBo.getContractOrder_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("adContractOrderAndContractDeviceModel failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("adContractOrderAndContractDeviceModel exception!");
				throw new RuntimeException(
						"adContractOrderAndContractDeviceModel Exception!");
			}
		} else {
			log.error("---adContractOrderAndContractDeviceModel -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public String addContractOrderAndContractDeviceModelFromQuotation(
			String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				
			Map<String,Object> dataMap = (Map<String, Object>)JSON.parse(data);
			if(dataMap == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数格式不正确 !");
				return JSON.toJSONString(dto);
			}
	
			if(dataMap.get("creator")== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的创建人(creator)不能为空 !");
				return JSON.toJSONString(dto);
			}
			String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(dataMap.get("creator").toString()));
			if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("创建者参数不正确!");
				return JSON.toJSONString(dto);
			}
			
			if(dataMap.get("quotationId")== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的报价单Id(quotationId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("quotation_Id", StringUtils.trim(dataMap.get("quotationId").toString()));
			BaseObjDto<QuotationBo> quotationBoDto = baseDao.getObjProperty(QuotationMapper.class, "selectQuotationAndDeviceById", paramsMap);					
			if (!FrameworkUtils.isSuccess(quotationBoDto)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的报价单Id(quotationId)不正确 !");
				return JSON.toJSONString(dto);
			} 
			
			QuotationBo quotationBo = quotationBoDto.getData();
			ContractOrder contractOrder = new ContractOrder();
			contractOrder.copyFromQuotation(quotationBo, StringUtils.trim(dataMap.get("creator").toString()));
			
				ArrayList<ContractDeviceModel> cachContractDeviceModelList = new ArrayList<ContractDeviceModel>();
				for (QuotationDeviceVo quotationDeviceVo : quotationBo.getQuotationDevices()) {
					ContractDeviceModel contractDeviceModel = new ContractDeviceModel();
					contractDeviceModel.copyFromQuotation(quotationDeviceVo);
					cachContractDeviceModelList.add(contractDeviceModel);
				}

				Date createDate = new Date();
				contractOrder.setCreateTime(createDate);
				dto = baseDao.insertSelective(ContractOrderMapper.class,contractOrder);
				if (!FrameworkUtils.isSuccess(dto)) {
					log.error("addContractOrderAndContractDeviceModelFromQuotation........when adContractOrder failure!");
					return JSON.toJSONString(dto);
				}
				for (ContractDeviceModel contractDeviceModel : cachContractDeviceModelList) {
					contractDeviceModel.setContractOrderId(contractOrder.getContractOrder_Id());
					dto = baseDao.insertSelective(ContractDeviceModelMapper.class,contractDeviceModel);
				}
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addContractOrderAndContractDeviceModelFromQuotation success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(contractOrder.getContractOrder_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addContractOrderAndContractDeviceModelFromQuotation failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addContractOrderAndContractDeviceModelFromQuotation exception!");
				throw new RuntimeException(
						"addContractOrderAndContractDeviceModelFromQuotation Exception!");
			}
		} else {
			log.error("---addContractOrderAndContractDeviceModelFromQuotation -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
		}
	

	@SuppressWarnings("unchecked")
	@Override
	public String updateContractOrder(String contractOrderId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(contractOrderId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数contractOrderId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(contractOrderId)
				&& StringUtils.isNotBlank(data)) {
			try {
				ContractOrder queryContractOrder = null;
				ContractOrder contractOrder = JSON.parseObject(data,
						ContractOrder.class);
				if (contractOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数格式不正确,解析成ContractOrder为空 !");
					return JSON.toJSONString(dto);
				}
				String queryContractOrderStr = this
						.getContractOrder(StringUtils.trim(contractOrderId));
				if (!FrameworkUtils.isSuccess(queryContractOrderStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数contractOrderId不正确 !");
					return JSON.toJSONString(dto);
				}
				BaseObjDto<ContractOrder> queryContractOrderDto = JSON
						.parseObject(queryContractOrderStr, BaseObjDto.class);
				if (queryContractOrderDto != null
						&& queryContractOrderDto.getData() != null) {
					queryContractOrder = JSON.parseObject(
							JSON.toJSONString(queryContractOrderDto.getData()),
							ContractOrder.class);
				}

				if (contractOrder.getCustomerId() != null) {
					String returnCustomerStr = customerService
							.getCustomer(StringUtils.trim(contractOrder
									.getCustomerId()));
					if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("承租方(乙方)信息正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if (contractOrder.getCusBankAccountId() != null) {
					String returnCustomerBankAccountStr = bankAccountService
							.getBankAccount(StringUtils.trim(contractOrder
									.getCusBankAccountId()));
					if (!FrameworkUtils.isSuccess(returnCustomerBankAccountStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("承租方(乙方)银行账户信息不正确!");
						return JSON.toJSONString(dto);
					}
					BaseObjDto<BankAccount> queryBankAccountDto = JSON
							.parseObject(returnCustomerBankAccountStr,
									BaseObjDto.class);
					BankAccount queryBankAccount = null;
					if (queryBankAccountDto != null
							&& queryBankAccountDto.getData() != null) {
						queryBankAccount = JSON.parseObject(JSON
								.toJSONString(queryBankAccountDto.getData()),
								BankAccount.class);
					}
					if (contractOrder.getLessorId() != null) {
						if (queryBankAccount != null
								&& queryBankAccount.getCustomerId() != null
								&& !queryBankAccount.getCustomerId().equals(
										contractOrder.getCustomerId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("承租方(乙方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					} else {
						if (queryContractOrder != null
								&& queryContractOrder.getCustomerId() != null
								&& queryBankAccount != null
								&& queryBankAccount.getCustomerId() != null
								&& !queryBankAccount.getCustomerId().equals(
										queryContractOrder.getCustomerId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("承租方(乙方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					}
				}
				if (contractOrder.getLessorId() != null) {
					String returnCompanyStr = companyService
							.getCompany(StringUtils.trim(contractOrder
									.getLessorId()));
					if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("出租方(甲方)Id信息不正确 !");
						return JSON.toJSONString(dto);
					}

				}
				if (contractOrder.getLesBankAccountId() != null) {
					String returnLesBankAccountStr = bankAccountService
							.getBankAccount(StringUtils.trim(contractOrder
									.getLesBankAccountId()));
					if (!FrameworkUtils.isSuccess(returnLesBankAccountStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("出租方(甲方)银行账户信息不正确!");
						return JSON.toJSONString(dto);
					}

					BaseObjDto<BankAccount> queryBankAccountDto = JSON
							.parseObject(returnLesBankAccountStr,
									BaseObjDto.class);
					BankAccount queryBankAccount = null;
					if (queryBankAccountDto != null
							&& queryBankAccountDto.getData() != null) {
						queryBankAccount = JSON.parseObject(JSON
								.toJSONString(queryBankAccountDto.getData()),
								BankAccount.class);
					}
					if (contractOrder.getLessorId() != null) {
						if (queryBankAccount != null
								&& queryBankAccount.getCompanyId() != null
								&& !queryBankAccount.getCompanyId().equals(
										contractOrder.getLessorId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("出租方(甲方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					} else {
						if (queryContractOrder != null
								&& queryContractOrder.getLessorId() != null
								&& queryBankAccount != null
								&& queryBankAccount.getCompanyId() != null
								&& !queryBankAccount.getCompanyId().equals(
										queryContractOrder.getLessorId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("出租方(甲方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					}

				}
				
				if(contractOrder.getSalesMan()!= null){
					String returnSalesManStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrder.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnSalesManStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("所属业务员参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				if(contractOrder.getCreator()!= null){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrder.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者参数不正确!");
						return JSON.toJSONString(dto);
					}
				}

				contractOrder.setContractOrder_Id(contractOrderId);
				dto = baseDao.updateByPrimaryKeySelective(
						ContractOrderMapper.class, contractOrder);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateContractOrder exception!");
				throw new RuntimeException("updateContractOrder Exception!");
			}

		} else {
			log.error("---updateContractOrder -------- data or contractOrderId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String updateContractOrderAndContractDeviceModel(
			String contractOrderId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(contractOrderId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数quotationId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(contractOrderId)
				&& StringUtils.isNotBlank(data)) {
			try {
				ContractOrderBo contractOrderBo = JSON.parseObject(data,
						ContractOrderBo.class);
				if (contractOrderBo == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的数据格式不正确!");
					return JSON.toJSONString(dto);
				}
				ContractOrder queryContractOrder = null;
				String queryContractOrderStr = this
						.getContractOrder(StringUtils.trim(contractOrderId));
				if (!FrameworkUtils.isSuccess(queryContractOrderStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数contractOrderId不正确 !");
					return JSON.toJSONString(dto);
				}
				if(contractOrderBo.getSalesMan()!= null){
					String returnSalesManStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrderBo.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnSalesManStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("所属业务员参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				if(contractOrderBo.getCreator()!= null){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(contractOrderBo.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				BaseObjDto<ContractOrder> queryContractOrderDto = JSON
						.parseObject(queryContractOrderStr, BaseObjDto.class);
				if (queryContractOrderDto != null
						&& queryContractOrderDto.getData() != null) {
					queryContractOrder = JSON.parseObject(
							JSON.toJSONString(queryContractOrderDto.getData()),
							ContractOrder.class);
				}

				if (contractOrderBo.getCustomerId() != null) {
					String returnCustomerStr = customerService
							.getCustomer(StringUtils.trim(contractOrderBo
									.getCustomerId()));
					if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("承租方(乙方)信息正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if (contractOrderBo.getCusBankAccountId() != null) {
					String returnCustomerBankAccountStr = bankAccountService
							.getBankAccount(StringUtils.trim(contractOrderBo
									.getCusBankAccountId()));
					if (!FrameworkUtils.isSuccess(returnCustomerBankAccountStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("承租方(乙方)银行账户信息不正确!");
						return JSON.toJSONString(dto);
					}
					BaseObjDto<BankAccount> queryBankAccountDto = JSON
							.parseObject(returnCustomerBankAccountStr,
									BaseObjDto.class);
					BankAccount queryBankAccount = null;
					if (queryBankAccountDto != null
							&& queryBankAccountDto.getData() != null) {
						queryBankAccount = JSON.parseObject(JSON
								.toJSONString(queryBankAccountDto.getData()),
								BankAccount.class);
					}
					if (contractOrderBo.getLessorId() != null) {
						if (queryBankAccount != null
								&& queryBankAccount.getCustomerId() != null
								&& !queryBankAccount.getCustomerId().equals(
										contractOrderBo.getCustomerId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("承租方(乙方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					} else {
						if (queryContractOrder != null
								&& queryContractOrder.getCustomerId() != null
								&& queryBankAccount != null
								&& queryBankAccount.getCustomerId() != null
								&& !queryBankAccount.getCustomerId().equals(
										queryContractOrder.getCustomerId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("承租方(乙方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					}
				}
				if (contractOrderBo.getLessorId() != null) {
					String returnCompanyStr = companyService
							.getCompany(StringUtils.trim(contractOrderBo
									.getLessorId()));
					if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("出租方(甲方)Id信息不正确 !");
						return JSON.toJSONString(dto);
					}

				}
				if (contractOrderBo.getLesBankAccountId() != null) {
					String returnLesBankAccountStr = bankAccountService
							.getBankAccount(StringUtils.trim(contractOrderBo
									.getLesBankAccountId()));
					if (!FrameworkUtils.isSuccess(returnLesBankAccountStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("出租方(甲方)银行账户信息不正确!");
						return JSON.toJSONString(dto);
					}
					BaseObjDto<BankAccount> queryBankAccountDto = JSON
							.parseObject(returnLesBankAccountStr,
									BaseObjDto.class);
					BankAccount queryBankAccount = null;
					if (queryBankAccountDto != null
							&& queryBankAccountDto.getData() != null) {
						queryBankAccount = JSON.parseObject(JSON
								.toJSONString(queryBankAccountDto.getData()),
								BankAccount.class);
					}
					if (contractOrderBo.getLessorId() != null) {
						if (queryBankAccount != null
								&& queryBankAccount.getCompanyId() != null
								&& !queryBankAccount.getCompanyId().equals(
										contractOrderBo.getLessorId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("出租方(甲方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					} else {
						if (queryContractOrder != null
								&& queryContractOrder.getLessorId() != null
								&& queryBankAccount != null
								&& queryBankAccount.getCompanyId() != null
								&& !queryBankAccount.getCompanyId().equals(
										queryContractOrder.getLessorId())) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("出租方(甲方)Id和其关联的银行账户Id不匹配!");
							return JSON.toJSONString(dto);
						}

					}
				}

				ArrayList<String> cachQueryContractDeviceModelList = new ArrayList<String>();
				ArrayList<ContractDeviceModel> cachUpdateCDModelList = new ArrayList<ContractDeviceModel>();
				ArrayList<ContractDeviceModel> cachAddCDModelList = new ArrayList<ContractDeviceModel>();
				if (contractOrderBo.getContractDeviceModels() != null
						&& contractOrderBo.getContractDeviceModels().size() > 0) {
					ContractDeviceModelQueryForm qDModelForm = new ContractDeviceModelQueryForm();
					qDModelForm.setContractOrderId(contractOrderId);
					BaseObjDto<List<ContractDeviceModelVo>> qDModelListDto = baseDao
							.getList(ContractDeviceModelMapper.class,
									ContractDeviceModelVo.class,
									"getContractDeviceModelAllList",
									qDModelForm);
					if (FrameworkUtils.isSuccess(qDModelListDto)
							&& qDModelListDto.getData() != null
							&& qDModelListDto.getData().size() > 0) {
						for (ContractDeviceModelVo contractDeviceModelVo : qDModelListDto
								.getData()) {
							cachQueryContractDeviceModelList
									.add(contractDeviceModelVo
											.getContractDeviceModel_Id());
						}

					}
					for (ContractDeviceModelVo contractDeviceModelVo : contractOrderBo
							.getContractDeviceModels()) {
						ContractDeviceModel doCDModel = new ContractDeviceModel();
						if (contractDeviceModelVo.getContractDeviceModel_Id() == null) {
							if (contractDeviceModelVo.getDeviceModelId() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("签约设备型号Id不能为空 !");
								return JSON.toJSONString(dto);
							}
							String returnDeviceModelStr = deviceModelService
									.getDeviceModel(StringUtils
											.trim(contractDeviceModelVo
													.getDeviceModelId()));
							if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("签约设备型号Id不正确 !");
								return JSON.toJSONString(dto);
							}
							if (contractDeviceModelVo.getQuantity() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("签约设备数量不能为空 !");
								return JSON.toJSONString(dto);
							}
							if (contractDeviceModelVo.getPlanStartTime() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("签约设备预计起租日不能为空 !");
								return JSON.toJSONString(dto);
							}
							if (contractDeviceModelVo.getBillingType() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("签约设备计费模式不能为空 !");
								return JSON.toJSONString(dto);
							}
							if (contractDeviceModelVo.getBillingType() != null
									&& !contractDeviceModelVo.isRightBillType()) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("签约设备计费模式参数值不正确!");
								return JSON.toJSONString(dto);
							}
							if (contractDeviceModelVo.getLeaseTerm() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("报价设备预计工期不能为空 !");
								return JSON.toJSONString(dto);
							}
							if (contractDeviceModelVo.getUnitPrice() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("报价设备价格说明不能为空 !");
								return JSON.toJSONString(dto);
							}
							doCDModel.setContractOrderId(contractOrderId);
							doCDModel.setDeviceModelId(contractDeviceModelVo
									.getDeviceModelId());
							doCDModel.setQuantity(contractDeviceModelVo
									.getQuantity());
							doCDModel.setPlanStartTime(contractDeviceModelVo
									.getPlanStartTime());
							doCDModel.setLeaseTerm(contractDeviceModelVo
									.getLeaseTerm());
							doCDModel.setBillingType(contractDeviceModelVo
									.getBillingType());
							doCDModel.setUnitPrice(contractDeviceModelVo
									.getUnitPrice());
							if(contractDeviceModelVo.getStatus() == null){
								doCDModel.setStatus(EntityConstant.STATUS_VALID);
							}
							if(contractDeviceModelVo.getChangeTye() == null){
								doCDModel.setChangeTye(EntityConstant.TM_CHANGORDER_CHANGETYPE_ZHENGCHANG);
							}
							cachAddCDModelList.add(doCDModel);

						} else {
							if (cachQueryContractDeviceModelList.size() > 0
									&& !cachQueryContractDeviceModelList
											.contains(contractDeviceModelVo
													.getContractDeviceModel_Id())) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("修改签约型号设备信息Id不存在 !");
								return JSON.toJSONString(dto);
							}
							doCDModel
									.setContractDeviceModel_Id(contractDeviceModelVo
											.getContractDeviceModel_Id());
							Boolean isContainUpdate = false;
							if (contractDeviceModelVo.getDeviceModelId() != null) {
								String returnDeviceModelStr = deviceModelService
										.getDeviceModel(StringUtils
												.trim(contractDeviceModelVo
														.getDeviceModelId()));
								if (!FrameworkUtils
										.isSuccess(returnDeviceModelStr)) {
									dto.setRcode(BaseDto.ERROR_RCODE);
									dto.setRinfo("签约型号设备中型号Id不正确 !");
									return JSON.toJSONString(dto);
								}
								doCDModel
										.setDeviceModelId(contractDeviceModelVo
												.getDeviceModelId());
								isContainUpdate = true;
							}

							if (contractDeviceModelVo.getQuantity() != null) {
								doCDModel.setQuantity(contractDeviceModelVo
										.getQuantity());
								isContainUpdate = true;
							}
							if (contractDeviceModelVo.getPlanStartTime() != null) {
								doCDModel
										.setPlanStartTime(contractDeviceModelVo
												.getPlanStartTime());
								isContainUpdate = true;
							}
							if (contractDeviceModelVo.getBillingType() != null
									&& !contractDeviceModelVo.isRightBillType()) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("签约设备计费模式参数值不正确!");
								return JSON.toJSONString(dto);
							}
							if (contractDeviceModelVo.getBillingType() != null) {
								doCDModel.setBillingType(contractDeviceModelVo
										.getBillingType());
								isContainUpdate = true;
							}
							if (contractDeviceModelVo.getLeaseTerm() != null) {
								doCDModel.setLeaseTerm(contractDeviceModelVo
										.getLeaseTerm());
								isContainUpdate = true;
							}
							if (contractDeviceModelVo.getUnitPrice() != null) {
								doCDModel.setUnitPrice(contractDeviceModelVo
										.getUnitPrice());
								isContainUpdate = true;
							}

							if (!isContainUpdate) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("签约设备中修改的参数为空!");
								return JSON.toJSONString(dto);
							}
							cachUpdateCDModelList.add(doCDModel);
						}

					}
				}
				contractOrderBo.setContractOrder_Id(contractOrderId);
				dto = baseDao.updateByPrimaryKeySelective(
						ContractOrderMapper.class, contractOrderBo);
				if (!FrameworkUtils.isSuccess(dto)) {
					return JSON.toJSONString(dto);
				}
				for (ContractDeviceModel updateCDModel : cachUpdateCDModelList) {
					dto = baseDao.updateByPrimaryKeySelective(
							ContractDeviceModelMapper.class, updateCDModel);
					if (!FrameworkUtils.isSuccess(dto)) {
						return JSON.toJSONString(dto);
					}
				}
				for (ContractDeviceModel addCDModel : cachAddCDModelList) {
					dto = baseDao.insertSelective(
							ContractDeviceModelMapper.class, addCDModel);
					if (!FrameworkUtils.isSuccess(dto)) {
						return JSON.toJSONString(dto);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateContractOrderAndContractDeviceModel exception!");
				throw new RuntimeException(
						"updateContractOrderAndContractDeviceModel Exception!");
			}

		} else {
			log.error("---updateContractOrderAndContractDeviceModel -------- data or contractOrderId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getContractOrder(String contractOrderId) {
		String jsonStr = "";
		BaseObjDto<ContractOrder> dto = new BaseObjDto<ContractOrder>();
		try {
			if (StringUtils.isBlank(contractOrderId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数contractOrderId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<ContractOrder> contractOrderDto = baseDao
					.selectByPrimaryKey(ContractOrderMapper.class,
							StringUtils.trim(contractOrderId));
			if (FrameworkUtils.isSuccess(contractOrderDto)) {
				ContractOrder contractOrder = contractOrderDto.getData();
				dto.setData(contractOrder);
				FrameworkUtils.setSuccess(dto);
				log.info("getContractOrder success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getContractOrder failure");
			}
		} catch (Exception e) {
			log.error("getContractOrder exception!");
			e.printStackTrace();
			throw new RuntimeException("getContractOrder Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getContractOrderAndContractDeviceModel(String contractOrderId) {
		String jsonStr = "";
		BaseObjDto<ContractOrderBo> dto = new BaseObjDto<ContractOrderBo>();
		try {
			if (StringUtils.isBlank(contractOrderId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数contractOrderId不能为空 !");
				return JSON.toJSONString(dto);
			}
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("contractOrder_Id", contractOrderId);
			BaseObjDto<ContractOrderBo> contractOrderBoDto = baseDao
					.getObjProperty(ContractOrderMapper.class,
							"selectContractOrderAndCDModelById", paramsMap);
			if (FrameworkUtils.isSuccess(contractOrderBoDto)) {
				ContractOrderBo contractOrderBo = contractOrderBoDto.getData();
				DeliveryOrderQueryForm form = new DeliveryOrderQueryForm();
				form.setContractOrderId(contractOrderId);
				form.setStatus(EntityConstant.STATUS_VALID);
				form.setDeliveryType(EntityConstant.TM_DELIVERYORDER_DELIVERYTYPE_OUTPUT);
				String listDeliveryOrderStr = deliveryOrderService
						.getAllDeliveryOrderList(form);
				if (FrameworkUtils.isSuccess(listDeliveryOrderStr)) {
					BaseObjDto<List<DeliveryOrder>> listoutDto = JSON
							.parseObject(listDeliveryOrderStr, BaseObjDto.class);
					if (listoutDto != null && listoutDto.getData() != null
							&& listoutDto.getData().size() > 0) {
						contractOrderBo.setDeliveryOrderOuts(listoutDto
								.getData());
					}
				}
				dto.setData(contractOrderBo);
				FrameworkUtils.setSuccess(dto);
				log.info("getContractOrderAndContractDeviceModel success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getContractOrderAndContractDeviceModel failure");
			}
		} catch (Exception e) {
			log.error("getContractOrderAndContractDeviceModel exception!");
			e.printStackTrace();
			throw new RuntimeException(
					"getContractOrderAndContractDeviceModel Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

	@Override
	public ContractOrder4PdfPojo getContractOrderDetailById(
			String contractOrderId) {
		ContractOrder4PdfPojo contractOrder4PdfPojo = null;
		try {
			if(StringUtils.isNotBlank(contractOrderId)){
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("contractOrder_Id", contractOrderId);
				BaseObjDto<ContractOrderBo> contractOrderBoDto = baseDao
						.getObjProperty(ContractOrderMapper.class,
								"selectContractOrderAndCDModelById", paramsMap);
				if (FrameworkUtils.isSuccess(contractOrderBoDto)) {
					ContractOrderBo contractOrderBo  = contractOrderBoDto.getData();
					contractOrder4PdfPojo = new ContractOrder4PdfPojo();
					contractOrder4PdfPojo.copyFrom(contractOrderBo);
					}
				
					log.info("getContractOrderDetailById success!");
				} else {
					
					log.error("getContractOrderDetailById failure");
				}
		} catch (Exception e) {
			log.error("getContractOrderDetailById exception!");
			e.printStackTrace();
			throw new RuntimeException("getContractOrderDetailById Exception!");
		}
		
		return contractOrder4PdfPojo;
	}

	@Override
	public String getContractOrderList(ContractOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<ContractOrderVo>> dto = new BaseObjDto<ItemPage<ContractOrderVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getLessorId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("甲方公司(lessorId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getLessorIdArray() == null
					|| (form.getLessorIdArray() != null && form
							.getLessorIdArray().length < 0)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("甲方方公司(lessorId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.isQueryAll()) {
				form.setLessorIdArray(null);
				form.setLessorId(null);
			}
			BaseObjDto<ItemPage<ContractOrderVo>> pagesDto = baseDao
					.getPageList(ContractOrderMapper.class,
							ContractOrderVo.class, form,
							"getContractOrderPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getContractOrderList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getContractOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getContractOrderList Exception !");
			throw new RuntimeException("getContractOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	

	

}
