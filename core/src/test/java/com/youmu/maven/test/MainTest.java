package com.youmu.maven.test;

import com.youmu.maven.utils.template.TemplatedLocker;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by youmu on 2017/6/14.
 */
public class MainTest {

	@Test
	public void test() throws Exception{
		TemplatedLocker lock=new TemplatedLocker(ReentrantLock.class) {
			@Override
			public Object template(Object... params) {
				System.out.println("locked");
				return null;
			}
		};
		lock.lock();
	}
}
