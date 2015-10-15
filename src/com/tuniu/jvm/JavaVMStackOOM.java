package com.tuniu.jvm;

/**
 * VM Args: -Xss2M
 * @author Administrator
 * 虚拟机栈和本地方法栈OOM测试
 */
public class JavaVMStackOOM {

	private void dontStop(){
		while(true){
		}
	}
	
	public void stackLeakByThread(){
		while(true){
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run(){
					dontStop();
				}
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) throws Throwable{
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}
