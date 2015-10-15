package com.tuniu.jvm;

public class ReferenceCountingGc {

	public Object instance = null;
	
	private static final int _1MB = 1024*1024;
	
	/**
	 * �����Ա���Ե�Ψһ�������ռ���ڴ棬�Ա�����GC��־�п�����Ƿ񱻻��չ�
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
//	 4754K->304K(62848K)��˵�����������ͨ�����ü����㷨���ж϶����Ƿ��
}
