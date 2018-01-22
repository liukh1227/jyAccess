package com.jy.access.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.UserPermissionsMapper;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.po.UserPermissions;
import com.jy.entity.pojo.SuccessReturnPojo;


@Service("userPermissionsService")
@Scope("prototype")
public class UserPermissionsServiceImpl implements UserPermissionsService {
	private static final Logger log = Logger.getLogger(UserPermissionsServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserAccountServie userAccountServie;
	
	@Override
	public String addUserPermissions(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				UserPermissions userPermissions = JSON.parseObject(data,UserPermissions.class);
				if (userPermissions == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (userPermissions.getUserAccountId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户Id不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnUserAccountStr = userAccountServie.getUserAccountById(StringUtils.trim(userPermissions.getUserAccountId()));
				if (!FrameworkUtils.isSuccess(returnUserAccountStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("用户Id参数不正确!");
					return JSON.toJSONString(dto);
				}
				if (userPermissions.getPermissionId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("权限Id不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnPermissionStr = permissionService.getPermission(StringUtils.trim(String.valueOf(userPermissions.getPermissionId())));
				if (!FrameworkUtils.isSuccess(returnPermissionStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("权限Id参数不正确!");
					return JSON.toJSONString(dto);
				}
				
				dto = baseDao.insertSelective(UserPermissionsMapper.class,userPermissions);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addRole success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(userPermissions.getUserPermissions_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addRole failure!");
				}

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addUserPermissions success!");
				} else {
					log.error("addUserPermissions failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addUserPermissions exception!");
				throw new RuntimeException("addUserPermissions Exception!");
			}
		} else {
			log.error("---addUserPermissions -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteUserPermissions(String userPermissionsId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(userPermissionsId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数userPermissionsId为空");
			log.error("---deleteUserPermissions -------- userPermissionsId is null ==== ");
			return JSON.toJSONString(dto);
		}

			try {
				dto = baseDao.deleteByPrimaryKey(UserPermissionsMapper.class,userPermissionsId);

			} catch (Exception e) {
				e.printStackTrace();
				log.error("deleteUserPermissions exception!");
				throw new RuntimeException("deleteUserPermissions Exception!");
			}
		
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
