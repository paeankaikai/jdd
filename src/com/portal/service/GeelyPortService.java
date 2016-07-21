package com.portal.service;

import java.util.List;

import com.portal.bean.GeelyUserInfo;
import com.portal.bean.UserRights;

public interface GeelyPortService {
	 
	GeelyUserInfo doLogin(GeelyUserInfo logUser);
	/*
	List<AreaInfoDto> getAllAreas();
*/
//	String getImgUrl(String userId, String imgurl);

	GeelyUserInfo getUserInfo(String userId);

	UserRights getUserRightsInfo(String userId);
	/*
	List<BirthDayEmplDto> getBirthDayUsersInfo(String month, String business_unit);*/

	String doLoginForOA(String tookid);
	
	List<GeelyUserInfo> getUserInfoByDate(String context, boolean isQueryLeave);
       
}
