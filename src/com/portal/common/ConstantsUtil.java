package com.portal.common;


/**
 * 常量定义
 */
public class ConstantsUtil {

	// 处理结果确认：处理失败
	public static Integer CONFIRM_RESULT_FAIL = new Integer(0);
	
	/** 用户登录结果状态 **/
	// 失败-- 信息不匹配
	public static final Integer LOGIN_STATUS_ADM = new Integer(3);
	public static final Integer LOGIN_STATUS_MANG = new Integer(4);
	public static final Integer LOGIN_STATUS_PSN = new Integer(5);
	// 失败--接口错误
	public static final Integer LOGIN_STATUS_INTF_ERROR = new Integer(6);
	
	public static final Integer MESSAGE_ROW_COUNT = new Integer(5);
	public static final Integer TEAM_ROW_COUNT = new Integer(10);
	
	//用户角色
	public static final String G_MALL_MGR = "G_MALL_MGR";
	public static final String G_MALL_SUP_MGR = "G_MALL_SUP_MGR";
	 
	
}