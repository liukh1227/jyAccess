package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.UserRolesQueryForm;
import com.jy.entity.vo.UserRolesVo;

public interface UserRolesMapper extends BaseMapper{
	public List<UserRolesVo>getUserRolesPageList(UserRolesQueryForm form);
	public List<UserRolesVo>getAllUserRolesList(UserRolesQueryForm form);
	public List<UserRolesVo>getUserRolesConfigPageList(UserRolesQueryForm form);
	public List<UserRolesVo>getAllUserRolesConfigList(UserRolesQueryForm form);
}