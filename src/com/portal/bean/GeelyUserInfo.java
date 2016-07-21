package com.portal.bean;

import java.io.Serializable;
//import com.portal.dto.welfare.EmplAreaDto;


public class GeelyUserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//用户照片路径
	private String photoPath;
	//用户
	private String userId;
	//用户姓名
	private String name;
	//出生日期
	private String birthdate;
	//性别
	private String sex;
	//公司名称
	private String companyName; 
	
	//公司名称
	private String companyCode; 
	
	//公司简称
	private String companyNameDesc; 
	//电话
	private String phone;
	//邮箱
	private String email;

	//所在公司区域ID
	private String areaId;
	
	//所在公司区域名
	private String areaName;
	
	//外派公司区域ID
	private String outAreaId;
	
	//外派公司区域名
	private String outAreaName;
	
	//用户权限信息
	private UserRights userRights;
	
	
	private String password;
	
	private String logType;
	// 登录返回信息
	private String msg;
	// 登录状态
    private Integer loginStatus;
    
    //入司时间 
    private String joinDate;
	
    //离职时间 
    private String leaveDate;
    
    //岗位  DESCR=20-专业人员
    private String position;
    
    
    //部门
    private String userDept;
    
    
    
    

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNameDesc() {
		return companyNameDesc;
	}

	public void setCompanyNameDesc(String companyNameDesc) {
		this.companyNameDesc = companyNameDesc;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getOutAreaId() {
		return outAreaId;
	}

	public void setOutAreaId(String outAreaId) {
		this.outAreaId = outAreaId;
	}

	public String getOutAreaName() {
		return outAreaName;
	}

	public void setOutAreaName(String outAreaName) {
		this.outAreaName = outAreaName;
	}

	public UserRights getUserRights() {
		return userRights;
	}

	public void setUserRights(UserRights userRights) {
		this.userRights = userRights;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	
	
	
	
	
	
}
