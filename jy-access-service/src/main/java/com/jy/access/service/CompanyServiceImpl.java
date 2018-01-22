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
import com.jy.access.mapper.CompanyMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.bo.CompanyBo;
import com.jy.entity.form.CompanyQueryForm;
import com.jy.entity.po.Company;
import com.jy.entity.po.UserAccount;
import com.jy.entity.pojo.SuccessReturnPojo;

@Service("companyService")
@Scope("prototype")
public class CompanyServiceImpl implements CompanyService {
	private static final Logger log = Logger
			.getLogger(CompanyServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private UserAccountServie userAccountServie;

	@Override
	public String addCompany(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				Company company = JSON.parseObject(data, Company.class);
				if (company == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (company.getCompanyName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("公司名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (company.getCity() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("所在城市不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (company.getParentComanyId() != null) {
					String returnParentCompStr = this.getCompany(StringUtils
							.trim(company.getParentComanyId()));
					if (!FrameworkUtils.isSuccess(returnParentCompStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数parentComanyId不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				Date createDate = new Date();
				company.setCreateTime(createDate);
				if (company.getStatus() == null) {
					company.setStatus(EntityConstant.STATUS_VALID);
				}
				dto = baseDao.insertSelective(CompanyMapper.class, company);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addCompany success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(company.getCompany_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addCompany failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addCompany exception!");
				throw new RuntimeException("addCompany Exception!");
			}
		} else {
			log.error("---addCompany -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateCompany(String companyId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(companyId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数companyId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(companyId) && StringUtils.isNotBlank(data)) {
			try {
				Company company = JSON.parseObject(data, Company.class);
				if (company == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				company.setCompany_Id(companyId);
				if (company.getParentComanyId() != null) {
					String returnParentCompStr = this.getCompany(StringUtils
							.trim(company.getParentComanyId()));
					if (!FrameworkUtils.isSuccess(returnParentCompStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数parentComanyId不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				dto = baseDao.updateByPrimaryKeySelective(CompanyMapper.class,
						company);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateCompany success!");
				} else {
					log.error("updateCompany failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateCompany exception!");
				throw new RuntimeException("updateCompany Exception!");
			}

		} else {
			log.error("---updateCompany -------- data or companyId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getCompany(String companyId) {
		String jsonStr = "";
		BaseObjDto<Company> dto = new BaseObjDto<Company>();
		try {
			if (StringUtils.isBlank(companyId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数companyId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<Company> companyDto = baseDao.selectByPrimaryKey(
					CompanyMapper.class, StringUtils.trim(companyId));
			if (FrameworkUtils.isSuccess(companyDto)) {
				Company company = companyDto.getData();
				dto.setData(company);
				FrameworkUtils.setSuccess(dto);
				log.info("getCompany success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCompany failure");
			}
		} catch (Exception e) {
			log.error("getCompany exception!");
			e.printStackTrace();
			throw new RuntimeException("getCompany Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getCompanyAndBankAccountList(String companyId) {
		String jsonStr = "";
		BaseObjDto<Company> dto = new BaseObjDto<Company>();
		try {
			if (StringUtils.isBlank(companyId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数companyId不能为空 !");
				return JSON.toJSONString(dto);
			}
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("company_Id", companyId);
			BaseObjDto<CompanyBo> companyBoDto = baseDao.getObjProperty(
					CompanyMapper.class, "selectCompanyAndBankAccountById",
					paramsMap);

			if (FrameworkUtils.isSuccess(companyBoDto)) {
				CompanyBo companyBo = companyBoDto.getData();
				dto.setData(companyBo);
				FrameworkUtils.setSuccess(dto);
				log.info("getCompanyAndBankAccountList success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCompanyAndBankAccountList failure");
			}
		} catch (Exception e) {
			log.error("getCompanyAndBankAccountList exception!");
			e.printStackTrace();
			throw new RuntimeException(
					"getCompanyAndBankAccountList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getCompanyList(CompanyQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<Company>> dto = new BaseObjDto<ItemPage<Company>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("管理的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyIdArray() == null
					|| (form.getCompanyIdArray() != null && form
							.getCompanyIdArray().length < 0)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("管理的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.isQueryAll()) {
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<ItemPage<Company>> pagesDto = baseDao.getPageList(
					CompanyMapper.class, Company.class, form,
					"getCompanyPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getCompanyList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCompanyList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCompanyList Exception !");
			throw new RuntimeException("getCompanyList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getCompanyAndBankAccountPageList(CompanyQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<CompanyBo>> dto = new BaseObjDto<ItemPage<CompanyBo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("管理的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyIdArray() == null
					|| (form.getCompanyIdArray() != null && form
							.getCompanyIdArray().length < 0)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("管理的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.isQueryAll()) {
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<ItemPage<CompanyBo>> pagesDto = baseDao.getPageList(
					CompanyMapper.class, CompanyBo.class, form,
					"getCompanyAndBankAccountPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getCompanyAndBankAccountPageList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCompanyAndBankAccountPageList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCompanyAndBankAccountPageList Exception !");
			throw new RuntimeException(
					"getCompanyAndBankAccountPageList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getCompanyAndBankAccountAndCustomerPageList(
			CompanyQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<CompanyBo>> dto = new BaseObjDto<ItemPage<CompanyBo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("管理的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyIdArray() == null
					|| (form.getCompanyIdArray() != null && form
							.getCompanyIdArray().length < 0)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("管理的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.isQueryAll()) {
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<ItemPage<CompanyBo>> pagesDto = baseDao.getPageList(
					CompanyMapper.class, CompanyBo.class, form,
					"getCompanyAndBankAccountAndCustomerPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getCompanyAndBankAccountAndCustomerPageList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCompanyAndBankAccountAndCustomerPageList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCompanyAndBankAccountAndCustomerPageList Exception !");
			throw new RuntimeException(
					"getCompanyAndBankAccountAndCustomerPageList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllCompanyList(CompanyQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<Company>> dto = new BaseObjDto<List<Company>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("管理的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getCompanyIdArray() == null
					|| (form.getCompanyIdArray() != null && form
							.getCompanyIdArray().length < 0)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("管理的公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.isQueryAll()) {
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}

			BaseObjDto<List<Company>> dataDto = baseDao.getList(
					CompanyMapper.class, Company.class, "getAllCompanyList",
					form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllCompanyList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllCompanyList Exception");
			throw new RuntimeException("getAllCompanyList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	/**
	 * 通过父公司Id获取下属公司
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午4:26:50
	 * @param companyId
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.CompanyService#getChildListByParentId(java.lang.String)
	 */

	@Override
	public List<Company> getChildListByParentId(String companyId) {
		CompanyQueryForm form = new CompanyQueryForm();
		form.setParentComanyId(StringUtils.trim(companyId));
		form.setStatus(EntityConstant.STATUS_VALID);
		BaseObjDto<List<Company>> dataDto = baseDao.getList(
				CompanyMapper.class, Company.class, "getAllCompanyList", form);
		if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null
				&& dataDto.getData().size() > 0) {
			return dataDto.getData();
		}
		return null;
	}

	/**
	 * 递归获取能范围公司的简要信息
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午4:26:01
	 * @param companyId
	 * @param cachCompanyList
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.CompanyService#getCurrentAndChildCompanyList(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public List<Company> getCurrentAndChildCompanyList(String companyId,
			List<Company> cachCompanyList) {

		List<Company> childList = getChildListByParentId(companyId);

		if (childList != null && childList.size() > 0) {
			for (Company company : childList) {
				getCurrentAndChildCompanyList(company.getCompany_Id(),
						cachCompanyList);

			}

		}

		BaseObjDto<Company> companyDto = baseDao.selectByPrimaryKey(
				CompanyMapper.class, StringUtils.trim(companyId));
		if (FrameworkUtils.isSuccess(companyDto)) {
			Company queryCompany = companyDto.getData();
			Company doCompany = new Company();
			doCompany.setCompany_Id(queryCompany.getCompany_Id());
			doCompany.setCompanyName(queryCompany.getCompanyName());
			cachCompanyList.add(doCompany);
		}

		return cachCompanyList;
	}

	/**
	 * 递归获获取下属公司的详细信息
	 * 
	 * @author liukh
	 * @date 2017-8-30 下午4:25:22
	 * @param companyId
	 * @param cachCompanyList
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.CompanyService#getCurrentAndChildCompanyDetailList(java.lang.String,
	 *      java.util.List)
	 */
	public List<Company> getCurrentAndChildCompanyDetailList(String companyId,
			List<Company> cachCompanyList) {

		List<Company> childList = getChildListByParentId(companyId);
		if (childList != null && childList.size() > 0) {
			for (Company company : childList) {
				getCurrentAndChildCompanyDetailList(company.getCompany_Id(),
						cachCompanyList);

			}

		}

		BaseObjDto<Company> companyDto = baseDao.selectByPrimaryKey(
				CompanyMapper.class, StringUtils.trim(companyId));
		if (FrameworkUtils.isSuccess(companyDto)) {
			Company queryCompany = companyDto.getData();
			cachCompanyList.add(queryCompany);
		}

		return cachCompanyList;
	}

	/**
	 * 获取当前公司信息及下属公司的信息
	 * 
	 * @author liukh
	 * @date 2017-7-24 下午3:39:29
	 * @param form
	 * @return (non-Javadoc)
	 * @see com.jy.access.service.CompanyService#getAllCompanyAndSubordinate(String
	 *      companyId)
	 */
	@Override
	public String getAllCompanyAndSubordinate(String companyId) {
		String jsonStr = "";
		BaseObjDto<List<Company>> dto = new BaseObjDto<List<Company>>();
		try {

			if (StringUtils.isBlank(companyId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数companyId不能为空 !");
				return JSON.toJSONString(dto);
			}
			List<Company> cachCompanyList = new ArrayList<Company>();

			List<Company> companyAndSubordinateList = this
					.getCurrentAndChildCompanyList(StringUtils.trim(companyId),
							cachCompanyList);
			if (companyAndSubordinateList != null
					&& companyAndSubordinateList.size() > 0) {
				dto.setData(companyAndSubordinateList);
				FrameworkUtils.setSuccess(dto);
			} else {
				FrameworkUtils.setNoData(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllCompanyAndSubordinate Exception");
			throw new RuntimeException("getAllCompanyAndSubordinate Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@SuppressWarnings("unchecked")
	public String getAllCanVisitCompanyAndSubordinateDetailByUserAccountId(String userAccountId) {
		String jsonStr = "";
		BaseObjDto<List<Company>> dto = new BaseObjDto<List<Company>>();
		try {

			if (StringUtils.isBlank(userAccountId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数userAccountId不能为空 !");
				return JSON.toJSONString(dto);
			}
			String returnUserAccountStr = userAccountServie
					.getUserAccountById(userAccountId);
			if (!FrameworkUtils.isSuccess(returnUserAccountStr)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数UserAccountId不正确!");
				return JSON.toJSONString(dto);
			}
			
			BaseObjDto<UserAccount> returnUserAccountDto = JSON.parseObject(
					returnUserAccountStr, BaseObjDto.class);
			UserAccount queryUserAccount = JSON.parseObject(
					JSON.toJSONString(returnUserAccountDto.getData()),
					UserAccount.class);
			List<Company> companyAndSubordinateList = null;
			if (queryUserAccount != null) {
				if (queryUserAccount.isUserLevelUserAccount()
						&& queryUserAccount.getCompanyId() != null) {

					if (queryUserAccount.isGlobalDataScope()) {
						companyAndSubordinateList = this
								.getCurrentAndChildCompanyDetailList(StringUtils
										.trim(queryUserAccount.getCompanyId()),
										new ArrayList<Company>());

					} else if (queryUserAccount.isPartDataScope()) {
						companyAndSubordinateList = new ArrayList<Company>();
						BaseObjDto<Company> companyDto = baseDao
								.selectByPrimaryKey(CompanyMapper.class,
										StringUtils.trim(queryUserAccount
												.getCompanyId()));
						if (FrameworkUtils.isSuccess(companyDto)) {
							Company company = companyDto.getData();
							companyAndSubordinateList.add(company);
						}

					}

				}

			}
			
			if (companyAndSubordinateList != null
					&& companyAndSubordinateList.size() > 0) {
				dto.setData(companyAndSubordinateList);
				FrameworkUtils.setSuccess(dto);
			} else {
				FrameworkUtils.setNoData(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllCanVisitCompanyAndSubordinateDetailByUserAccountId Exception");
			throw new RuntimeException("getAllCanVisitCompanyAndSubordinateDetailByUserAccountId Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllCanVisitCompanyAndSubordinateByUserAccountId(
			String userAccountId) {
		String jsonStr = "";
		BaseObjDto<List<Company>> dto = new BaseObjDto<List<Company>>();
		try {

			if (StringUtils.isBlank(userAccountId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("参数userAccountId不能为空 !");
				return JSON.toJSONString(dto);
			}

			String returnUserAccountStr = userAccountServie
					.getUserAccountById(userAccountId);
			if (!FrameworkUtils.isSuccess(returnUserAccountStr)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数UserAccountId不正确!");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<UserAccount> returnUserAccountDto = JSON.parseObject(
					returnUserAccountStr, BaseObjDto.class);
			UserAccount queryUserAccount = JSON.parseObject(
					JSON.toJSONString(returnUserAccountDto.getData()),
					UserAccount.class);
			List<Company> companyAndSubordinateList = null;
			if (queryUserAccount != null) {
				if (queryUserAccount.isUserLevelUserAccount()
						&& queryUserAccount.getCompanyId() != null) {

					if (queryUserAccount.isGlobalDataScope()) {
						companyAndSubordinateList = this
								.getCurrentAndChildCompanyList(StringUtils
										.trim(queryUserAccount.getCompanyId()),
										new ArrayList<Company>());

					} else if (queryUserAccount.isPartDataScope()) {
						companyAndSubordinateList = new ArrayList<Company>();
						BaseObjDto<Company> companyDto = baseDao
								.selectByPrimaryKey(CompanyMapper.class,
										StringUtils.trim(queryUserAccount
												.getCompanyId()));
						if (FrameworkUtils.isSuccess(companyDto)) {
							Company company = companyDto.getData();
							Company addCompany = new Company();
							addCompany.setCompany_Id(company.getCompany_Id());
							addCompany.setCompanyName(company.getCompanyName());
							companyAndSubordinateList.add(addCompany);
						}

					}

				}

			}

			if (companyAndSubordinateList != null
					&& companyAndSubordinateList.size() > 0) {
				dto.setData(companyAndSubordinateList);
				FrameworkUtils.setSuccess(dto);
			} else {
				FrameworkUtils.setNoData(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllCanVisitCompanyAndSubordinateByUserAccountId Exception");
			throw new RuntimeException(
					"getAllCanVisitCompanyAndSubordinateByUserAccountId Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
