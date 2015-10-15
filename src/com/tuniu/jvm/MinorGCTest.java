package com.tuniu.jvm;

/**
 * 
 * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @author Administrator
 *
 */
public class MinorGCTest {

	private static final int _1MB = 1024*1024;
	
	public static void testAllocation(){
		byte[] allocation1 = new byte[2 * _1MB];
		byte[] allocation2 = new byte[2 * _1MB];
		byte[] allocation3 = new byte[2 * _1MB];
		byte[] allocation4 = new byte[4 * _1MB];//出现一次Minor GC
	}
	
	public static void main(String[] args){
		testAllocation();
	}
	
/*	Heap
	 PSYoungGen      total 9216K, used 6839K [0x0000000019ec0000, 0x000000001a8c0000, 0x000000001a8c0000)
	  eden space 8192K, 83% used [0x0000000019ec0000,0x000000001a56ded8,0x000000001a6c0000)
	  from space 1024K, 0% used [0x000000001a7c0000,0x000000001a7c0000,0x000000001a8c0000)
	  to   space 1024K, 0% used [0x000000001a6c0000,0x000000001a6c0000,0x000000001a7c0000)
	 PSOldGen        total 55296K, used 4096K [0x000000000a8c0000, 0x000000000dec0000, 0x0000000019ec0000)
	  object space 55296K, 7% used [0x000000000a8c0000,0x000000000acc0018,0x000000000dec0000)
	 PSPermGen       total 21248K, used 3008K [0x00000000054c0000, 0x0000000006980000, 0x000000000a8c0000)
	  object space 21248K, 14% used [0x00000000054c0000,0x00000000057b0010,0x0000000006980000)*/

}


