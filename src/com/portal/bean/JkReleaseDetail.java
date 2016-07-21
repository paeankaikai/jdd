package com.portal.bean;

import java.io.Serializable;

public class JkReleaseDetail implements Serializable {
    @Override
	public String toString() {
		return "[guid='" + guid + "', releaseId='" + releaseId + "', userId='" + userId
				+ "', userName='" + userName 
				+ "', jkCount=" + jkCount 
				+ ", userCom='" + userCom 
				+ "']";
	}

	private String guid;

    private String releaseId;

    private String userId;

    private String userName;

    private Integer jkCount;
    
    
    private String userCom;

    private static final long serialVersionUID = 1L;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId == null ? null : releaseId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getJkCount() {
        return jkCount;
    }

    public void setJkCount(Integer jkCount) {
        this.jkCount = jkCount;
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
        JkReleaseDetail other = (JkReleaseDetail) that;
        return (this.getGuid() == null ? other.getGuid() == null : this.getGuid().equals(other.getGuid()))
            && (this.getReleaseId() == null ? other.getReleaseId() == null : this.getReleaseId().equals(other.getReleaseId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getJkCount() == null ? other.getJkCount() == null : this.getJkCount().equals(other.getJkCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGuid() == null) ? 0 : getGuid().hashCode());
        result = prime * result + ((getReleaseId() == null) ? 0 : getReleaseId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getJkCount() == null) ? 0 : getJkCount().hashCode());
        return result;
    }

	public String getUserCom() {
		return userCom;
	}

	public void setUserCom(String userCom) {
		this.userCom = userCom;
	}
}