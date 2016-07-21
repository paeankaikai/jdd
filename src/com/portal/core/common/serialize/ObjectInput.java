package com.portal.core.common.serialize;

import java.io.IOException;
import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;


public interface ObjectInput extends DataInput{
	/**
	 * read object.
	 * 
	 * @return object.
	 */
	Object readObject() throws IOException, ClassNotFoundException;

	/**
	 * read object.
	 * 
	 * @param cls
	 *            object type.
	 * @return object.
	 */
	<T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException;

	/**
	 * 多传入一个日期格式参数，用来指定将json字符串转换对象中Date类型的转换格式 临时使用。需要优化
	 */
	<T> T readObject(Class<T> cls, String dateFormat) throws IOException,
			ClassNotFoundException;

	/**
	 * read object.
	 * 
	 * @param cls
	 *            object type.
	 * @return object.
	 */
	<T> T readObject(Class<T> cls, Type type) throws IOException,
			ClassNotFoundException;

	<T> T readObject(TypeReference<?> valueTypeRef) throws IOException,
			ClassNotFoundException;
}
