package com.portal.core.cache.support.hession;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.portal.core.common.URL;
import com.portal.core.common.serialize.ObjectInput;
import com.portal.core.common.serialize.ObjectOutput;
import com.portal.core.common.serialize.Serialization;

public class Hessian2Serialization implements Serialization{
	public static final byte ID = 2;

	@Override
	public byte getContentTypeId() {
		return ID;
	}

	@Override
	public String getContentType() {
		return "x-application/hessian2";
	}

	@Override
	public ObjectOutput serialize(URL url, OutputStream out) throws IOException {
		return new Hessian2ObjectOutput(out);
	}

	@Override
	public ObjectInput deserialize(URL url, InputStream is) throws IOException {
		return new Hessian2ObjectInput(is);
	}
}
