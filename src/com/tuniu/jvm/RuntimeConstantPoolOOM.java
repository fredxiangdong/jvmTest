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
		//ʹ��List�����ų��������ã�����Full GC���ճ�������Ϊ
		List<String> list = new ArrayList<String>();
		//10M ��PermSize��integer��Χ���㹻����OOM��
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
	
/*	Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
	at java.lang.String.intern(Native Method)
	at com.tuniu.jvm.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:19)
	
	����ʱ���������������OutofMemoryError����������Ϣ�ǡ�PermGen space����˵������ʱ���������ڷ�����
	��HotSpot ������е�����������һ����
	*/
}
