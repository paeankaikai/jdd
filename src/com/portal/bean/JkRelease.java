package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class JkRelease implements Serializable {
    private String guid;

    private Integer ststus;

    private Date createTime;

    private String createBy;

    private String createName;

    private String checkBy;

    private String checkName;

    private Date checkTime;

    private String excelPath;
    
    private String userCom;

    private static final long serialVersionUID = 1L;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public Integer getStstus() {
        return ststus;
    }

    public void setStstus(Integer ststus) {
        this.ststus = ststus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy == null ? null : checkBy.trim();
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName == null ? null : checkName.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath == null ? null : excelPath.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        JkRelease other = (JkRelease) that;
        return (this.getGuid() == null ? other.getGuid() == null : this.getGuid().equals(other.getGuid()))
            && (this.getStstus() == null ? other.getStstus() == null : this.getStstus().equals(other.getStstus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateName() == null ? other.getCreateName() == null : this.getCreateName().equals(other.getCreateName()))
            && (this.getCheckBy() == null ? other.getCheckBy() == null : this.getCheckBy().equals(other.getCheckBy()))
            && (this.getCheckName() == null ? other.getCheckName() == null : this.getCheckName().equals(other.getCheckName()))
            && (this.getCheckTime() == null ? other.getCheckTime() == null : this.getCheckTime().equals(other.getCheckTime()))
            && (this.getExcelPath() == null ? other.getExcelPath() == null : this.getExcelPath().equals(other.getExcelPath()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGuid() == null) ? 0 : getGuid().hashCode());
        result = prime * result + ((getStstus() == null) ? 0 : getStstus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateName() == null) ? 0 : getCreateName().hashCode());
        result = prime * result + ((getCheckBy() == null) ? 0 : getCheckBy().hashCode());
        result = prime * result + ((getCheckName() == null) ? 0 : getCheckName().hashCode());
        result = prime * result + ((getCheckTime() == null) ? 0 : getCheckTime().hashCode());
        result = prime * result + ((getExcelPath() == null) ? 0 : getExcelPath().hashCode());
        return result;
    }

	public String getUserCom() {
		return userCom;
	}

	public void setUserCom(String userCom) {
		this.userCom = userCom;
	}
}