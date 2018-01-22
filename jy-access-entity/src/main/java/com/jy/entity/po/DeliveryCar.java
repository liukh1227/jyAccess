package com.jy.entity.po;

import java.io.Serializable;

public class DeliveryCar implements Serializable {
    private String deliveryCar_Id;

    private String deliveryOrderId;

    private String plateNumber;

    private String driver;

    private String deriverPhone;

    private String doorkeeper;

    private static final long serialVersionUID = 1L;

    public String getDeliveryCar_Id() {
        return deliveryCar_Id;
    }

    public void setDeliveryCar_Id(String deliveryCar_Id) {
        this.deliveryCar_Id = deliveryCar_Id == null ? null : deliveryCar_Id.trim();
    }

    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId == null ? null : deliveryOrderId.trim();
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
    }

    public String getDeriverPhone() {
        return deriverPhone;
    }

    public void setDeriverPhone(String deriverPhone) {
        this.deriverPhone = deriverPhone == null ? null : deriverPhone.trim();
    }

    public String getDoorkeeper() {
        return doorkeeper;
    }

    public void setDoorkeeper(String doorkeeper) {
        this.doorkeeper = doorkeeper == null ? null : doorkeeper.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deliveryCar_Id=").append(deliveryCar_Id);
        sb.append(", deliveryOrderId=").append(deliveryOrderId);
        sb.append(", plateNumber=").append(plateNumber);
        sb.append(", driver=").append(driver);
        sb.append(", deriverPhone=").append(deriverPhone);
        sb.append(", doorkeeper=").append(doorkeeper);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}