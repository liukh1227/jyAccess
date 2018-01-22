package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.RoleQueryForm;
import com.jy.entity.po.Role;
import com.jy.entity.vo.RoleVo;

public interface RoleMapper extends BaseMapper{
	public List<Role>getRolePageList(RoleQueryForm form);
	public List<Role>getAllRoleList(RoleQueryForm form);
	public List<RoleVo>getRoleConfigPageList(RoleQueryForm form);
	public List<RoleVo>getAllRoleConfigList(RoleQueryForm form);
}