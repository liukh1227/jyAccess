package com.jy.access.service;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.jy.access.common.DataCache;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.CompanyMapper;
import com.jy.access.mapper.UserAccountMapper;
import com.jy.base.common.Constant;
import com.jy.base.common.ItemPage;
import com.jy.base.common.utils.CommonUtils;
import com.jy.base.common.utils.PasswordUtils;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.bo.UserAccountBo;
import com.jy.entity.form.PermissionQueryForm;
import com.jy.entity.form.UserAccountQueryForm;
import com.jy.entity.po.Company;
import com.jy.entity.po.Permission;
import com.jy.entity.po.UserAccount;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.pojo.UserAccountLoginPojo;
import com.jy.entity.pojo.UserAccountPasswordResetPojo;
import com.jy.entity.pojo.UserAccountPasswordUpdatePojo;
import com.jy.entity.vo.UserAccountVo;

@Service("userAccountServie")
@Scope("prototype")
public class UserAccountServieImpl implements UserAccountServie {
	private static final Logger log = Logger
			.getLogger(UserAccountServieImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PermissionService permissionService;

	@SuppressWarnings("unchecked")
	@Override
	public String addMessageHistoryAndGetValidCode(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			Map<String, Object> params = (Map<String, Object>) JSON.parse(data);
			if (params == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("The data parse Map is  null");
				return JSON.toJSONString(dto);
			} else if (params != null && params.get("cellPhone") == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("The params cellPhone is null");
				return JSON.toJSONString(dto);
			}
			String cellPhone = params.get("cellPhone").toString();
			String smsCode = null;
			try {
				// smsCode = CommonUtils.getRandomNumberString(4);
				smsCode = "0000";
				String nowStr = CommonUtils.getDateString(new Date(),
						"yyyy-MM-dd HH:mm:ss");
				DataCache cache = DataCache.getInstance();
				cache.setSmsCode(cellPhone, smsCode + "|" + nowStr);
				// jsonStr = SmsUtil.sendSms(cellPhone, smsCode);
				log.info("getValidCode ---- smsCode ========= " + smsCode);
				log.info("getValidCode ---- jsonStr ========= " + jsonStr);
				// 插入短信发送历史表
				jsonStr = "{\"rcode\":0,\"rinfo\":\"短信发送成功\"}";

				return jsonStr;
			} catch (Exception e) {
				log.error("---addMessageHistoryAndGetValidCode ---- data parse error ====== ");
				e.printStackTrace();
				throw new RuntimeException(
						"addMessageHistoryAndGetValidCode Exception!");
			}
		} else {
			log.error("---addMessageHistoryAndGetValidCode ---- data is null ====== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String checkValidCode(String cellPhone, String validCode) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		try {
			if (StringUtils.isBlank(cellPhone)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("cellPhone is null !");
				return JSON.toJSONString(dto);
			}
			if (StringUtils.isBlank(validCode)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("validCode is null !");
				return JSON.toJSONString(dto);
			}
			DataCache cache = DataCache.getInstance();
			String smsStr = cache.getSmsCode(cellPhone);
			if (StringUtils.isNotBlank(smsStr)) {
				String[] sms = smsStr.split("\\|");
				if (StringUtils.isNotBlank(sms[0])) {
					String smsCode = sms[0];
					String createTime = sms[1];
					if (StringUtils.isBlank(smsCode)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("data is error");
						return JSON.toJSONString(dto);
					} else if (StringUtils.isEmpty(createTime)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("data is error");
						return JSON.toJSONString(dto);
					}
					if (!StringUtils.equals(validCode, smsCode)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("validCode is error");
						return JSON.toJSONString(dto);
					}
					Calendar now = Calendar.getInstance();
					Calendar time = Calendar.getInstance();
					time.setTime(CommonUtils.parseDate(createTime,
							"yyyy-MM-dd HH:mm:ss"));
					long nowTime = now.getTimeInMillis();
					long smsTime = time.getTimeInMillis();
					long minute = (nowTime - smsTime) / (1000 * 60);// 转化minute
					if (minute >= 3) {// 如果超过3分钟,就移除
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("validCode is timeout");
						return JSON.toJSONString(dto);
					}
					if (!StringUtils.equals(validCode, smsCode)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("validCode is not equal smsCode");
						return JSON.toJSONString(dto);
					}
					dto.setRcode(BaseDto.SUCCESS_RCODE);
					dto.setRinfo("success");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("checkValidCode exception!");
			throw new RuntimeException("checkValidCode Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String isValidUserAccount(String account) {
		BaseDto dto = new BaseDto();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", StringUtils.trim(account));
		BaseObjDto<UserAccount> userVailDto = baseDao.getObjProperty(
				UserAccountMapper.class, "getUserAccountByIdOrAccount", params);
		if (FrameworkUtils.isSuccess(userVailDto)) {
			dto.setRcode(BaseDto.SUCCESS_RCODE);
			dto.setRinfo("该手机后已经注册，请勿重复注册!");
			return JSON.toJSONString(dto);
		} else if (String.valueOf(userVailDto.getRcode()).equals(
				BaseDto.NO_DATA_RCODE.toString())) {
			dto.setRcode(BaseDto.NO_DATA_RCODE);
			dto.setRinfo("该手机尚未注册，可以注册使用!");
			return JSON.toJSONString(dto);
		}

		return JSON.toJSONString(dto);
	}

	@Override
	public String addUserAccount(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				UserAccount userAccount = JSON.parseObject(data,
						UserAccount.class);
				if (userAccount == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (userAccount.getAccount() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户账号不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (userAccount.getUserName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户姓名不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (userAccount.getCellPhone() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户手机号不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (!CommonUtils.isMobileNo(userAccount.getCellPhone())) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户手机号不正确 !");
					return JSON.toJSONString(dto);
				}

				if (userAccount.getPassword() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户密码不能为空 !");
					return JSON.toJSONString(dto);
				}

				String passwordSalt = CommonUtils.getRandomString(40)
						.toLowerCase();
				userAccount.setPasswordSalt(passwordSalt);
				String userPass = PasswordUtils.encyptPassword(
						userAccount.getAccount(), userAccount.getPassword(),
						passwordSalt);
				userAccount.setPassword(userPass);

				if (userAccount.getSysLevel() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户所属级别不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (!userAccount.isRightLeve()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户所属级别参数不正确 !");
					return JSON.toJSONString(dto);
				}
				if (userAccount.isUserLevelUserAccount()) {
					if (userAccount.getDataScope() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("数据范围不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (!userAccount.isRightDataScope()) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("数据范围参数不正确 !");
						return JSON.toJSONString(dto);
					}
					if (userAccount.getCreator() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建人不能为空 !");
						return JSON.toJSONString(dto);
					}
					String returnCreatorStr = this
							.getUserAccountById(StringUtils.trim(userAccount
									.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建人参数不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				// 手机验证码
				/*
				 * jsonStr = this.checkValidCode(userAccount.getCellPhone(),
				 * userAccount.getValidCode()); BaseDto basDto =
				 * JSON.parseObject(jsonStr, BaseDto.class); if
				 * (!FrameworkUtils.isSuccess(basDto)) { return jsonStr; }
				 */
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("account",
						StringUtils.trim(userAccount.getAccount()));
				BaseObjDto<UserAccount> userVailDto = baseDao.getObjProperty(
						UserAccountMapper.class, "getUserAccountByIdOrAccount",
						params);
				if (FrameworkUtils.isSuccess(userVailDto)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("该手机号已经注册，请勿重复注册!");
					return JSON.toJSONString(dto);
				}

				if (userAccount.getCompanyId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户所属公司Id(companyId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCompanyStr = companyService.getCompany(StringUtils
						.trim(userAccount.getCompanyId()));
				if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户所属公司Id(companyId)不正确 !");
					return JSON.toJSONString(dto);
				}
				Date createDate = new Date();
				userAccount.setCreateTime(createDate);
				if (userAccount.getStatus() == null) {
					userAccount.setStatus(EntityConstant.STATUS_VALID);
				}

				dto = baseDao.insertSelective(UserAccountMapper.class,
						userAccount);
				if (FrameworkUtils.isSuccess(dto)) {

					log.info("addUserAccount success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(userAccount.getUserAccount_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addUserAccount failure!");
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error("addUserAccount exception!");
				throw new RuntimeException("addUserAccount Exception!");
			}

		} else {
			log.error("---addStopOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getUserAccountById(String userAccountId) {
		String jsonStr = "";
		BaseObjDto<UserAccountVo> dto = new BaseObjDto<UserAccountVo>();
		try {
			if (StringUtils.isBlank(userAccountId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数userAccountId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<UserAccountVo> userAccountDto = baseDao
					.selectByPrimaryKey(UserAccountMapper.class,
							StringUtils.trim(userAccountId));
			if (FrameworkUtils.isSuccess(userAccountDto)) {
				UserAccountVo userAccount = userAccountDto.getData();
				dto.setData(userAccount);
				FrameworkUtils.setSuccess(dto);
				log.info("getUserAccountById success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getUserAccountById failure");
			}
		} catch (Exception e) {
			log.error("getUserAccountById exception!");
			e.printStackTrace();
			throw new RuntimeException("getUserAccountById Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getUserAccountByIdOrAccount(String data) {
		String jsonStr = "";
		BaseObjDto<UserAccount> dto = new BaseObjDto<UserAccount>();
		try {
			if (StringUtils.isBlank(data)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			Map<String, Object> paramas = JSON.parseObject(data);
			if (paramas == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数不能为空 !");
				return JSON.toJSONString(dto);
			} else if (paramas.get("userAccountId") == null
					&& paramas.get("account") == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数不能为空 !");
				return JSON.toJSONString(dto);
			}

			Map<String, Object> queryParamas = new HashMap<String, Object>();
			if (paramas.get("userAccountId") != null) {
				queryParamas
						.put("userAccount_Id", paramas.get("userAccountId"));
			}
			if (paramas.get("account") != null) {
				queryParamas.put("account", paramas.get("account"));
			}

			BaseObjDto<UserAccount> queryUserAccountDto = baseDao
					.getObjProperty(UserAccountMapper.class,
							"getUserAccountByIdOrAccount", queryParamas);

			if (FrameworkUtils.isSuccess(queryUserAccountDto)) {
				UserAccount userAccount = queryUserAccountDto.getData();
				dto.setData(userAccount);
				FrameworkUtils.setSuccess(dto);
				log.info("getUserAccountByIdOrAccount success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getUserAccountByIdOrAccount failure");
			}
		} catch (Exception e) {
			log.error("getUserAccountByIdOrAccount exception!");
			e.printStackTrace();
			throw new RuntimeException("getUserAccountByIdOrAccount Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getUserAccountDetailInfor(String data) {
		String jsonStr = "";
		BaseObjDto<UserAccountBo> dto = new BaseObjDto<UserAccountBo>();
		if (StringUtils.isNotBlank(data)) {
			try {
				UserAccountLoginPojo userAccountLoginPojo = JSON.parseObject(
						data, UserAccountLoginPojo.class);
				if (userAccountLoginPojo == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}

				if (userAccountLoginPojo.getAccount() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("账号不能为空!");
					return JSON.toJSONString(dto);
				}
				if (userAccountLoginPojo.getPassword() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("密码不能为空!");
					return JSON.toJSONString(dto);
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("account",
						StringUtils.trim(userAccountLoginPojo.getAccount()));
				BaseObjDto<UserAccount> queryUserAccountDto = baseDao
						.getObjProperty(UserAccountMapper.class,
								"getUserAccountByIdOrAccount", params);
				if (!FrameworkUtils.isSuccess(queryUserAccountDto)) {
					dto.setRcode(Constant.USER_LOGON_ERROR_USERNOTEXIST);
					dto.setRinfo(Constant.USER_LOGON_ERROR_USERNOTEXIST_INFOR);
					return JSON.toJSONString(dto);
				}

				UserAccount queryUserAccount = queryUserAccountDto.getData();
				if (queryUserAccount != null) {
					String passwordSalt = queryUserAccount.getPasswordSalt();
					String password = PasswordUtils.encyptPassword(
							StringUtils.trim(queryUserAccount.getAccount()),
							userAccountLoginPojo.getPassword(), passwordSalt);
					if (!password.equals(queryUserAccount.getPassword())) {
						dto.setRcode(Constant.USER_LOGON_ERROR_PASSWORDNOTMATCH);
						dto.setRinfo(Constant.USER_LOGON_ERROR_PASSWORDNOTMATCH_INFOR);
						return JSON.toJSONString(dto);
					}

				}
				UserAccountBo userAccountBo = new UserAccountBo();
				userAccountBo.copyFrom(queryUserAccount);
				//可以访问的权限
				PermissionQueryForm permForm = new PermissionQueryForm();
				permForm.setUserAccountId(userAccountBo.getUserAccount_Id());
				String listPermissionStr = permissionService.getAllPermissionListByUserAccountId(permForm);
				if (FrameworkUtils.isSuccess(listPermissionStr)) {
					BaseObjDto<List<Permission>> listoutDto = JSON
							.parseObject(listPermissionStr, BaseObjDto.class);
					if (listoutDto != null && listoutDto.getData() != null
							&& listoutDto.getData().size() > 0) {
						//userAccountBo.setVistPermissionList(listoutDto.getData());	
						List<Permission> vistPermissionList =  JSON.parseArray(listoutDto.getData().toString(), Permission.class);
						String[] vistPermissionArray = new String[vistPermissionList.size()];
						for(int index = 0;index<vistPermissionList.size();index++){
							Permission permission = vistPermissionList.get(index);
							vistPermissionArray[index] = permission.getResourceUrl();
						}
						userAccountBo.setVistPermissionArray(vistPermissionArray);
					}
				}
				
				//可以访问的公司
				if (queryUserAccount.isUserLevelUserAccount()
						&& queryUserAccount.getCompanyId() != null) {
					List<Company> companyAndSubordinateList = null;
					if(queryUserAccount.isGlobalDataScope()){
						companyAndSubordinateList = companyService
								.getCurrentAndChildCompanyList(StringUtils
										.trim(queryUserAccount.getCompanyId()),
										new ArrayList<Company>());
					
					
					}else if(queryUserAccount.isPartDataScope()){
						 companyAndSubordinateList = new ArrayList<Company>();
						 BaseObjDto<Company> companyDto = baseDao
									.selectByPrimaryKey(CompanyMapper.class,
											StringUtils.trim(queryUserAccount.getCompanyId()));
							if (FrameworkUtils.isSuccess(companyDto)) {
								Company company = companyDto.getData();
								Company addCompany = new Company();
								addCompany.setCompany_Id(company.getCompany_Id());
								addCompany.setCompanyName(company.getCompanyName());
								companyAndSubordinateList.add(addCompany);
								}
						 
						 
					}
					if (companyAndSubordinateList != null
							&& companyAndSubordinateList.size() > 0) {
						String[] vistCompanyArray = new String[companyAndSubordinateList.size()];
						for(int index = 0;index<companyAndSubordinateList.size();index++){
							Company company = companyAndSubordinateList.get(index);
							vistCompanyArray[index] = company.getCompany_Id();
						}
						//userAccountBo.setVistCompanyArray(vistCompanyArray);
						userAccountBo
								.setVistCompanyList(companyAndSubordinateList);
					}
				
				}
				dto.setData(userAccountBo);
				FrameworkUtils.setSuccess(dto);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("getUserAccountDetailInfor exception!");
				throw new RuntimeException("getUserAccountDetailInfor Exception!");
			}

		} else {
			log.error("---getUserAccountDetailInfor -------- data  ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateUserAccount(String userAccountId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(userAccountId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数userAccountId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(userAccountId)
				&& StringUtils.isNotBlank(data)) {
			try {
				UserAccount userAccount = JSON.parseObject(data,
						UserAccount.class);
				if (userAccount == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				userAccount.setUserAccount_Id(userAccountId);

				if (userAccount.getPassword() != null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("该方法不支持修改密码!");
					return JSON.toJSONString(dto);
				}

				if (userAccount.getSysLevel() != null
						&& !userAccount.isRightLeve()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数用户级别不正确!");
					return JSON.toJSONString(dto);
				}

				if (userAccount.getDataScope() != null
						&& !userAccount.isRightDataScope()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数用户数据访问范围不正确!");
					return JSON.toJSONString(dto);
				}

				if (userAccount.getCompanyId() != null) {
					String returnCompanyStr = companyService
							.getCompany(StringUtils.trim(userAccount
									.getCompanyId()));

					if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数所属公司Id(companyId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if (userAccount.getCreator() != null) {
					String returnCreatorStr = this
							.getUserAccountById(StringUtils.trim(userAccount
									.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id(creator)不正确 !");
						return JSON.toJSONString(dto);
					}
				}

				dto = baseDao.updateByPrimaryKeySelective(
						UserAccountMapper.class, userAccount);
				if (FrameworkUtils.isSuccess(dto)) {
					log.error("updateUserAccount success!");
				} else {
					log.error("updateUserAccount failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateUserAccount exception!");
				throw new RuntimeException("updateUserAccount Exception!");
			}

		} else {
			log.error("---updateUserAccount -------- data or userAccountId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updatePassword(String userAccountId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(userAccountId)
				&& StringUtils.isNotBlank(data)) {
			try {
				UserAccount updateUserAccount = new UserAccount();
				UserAccountPasswordUpdatePojo userAccountPassword = JSON
						.parseObject(data, UserAccountPasswordUpdatePojo.class);
				if (userAccountPassword == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				updateUserAccount.setUserAccount_Id(userAccountId);
				if (userAccountPassword.getOldPassword() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("原密码不能为空!");
					return JSON.toJSONString(dto);
				}
				if (userAccountPassword.getNewPassword() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("新密码不能为空!");
					return JSON.toJSONString(dto);
				}

				BaseObjDto<UserAccountVo> userAccountDto = baseDao
						.selectByPrimaryKey(UserAccountMapper.class,
								StringUtils.trim(userAccountId));
				if (!FrameworkUtils.isSuccess(userAccountDto)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数(userAccountId)不正确!");
					return JSON.toJSONString(dto);
				}
				UserAccountVo queryUserAccount = userAccountDto.getData();
				if (queryUserAccount != null) {
					String passwordSalt = queryUserAccount.getPasswordSalt();
					String password = PasswordUtils.encyptPassword(
							StringUtils.trim(queryUserAccount.getAccount()),
							userAccountPassword.getOldPassword(), passwordSalt);
					if (!password.equals(queryUserAccount.getPassword())) {
						dto.setRcode(Constant.USER_LOGON_ERROR_PASSWORDNOTMATCH);
						dto.setRinfo("旧密码不正确,请重新输入!");
						return JSON.toJSONString(dto);
					}
					password = PasswordUtils.encyptPassword(
							StringUtils.trim(queryUserAccount.getAccount()),
							userAccountPassword.getNewPassword(), passwordSalt);
					updateUserAccount.setPassword(password);
				}
				dto = baseDao.updateByPrimaryKeySelective(
						UserAccountMapper.class, updateUserAccount);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updatePassword success!");
				} else {
					log.error("updatePassword failure!");
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error("updatePassword exception!");
				throw new RuntimeException("updatePassword Exception!");
			}

		} else {
			log.error("---updatePassword -------- data or userAccountId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updatePasswordReset(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				UserAccount updateUserAccount = new UserAccount();
				UserAccountPasswordResetPojo userAccountRestetPassword = JSON
						.parseObject(data, UserAccountPasswordResetPojo.class);
				if (userAccountRestetPassword == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (userAccountRestetPassword.getAccount() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数账号(account)不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (userAccountRestetPassword.getPassword() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数密码不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (userAccountRestetPassword.getAgainPassword() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数确认密码不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (!userAccountRestetPassword.getPassword().equals(
						userAccountRestetPassword.getAgainPassword())) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数确认密码和确认密码不一致!");
					return JSON.toJSONString(dto);
				}
				/**
				 * if(userAccountRestetPassword.getCellPhone()== null){
				 * dto.setRcode(BaseDto.ERROR_RCODE);
				 * dto.setRinfo("传入的参数确手机号不能为空 !"); return
				 * JSON.toJSONString(dto); }
				 * if(userAccountRestetPassword.getValidCode() == null){
				 * dto.setRcode(BaseDto.ERROR_RCODE);
				 * dto.setRinfo("传入的参数验证码不能为空 !"); return
				 * JSON.toJSONString(dto); }
				 * 
				 * 
				 * 
				 * String cellPhone = userAccountRestetPassword.getCellPhone();
				 * String validCode = userAccountRestetPassword.getValidCode();
				 * 
				 * // 校验手机验证码是否正确和过期 jsonStr = this.checkValidCode(cellPhone,
				 * validCode); BaseDto basDto = JSON.parseObject(jsonStr,
				 * BaseDto.class); if (!FrameworkUtils.isSuccess(basDto)) {
				 * return jsonStr; }
				 **/

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("account", StringUtils
						.trim(userAccountRestetPassword.getAccount()));
				BaseObjDto<UserAccount> queryUserAccountDto = baseDao
						.getObjProperty(UserAccountMapper.class,
								"getUserAccountByIdOrAccount", params);
				if (!FrameworkUtils.isSuccess(queryUserAccountDto)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数账号(account)不正确!");
					return JSON.toJSONString(dto);
				}
				UserAccount queryUserAccount = queryUserAccountDto.getData();
				String passwordSalt = queryUserAccount.getPasswordSalt();
				String usernewPass = PasswordUtils.encyptPassword(StringUtils
						.trim(userAccountRestetPassword.getAccount()),
						StringUtils.trim(userAccountRestetPassword
								.getPassword()), passwordSalt);

				updateUserAccount.setPassword(usernewPass);
				updateUserAccount.setUserAccount_Id(queryUserAccount
						.getUserAccount_Id());

				dto = baseDao.updateByPrimaryKeySelective(
						UserAccountMapper.class, updateUserAccount);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updatePassword success!");
				} else {
					log.error("updatePassword failure!");
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error("passwordReset exception!");
				throw new RuntimeException("passwordReset Exception!");
			}

		} else {
			log.error("---passwordReset -------- data or userId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getUserAccountList(UserAccountQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<UserAccountVo>> dto = new BaseObjDto<ItemPage<UserAccountVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("用户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyIdArray()== null || (form.getCompanyIdArray()!= null && form.getCompanyIdArray().length<0)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("用户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<ItemPage<UserAccountVo>> pagesDto = baseDao.getPageList(
					UserAccountMapper.class, UserAccountVo.class, form,
					"getUserAccountPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getUserAccountList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getUserAccountList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getUserAccountList Exception !");
			throw new RuntimeException("getUserAccountList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllUserAccountList(UserAccountQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<UserAccountVo>> dto = new BaseObjDto<List<UserAccountVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("用户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyIdArray()== null || (form.getCompanyIdArray()!= null && form.getCompanyIdArray().length<0)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("用户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<List<UserAccountVo>> dataDto = baseDao.getList(
					UserAccountMapper.class, UserAccountVo.class,
					"getAllUserAccountList", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllUserAccountList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllUserAccountList Exception");
			throw new RuntimeException("getAllUserAccountList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllNoConfigRoleUserAccountList(UserAccountQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<UserAccount>> dto = new BaseObjDto<List<UserAccount>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("用户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getRoleId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("角色Id不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getStatus() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("用户状态不能为空 !");
				return JSON.toJSONString(dto);
			}
		
			BaseObjDto<List<UserAccount>> dataDto = baseDao.getList(
					UserAccountMapper.class, UserAccount.class,
					"getAllNoConfigRoleUserAccountList", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllNoConfigRoleUserAccountList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllNoConfigRoleUserAccountList Exception");
			throw new RuntimeException("getAllNoConfigRoleUserAccountList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

}
