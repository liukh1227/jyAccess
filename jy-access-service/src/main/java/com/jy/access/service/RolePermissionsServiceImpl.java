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
import com.jy.access.mapper.RolePermissionsMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.RolePermissionsQueryForm;
import com.jy.entity.po.RolePermissions;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.RolePermissionsVo;

@Service("rolePermissionsService")
@Scope("prototype")
public class RolePermissionsServiceImpl implements RolePermissionsService {
	private static final Logger log = Logger.getLogger(RolePermissionsServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	
	@Override
	public String addRolePermissions(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				RolePermissions rolePermissions = JSON.parseObject(data,RolePermissions.class);
				if (rolePermissions == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (rolePermissions.getPermissionId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("权限Id不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnPermissionStr = permissionService.getPermission(StringUtils.trim(String.valueOf(rolePermissions.getPermissionId())));
				if (!FrameworkUtils.isSuccess(returnPermissionStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("权限Id参数不正确!");
					return JSON.toJSONString(dto);
				}
				if (rolePermissions.getRoleId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("角色Id不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnRoleStr = roleService.getRole(StringUtils.trim(String.valueOf(rolePermissions.getRoleId())));
				if (!FrameworkUtils.isSuccess(returnRoleStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("角色Id参数不正确!");
					return JSON.toJSONString(dto);
				}
				RolePermissionsQueryForm form = new RolePermissionsQueryForm();
				form.setPermissionId(rolePermissions.getPermissionId());
				form.setRoleId(rolePermissions.getRoleId());
				jsonStr = this.getRolePermissionsList(form);
				if(FrameworkUtils.isSuccess(jsonStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("此角色权限已添加，请勿重复添加!");
					return JSON.toJSONString(dto);
				}
				
				dto = baseDao.insertSelective(RolePermissionsMapper.class,rolePermissions);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addRolePermissions success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(rolePermissions.getRolePermissions_Id());
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addRolePermissions failure!");
				}

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addRolePermissions success!");
				} else {
					log.error("addRolePermissions failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addRolePermissions exception!");
				throw new RuntimeException("addRolePermissions Exception!");
			}
		} else {
			log.error("---addRolePermissions -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteRolePermissions(String rolePermissionsId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(rolePermissionsId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数rolePermissionsId为空");
			log.error("---deleteRolePermissions -------- rolePermissionsId is null ==== ");
			return JSON.toJSONString(dto);
		}

			try {
				dto = baseDao.deleteByPrimaryKey(RolePermissionsMapper.class,rolePermissionsId);

			} catch (Exception e) {
				e.printStackTrace();
				log.error("deleteRolePermissions exception!");
				throw new RuntimeException("deleteRolePermissions Exception!");
			}
		
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getRolePermissionsList(RolePermissionsQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<RolePermissionsVo>> dto = new BaseObjDto<ItemPage<RolePermissionsVo>>();
		try {

			BaseObjDto<ItemPage<RolePermissionsVo>> pagesDto = baseDao.getPageList(
					RolePermissionsMapper.class, RolePermissionsVo.class, form,
					"getRolePermissionsPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getRolePermissionsList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getRolePermissionsList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getRolePermissionsList Exception !");
			throw new RuntimeException("getRolePermissionsList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllRolePermissionsList(RolePermissionsQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<RolePermissionsVo>> dto = new BaseObjDto<List<RolePermissionsVo>>();
		try {
			BaseObjDto<List<RolePermissionsVo>> dataDto = baseDao.getList(
					RolePermissionsMapper.class, RolePermissionsVo.class,
					"getAllRolePermissionsList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllRolePermissionsList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllRolePermissionsList Exception");
			throw new RuntimeException("getAllRolePermissionsList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

}
