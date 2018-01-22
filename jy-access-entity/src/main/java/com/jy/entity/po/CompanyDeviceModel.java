package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class CompanyDeviceModel implements Serializable {
    private String companyDeviveModel_Id;

    private String deviceModelId;

    private String companyId;

    private String cdmPicture;

    private BigDecimal rentPerDay;

    private BigDecimal rentPerWeek;

    private BigDecimal rentPerMonth;

    private String creator;

    private Integer orderSeq;

    private Date createTime;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getCompanyDeviveModel_Id() {
        return companyDeviveModel_Id;
    }

    public void setCompanyDeviveModel_Id(String companyDeviveModel_Id) {
        this.companyDeviveModel_Id = companyDeviveModel_Id == null ? null : companyDeviveModel_Id.trim();
    }

    public String getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(String deviceModelId) {
        this.deviceModelId = deviceModelId == null ? null : deviceModelId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCdmPicture() {
        return cdmPicture;
    }

    public void setCdmPicture(String cdmPicture) {
        this.cdmPicture = cdmPicture == null ? null : cdmPicture.trim();
    }

    public BigDecimal getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(BigDecimal rentPerDay) {
        this.rentPerDay = rentPerDay;
    }

    public BigDecimal getRentPerWeek() {
        return rentPerWeek;
    }

    public void setRentPerWeek(BigDecimal rentPerWeek) {
        this.rentPerWeek = rentPerWeek;
    }

    public BigDecimal getRentPerMonth() {
        return rentPerMonth;
    }

    public void setRentPerMonth(BigDecimal rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
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
        sb.append(", companyDeviveModel_Id=").append(companyDeviveModel_Id);
        sb.append(", deviceModelId=").append(deviceModelId);
        sb.append(", companyId=").append(companyId);
        sb.append(", cdmPicture=").append(cdmPicture);
        sb.append(", rentPerDay=").append(rentPerDay);
        sb.append(", rentPerWeek=").append(rentPerWeek);
        sb.append(", rentPerMonth=").append(rentPerMonth);
        sb.append(", creator=").append(creator);
        sb.append(", orderSeq=").append(orderSeq);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}