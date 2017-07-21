package com.youmu.maven.utils;

import com.youmu.maven.utils.codec.model.DigestMethod;

import java.util.Comparator;

/**
 * Created by dehua.lai on 2017/6/19.
 */
public class EnumUtils {

	public static <E extends Enum,V> boolean contains(Class<? extends Enum> enumClass,V ele,EqualsComparator<E,V> comparator){
		try {
			Enum[] es = enumClass.getEnumConstants();
			for (int i = 0; i < es.length; i++) {
				Enum e = es[i];
				if(comparator.equalsTo((E)e,ele)){
					return true;
				}
			}
		}catch (NullPointerException e){
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(contains(TestE.class,"public",(e,s)->((TestE)e).getCode().equals(s)));
	}

	public static interface EqualsComparator<V1,V2>{

		public boolean equalsTo(V1 value1,V2 value2);

	}

	public static enum TestE{
		PUBLIC("public"),PRIVATE("private");

		String code;

		TestE(String str) {
			this.code=str;
		}

		public String getCode() {
			return code;
		}
	}
}
