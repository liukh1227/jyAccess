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
import com.jy.access.mapper.PermissionMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.PermissionQueryForm;
import com.jy.entity.po.Permission;
import com.jy.entity.pojo.SuccessReturnPojo;

@Service("permissionService")
@Scope("prototype")
public class PermissionServiceImpl implements PermissionService {
	private static final Logger log = Logger.getLogger(PermissionServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;

	@Override
	public String addPermission(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				Permission permission = JSON.parseObject(data,Permission.class);
				if (permission == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (permission.getPermissionName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("权限名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (permission.getResourceUrl() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("权限访问的资源Url不能为空 !");
					return JSON.toJSONString(dto);
				}
			
				dto = baseDao.insertSelective(PermissionMapper.class,permission);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addPermission success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(String.valueOf(permission.getPermission_Id()));
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addPermission failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addPermission exception!");
				throw new RuntimeException("addPermission Exception!");
			}
		} else {
			log.error("---addPermission -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deletePermission(String permissionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatePermission(String permissionId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(permissionId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数permissionId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(permissionId)
				&& StringUtils.isNotBlank(data)) {
			try {
				Permission permission = JSON.parseObject(data,
						Permission.class);
				if (permission == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				permission.setPermission_Id(Integer.valueOf(permissionId));
				
				dto = baseDao.updateByPrimaryKeySelective(PermissionMapper.class, permission);
				
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updatePermission success!");
				} else {
					log.error("updatePermission failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updatePermission exception!");
				throw new RuntimeException("updatePermission Exception!");
			}

		} else {
			log.error("---updatePermission -------- data or permissionId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getPermission(String permissionId) {
		String jsonStr = "";
		BaseObjDto<Permission> dto = new BaseObjDto<Permission>();
		try {
			if (StringUtils.isBlank(permissionId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数permissionId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<Permission> permissionDto = baseDao
					.selectByPrimaryKey(PermissionMapper.class,
							StringUtils.trim(permissionId));
			if (FrameworkUtils.isSuccess(permissionDto)) {
				Permission permission = permissionDto.getData();
				dto.setData(permission);
				FrameworkUtils.setSuccess(dto);
				log.info("getPermission success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getPermission failure");
			}
		} catch (Exception e) {
			log.error("getPermission exception!");
			e.printStackTrace();
			throw new RuntimeException("getPermission Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getPermissionList(PermissionQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<Permission>> dto = new BaseObjDto<ItemPage<Permission>>();
		try {

			BaseObjDto<ItemPage<Permission>> pagesDto = baseDao.getPageList(
					PermissionMapper.class, Permission.class, form,
					"getPermissionPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getPermissionList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getPermissionList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getPermissionList Exception !");
			throw new RuntimeException("getPermissionList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllPermissionList(PermissionQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<Permission>> dto = new BaseObjDto<List<Permission>>();
		try {
			BaseObjDto<List<Permission>> dataDto = baseDao.getList(
					PermissionMapper.class, Permission.class,
					"getAllPermissionList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllPermissionList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllPermissionList Exception");
			throw new RuntimeException("getAllPermissionList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllPermissionListByUserAccountId(PermissionQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<Permission>> dto = new BaseObjDto<List<Permission>>();
		try {
			if(form == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数不能为空!");
				return JSON.toJSONString(dto);
			}
			if(form.getUserAccountId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数(userAccountId)不能为空!");
				return JSON.toJSONString(dto);
			}
			
			BaseObjDto<List<Permission>> dataDto = baseDao.getList(
					PermissionMapper.class, Permission.class,
					"getAllPermissionListByUserAccountId", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllPermissionList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllPermissionList Exception");
			throw new RuntimeException("getAllPermissionList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

}
