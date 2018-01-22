package com.jy.entity.po;

import java.io.Serializable;

public class DeliveryDevice implements Serializable {
    private String deliveryDevice_Id;

    private String deliveryOrderId;

    private String deviceId;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getDeliveryDevice_Id() {
        return deliveryDevice_Id;
    }

    public void setDeliveryDevice_Id(String deliveryDevice_Id) {
        this.deliveryDevice_Id = deliveryDevice_Id == null ? null : deliveryDevice_Id.trim();
    }

    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId == null ? null : deliveryOrderId.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deliveryDevice_Id=").append(deliveryDevice_Id);
        sb.append(", deliveryOrderId=").append(deliveryOrderId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}