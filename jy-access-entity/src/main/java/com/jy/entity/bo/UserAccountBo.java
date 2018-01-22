package com.jy.entity.bo;



import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.entity.po.Company;
import com.jy.entity.po.Permission;
import com.jy.entity.po.UserAccount;
import com.jy.entity.vo.UserAccountVo;


public class UserAccountBo extends UserAccountVo {
	
	private static final long serialVersionUID = 1L;

	private List<Company> vistCompanyList;
	
	private String[] vistCompanyArray;
	
	private List<Permission> vistPermissionList;
	
	private String[] vistPermissionArray;

	public List<Company> getVistCompanyList() {
		return vistCompanyList;
	}

	public void setVistCompanyList(List<Company> vistCompanyList) {
		this.vistCompanyList = vistCompanyList;
	}

	public List<Permission> getVistPermissionList() {
		return vistPermissionList;
	}

	public void setVistPermissionList(List<Permission> vistPermissionList) {
		this.vistPermissionList = vistPermissionList;
	}
	
	
	 public String[] getVistCompanyArray() {
		return vistCompanyArray;
	}

	public void setVistCompanyArray(String[] vistCompanyArray) {
		this.vistCompanyArray = vistCompanyArray;
	}

	public String[] getVistPermissionArray() {
		return vistPermissionArray;
	}

	public void setVistPermissionArray(String[] vistPermissionArray) {
		this.vistPermissionArray = vistPermissionArray;
	}

	@JSONField(serialize=false) 
	public  void copyFrom(UserAccount other){
		 setUserAccount_Id(other.getUserAccount_Id());
		 setAccount(other.getAccount());
		 setUserName(other.getUserName());
		 setCellPhone(other.getCellPhone());
		 setSysLevel(other.getSysLevel());
		 setStatus(other.getStatus());
		 setCompanyId(other.getCompanyId());
		 setCreator(other.getCreator());
		 setCreateTime(other.getCreateTime());
		 setHeadPortrait(other.getHeadPortrait());
		 setDataScope(other.getDataScope());
		
	}
	
	

}
