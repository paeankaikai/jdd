package com.portal.core.cache.support;

public interface SerializableAdapter {
	public  byte[] serialize(Object obj);

    public  Object deserialize(byte abyte0[]);
}
