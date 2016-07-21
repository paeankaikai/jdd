package com.portal.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 动态装载bean工具类
 */

@Component
public class SpringContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
	
	
	public static ApplicationContext getApplicationContext() {
        return applicationContext;
 }
	
	/**
	 * 根据bean的Id获取一个对象bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}
	
}
