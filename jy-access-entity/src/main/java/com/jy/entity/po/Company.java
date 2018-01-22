package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Company implements Serializable {
    private String company_Id;

    private String parentComanyId;

    private String companyName;
    
    private String comLinkman;

    private String telephone;

    private String email;

    private String address;

    private String city;

    private String latitude;

    private String longitude;

    private String companyBusinessType;

    private String creator;

    private Date createTime;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getCompany_Id() {
        return company_Id;
    }

    public void setCompany_Id(String company_Id) {
        this.company_Id = company_Id == null ? null : company_Id.trim();
    }

    public String getParentComanyId() {
        return parentComanyId;
    }

    public void setParentComanyId(String parentComanyId) {
        this.parentComanyId = parentComanyId == null ? null : parentComanyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    
    public String getComLinkman() {
		return comLinkman;
	}

	public void setComLinkman(String comLinkman) {
		this.comLinkman = comLinkman;
	}

	public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getCompanyBusinessType() {
        return companyBusinessType;
    }

    public void setCompanyBusinessType(String companyBusinessType) {
        this.companyBusinessType = companyBusinessType == null ? null : companyBusinessType.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", company_Id=").append(company_Id);
        sb.append(", parentComanyId=").append(parentComanyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", telephone=").append(telephone);
        sb.append(", email=").append(email);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", companyBusinessType=").append(companyBusinessType);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}