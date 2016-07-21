package com.portal.core.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import redis.clients.jedis.JedisPubSub;

import com.portal.common.CommonUtil;
import com.portal.core.SpringContext;

public class CacheUtils {
	// cache客户端
	static CacheClient cacheClient = SpringContext.getBean(CacheClient.class);

	/**
	 * <p>
	 * Description: flushall
	 * </p>
	 * 
	 * @author wqb
	 * @created 2013-8-29 上午10:41:28
	 * @since v1.3.1
	 * @return void
	 */
	public static void flushAll() {
		cacheClient.flushAll();
	}

	/**
	 * <p>
	 * Description: 持久存储
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午1:05:46
	 * @since v1.3.1
	 * @param key
	 * @param value
	 * @return Boolean
	 */
	public static Boolean set(String key, Object value) {
		boolean result = cacheClient.set(key, value);
		if (!result) {
			result = cacheClient.set(key, value);
		}
		return result;
	}

	/**
	 * <p>
	 * Description: 按时间存储
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午1:06:06
	 * @since v1.3.1
	 * @param key
	 * @param expiredTime
	 * @param value
	 * @return Boolean
	 */
	public static Boolean set(String key, int expiredTime, Object value) {
		boolean result = cacheClient.set(key, expiredTime, value);
		if (!result) {
			result = cacheClient.set(key, expiredTime, value);
		}
		return result;
	}

	/**
	 * <p>
	 * Description: 查询
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午1:06:45
	 * @since v1.3.1
	 * @param key
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key) {
		try {
			return (T) cacheClient.get(key);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>
	 * Description: 批量查询
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午1:00:30
	 * @since v1.3.1
	 * @param keys
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> batchGet(String[] keys) {
		if (ArrayUtils.isEmpty(keys)) {
			return null;
		}

		return (List<T>) cacheClient.batchGet(keys);
	}

	/**
	 * <p>
	 * Description: 批量查询
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午12:55:26
	 * @since v1.3.1
	 * @param keyList
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> batchGet(List<String> keyList) {
		if (keyList.isEmpty()) {
			return null;
		}
		String[] keysString = new String[keyList.size()];
		keyList.toArray(keysString);
		return (List<T>) batchGet(keysString);
	}

	/**
	 * <p>
	 * Description: 删除
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午12:51:20
	 * @since v1.3.1
	 * @param key
	 * @return Boolean
	 */
	public static Boolean delete(String key) {
		boolean result = cacheClient.delete(key);
		if (!result) {
			result = cacheClient.delete(key);
		}

		return result;
	}

	/**
	 * <p>
	 * Description: 批量删除
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午1:07:06
	 * @since v1.3.1
	 * @param keys
	 * @return Object
	 */
	public static Object batchDelete(String[] keys) {
		if (ArrayUtils.isEmpty(keys)) {
			return null;
		}

		return cacheClient.batchDelete(keys);
	}

	/**
	 * <p>
	 * Description: 批量删除
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午1:07:16
	 * @since v1.3.1
	 * @param keyList
	 * @return Object
	 */
	public static Object batchDelete(List<String> keyList) {
		if (CommonUtil.isEmpty(keyList)) {
			return null;
		}

		String[] keysString = new String[keyList.size()];
		keyList.toArray(keysString);
		return batchDelete(keysString);
	}

	public static Long lpush(String key, Object value) {
		return cacheClient.lpush(key, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> lrange(String key, int start, int end) {
		return (List<T>) cacheClient.lrange(key, start, end);
	}

	public static Long llen(String key) {
		return cacheClient.llen(key);
	}

	@SuppressWarnings("unchecked")
	public static <T> T rpop(String key) {
		return (T) cacheClient.rpop(key);
	}

	@SuppressWarnings("unchecked")
	public static <T> T lpop(String key) {
		return (T) cacheClient.lpop(key);
	}

	public static Long decrBy(String key, long integer) {
		return cacheClient.decrBy(key, integer);
	}

	public static Long incr(String key) {
		return cacheClient.incr(key);
	}

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
	public static Long hset(String key, String field, Object value) {
		return cacheClient.hset(key, field, value);
	}

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
	public static Object hget(String key, String field) {
		return cacheClient.hget(key, field);
	}

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
	public static List<Object> hmget(String key, String... fields) {
		return cacheClient.hmget(key, fields);
	}

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
	public static Boolean hexists(String key, String field) {
		return cacheClient.hexists(key, field);
	}

	/**
	 * 
	 * 
	 * @author wei
	 * @created 2013-3-7 上午10:58:11
	 * @since v1.3.1
	 * @param key
	 * @return
	 */
	public static Set<String> hkeys(String key) {
		return cacheClient.hkeys(key);
	}

	/**
	 * 
	 * 
	 * @author wei
	 * @created 2013-3-7 上午10:58:14
	 * @since v1.3.1
	 * @param key
	 * @return
	 */
	public static Map<String, Object> hgetAll(String key) {
		return cacheClient.hgetAll(key);
	}

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
	public static Long hdel(String key, String field) {
		return cacheClient.hdel(key, field);
	}

	/**
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wei
	 * @created 2013-5-16 下午4:40:26
	 * @since v1.3.1
	 * @param channel
	 * @param message
	 * @return void
	 */
	public static void publish(final String channel, final String message) {
		cacheClient.publish(channel, message);
	}

	/**
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wei
	 * @created 2013-5-16 下午6:17:03
	 * @since v1.3.1
	 * @param jedisPubSub
	 * @param patterns
	 * @return void
	 */
	public static void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
		cacheClient.psubscribe(jedisPubSub, patterns);
	}
	
	public static long getSize(String key){
		return cacheClient.getByteSize(key);
	}
}
