package com.portal.common;


public class SessionConstants {
	
	//个人信息
	public static String USERINFO="userInfo";
	
	//系统参数-费用来源
	public static String SOURCE="source";

	
	//设置缓存时间为三十分钟
	public static int REDIS_TIME= 30*60;
	
	//所有表    isdeleted  通用
	public static int DELETED=1;
	
	public static int NOTDELETED=0;
	
	/** 支出收入类别 **/
	//支出
	public static int PARAMETER_RULE_TYPE_EXPENSES = 1;
	//收入
	public static int PARAMETER_RULE_TYPE_INCOME = 0;
	//分页显示条数
	public static int PAGE_SHOW_NUM = 10;
	
	/*规则表*/
	//收入规则         List<TreeNode> InTree
	public static String INCOME="inCome";	
	public static int INCOME_TYPE=0;	
	
	//支出规则
	public static String OUTCOME="outCome";
	public static int OUTCOME_TYPE=1;
	
	//微送GUID
	public static String RULE_MICRO_IN_GUID = "49";
	public static String RULE_MICRO_OUT_GUID = "52";
	
	//系统三级guid
	public static String RULE_SYS_GRAD_GUID = "56";
	public static String RULE_SYS_FATHER_GUID = "57";
	public static String RULE_YEAR_CHILD_GUID = "58";
	public static String RULE_LEAVE_CHILD_GUID = "59";
	public static String RULE_CELEBRATE_GUID = "5";
	public static String RULE_TOEMPOLYEE_GUID = "6";
	public static String RULE_ANNIVERSARY_GUID = "7";
	public static String RULE_MALL_OUT_GUID = "46";
	public static String RULE_MALL_IN_GUID = "62";

	/* 系统参数表 */

	// 来源
	public static String PARAMETER_SOURCE = "SOURCE";
	// 来源中的受支援公司
	public static String PARAMETER_SOURCE_HELP_GUID = "5";
	// 赠送数量
	public static String PARAMETER_SENDCOUNT = "microSendCount";
	// 赠送次数
	public static String PARAMETER_SENDTIME = "microSendTime";
	//系统清零日期
	public static String PARAMETER_YEARCLEARDATE = "yearClearDate";
	//司庆日期
	public static String PARAMETER_CELEBRATEDATE = "celebrateDate";
	//司庆发放点数
	public static String PARAMETER_CELEBRATESENDNUM = "celebrateSendNum";
	//转正发放点数
	public static String PARAMETER_BEEMPLOYEESENDNUM = "beEmployeeSendNum";	
	//入职周年
	public static String PARAMETER_ANNIVERSARYSENDNUM = "anniversarySendNum";
	
	
	/** 发放配置 **/
	// 微送规则信息在系统参数表中的关键字
	public static String MICROSEND_PARAMETER_MICROSENDNUM = "microSendCount";
	public static String MICROSEND_PARAMETER_MICROSENDCOUNT = "microSendTime";
	
	
	/* 发放文件保留路径 */
	public static String JDDRELEASE_FILEPATH="filePath";
	

	public static String JDDRELEASE_UPLOADEXCELDATA="jddExcelData";
	
	public static String JKRELEASE_UPLOADEXCELDATA="jkExcelData";
	
	
	
	//兑换比例
	public static String CHANGENUM="changeNum";
	
	

	//权限管理等级
	public static int LEVEL_1=1;//普通员工

	public static int LEVEL_2=2;//普通管理员

	public static int LEVEL_3=3;//集团人资普通管理员

	public static int LEVEL_4=4;//审核管理员
	
	public static int LEVEL_5=5;//企业大学管理员
	
	public static int LEVEL_6=6;//超级管理员

	
}
