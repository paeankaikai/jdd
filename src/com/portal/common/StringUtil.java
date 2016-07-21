package com.portal.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * 字符串操作工具
 */
public class StringUtil {
	

	
	/**
	 * 获取guid
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月8日 下午1:45:04
	 * @return
	 */
	public static String getGUID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 获取随机编号
	 * 
	 */
	public static String getRandom(){		
		Random r=new Random();
		int number=r.nextInt();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date())+String.valueOf(number>0?number:-number);
		
	}
	
	

	/**
	 * 判断字符串是否为空
	 * 
	 * @param arg
	 * @return 空返回true
	 */
	public static boolean isEmpty(String arg) {
		return null == arg || arg.trim().length() == 0;
	}
	
	/**
	 * 判断一个字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(Object str)
	{
		return str != null && str.toString().matches("[0-9]+");
	}
	
	/**
	 * 判断一个字符串是否定长的字符 
	 * @param str
	 * @param len
	 * @return
	 */
	public static boolean isNumber(Object str, int len)
	{
		return str != null && str.toString().matches("[0-9]{" + len + "}");
	}

	/**
	 * 判断一个字符串是否是数字
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isNumeric(String arg) {

		if (isEmpty(arg)) return false;
		
		String tmp = arg.trim();

		for (int i = 0; i < tmp.length(); i++) {
			char one = tmp.charAt(i);
			if (one > '9' || one < '0')
				return false;
		}
		return true;
	}
	
	/**
	 * 判断一个字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(Object str)
	{
		return str == null || str.toString().trim().length() < 1;
	}
	
	
	public static boolean isNull(List list)
	{
		return list == null || list.size()==0;
	}
	
	
	public static boolean isNotNull(List list)
	{
		return !isNull(list);
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		System.out.println(StringUtil.getRandom());
		List<String> list=new ArrayList<String>();
		list.add("aa");
		
		list.add("sss");
		System.out.println(list);
		
		System.out.println(StringUtil.isNull(list));
		System.out.println(StringUtil.isNotNull(list));
	}

}
