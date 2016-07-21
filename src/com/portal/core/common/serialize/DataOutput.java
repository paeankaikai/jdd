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
 * DataOutput
 *
 * @author  wei
 * @created 2012-12-11 下午1:13:43
 * @since   v1.3.1
 */
public interface DataOutput {

	/**
	 * Write boolean.
	 * 
	 * @param v value.
	 * @throws IOException
	 */
	void writeBool(boolean v) throws IOException;

	/**
	 * Write byte.
	 * 
	 * @param v value.
	 * @throws IOException
	 */
	void writeByte(byte v) throws IOException;

	/**
	 * Write short.
	 * 
	 * @param v value.
	 * @throws IOException
	 */
	void writeShort(short v) throws IOException;

	/**
	 * Write integer.
	 * 
	 * @param v value.
	 * @throws IOException
	 */
	void writeInt(int v) throws IOException;

	/**
	 * Write long.
	 * 
	 * @param v value.
	 * @throws IOException
	 */
	void writeLong(long v) throws IOException;

	/**
	 * Write float.
	 * 
	 * @param v value.
	 * @throws IOException
	 */
	void writeFloat(float v) throws IOException;

	/**
	 * Write double.
	 * 
	 * @param v value.
	 * @throws IOException
	 */
	void writeDouble(double v) throws IOException;

	/**
	 * Write string.
	 * 
	 * @param v value.
	 * @throws IOException
	 */
	void writeUTF(String v) throws IOException;

	/**
	 * Write byte array.
	 * 
	 * @param v value.
	 * @throws IOException
	 */
	void writeBytes(byte[] v) throws IOException;

	/**
	 * Write byte array.
	 * 
	 * @param v value.
	 * @param off offset.
	 * @param len length.
	 * @throws IOException
	 */
	void writeBytes(byte[] v, int off, int len) throws IOException;

	/**
	 * Flush buffer.
	 * 
	 * @throws IOException
	 */
	void flushBuffer() throws IOException;
}