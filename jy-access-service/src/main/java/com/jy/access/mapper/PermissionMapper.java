package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.PermissionQueryForm;
import com.jy.entity.po.Permission;

public interface PermissionMapper extends BaseMapper{
	public List<Permission>getPermissionPageList(PermissionQueryForm form);
	public List<Permission>getAllPermissionList(PermissionQueryForm form);
	public List<Permission>getAllPermissionListByUserAccountId(PermissionQueryForm form);
}