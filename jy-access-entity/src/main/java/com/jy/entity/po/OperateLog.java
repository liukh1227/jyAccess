package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class OperateLog implements Serializable {
    private String operateLog_Id;

    private String creator;

    private Date logDate;

    private String logType;

    private String objectName;

    private String objectId;

    private static final long serialVersionUID = 1L;

    public String getOperateLog_Id() {
        return operateLog_Id;
    }

    public void setOperateLog_Id(String operateLog_Id) {
        this.operateLog_Id = operateLog_Id == null ? null : operateLog_Id.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss" ) 
    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName == null ? null : objectName.trim();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operateLog_Id=").append(operateLog_Id);
        sb.append(", creator=").append(creator);
        sb.append(", logDate=").append(logDate);
        sb.append(", logType=").append(logType);
        sb.append(", objectName=").append(objectName);
        sb.append(", objectId=").append(objectId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}