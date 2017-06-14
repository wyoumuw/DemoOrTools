package com.youmu.maven.utils.template;

import java.util.concurrent.locks.Lock;

/**
 * Created by youmu on 2017/6/14.
 */
public abstract class TemplatedLocker {

	private Lock lock;

	public TemplatedLocker(Class<? extends Lock> lockClass){
		try {
			lock=lockClass.newInstance();
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public Object lock(Object... params){
		lock.lock();
		try{
			return template(params);
		}finally {
			lock.unlock();
		}
	}

	public abstract Object template(Object... params);

}
