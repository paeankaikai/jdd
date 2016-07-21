package com.portal.bean;

public class UserInfoQuery {
    private String guid;

    private String userId;

    private String userName;

    private Integer status;

    private Integer yearClearedFlag;
    
    private Integer leaveClearedFlag;
    
    private Integer celebrateSendFlag;

	public Integer getCelebrateSendFlag() {
		return celebrateSendFlag;
	}

	public void setCelebrateSendFlag(Integer celebrateSendFlag) {
		this.celebrateSendFlag = celebrateSendFlag;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getYearClearedFlag() {
		return yearClearedFlag;
	}

	public void setYearClearedFlag(Integer yearClearedFlag) {
		this.yearClearedFlag = yearClearedFlag;
	}

	public Integer getLeaveClearedFlag() {
		return leaveClearedFlag;
	}

	public void setLeaveClearedFlag(Integer leaveClearedFlag) {
		this.leaveClearedFlag = leaveClearedFlag;
	}
    
	

}