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
import com.jy.access.mapper.RoleMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.RoleQueryForm;
import com.jy.entity.po.Role;
import com.jy.entity.pojo.SuccessReturnPojo;

@Service("roleService")
@Scope("prototype")
public class RoleServiceImpl implements RoleService {
	private static final Logger log = Logger.getLogger(RoleServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;

	@Override
	public String addRole(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				Role role = JSON.parseObject(data,Role.class);
				if (role == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (role.getRoleName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("角色名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				dto = baseDao.insertSelective(RoleMapper.class,role);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addRole success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(String.valueOf(role.getRole_Id()));
					successPojo.setCreateTime(new Date());
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addRole failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addRole exception!");
				throw new RuntimeException("addRole Exception!");
			}
		} else {
			log.error("---addRole -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteRole(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateRole(String roleId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(roleId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数roleId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(roleId)
				&& StringUtils.isNotBlank(data)) {
			try {
				Role role = JSON.parseObject(data,
						Role.class);
				if (role == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				role.setRole_Id(Integer.valueOf(roleId));
				dto = baseDao.updateByPrimaryKeySelective(RoleMapper.class, role);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateRole success!");
				} else {
					log.error("updateRole failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateRole exception!");
				throw new RuntimeException("updateRole Exception!");
			}

		} else {
			log.error("---updateRole -------- data or roleId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getRole(String roleId) {
		String jsonStr = "";
		BaseObjDto<Role> dto = new BaseObjDto<Role>();
		try {
			if (StringUtils.isBlank(roleId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数roleId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<Role> roleDto = baseDao
					.selectByPrimaryKey(RoleMapper.class,
							StringUtils.trim(roleId));
			if (FrameworkUtils.isSuccess(roleDto)) {
				Role role = roleDto.getData();
				dto.setData(role);
				FrameworkUtils.setSuccess(dto);
				log.info("getRole success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getRole failure");
			}
		} catch (Exception e) {
			log.error("getRole exception!");
			e.printStackTrace();
			throw new RuntimeException("getRole Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getRoleList(RoleQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<Role>> dto = new BaseObjDto<ItemPage<Role>>();
		try {

			BaseObjDto<ItemPage<Role>> pagesDto = baseDao.getPageList(
					RoleMapper.class, Role.class, form,
					"getRolePageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getRoleList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getRoleList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getRoleList Exception !");
			throw new RuntimeException("getRoleList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllRoleList(RoleQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<Role>> dto = new BaseObjDto<List<Role>>();
		try {
			BaseObjDto<List<Role>> dataDto = baseDao.getList(
					RoleMapper.class, Role.class,
					"getAllRoleList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllRoleList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllRoleList Exception");
			throw new RuntimeException("getAllRoleList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getRoleConfigList(RoleQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<Role>> dto = new BaseObjDto<ItemPage<Role>>();
		try {
			if(form == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数不能为空 !");
				return JSON.toJSONString(dto);
			}else if(form.getCompanyId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}

			BaseObjDto<ItemPage<Role>> pagesDto = baseDao.getPageList(
					RoleMapper.class, Role.class, form,
					"getRoleConfigPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getRoleConfigPageList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getRoleConfigPageList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getRoleConfigPageList Exception !");
			throw new RuntimeException("getRoleConfigPageList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllRoleConfigList(RoleQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<Role>> dto = new BaseObjDto<List<Role>>();
		try {
			if(form == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数不能为空 !");
				return JSON.toJSONString(dto);
			}else if(form.getCompanyId() == null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数公司Id(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<List<Role>> dataDto = baseDao.getList(
					RoleMapper.class, Role.class,
					"getAllRoleConfigList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllRoleConfigList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllRoleConfigList Exception");
			throw new RuntimeException("getAllRoleConfigList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	

}
