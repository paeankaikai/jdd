package com.portal.core.cache.support.redis.adapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.portal.core.cache.support.SerializableAdapter;
import com.portal.core.cache.support.hession.Hessian2Serialization;
import com.portal.core.common.serialize.ObjectInput;
import com.portal.core.common.serialize.ObjectOutput;

public class HessianSerializerAdapter implements SerializableAdapter {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.evun.gap.common.cache.SerializableAdapter#serialize(java.lang.Object)
	 */
	@Override
	public byte[] serialize(Object object) {
		if (null == object) {
			return null;
		}

		byte[] result = null;
		ByteArrayOutputStream bos = null;
		ObjectOutput oo = null;
		try {
			bos = new ByteArrayOutputStream();
			oo = new Hessian2Serialization().serialize(null, bos);
			oo.writeObject(object);
			oo.flushBuffer();
			result = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bos) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.evun.gap.common.cache.SerializableAdapter#desialize(byte[])
	 */
	@Override
	public Object deserialize(byte[] value) {
		if (value == null) {
			return null;
		}

		Object result = null;
		ByteArrayInputStream bis = null;
		ObjectInput oi = null;
		try {
			bis = new ByteArrayInputStream(value);
			oi = new Hessian2Serialization().deserialize(null, bis);
			result = oi.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
