package com.portal.bean;

import java.util.Date;

public class JddReleaseDetailQuery {
	private String guid;

    private String userId;

    private String userName;

    private String ruleGrad;
    
    private String ruleFather;
    
    private String ruleChild;

    private String source;
    
    private Date startDate;
    
	private Date endDate;
	
	//员工公司编码
	private String userUnit;
	
	private int ststus;
	
	public String getRuleGrad() {
		return ruleGrad;
	}

	public void setRuleGrad(String ruleGrad) {
		this.ruleGrad = ruleGrad;
	}

	public String getRuleFather() {
		return ruleFather;
	}

	public void setRuleFather(String ruleFather) {
		this.ruleFather = ruleFather;
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

	public String getRuleChild() {
		return ruleChild;
	}

	public void setRuleChild(String ruleChild) {
		this.ruleChild = ruleChild;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public int getStstus() {
		return ststus;
	}

	public void setStstus(int ststus) {
		this.ststus = ststus;
	}



}