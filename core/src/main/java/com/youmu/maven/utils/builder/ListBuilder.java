package com.youmu.maven.utils.builder;

import com.youmu.maven.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dehua.lai on 2017/6/16.
 */
public class ListBuilder<V> implements Builder<List<V>>{
	private List<V> list;

	public ListBuilder(){
		list=new ArrayList<>();
	}

	public ListBuilder<V> add(V value){
		list.add(value);
		return this;
	}

	public List<V> build(Class<? extends List> listClass) {
		List<V> list=null;
		try {
			list= listClass.newInstance();
			ArrayUtils.copy(this.list,list);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
		return list;
	}

	/**
	 *
	 * @return ArrayList
	 */
	@Override
	public List<V> build() {
		return build(ArrayList.class);
	}

	@Override
	public List<V> getRaw() {
		return list;
	}
}
