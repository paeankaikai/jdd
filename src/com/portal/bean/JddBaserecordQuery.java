package com.portal.bean;

import java.util.Date;

public class JddBaserecordQuery {
    private String guid;

    private Integer ruleType;

    private String ruleChild;
    
    private String ruleFather;
    
    private String ruleGrad;
    
    private String createBy;
    
    private Date createTime;
    
    private String source;
    
    private Date startDate;
    
	private Date endDate;
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getRuleType() {
		return ruleType;
	}

	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}

	public String getRuleChild() {
		return ruleChild;
	}

	public void setRuleChild(String ruleChild) {
		this.ruleChild = ruleChild;
	}

	public String getRuleFather() {
		return ruleFather;
	}

	public void setRuleFather(String ruleFather) {
		this.ruleFather = ruleFather;
	}

	public String getRuleGrad() {
		return ruleGrad;
	}

	public void setRuleGrad(String ruleGrad) {
		this.ruleGrad = ruleGrad;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	

}