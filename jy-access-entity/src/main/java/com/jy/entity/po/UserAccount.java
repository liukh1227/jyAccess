package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;

public class UserAccount implements Serializable {
    private String userAccount_Id;

    private String account;

    private String userName;

    private String password;

    private String passwordSalt;

    private String cellPhone;

    private String sysLevel;

    private String status;

    private String companyId;

    private String creator;

    private Date createTime;

    private String headPortrait;
    
    private String dataScope;
    
    @JSONField(serialize=false) 
    private String validCode;
    
    private static final long serialVersionUID = 1L;

    public String getUserAccount_Id() {
        return userAccount_Id;
    }

    public void setUserAccount_Id(String userAccount_Id) {
        this.userAccount_Id = userAccount_Id == null ? null : userAccount_Id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
    }

    
    public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone == null ? null : cellPhone.trim();;
	}

    public String getSysLevel() {
        return sysLevel;
    }

    public void setSysLevel(String sysLevel) {
        this.sysLevel = sysLevel == null ? null : sysLevel.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss" ) 
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait == null ? null : headPortrait.trim();
    }

    public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	
	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userAccount_Id=").append(userAccount_Id);
        sb.append(", account=").append(account);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", passwordSalt=").append(passwordSalt);
        sb.append(", cellPhone=").append(cellPhone);
        sb.append(", sysLevel=").append(sysLevel);
        sb.append(", status=").append(status);
        sb.append(", companyId=").append(companyId);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", headPortrait=").append(headPortrait);
        sb.append(", dataScope=").append(dataScope);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    @JSONField(serialize=false) 
    public boolean isSystemLevelUserAccount(){
    	return getSysLevel().equals(EntityConstant.USERACCOUNT_LEVEL_SYSTEM);
    }
    
    @JSONField(serialize=false) 
    public boolean isUserLevelUserAccount(){
    	return getSysLevel().equals(EntityConstant.USERACCOUNT_LEVEL_USER);
    }
    
    
    @JSONField(serialize=false) 
    public boolean isChengzuLevelUserAccount(){
    	return getSysLevel().equals(EntityConstant.USERACCOUNT_LEVEL_CHENGZU);
    }
    
    
    @JSONField(serialize=false) 
    public boolean isRightLeve(){
    	return getSysLevel().equals(EntityConstant.USERACCOUNT_LEVEL_SYSTEM)||getSysLevel().equals(EntityConstant.USERACCOUNT_LEVEL_USER)||getSysLevel().equals(EntityConstant.USERACCOUNT_LEVEL_CHENGZU);
    }
    
    @JSONField(serialize=false) 
    public boolean isRightDataScope(){
    	return getDataScope().equals(EntityConstant.USERACCOUNT_DATASCOPE_GLOBAL)|| getDataScope().equals(EntityConstant.USERACCOUNT_DATASCOPE_PART);
    }
    
    @JSONField(serialize=false) 
    public boolean isPartDataScope(){
    	return getDataScope().equals(EntityConstant.USERACCOUNT_DATASCOPE_PART);
    }
    
    @JSONField(serialize=false) 
    public boolean isGlobalDataScope(){
    	return getDataScope().equals(EntityConstant.USERACCOUNT_DATASCOPE_GLOBAL);
    }
}