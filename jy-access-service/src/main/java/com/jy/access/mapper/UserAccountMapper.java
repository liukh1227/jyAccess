package com.jy.access.mapper;

import java.util.List;
import java.util.Map;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.bo.UserAccountBo;
import com.jy.entity.form.UserAccountQueryForm;
import com.jy.entity.po.UserAccount;
import com.jy.entity.vo.UserAccountVo;


public interface UserAccountMapper extends BaseMapper{
	 
    
    public UserAccount getUserByAccount(Map<String,Object> params);
    
    public UserAccountBo getUserAccountDetailInfor(Map<String,Object> params);
    
    public UserAccount getUserAccountByIdOrAccount(Map<String,Object> params);
    
    public UserAccount isValidUserAccount(Map<String,Object> params);
    
    public List<UserAccountVo>getUserAccountPageList(UserAccountQueryForm form);
    
	public List<UserAccountVo>getAllUserAccountList(UserAccountQueryForm form);
	
	public List<UserAccount>getAllNoConfigRoleUserAccountList(UserAccountQueryForm form);
}