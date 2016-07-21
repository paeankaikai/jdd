package com.portal.common;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * **********************************************
 * Copyright (c)  by anhui tonghui information technology Co., Ltd.
 * All right reserved.
 * Create Date: 2012-8-22
 * Create Author: doumingjun
 * File Name:  PropertiesUtil
 * Last version:  1.0
 * Function:读取.properties配置文件的内容至Map中。
 * Last Update Date:
 * Change Log:
 *************************************************
 */
public class PropertiesUtil {

	/**
	 * 读取.properties配置文件的内容至Map中
	 * @param propertiesFile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object,Object> read(String propertiesFile) {
		ResourceBundle rb = ResourceBundle.getBundle(propertiesFile);
		Map<Object,Object> map = new HashMap<Object,Object>();
		Enumeration enu = rb.getKeys();
		while (enu.hasMoreElements()) {
			Object obj = enu.nextElement();
			Object objv = rb.getObject(obj.toString());
			map.put(obj, objv);
		}
		return map;
	}
	
	/**
	 * 读取.properties配置文件的内容至List<List<String>>
	 * @param propertiesFile
	 * @return
	 * @author cyk
	 * @Date  2015-9-13
	 */
	public static List<List<Object>> readAsList(String propertiesFile) {
		ResourceBundle rb = ResourceBundle.getBundle(propertiesFile);
		List<List<Object>> lists=new ArrayList<List<Object>>();
		Enumeration enu = rb.getKeys();
		while (enu.hasMoreElements()) {
			List<Object> list=new ArrayList<Object>();
			Object obj = enu.nextElement();
			Object objv = rb.getObject(obj.toString());
			list.add(obj);
			list.add(objv);
			lists.add(list);
		}
		return lists;
	}
	
	
/*	 public static void writePropertiesFile(String filename)  
	    {  
		 
	//	 ResourceBundle bundle = ResourceBundle.getBundle(filename);  
	//	   String s = bundle.getString("pdfAddress");  
	//	   System.out.println(s);  
		 
		 Properties properties = new Properties();  
	        try  
	        {  
	            OutputStream outputStream = new FileOutputStream(filename);  
	            properties.setProperty("username", "myname");    
	            properties.store(outputStream, " Studio");  
	            outputStream.close();  
	        }  
	        catch (IOException e)  
	        {  
	            e.printStackTrace();  
	        }  
	    }  
	 
	 
	 public static void  writeProperties(String filename,Writer writer) throws IOException {
		 Resource resource = new ClassPathResource("config/address.properties");
		 Properties props = PropertiesLoaderUtils.loadProperties(resource);
		//  OutputStream fos = new FileOutputStream(profilepath);
		 props.setProperty("username", "myname");    
		 props.store(writer, "sss");
		 //props.put("name", "sss");
		// props.store(resource, "ss");
		 
	 }
	*/
	
	
	public static void main(String[] args) {
		Map map = PropertiesUtil.read("config/address");
		Object a= (Object)map.get("pdfAddress");
		System.out.println(a);
	
	}

}

