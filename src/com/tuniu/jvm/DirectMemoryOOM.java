package com.tuniu.jvm;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * @author Administrator
 *
 */
public class DirectMemoryOOM {

	private static final int _1MB = 1024*1024;
	
	public static void main(String[] args) throws Exception{
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		while(true){
			unsafe.allocateMemory(_1MB);
		}
	}
	
//	Exception in thread "main" java.lang.OutOfMemoryError
//	at sun.misc.Unsafe.allocateMemory(Native Method)
//	at com.tuniu.jvm.DirectMemoryOOM.main(DirectMemoryOOM.java:21)
//	DirectMemory容量可通过 -XX:MaxDirectMemorySize指定，如果不指定，则默认与java堆最大值（-Xms）一样
	
}
