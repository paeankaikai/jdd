package com.portal.core.cache.support.redis.jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.portal.core.cache.support.AbstractCacheClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.PipelineBlock;
import redis.clients.util.SafeEncoder;

public class JedisClient extends AbstractCacheClient {
	// jedis连接池
	private JedisPool jedisPool;

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * <p>
	 * Description: 从连接池中获取jedis连接
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-11 下午3:32:16
	 * @since v1.3.1
	 * @return Jedis
	 */
	public Jedis getResource() {
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
		} catch (Exception e) {
			return null;
		}
		
		return jedis;
	}

	/**
	 * <p>
	 * Description: 将jedis连接放回连接池中
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-11 下午3:32:46
	 * @since v1.3.1
	 * @param resource
	 *            void
	 */
	public void returnResource(Jedis resource) {
		jedisPool.returnResource(resource);
	}

	@Override
	public Boolean set(String key, byte[] value) {
		String result = null;
		Jedis jedis = getResource();
		if(jedis==null){
			return false;
		}
		try {
			result = jedis.set(SafeEncoder.encode(key), value);
		} finally {
			returnResource(jedis);
		}
		return "OK".equals(result) ? true : false;
	}

	@Override
	public Boolean set(String key, int seconds, byte[] value) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.setex(SafeEncoder.encode(key), seconds, value);
		} finally {
			returnResource(jedis);
		}
		return "OK".equals(result) ? true : false;
	}

	@Override
	public byte[] getBytes(String key) {
		byte[] result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.get(SafeEncoder.encode(key));
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	@Override
	public Boolean delete(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.del(key);
		} finally {
			returnResource(jedis);
		}

		return result == 1l ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.evun.gap.common.cache.CacheClient#batchGet(java.lang.String[])
	 */
	@Override
	public List<Object> batchGet(final String[] keys) {
		Jedis jedis = getResource();
		List<Object> plList = null;
		try {
			plList = jedis.pipelined(new PipelineBlock() {
				@Override
				public void execute() {
					for (String key : keys) {
						get(key);
					}
				}
			});
		} finally {
			returnResource(jedis);
		}

		return deserializePipelineResultForByteArray(plList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.evun.gap.common.cache.CacheClient#batchDelete(java.lang.String[])
	 */
	@Override
	public List<Object> batchDelete(final String[] keys) {
		Jedis jedis = getResource();
		List<Object> plList = null;
		try {
			plList = jedis.pipelined(new PipelineBlock() {
				@Override
				public void execute() {
					for (String key : keys) {
						expire(key, 0);
					}
				}
			});
		} finally {
			returnResource(jedis);
		}

		return plList;
	}

	/**
	 * <p>
	 * Description: 序列化pipeline返回的byte[]的结果
	 * </p>
	 * 
	 * @author wqb
	 * @created 2012-12-12 下午12:49:49
	 * @since v1.3.1
	 * @param list
	 * @return List<Object>
	 */
	private List<Object> deserializePipelineResultForByteArray(List<Object> list) {
		List<Object> resultList = new ArrayList<Object>(list.size());
		for (Object object : list) {
			if (null == object) {
				continue;
			}
			Object o = getSerializer().deserialize((byte[]) object);
			resultList.add(o);
		}
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.evun.gap.common.cache.support.AbstractCacheClient#lpush(java.lang.
	 * String, byte[])
	 */
	@Override
	public Long lpush(String key, byte[] value) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.lpush(SafeEncoder.encode(key), value);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.evun.gap.common.cache.support.AbstractCacheClient#lrangeBytes(java
	 * .lang.String, long, long)
	 */
	@Override
	public List<byte[]> lrangeBytes(String key, int start, int end) {
		List<byte[]> result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.lrange(SafeEncoder.encode(key), start, end);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	@Override
	public Long llen(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.llen(key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	@Override
	public byte[] rpopBytes(String key) {
		byte[] result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.rpop(SafeEncoder.encode(key));
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	@Override
	public byte[] lpopBytes(String key) {
		byte[] result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.lpop(SafeEncoder.encode(key));
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	@Override
	public Long decrBy(String key, long integer) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.decrBy(key, integer);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	@Override
	public Long incr(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.incr(key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.evun.gap.core.cache.CacheClient#hexists(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Boolean hexists(String key, String field) {
		Boolean result = false;
		Jedis jedis = getResource();
		try {
			result = jedis.hexists(key, field);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.evun.gap.core.cache.CacheClient#hkeys(java.lang.String)
	 */
	@Override
	public Set<String> hkeys(String key) {
		Set<String> result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hkeys(key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.evun.gap.core.cache.CacheClient#hdel(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Long hdel(String key, String field) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hdel(key, field);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.evun.gap.core.cache.support.AbstractCacheClient#hsetBytes(java.lang
	 * .String, java.lang.String, byte[])
	 */
	@Override
	public Long hsetBytes(String key, String field, byte[] value) {
		Long result = 0L;
		Jedis jedis = getResource();
		try {
			result = jedis.hset(SafeEncoder.encode(key),
					SafeEncoder.encode(field), value);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.evun.gap.core.cache.support.AbstractCacheClient#hgetBytes(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public byte[] hgetBytes(String key, String field) {
		byte[] result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hget(SafeEncoder.encode(key),
					SafeEncoder.encode(field));
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.evun.gap.core.cache.support.AbstractCacheClient#hmgetBytes(java.lang
	 * .String, java.lang.String[])
	 */
	@Override
	public List<byte[]> hmgetBytes(String key, String... fields) {
		byte[][] byteFields = new byte[fields.length][];
		for (int i = 0; i < fields.length; i++) {
			byteFields[i] = SafeEncoder.encode(fields[i]);
		}
		List<byte[]> result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hmget(SafeEncoder.encode(key), byteFields);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.evun.gap.core.cache.support.AbstractCacheClient#hgetAllBytes(java.
	 * lang.String)
	 */
	@Override
	public Map<byte[], byte[]> hgetAllBytes(String key) {
		Map<byte[], byte[]> result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.hgetAll(SafeEncoder.encode(key));
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	@Override
	public void publish(String channel, String message) {
		Jedis jedis = getResource();
		try {
			jedis.publish(channel, message);
		} finally {
			returnResource(jedis);
		}
	}

	@Override
	public void psubscribe(JedisPubSub jedisPubSub, String[] patterns) {
		Jedis jedis = getResource();
		try {
			jedis.psubscribe(jedisPubSub, patterns);
		} finally {
			returnResource(jedis);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.evun.gap.core.nosql.CacheClient#flushAll()
	 */
	@Override
	public void flushAll() {
		Jedis jedis = getResource();
		jedis.flushAll();
	}

	/**
	 * 循环获取锁的等待超时时间，在此时间内会一直尝试获取锁直到超时，为0表示失败后直接返回不等待
	 */
	private final long timeout = 0;

	/**
	 * 当前锁的最大生存时间(秒)，必须大于0，如果超过生存时间锁仍未被释放，则系统会自动强制释放
	 */
	private final long expire = 15;

	/**
	 * 获取锁失败后再试的间隔时间微秒
	 */
	private final long waitInterval = 10000;

	/**
	 * 
	 * @param name
	 * @param timeout
	 *            单位秒
	 * @param expire
	 *            单位秒
	 * @param waitInterval
	 *            微秒
	 * @return
	 */
	public boolean lock(String name, long timeout, long expire,
			long waitInterval) {
		if (StringUtils.isEmpty(name)) {
			return false;
		}
		Date currentDate = new Date();
		long timeOutAt = currentDate.getTime() + timeout * 1000;
		long expireAt = currentDate.getTime() + expire * 1000;
		Jedis jedis = getResource();
		return true;
	}

	public boolean lock(String name) {
		return this.lock(name, timeout, expire, waitInterval);
	}
	
	@Override
	public long getByteSize(String key){
		byte [] bytes = this.getBytes(key);
		return bytes.length;
	}
}