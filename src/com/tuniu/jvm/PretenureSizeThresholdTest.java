package com.tuniu.jvm;

/**
 * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728
 * @author Administrator
 *
 */
public class PretenureSizeThresholdTest {
	private static final int _1MB = 1024*1024;
	
	public static void testAllocation(){
		byte[] allocation = new byte[4 * _1MB];//ֱ�ӷ������������
	}
	
	public static void main(String[] args){
		testAllocation();
	}
}
