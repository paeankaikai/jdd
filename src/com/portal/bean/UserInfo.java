package com.portal.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private String guid;

	private String userId;

	private String userName;

	private Integer status;

	private Integer jkSum;

	private Integer usedJkSum;

	private String accountGuid;

	private Integer lyNum;

	private Integer lyCostNum;

	private Integer cyNum;

	private Integer cyCostNum;

	private String userCom;

	private String userPosition;

	private String userUnit;
	
	private String userDept;

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getUserUnit() {
		return userUnit;
	}

	public void setUserUnit(String userUnit) {
		this.userUnit = userUnit;
	}

	private static final long serialVersionUID = 1L;

	public String getUserCom() {
		return userCom;
	}

	public void setUserCom(String userCom) {
		this.userCom = userCom;
	}

	public String getAccountGuid() {
		return accountGuid;
	}

	public void setAccountGuid(String accountGuid) {
		this.accountGuid = accountGuid;
	}

	public Integer getLyNum() {
		return lyNum;
	}

	public void setLyNum(Integer lyNum) {
		this.lyNum = lyNum;
	}

	public Integer getLyCostNum() {
		return lyCostNum;
	}

	public void setLyCostNum(Integer lyCostNum) {
		this.lyCostNum = lyCostNum;
	}

	public Integer getCyNum() {
		return cyNum;
	}

	public void setCyNum(Integer cyNum) {
		this.cyNum = cyNum;
	}

	public Integer getCyCostNum() {
		return cyCostNum;
	}

	public void setCyCostNum(Integer cyCostNum) {
		this.cyCostNum = cyCostNum;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid == null ? null : guid.trim();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getJkSum() {
		return jkSum;
	}

	public void setJkSum(Integer jkSum) {
		this.jkSum = jkSum;
	}

	public Integer getUsedJkSum() {
		return usedJkSum;
	}

	public void setUsedJkSum(Integer usedJkSum) {
		this.usedJkSum = usedJkSum;
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
		UserInfo other = (UserInfo) that;
		return (this.getGuid() == null ? other.getGuid() == null : this.getGuid().equals(other.getGuid()))
				&& (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
				&& (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(
						other.getUserName()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
				&& (this.getJkSum() == null ? other.getJkSum() == null : this.getJkSum().equals(other.getJkSum()))
				&& (this.getUsedJkSum() == null ? other.getUsedJkSum() == null : this.getUsedJkSum().equals(
						other.getUsedJkSum()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getGuid() == null) ? 0 : getGuid().hashCode());
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
		result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getJkSum() == null) ? 0 : getJkSum().hashCode());
		result = prime * result + ((getUsedJkSum() == null) ? 0 : getUsedJkSum().hashCode());
		return result;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
}