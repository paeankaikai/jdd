package com.portal.bean;

import java.io.Serializable;

public class UserRights implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	
	
	/*角色名	角色
	G_EMPLOYEE	普通员工                                       ====1
	G_JDD_SUB_ADMIN	普通管理员                       ====2
	G_JDD_SUPER_ADMIN	超级管理员           ====3
	G_JDD_SH_ADMIN	审核管理员                       ====4     
*/

}
