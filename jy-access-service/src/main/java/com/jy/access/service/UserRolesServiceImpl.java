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
import com.jy.access.mapper.UserRolesMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.UserRolesQueryForm;
import com.jy.entity.po.UserRoles;
import com.jy.entity.pojo.SuccessReturnPojo;

@Service("userRolesService")
@Scope("prototype")
public class UserRolesServiceImpl implements UserRolesService {
	private static final Logger log = Logger.getLogger(UserRolesServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserAccountServie userAccountServie;
	
	@Override
	public String addUserRoles(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				UserRoles userRoles = JSON.parseObject(data,UserRoles.class);
				if (userRoles == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (userRoles.getUserAccountId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户Id不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnUserAccountStr = userAccountServie.getUserAccountById(StringUtils.trim(userRoles.getUserAccountId()));
				if (!FrameworkUtils.isSuccess(returnUserAccountStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户Id参数不正确!");
					return JSON.toJSONString(dto);
				}
				
				if (userRoles.getRoleId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("角色Id不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnRoleStr = roleService.getRole(StringUtils.trim(String.valueOf(userRoles.getRoleId())));
				if (!FrameworkUtils.isSuccess(returnRoleStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("角色Id参数不正确!");
					return JSON.toJSONString(dto);
				}
				
				
				UserRolesQueryForm form = new UserRolesQueryForm();
				form.setUserAccountId(userRoles.getUserAccountId());
				form.setRoleId(userRoles.getRoleId());
				jsonStr = this.getUserRolesList(form);
				if (FrameworkUtils.isSuccess(jsonStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("此用户和角色已关联，请勿重复添加!");
					return JSON.toJSONString(dto);
				}
				
				dto = baseDao.insertSelective(UserRolesMapper.class,userRoles);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addUserRoles success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(userRoles.getUserRoles_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addUserRoles failure!");
				}

			
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addUserRoles exception!");
				throw new RuntimeException("addUserRoles Exception!");
			}
		} else {
			log.error("---addUserRoles -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteUserRoles(String userRolesId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(userRolesId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数userRolesId为空");
			log.error("---deleteUserRoles -------- userRolesId is null ==== ");
			return JSON.toJSONString(dto);
		}

			try {
				dto = baseDao.deleteByPrimaryKey(UserRolesMapper.class,userRolesId);

			} catch (Exception e) {
				e.printStackTrace();
				log.error("deleteUserRoles exception!");
				throw new RuntimeException("deleteUserRoles Exception!");
			}
		
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getUserRolesList(UserRolesQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<UserRoles>> dto = new BaseObjDto<ItemPage<UserRoles>>();
		try {

			BaseObjDto<ItemPage<UserRoles>> pagesDto = baseDao.getPageList(
					UserRolesMapper.class, UserRoles.class, form,
					"getUserRolesPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getUserRolesList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getUserRolesList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getUserRolesList Exception !");
			throw new RuntimeException("getUserRolesList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllUserRolesList(UserRolesQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<UserRoles>> dto = new BaseObjDto<List<UserRoles>>();
		try {
			BaseObjDto<List<UserRoles>> dataDto = baseDao.getList(
					UserRolesMapper.class, UserRoles.class,
					"getAllUserRolesList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllUserRolesList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllUserRolesList Exception");
			throw new RuntimeException("getAllUserRolesList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getUserRolesConfigList(UserRolesQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<UserRoles>> dto = new BaseObjDto<ItemPage<UserRoles>>();
		try {
			if(form == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数不能为空 !");
				return JSON.toJSONString(dto);
			}else if(form.getCompanyId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}else if(form.getRoleId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数角色Id(roleId)不能为空 !");
				return JSON.toJSONString(dto);
			}

			BaseObjDto<ItemPage<UserRoles>> pagesDto = baseDao.getPageList(
					UserRolesMapper.class, UserRoles.class, form,
					"getUserRolesConfigPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getUserRolesConfigPageList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getUserRolesConfigPageList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getUserRolesConfigPageList Exception !");
			throw new RuntimeException("getUserRolesConfigPageList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllUserRolesConfigList(UserRolesQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<UserRoles>> dto = new BaseObjDto<List<UserRoles>>();
		try {
			if(form == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数不能为空 !");
				return JSON.toJSONString(dto);
			}else if(form.getCompanyId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}else if(form.getRoleId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数角色Id(roleId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<List<UserRoles>> dataDto = baseDao.getList(
					UserRolesMapper.class, UserRoles.class,
					"getAllUserRolesConfigList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllUserRolesConfigList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllUserRolesConfigList Exception");
			throw new RuntimeException("getAllUserRolesConfigList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

}
