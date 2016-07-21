package com.portal.service;

import com.github.pagehelper.PageInfo;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddRelease;
import com.portal.common.Page;

public interface JddReleaseService {
	
	public int insert(JddRelease jddRelease);
	
	public int countJddRelease();

	public PageInfo<JddRelease> selectJddReleaseByExample(Page page,String startDate,String endDate,String content,String userName,Integer ststus,String userId);
	
	
	public JddRelease selectByPrimaryKey(String guid);
	
	
	public int updateByPrimaryKeySelective(JddRelease release);
	

}
