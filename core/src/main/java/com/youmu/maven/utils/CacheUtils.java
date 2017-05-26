package com.youmu.maven.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheUtils {

	public static class CacheBuilder {
		private Long expireTime = (long) 180000;
		private Long idolTime = (long) 100000;

		public CacheBuilder setExpireTime(Long expireTime) {
			this.expireTime = expireTime;
			return this;
		}

		public CacheBuilder setIdolTime(Long idolTime) {
			this.idolTime = idolTime;
			return this;
		}

		public Cache build() {
			return new Cache(expireTime, idolTime);
		}
	}

	public static class Cache {
		private static class CacheObject {
			private Object target;
			private AtomicLong createTime;
			private AtomicLong lastDeliveTime;

			public CacheObject(Object target) {
				this.target = target;
				this.createTime = new AtomicLong(System.currentTimeMillis());
				this.lastDeliveTime = new AtomicLong(this.createTime.get());
			}

			public Object getTarget() {
				return target;
			}

			public AtomicLong getCreateTime() {
				return createTime;
			}

			public AtomicLong getLastDeliveTime() {
				return lastDeliveTime;
			}

			public void setLastDeliveTime(Long lastDeliveTime) {
				if (lastDeliveTime.longValue() > this.lastDeliveTime.get())
					this.lastDeliveTime.set(lastDeliveTime.longValue());
			}
		}

		private Map<String, CacheObject> cache;
		private Long expireTime;
		private Long idolTime;

		private ReadWriteLock lock = new ReentrantReadWriteLock();

		private Cache(Long expireTime, Long idolTime) {
			cache = new HashMap<String, CacheObject>();
			this.expireTime = expireTime;
			this.idolTime = idolTime;
		}

		public Object get(String name) {
			try {
				lock.readLock().lock();
				CacheObject target = cache.get(name);
				if (target == null)
					return null;
				if ((System.currentTimeMillis()
						- target.getLastDeliveTime().get() >= expireTime)
						|| (System.currentTimeMillis()
								- target.getCreateTime().get() >= expireTime)) {
					cache.remove(name);
					return null;
				}
				target.setLastDeliveTime(System.currentTimeMillis());
				return target.getTarget();
			} finally {
				lock.readLock().unlock();
			}
		}

		public void put(String name, Object obj) {
			try {
				lock.writeLock().lock();
				cache.put(name, new CacheObject(obj));
			} finally {
				lock.writeLock().unlock();
			}
		}

		public Long getExpireTime() {
			return expireTime;
		}

		public Long getIdolTime() {
			return idolTime;
		}

	}

	public static final Map<String, Cache> manager = new HashMap<String, Cache>();

	public static void createCache(String name, Cache cache) {
		manager.put(name, cache);
	}

	public static void removeCache(String name) {
		manager.remove(name);
	}

	public static Object get(String name, String key) {
		Cache cache = manager.get(name);
		if (cache == null) {
			throw new NullPointerException("can not found cache name is "
					+ name);
		}
		return cache.get(key);
	}

	public static void put(String name, String key, Object obj) {
		Cache cache = manager.get(name);
		if (cache == null) {
			throw new NullPointerException("can not found cache name is "
					+ name);
		}
		cache.put(key, obj);
	}

}
