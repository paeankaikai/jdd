package com.portal.core.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.JedisPubSub;

public interface CacheClient {

	void flushAll();

	/**
	 * <p>
	 * Description: set
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-11 下午3:30:38
	 * @since v1.3.1
	 * @param key
	 * @param value
	 * @return Object
	 */
	Boolean set(String key, Object value);

	/**
	 * <p>
	 * Description: set
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-11 下午3:30:48
	 * @since v1.3.1
	 * @param key
	 * @param seconds
	 * @param value
	 * @return Object
	 */
	Boolean set(String key, int seconds, Object value);

	/**
	 * <p>
	 * Description: get
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-11 下午3:30:55
	 * @since v1.3.1
	 * @param key
	 * @return Object
	 */
	Object get(String key);

	/**
	 * <p>
	 * Description: 批处理查询
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 上午9:27:47
	 * @since v1.3.1
	 * @param keys
	 * @return List<Object>
	 */
	List<Object> batchGet(String[] keys);

	/**
	 * <p>
	 * Description: 批处理get
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-11 下午3:30:59
	 * @since v1.3.1
	 * @param key
	 * @param t
	 * @return Object
	 */
	<T> Object get(String key, Class<T> t);

	/**
	 * <p>
	 * Description: delete
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-11 下午3:31:07
	 * @since v1.3.1
	 * @param key
	 * @return Object
	 */
	Boolean delete(String key);

	/**
	 * <p>
	 * Description: 批处理delete
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 上午9:29:29
	 * @since v1.3.1
	 * @param keys
	 * @return List<Object>
	 */
	Object batchDelete(String[] keys);

	/**
	 * <p>
	 * Description: lpush
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午3:19:24
	 * @since v1.3.1
	 * @param key
	 * @param value
	 * @return Long
	 */
	Long lpush(String key, Object value);

	List<Object> lrange(String key, int start, int end);

	Long llen(String key);

	Object rpop(String key);

	Object lpop(String key);

	Long decrBy(String key, long integer);

	Long incr(String key);

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
	Long hset(String key, String field, Object value);

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
	Object hget(String key, String field);

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
	List<Object> hmget(String key, String... fields);

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
	Boolean hexists(String key, String field);

	/**
	 * 
	 * 
	 * @author wei
	 * @created 2013-3-7 上午10:58:11
	 * @since v1.3.1
	 * @param key
	 * @return
	 */
	Set<String> hkeys(String key);

	/**
	 * 
	 * 
	 * @author wei
	 * @created 2013-3-7 上午10:58:14
	 * @since v1.3.1
	 * @param key
	 * @return
	 */
	Map<String, Object> hgetAll(String key);

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
	Long hdel(String key, String field);

	/**
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wei
	 * @created 2013-5-16 下午5:34:05
	 * @since v1.3.1
	 * @param channel
	 * @param message
	 * @return void
	 */
	void publish(String channel, String message);

	/**
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wei
	 * @created 2013-5-16 下午5:34:12
	 * @since v1.3.1
	 * @param jedisPubSub
	 * @param patterns
	 * @return void
	 */
	void psubscribe(JedisPubSub jedisPubSub, String[] patterns);
	
	long getByteSize(String key);
}
