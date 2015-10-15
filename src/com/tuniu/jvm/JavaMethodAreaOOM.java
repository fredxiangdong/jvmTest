package com.tuniu.jvm;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



/**
 * VM Args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author Administrator
 * ������CGLibֱ�Ӳ����ֽ��룬�����˴����Ķ�̬�࣬ʹ�÷����������ڴ�����쳣
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
//	ʹ�õ�CGLib�����ֽ��뼼������ǿ����Խ�࣬����ҪԽ��ķ���������֤��̬�����ɵ�Class���Լ������ڴ�
//	���������Ҳ��һ�ֳ������ڴ�����쳣��һ�������Ҫ�������ռ������յ����ж������Ƿǳ����̵ġ�
//	�ھ�����̬���ɴ���Class��Ӧ���У���Ҫ�ر�ע����Ļ���״����

}
