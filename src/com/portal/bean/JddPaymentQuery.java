package com.portal.bean;

import java.util.Date;

public class JddPaymentQuery {
    private String guid;

    private String userId;

    private String userName;

    private Date createTime;
    
    private String linkSystemId;
    
    private String ruleChild;
    
    private Date startDate;
    
	private Date endDate;
	
	//员工公司编码
	private String userUnit;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLinkSystemId() {
		return linkSystemId;
	}

	public void setLinkSystemId(String linkSystemId) {
		this.linkSystemId = linkSystemId;
	}

	public String getRuleChild() {
		return ruleChild;
	}

	public void setRuleChild(String ruleChild) {
		this.ruleChild = ruleChild;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserUnit() {
		return userUnit;
	}

	public void setUserUnit(String userUnit) {
		this.userUnit = userUnit;
	}
    

}