package com.portal.service;

import java.util.List;

import com.portal.bean.JkReleaseDetail;

public interface JkReleaseDetailService {
	
	
	public int insert(JkReleaseDetail detail);
	
	
	public List<JkReleaseDetail> selectByReleaseGuid(String releaseGuid);

}
