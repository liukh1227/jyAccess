package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.RolePermissionsQueryForm;
import com.jy.entity.vo.RolePermissionsVo;

public interface RolePermissionsMapper extends BaseMapper{
	public List<RolePermissionsVo>getRolePermissionsPageList(RolePermissionsQueryForm form);
	public List<RolePermissionsVo>getAllRolePermissionsList(RolePermissionsQueryForm form);

}