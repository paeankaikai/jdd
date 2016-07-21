package com.portal.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Global {

	private Properties properties;

	public static String KEY_BASE_ADMIN_ACCOUNT = "global.base_admin";
	public static String KEY_BASE_ADMIN_PASSWORD = "global.base_admin_password";

	private void load(InputStream is){
		try {
			if(null == properties){
				properties = new Properties();
				properties.load(is);
				String path=getClass().getResource("/").getPath();
				//win
				if(path.contains(":/")){
					path=path.substring(1);
				}
				//linux
				else{
					path=path.substring(0);
				}				
				String intfConfPath =path+ properties.get("geely.intf.conf.path").toString();
				
				properties.put("INTFCONF", CommonUtil.getIntfConfXml(intfConfPath));
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != is){
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setLocation(String location) {
		load(getClass().getResourceAsStream(location.replace("classpath:", "/")));
	}
	
	public String getValue(String key) {
		return properties.getProperty(key, "");
	}
	
	public Object getObject(String key) {
		return properties.get(key);
	}
}
