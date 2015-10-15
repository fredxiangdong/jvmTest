package com.tuniu.jvm;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



/**
 * VM Args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author Administrator
 * 借助于CGLib直接操作字节码，生成了大量的动态类，使得方法区出现内存溢出异常
 */
public class JavaMethodAreaOOM {

	public static void main(String[] args){
		while(true){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor(){
				public Object intercept(Object obj,Method method,
						Object[] args,MethodProxy proxy) throws Throwable{
					return proxy.invokeSuper(obj,args);
				}
			});
			enhancer.create();
		}
	}
	
	static class OOMObject{}
	
//	Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
//	at java.lang.String.intern(Native Method)
//	at com.tuniu.jvm.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:19)
//	使用到CGLib这类字节码技术，增强的类越多，就需要越大的方法区来保证动态的生成的Class可以加载入内存
//	方法区溢出也是一种常见的内存溢出异常，一个类如果要被垃圾收集器回收掉，判定条件是非常苛刻的。
//	在经常动态生成大量Class的应用中，需要特别注意类的回收状况。

}
