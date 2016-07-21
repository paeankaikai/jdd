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
import java.io.InputStream;
import java.lang.reflect.Type;

import com.caucho.hessian.io.Hessian2Input;
import com.fasterxml.jackson.core.type.TypeReference;
import com.portal.core.common.serialize.ObjectInput;

/**
 * Hessian2ObjectInput
 * inputstream content is null will throw NullPointerException
 * 
 * @author  wei
 * @created 2012-12-11 下午1:10:38
 * @since   v1.3.1
 */
public class Hessian2ObjectInput implements ObjectInput {
	private final Hessian2Input mH2i;

	/**
	 * 
	 * @param is
	 */
	public Hessian2ObjectInput(InputStream is) {
		mH2i = new Hessian2Input(is);
		mH2i.setSerializerFactory(Hessian2SerializerFactory.SERIALIZER_FACTORY);
	}

	@Override
	public boolean readBool() throws IOException {
		return mH2i.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return (byte) mH2i.readInt();
	}

	@Override
	public short readShort() throws IOException {
		return (short) mH2i.readInt();
	}

	@Override
	public int readInt() throws IOException {
		return mH2i.readInt();
	}

	@Override
	public long readLong() throws IOException {
		return mH2i.readLong();
	}

	@Override
	public float readFloat() throws IOException {
		return (float) mH2i.readDouble();
	}

	@Override
	public double readDouble() throws IOException {
		return mH2i.readDouble();
	}

	@Override
	public byte[] readBytes() throws IOException {
		return mH2i.readBytes();
	}

	@Override
	public String readUTF() throws IOException {
		return mH2i.readString();
	}

	@Override
	public Object readObject() throws IOException {
		return mH2i.readObject();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		return (T) mH2i.readObject(cls);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T readObject(Class<T> cls, String dateFormat)
			throws IOException, ClassNotFoundException {
		return (T) mH2i.readObject(cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		return readObject(cls);
	}

	@Override
	public <T> T readObject(TypeReference<?> valueTypeRef) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}