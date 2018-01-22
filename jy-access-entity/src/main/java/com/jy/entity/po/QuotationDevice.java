package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class QuotationDevice implements Serializable {
    private String quodevice_Id;

    private String quotationId;

    private String deviceModelId;

    private Integer quantity;

    private BigDecimal rentPerDay;

    private BigDecimal rentPerWeek;

    private BigDecimal rentPerMonth;

    private static final long serialVersionUID = 1L;

    public String getQuodevice_Id() {
        return quodevice_Id;
    }

    public void setQuodevice_Id(String quodevice_Id) {
        this.quodevice_Id = quodevice_Id == null ? null : quodevice_Id.trim();
    }
    
    public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		  this.quotationId = quotationId == null ? null : quotationId.trim();
	}

	public String getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(String deviceModelId) {
        this.deviceModelId = deviceModelId == null ? null : deviceModelId.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", quodevice_Id=").append(quodevice_Id);
        sb.append(", quotationId=").append(quotationId);
        sb.append(", deviceModelId=").append(deviceModelId);
        sb.append(", quantity=").append(quantity);
        sb.append(", rentPerDay=").append(rentPerDay);
        sb.append(", rentPerWeek=").append(rentPerWeek);
        sb.append(", rentPerMonth=").append(rentPerMonth);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}