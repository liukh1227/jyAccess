package com.jy.entity.po;

import java.io.Serializable;

public class Datamapping extends DatamappingKey implements Serializable {
    private String valueString;

    private Boolean enabled;

    private Integer orderSeq;

    private static final long serialVersionUID = 1L;

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString == null ? null : valueString.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", valueString=").append(valueString);
        sb.append(", enabled=").append(enabled);
        sb.append(", orderSeq=").append(orderSeq);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}