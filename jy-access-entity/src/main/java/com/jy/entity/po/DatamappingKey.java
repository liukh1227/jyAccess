package com.jy.entity.po;

import java.io.Serializable;

public class DatamappingKey implements Serializable {
    private String catalogs;

    private String keyString;

    private static final long serialVersionUID = 1L;

    public String getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(String catalogs) {
        this.catalogs = catalogs == null ? null : catalogs.trim();
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString == null ? null : keyString.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catalogs=").append(catalogs);
        sb.append(", keyString=").append(keyString);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}