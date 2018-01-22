package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReceivableAccount implements Serializable {
    private String receableAccount_Id;

    private String staAccountId;

    private String fAccountNO;

    private BigDecimal receableAmount;

    private String receableAccountType;

    private static final long serialVersionUID = 1L;

    public String getReceableAccount_Id() {
        return receableAccount_Id;
    }

    public void setReceableAccount_Id(String receableAccount_Id) {
        this.receableAccount_Id = receableAccount_Id == null ? null : receableAccount_Id.trim();
    }

    public String getStaAccountId() {
        return staAccountId;
    }

    public void setStaAccountId(String staAccountId) {
        this.staAccountId = staAccountId == null ? null : staAccountId.trim();
    }

    public String getfAccountNO() {
        return fAccountNO;
    }

    public void setfAccountNO(String fAccountNO) {
        this.fAccountNO = fAccountNO == null ? null : fAccountNO.trim();
    }

    public BigDecimal getReceableAmount() {
        return receableAmount;
    }

    public void setReceableAmount(BigDecimal receableAmount) {
        this.receableAmount = receableAmount;
    }

    public String getReceableAccountType() {
        return receableAccountType;
    }

    public void setReceableAccountType(String receableAccountType) {
        this.receableAccountType = receableAccountType == null ? null : receableAccountType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", receableAccount_Id=").append(receableAccount_Id);
        sb.append(", staAccountId=").append(staAccountId);
        sb.append(", fAccountNO=").append(fAccountNO);
        sb.append(", receableAmount=").append(receableAmount);
        sb.append(", receableAccountType=").append(receableAccountType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}