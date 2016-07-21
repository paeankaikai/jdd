package com.portal.core.common.serialize;

/*
 * Copyright 2009-2012 Evun Technology. 
 * 
 * This software is the confidential and proprietary information of
 * Evun Technology. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with evun.cn.
 */

import java.io.IOException;


/**
 * DataInput
 * 
 * @author  wei
 * @created 2012-12-11 下午1:13:11
 * @since   v1.3.1
 */
public interface DataInput {
    
	/**
	 * Read boolean.
	 * 
	 * @return boolean.
	 * @throws IOException.
	 */
	boolean readBool() throws IOException;

	/**
	 * Read byte.
	 * 
	 * @return byte value.
	 * @throws IOException.
	 */
	byte readByte() throws IOException;

	/**
	 * Read short integer.
	 * 
	 * @return short.
	 * @throws IOException.
	 */
	short readShort() throws IOException;

	/**
	 * Read integer.
	 * 
	 * @return integer.
	 * @throws IOException.
	 */
	int readInt() throws IOException;

	/**
	 * Read long.
	 * 
	 * @return long.
	 * @throws IOException.
	 */
	long readLong() throws IOException;

	/**
	 * Read float.
	 * 
	 * @return float.
	 * @throws IOException.
	 */
	float readFloat() throws IOException;

	/**
	 * Read double.
	 * 
	 * @return double.
	 * @throws IOException.
	 */
	double readDouble() throws IOException;

	/**
	 * Read UTF-8 string.
	 * 
	 * @return string.
	 * @throws IOException.
	 */
	String readUTF() throws IOException;

	/**
	 * Read byte array.
	 * 
	 * @return byte array.
	 * @throws IOException.
	 */
	byte[] readBytes() throws IOException;
}