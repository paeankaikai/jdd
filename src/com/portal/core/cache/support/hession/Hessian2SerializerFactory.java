package com.portal.core.cache.support.hession;

/*
 * Copyright 2009-2012 Evun Technology. 
 * 
 * This software is the confidential and proprietary information of
 * Evun Technology. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with evun.cn.
 */

import com.caucho.hessian.io.SerializerFactory;


/**
 * Hessian2SerializerFactory
 *
 * @author  wei
 * @created 2012-12-11 下午5:41:21
 * @since   v1.3.1
 */
public class Hessian2SerializerFactory extends SerializerFactory {

	public static final SerializerFactory SERIALIZER_FACTORY = new Hessian2SerializerFactory();

	private Hessian2SerializerFactory() {
	}

	@Override
	public ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

}
