package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;

public class Device implements Serializable {
    private String device_Id;

    private String companyId;

    private String deviceModelId;

    private String deviceNum;

    private String sequenceNum;

    private String plateNumber;

    private String manufactureYear;

    private Boolean isImported;

    private String workingTime;

    private String devicePicture;

    private Boolean isRealDevice;

    private String gPSCode;

    private String status;

    private String creator;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getDevice_Id() {
        return device_Id;
    }

    public void setDevice_Id(String device_Id) {
        this.device_Id = device_Id == null ? null : device_Id.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(String deviceModelId) {
        this.deviceModelId = deviceModelId == null ? null : deviceModelId.trim();
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public String getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(String sequenceNum) {
        this.sequenceNum = sequenceNum == null ? null : sequenceNum.trim();
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear == null ? null : manufactureYear.trim();
    }

    public Boolean getIsImported() {
        return isImported;
    }

    public void setIsImported(Boolean isImported) {
        this.isImported = isImported;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime == null ? null : workingTime.trim();
    }

    public String getDevicePicture() {
        return devicePicture;
    }

    public void setDevicePicture(String devicePicture) {
        this.devicePicture = devicePicture == null ? null : devicePicture.trim();
    }

    public Boolean getIsRealDevice() {
        return isRealDevice;
    }

    public void setIsRealDevice(Boolean isRealDevice) {
        this.isRealDevice = isRealDevice;
    }

    public String getgPSCode() {
        return gPSCode;
    }

    public void setgPSCode(String gPSCode) {
        this.gPSCode = gPSCode == null ? null : gPSCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", device_Id=").append(device_Id);
        sb.append(", companyId=").append(companyId);
        sb.append(", deviceModelId=").append(deviceModelId);
        sb.append(", deviceNum=").append(deviceNum);
        sb.append(", sequenceNum=").append(sequenceNum);
        sb.append(", plateNumber=").append(plateNumber);
        sb.append(", manufactureYear=").append(manufactureYear);
        sb.append(", isImported=").append(isImported);
        sb.append(", workingTime=").append(workingTime);
        sb.append(", devicePicture=").append(devicePicture);
        sb.append(", isRealDevice=").append(isRealDevice);
        sb.append(", gPSCode=").append(gPSCode);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
	@JSONField(serialize = false)
    public boolean isNotDiscard(){
    	return !(getStatus().equals(EntityConstant.DEVICE_STATUS_DISCARD));
    }
    
}