package com.portal.core;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContext implements ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(SpringContext.class);
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口,注入目标.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		if (null == SpringContext.applicationContext) {
			SpringContext.applicationContext = applicationContext;
		}
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	private static void checkApplicationContext() {
		if (applicationContext == null) {
			logger.error("No applicaitonContext found, please check if SpringContextHolder has bean defined!");
			throw new IllegalStateException("No applicaitonContext found, please check if SpringContextHolder has bean defined!");
		}
	}
	
	/**
	 * 根据name获取Bean,自动转型为所赋值对象的类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 根据class获取Bean,自动转型为所赋值对象的类型
	 */
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return applicationContext.getBean(clazz);
	}

	public static <T>  Map<String,T>  getBeans(Class<T> clazz) {
		checkApplicationContext();
		return applicationContext.getBeansOfType(clazz);
	}
	
	/**
	 * 清楚所持有的applicationContext.
	 */
	public static void cleanApplicationContext() {
		applicationContext = null;
	}


}
