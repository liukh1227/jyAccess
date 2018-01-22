package com.jy.access.service;

import java.util.ArrayList;
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
import com.jy.access.mapper.BankAccountMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.BankAccountQueryForm;
import com.jy.entity.po.BankAccount;
import com.jy.entity.pojo.SuccessReturnPojo;

@Service("bankAccountService")
@Scope("prototype")
public class BankAccountServiceImpl implements BankAccountService {
	private static final Logger log = Logger
			.getLogger(BankAccountServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;

	@Override
	public String addBankAccount(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				BankAccount bankAccount = JSON.parseObject(data,
						BankAccount.class);
				if (bankAccount == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (bankAccount.getBankAccountNum() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("银行账号不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (bankAccount.getBankAccountName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("开户行名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (bankAccount.getBankAccountName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("户名不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (bankAccount.getIsDefault() == null) {
					bankAccount.setIsDefault(Boolean.FALSE);
				}
				if (bankAccount.getCompanyId() == null
						&& bankAccount.getCustomerId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("银行账号所属的用户不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (bankAccount.getCompanyId() != null
						&& bankAccount.getCustomerId() != null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("不能同时存在参数customerId和companyId !");
					return JSON.toJSONString(dto);
				}
				if (bankAccount.getCompanyId() != null) {
					String returnCompanyStr = companyService
							.getCompany(StringUtils.trim(bankAccount
									.getCompanyId()));
					if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数companyId不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if (bankAccount.getCustomerId() != null) {
					String returnCustomerStr = customerService
							.getCustomer(StringUtils.trim(bankAccount
									.getCustomerId()));
					if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数customerId不正确 !");
						return JSON.toJSONString(dto);
					}
				}

				Date createDate = new Date();
				bankAccount.setCreateTime(createDate);
				dto = baseDao.insertSelective(BankAccountMapper.class,
						bankAccount);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addBankAccount success!");
					jsonStr = this.updateBankAccountIsNotDefalut(bankAccount);
					if (!FrameworkUtils.isSuccess(jsonStr)) {
						return jsonStr;
					}
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(bankAccount.getBankAccount_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addBankAccount failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addBankAccount exception!");
				throw new RuntimeException("addBankAccount Exception!");
			}
		} else {
			log.error("---addBankAccount -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateBankAccount(String bankAccountId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(bankAccountId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数bankAccountId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(bankAccountId)
				&& StringUtils.isNotBlank(data)) {
			try {
				BankAccount bankAccount = JSON.parseObject(data,
						BankAccount.class);
				if (bankAccount == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (bankAccount.getCompanyId() != null
						&& bankAccount.getCustomerId() != null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("不能同时存在参数customerId和companyId !");
					return JSON.toJSONString(dto);
				}
				bankAccount.setBankAccount_Id(bankAccountId);

				if (bankAccount.getCompanyId() != null) {
					String returnCompanyStr = companyService
							.getCompany(StringUtils.trim(bankAccount
									.getCompanyId()));
					if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数companyId不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if (bankAccount.getCustomerId() != null) {
					String returnCustomerStr = customerService
							.getCustomer(StringUtils.trim(bankAccount
									.getCustomerId()));
					if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数customerId不正确 !");
						return JSON.toJSONString(dto);
					}
				}

				dto = baseDao.updateByPrimaryKeySelective(
						BankAccountMapper.class, bankAccount);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateBankAccount success!");
					if(bankAccount.getIsDefault() != null && bankAccount.getIsDefault()){
						return this.updateBankAccountIsNotDefalut(bankAccount);
					}
					
				} else {
					log.error("updateBankAccount failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateBankAccount exception!");
				throw new RuntimeException("updateCompany Exception!");
			}

		} else {
			log.error("---updateBankAccount -------- data or bankAccountId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateBankAccountIsNotDefalut(
			BankAccount updateDefaultBankAccount) {
		BaseDto dto = new BaseDto();
		if (updateDefaultBankAccount!= null && updateDefaultBankAccount.getIsDefault()) {
			List<String> cacheBankAccountIdList = new ArrayList<String>();
			if (updateDefaultBankAccount.getCompanyId() != null) {
				BankAccountQueryForm form = new BankAccountQueryForm();
				form.setCompanyId(updateDefaultBankAccount.getCompanyId());
				BaseObjDto<List<BankAccount>> dataDto = baseDao.getList(
						BankAccountMapper.class, BankAccount.class,
						"getAllBankAccountList", form);
				if (FrameworkUtils.isSuccess(dataDto)) {
					List<BankAccount> queryBAccountList = dataDto.getData();
					for (BankAccount queryBAccount : queryBAccountList) {
						if (queryBAccount.getBankAccount_Id() != null
								&& !queryBAccount.getBankAccount_Id().equals(
										updateDefaultBankAccount
												.getBankAccount_Id())) {
							cacheBankAccountIdList.add(queryBAccount
									.getBankAccount_Id());
						}
					}
				}
			} else if (updateDefaultBankAccount.getCustomerId() != null) {
				BankAccountQueryForm form = new BankAccountQueryForm();
				form.setCustomerId(updateDefaultBankAccount.getCustomerId());
				BaseObjDto<List<BankAccount>> dataDto = baseDao.getList(
						BankAccountMapper.class, BankAccount.class,
						"getAllBankAccountList", form);
				if (FrameworkUtils.isSuccess(dataDto)) {
					List<BankAccount> queryBAccountList = dataDto.getData();
					for (BankAccount queryBAccount : queryBAccountList) {
						if (queryBAccount.getBankAccount_Id() != null
								&& !queryBAccount.getBankAccount_Id().equals(
										updateDefaultBankAccount
												.getBankAccount_Id())) {
							cacheBankAccountIdList.add(queryBAccount
									.getBankAccount_Id());
						}
					}
				}
			} else {
				BaseObjDto<BankAccount> bankAccountDto = baseDao
						.selectByPrimaryKey(BankAccountMapper.class,
								StringUtils.trim(updateDefaultBankAccount
										.getBankAccount_Id()));
				if (FrameworkUtils.isSuccess(bankAccountDto)) {
					BankAccount queryBankAccount = bankAccountDto.getData();
					if (queryBankAccount.getCompanyId() != null) {
						BankAccountQueryForm form = new BankAccountQueryForm();
						form.setCompanyId(queryBankAccount.getCompanyId());
						BaseObjDto<List<BankAccount>> dataDto = baseDao
								.getList(BankAccountMapper.class,
										BankAccount.class,
										"getAllBankAccountList", form);
						if (FrameworkUtils.isSuccess(dataDto)) {
							List<BankAccount> queryBAccountList = dataDto
									.getData();
							for (BankAccount queryBAccount : queryBAccountList) {
								if (queryBAccount.getBankAccount_Id() != null
										&& !queryBAccount
												.getBankAccount_Id()
												.equals(updateDefaultBankAccount
														.getBankAccount_Id())) {
									cacheBankAccountIdList.add(queryBAccount
											.getBankAccount_Id());
								}
							}
						}
					} else if (queryBankAccount.getCustomerId() != null) {
						BankAccountQueryForm form = new BankAccountQueryForm();
						form.setCustomerId(queryBankAccount.getCustomerId());
						BaseObjDto<List<BankAccount>> dataDto = baseDao
								.getList(BankAccountMapper.class,
										BankAccount.class,
										"getAllBankAccountList", form);
						if (FrameworkUtils.isSuccess(dataDto)) {
							List<BankAccount> queryBAccountList = dataDto
									.getData();
							for (BankAccount queryBAccount : queryBAccountList) {
								if (queryBAccount.getBankAccount_Id() != null
										&& !queryBAccount
												.getBankAccount_Id()
												.equals(updateDefaultBankAccount
														.getBankAccount_Id())) {
									cacheBankAccountIdList.add(queryBAccount
											.getBankAccount_Id());
								}
							}
						}
					}
				}
			}
			if (cacheBankAccountIdList != null
					&& cacheBankAccountIdList.size() > 0) {
				for (String updateBankAccountId : cacheBankAccountIdList) {
					BankAccount updatebankAccount = new BankAccount();
					updatebankAccount.setBankAccount_Id(updateBankAccountId);
					updatebankAccount.setIsDefault(Boolean.FALSE);
					dto = baseDao.updateByPrimaryKeySelective(
							BankAccountMapper.class, updatebankAccount);
					if (!FrameworkUtils.isSuccess(dto)) {
						return JSON.toJSONString(dto);
					}

				}
			} else {
				dto.setRcode(BaseDto.SUCCESS_RCODE);
				dto.setRinfo(BaseDto.DEFAULT_SUCCESS_INFO);
			}

		} else {
			dto.setRcode(BaseDto.SUCCESS_RCODE);
			dto.setRinfo(BaseDto.DEFAULT_SUCCESS_INFO);
		}
		return JSON.toJSONString(dto);
	}

	@Override
	public String getBankAccount(String bankAccountId) {
		String jsonStr = "";
		BaseObjDto<BankAccount> dto = new BaseObjDto<BankAccount>();
		try {
			if (StringUtils.isBlank(bankAccountId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数bankAccountId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<BankAccount> bankAccountDto = baseDao
					.selectByPrimaryKey(BankAccountMapper.class,
							StringUtils.trim(bankAccountId));
			if (FrameworkUtils.isSuccess(bankAccountDto)) {
				BankAccount bankAccount = bankAccountDto.getData();
				dto.setData(bankAccount);
				FrameworkUtils.setSuccess(dto);
				log.info("getBankAccount success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getBankAccount failure");
			}
		} catch (Exception e) {
			log.error("getBankAccount exception!");
			e.printStackTrace();
			throw new RuntimeException("getBankAccount Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getBankAccountList(BankAccountQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<BankAccount>> dto = new BaseObjDto<ItemPage<BankAccount>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求的参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyId() == null && form.getCustomerId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求的参数公司Id或客户Id不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<ItemPage<BankAccount>> pagesDto = baseDao.getPageList(
					BankAccountMapper.class, BankAccount.class, form,
					"getBankAccountPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getBankAccountList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getBankAccountList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getBankAccountList Exception !");
			throw new RuntimeException("getBankAccountList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllBankAccountList(BankAccountQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<BankAccount>> dto = new BaseObjDto<List<BankAccount>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求的参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyId() == null && form.getCustomerId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求的参数公司Id或客户Id不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<List<BankAccount>> dataDto = baseDao.getList(
					BankAccountMapper.class, BankAccount.class,
					"getAllBankAccountList", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllBankAccountList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllBankAccountList Exception");
			throw new RuntimeException("getAllBankAccountList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
