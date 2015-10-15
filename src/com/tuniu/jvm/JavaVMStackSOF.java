package com.tuniu.jvm;

/**
 * VM Args:-Xss128K
 * @author Administrator
 * 创建线程导致内存溢出异常
 */
public class JavaVMStackSOF {

	private int stackLength = 1;
	
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) throws Throwable{
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try{
			oom.stackLeak();
		}catch(Throwable e){
			System.out.println("stack length:"+ oom.stackLength);
			throw e;
		}
	}
}
