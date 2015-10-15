package com.tuniu.jvm;

public class ReferenceCountingGc {

	public Object instance = null;
	
	private static final int _1MB = 1024*1024;
	
	/**
	 * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过
	 */
	private byte[] bigSize = new byte[2 * _1MB];
	
	public static void testGC(){
		ReferenceCountingGc objA = new ReferenceCountingGc();
		ReferenceCountingGc objB = new ReferenceCountingGc();
		objA.instance = objB;
		objB.instance = objA;
		objA = null;
		objB = null;
		System.gc();
	}
	
	public static void main(String[] args){
		testGC();
	}
	
//	[GC [PSYoungGen: 4754K->304K(19136K)] 4754K->304K(62848K), 0.0102194 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
//	 4754K->304K(62848K)，说明虚拟机不是通过引用计数算法来判断对象是否存活。
}
