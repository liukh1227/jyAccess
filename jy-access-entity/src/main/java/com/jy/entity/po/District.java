package com.jy.entity.po;

import java.io.Serializable;

public class District implements Serializable {
    private Integer district_Id;

    private Integer pId;

    private String district;

    private Integer level;

    private static final long serialVersionUID = 1L;

    public Integer getDistrict_Id() {
        return district_Id;
    }

    public void setDistrict_Id(Integer district_Id) {
        this.district_Id = district_Id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", district_Id=").append(district_Id);
        sb.append(", pId=").append(pId);
        sb.append(", district=").append(district);
        sb.append(", level=").append(level);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}