package com.youmu.maven;

import net.sf.ehcache.*;

public class EhCacheDemo {

	public static void main(String[] args) {
		 CacheManager cacheManager = new CacheManager(
				 EhCacheDemo.class.getResource("ehcache.xml"));
		 Cache cache = cacheManager.getCache("youmu");

		cache.put(new Element("key1", "values1"));
		cache.get("key1").setTimeToLive(6);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(cache.get("key1"));


	}
}
