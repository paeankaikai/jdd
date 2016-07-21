package com.portal.service;

import com.github.pagehelper.PageInfo;
import com.portal.bean.JkRelease;
import com.portal.common.Page;

public interface JkReleaseService {
	
	public PageInfo<JkRelease> selectJkReleaseByExample(Page page,String startDate,String endDate,String createName,Integer ststus,String userGuid);
	
	
	public int insert(JkRelease release);
	
	public JkRelease selectByPrimaryKey(String guid);
	
	
	public int updateStatus(JkRelease release);
	

}
