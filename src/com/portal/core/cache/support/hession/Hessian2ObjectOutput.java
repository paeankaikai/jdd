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

import java.io.IOException;
import java.io.OutputStream;

import com.caucho.hessian.io.Hessian2Output;
import com.portal.core.common.serialize.ObjectOutput;


/**
 *Hessian2ObjectOutput
 *
 * @author  wei
 * @created 2012-12-11 下午5:40:31
 * @since   v1.3.1
 */
public class Hessian2ObjectOutput implements ObjectOutput {
	private final Hessian2Output mH2o;

	public Hessian2ObjectOutput(OutputStream os) {
		mH2o = new Hessian2Output(os);
		mH2o.setSerializerFactory(Hessian2SerializerFactory.SERIALIZER_FACTORY);
	}

	@Override
	public void writeBool(boolean v) throws IOException {
		mH2o.writeBoolean(v);
	}

	@Override
	public void writeByte(byte v) throws IOException {
		mH2o.writeInt(v);
	}

	@Override
	public void writeShort(short v) throws IOException {
		mH2o.writeInt(v);
	}

	@Override
	public void writeInt(int v) throws IOException {
		mH2o.writeInt(v);
	}

	@Override
	public void writeLong(long v) throws IOException {
		mH2o.writeLong(v);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		mH2o.writeDouble(v);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		mH2o.writeDouble(v);
	}

	@Override
	public void writeBytes(byte[] b) throws IOException {
		mH2o.writeBytes(b);
	}

	@Override
	public void writeBytes(byte[] b, int off, int len) throws IOException {
		mH2o.writeBytes(b, off, len);
	}

	@Override
	public void writeUTF(String v) throws IOException {
		mH2o.writeString(v);
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		mH2o.writeObject(obj);
	}

	@Override
	public void flushBuffer() throws IOException {
		mH2o.flushBuffer();
	}
}