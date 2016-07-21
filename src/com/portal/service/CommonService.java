package com.portal.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddJkrecord;
import com.portal.bean.JddReleaseDetail;
import com.portal.bean.LoginVerify;

public interface CommonService {
	
	/**
	 * 批量审批jdd	
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月14日 下午6:58:28
	 * @param map
	 * @return
	 */
	public	Integer jddbaserecord(List<JddReleaseDetail> details,HttpServletRequest request);
	
	
	/**
	 * 登录判断用户
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月14日 下午6:59:00
	 * @param map
	 * @return
	 */
	public	Integer jdduserinit(GeelyUserInfo geelyUserInfo)throws ParseException;
	
	
	/**
	 * jk兑换jdd
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月23日 下午2:56:16
	 * @param jkRecord
	 * @return
	 */
	public Integer exchange(JddJkrecord jkRecord,GeelyUserInfo userInfo);


	/**
	 * 回调验证信息插入
	 * @param loginVerify
	 * @return
	 */
	public Integer callbackVerifyInsert(LoginVerify loginVerify);

	
	/**
	 * 回调验证信息获取
	 * @param guid
	 * @return
	 */
	public LoginVerify getCallbackVerify(String guid);


	

}
