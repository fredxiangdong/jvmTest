package com.tuniu.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:+PrintGCDetails
 * VM Args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author Administrator
 *
 */
public class RuntimeConstantPoolOOM {
	
	public static void main(String[] args){
		//使用List保持着常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		//10M 的PermSize在integer范围内足够产生OOM了
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
	
/*	Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
	at java.lang.String.intern(Native Method)
	at com.tuniu.jvm.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:19)
	
	运行时，常量池溢出，在OutofMemoryError后面跟随的信息是“PermGen space”，说明运行时常量池属于方法区
	（HotSpot 虚拟机中的永生代）的一部分
	*/
}
