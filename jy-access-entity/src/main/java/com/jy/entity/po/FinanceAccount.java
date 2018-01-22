package com.jy.entity.po;

import java.io.Serializable;

public class FinanceAccount implements Serializable {
    private String fAccountNO;

    private String fAccountName;

    private String fAccountType;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getfAccountNO() {
        return fAccountNO;
    }

    public void setfAccountNO(String fAccountNO) {
        this.fAccountNO = fAccountNO == null ? null : fAccountNO.trim();
    }

    public String getfAccountName() {
        return fAccountName;
    }

    public void setfAccountName(String fAccountName) {
        this.fAccountName = fAccountName == null ? null : fAccountName.trim();
    }

    public String getfAccountType() {
        return fAccountType;
    }

    public void setfAccountType(String fAccountType) {
        this.fAccountType = fAccountType == null ? null : fAccountType.trim();
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
        sb.append(", fAccountNO=").append(fAccountNO);
        sb.append(", fAccountName=").append(fAccountName);
        sb.append(", fAccountType=").append(fAccountType);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}