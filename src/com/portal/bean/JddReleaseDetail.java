package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JddReleaseDetail implements Serializable {
    @Override
	public String toString() {
		return " [guid='" + guid + "', releaseId='" + releaseId + "', userId='" + userId
				+ "', userName='" + userName + "', jddCount=" + jddCount + ", reason='" + reason
				+ "', ruleChild='" + ruleChild + "', source='" + source + "', sourceComp='" + sourceComp
				+ "', userCom='" + userCom+ "', userDept='" + userDept+ "']";
	}

	private String guid;

    private String releaseId;

    private String userId;

    private String userName;

    private Integer jddCount;

    private String reason;

    private String ruleChild;

    private String source;

    //公司编码
    private String sourceComp;
    
    //公司简称
    private String userCom;
    
    //公司部门
    private String userDept;
    
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;
    
    private String childName;
    
    private String sourceName;

    private static final long serialVersionUID = 1L;

    
    public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

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

    public Integer getJddCount() {
        return jddCount;
    }

    public void setJddCount(Integer jddCount) {
        this.jddCount = jddCount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRuleChild() {
        return ruleChild;
    }

    public void setRuleChild(String ruleChild) {
        this.ruleChild = ruleChild == null ? null : ruleChild.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getSourceComp() {
        return sourceComp;
    }

    public void setSourceComp(String sourceComp) {
        this.sourceComp = sourceComp == null ? null : sourceComp.trim();
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
        JddReleaseDetail other = (JddReleaseDetail) that;
        return (this.getGuid() == null ? other.getGuid() == null : this.getGuid().equals(other.getGuid()))
            && (this.getReleaseId() == null ? other.getReleaseId() == null : this.getReleaseId().equals(other.getReleaseId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getJddCount() == null ? other.getJddCount() == null : this.getJddCount().equals(other.getJddCount()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getRuleChild() == null ? other.getRuleChild() == null : this.getRuleChild().equals(other.getRuleChild()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getSourceComp() == null ? other.getSourceComp() == null : this.getSourceComp().equals(other.getSourceComp()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGuid() == null) ? 0 : getGuid().hashCode());
        result = prime * result + ((getReleaseId() == null) ? 0 : getReleaseId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getJddCount() == null) ? 0 : getJddCount().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getRuleChild() == null) ? 0 : getRuleChild().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getSourceComp() == null) ? 0 : getSourceComp().hashCode());
        return result;
    }

	public String getUserCom() {
		return userCom;
	}

	public void setUserCom(String userCom) {
		this.userCom = userCom;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
}