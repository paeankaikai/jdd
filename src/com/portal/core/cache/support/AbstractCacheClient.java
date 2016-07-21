package com.portal.core.cache.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.util.SafeEncoder;

import com.portal.core.cache.CacheClient;

public abstract class AbstractCacheClient implements CacheClient{
	// 序列化器
		private SerializableAdapter serializer;

		public void setSerializer(SerializableAdapter serializer) {
			this.serializer = serializer;
		}

		public SerializableAdapter getSerializer() {
			return serializer;
		}

		@Override
		public Boolean set(String key, Object value) {
			byte[] valueBytes = serializer.serialize(value);
			return set(key, valueBytes);
		}

		@Override
		public Boolean set(String key, int seconds, Object value) {
			byte[] valueBytes = serializer.serialize(value);
			return set(key, seconds, valueBytes);
		}

		@Override
		public Object get(String key) {
			byte[] valueBytes = getBytes(key);
			return serializer.deserialize(valueBytes);
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T get(String key, Class<T> t) {
			byte[] valueBytes = getBytes(key);
			return (T) serializer.deserialize(valueBytes);
		}

		@Override
		public Long lpush(String key, Object value) {
			byte[] valueBytes = serializer.serialize(value);
			return lpush(key, valueBytes);
		}

		@Override
		public List<Object> lrange(String key, int start, int end) {
			List<byte[]> list = lrangeBytes(key, start, end);
			List<Object> oList = new ArrayList<Object>(list.size());
			for (byte[] ba : list) {
				oList.add(serializer.deserialize(ba));
			}
			return oList;
		}

		@Override
		public Object rpop(String key) {
			byte[] bytes = rpopBytes(key);
			return serializer.deserialize(bytes);
		}

		@Override
		public Object lpop(String key) {
			byte[] bytes = lpopBytes(key);
			return serializer.deserialize(bytes);
		}

		public abstract Boolean set(String key, byte[] value);

		public abstract Boolean set(String key, int secondes, byte[] value);

		public abstract byte[] getBytes(String key);

		public abstract Long lpush(String key, byte[] value);

		public abstract List<byte[]> lrangeBytes(String key, int start, int end);

		public abstract byte[] rpopBytes(String key);

		public abstract byte[] lpopBytes(String key);

		/**
		 * 
		 * 
		 * @author wei
		 * @created 2013-3-7 上午10:57:48
		 * @since v1.3.1
		 * @param key
		 * @param field
		 * @param value
		 * @return
		 */
		@Override
		public Long hset(String key, String field, Object value) {
			byte[] valueBytes = serializer.serialize(value);
			return hsetBytes(key, field, valueBytes);
		}

		public abstract Long hsetBytes(String key, String field, byte[] value);

		/**
		 * 
		 * 
		 * @author wei
		 * @created 2013-3-7 上午10:57:54
		 * @since v1.3.1
		 * @param key
		 * @param field
		 * @return
		 */
		@Override
		public Object hget(String key, String field) {
			byte[] bytes = hgetBytes(key, field);
			return serializer.deserialize(bytes);
		}

		public abstract byte[] hgetBytes(String key, String field);

		/**
		 * 
		 * 
		 * @author wei
		 * @created 2013-3-7 上午10:58:02
		 * @since v1.3.1
		 * @param key
		 * @param fields
		 * @return
		 */
		@Override
		public List<Object> hmget(String key, String... fields) {

			List<byte[]> list = hmgetBytes(key, fields);
			List<Object> oList = new ArrayList<Object>(list.size());
			for (byte[] ba : list) {
				oList.add(serializer.deserialize(ba));
			}
			return oList;
		}

		/**
		 * 
		 * 
		 * @author wei
		 * @created 2013-3-8 上午11:46:11
		 * @since v1.3.1
		 * @param key
		 * @param fields
		 * @return
		 */
		public abstract List<byte[]> hmgetBytes(String key, String... fields);

		/**
		 * 
		 * 
		 * @author wei
		 * @created 2013-3-7 上午10:58:06
		 * @since v1.3.1
		 * @param key
		 * @param field
		 * @return
		 */
		@Override
		public abstract Boolean hexists(String key, String field);

		/**
		 * 
		 * 
		 * @author wei
		 * @created 2013-3-7 上午10:58:11
		 * @since v1.3.1
		 * @param key
		 * @return
		 */
		@Override
		public abstract Set<String> hkeys(String key);

		/**
		 * 
		 * 
		 * @author wei
		 * @created 2013-3-7 上午10:58:14
		 * @since v1.3.1
		 * @param key
		 * @return
		 */
		@Override
		public Map<String, Object> hgetAll(String key) {
			Map<byte[], byte[]> map = hgetAllBytes(key);
			Map<String, Object> omap = new HashMap<String, Object>();
			for (byte[] mapKey : map.keySet()) {
				omap.put(SafeEncoder.encode(mapKey),
						serializer.deserialize(map.get(mapKey)));
			}
			return omap;
		}

		/**
		 * 
		 * 
		 * @author wei
		 * @created 2013-3-8 上午11:27:53	
		 * @since v1.3.1
		 * @param key
		 * @return
		 */
		public abstract Map<byte[], byte[]> hgetAllBytes(String key);

		/**
		 * 
		 * 
		 * @author wei
		 * @created 2013-3-7 上午10:58:18
		 * @since v1.3.1
		 * @param key
		 * @param field
		 * @return
		 */
		@Override
		public abstract Long hdel(String key, String field);
		
		@Override
		public abstract long getByteSize(String key);
}
