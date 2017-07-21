package com.youmu.maven.utils.builder;

/**
 * Created by dehua.lai on 2017/6/16.
 */
public interface Builder<V> {
	/**
	 * build a new V for return,cost time more than getRaw but can be reused
	 * @return
	 */
	public V build();

	/**
	 * get the inner V ,faster than build but cannot be reused
	 * @return
	 */
	public V getRaw();
}
