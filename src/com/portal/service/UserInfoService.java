package com.portal.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JkReleaseDetail;
import com.portal.bean.UserInfo;
import com.portal.bean.UserInfoExample;
import com.portal.bean.UserInfoQuery;

public interface UserInfoService {
	
	public UserInfo selectByUserId(String userId);
	
	
	
	
	
	public int insertUserInfo(UserInfo userInfo);
	
	/**
	 * 从接口将数据保存在本地userInfo表
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月7日 下午7:32:45
	 * @param userInfo
	 * @return
	 */
	public int insertUserInfoFromGeely(GeelyUserInfo userInfo)  ;
	
	
	
	/**
	 * 个人金币更新
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月14日 下午3:46:41
	 * @param detail
	 * @return
	 */
	public int updateJkSum(JkReleaseDetail detail);
	
	/**
	 * 批量金币更新
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月14日 下午3:46:41
	 * @param detail
	 * @return
	 */
	public int updateJkSum(List<JkReleaseDetail> details, HttpServletRequest request);
	
	
	/**
	 * 通用查询
	 * @param query
	 * @return
	 */
	List<UserInfo> selectByWhere(UserInfoQuery query);

	/**
	 * 更新
	 * @param userInfo
	 */
	public int updateByUserId(UserInfo userInfo);

	/**
	 * 是否存在在职用户
	 * @param userId
	 * @return
	 */
	public int isExistUser(String userId);
	
	
	
	public List<UserInfo> selectByUserCom(String userCom);

}
