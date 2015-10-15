package com.tuniu.jvm;

public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	public void isAlive(){
		System.out.println("yes,I am still alive!");
	}
	
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("finalize method executed!");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws Throwable{
		SAVE_HOOK = new FinalizeEscapeGC();
		
		//�����һ�γɹ������Լ�
		SAVE_HOOK = null;
		System.gc();
		//��ΪFinalizer�������ȼ��ܵͣ���ͣ0.5���Եȴ���
		Thread.sleep(500);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,i am dead!");
		}
		
		//������δ������������ȫ��ͬ����������Ծ�ȴʧ����
		SAVE_HOOK = null;
		System.gc();
		//��ΪFinalizer�������ȼ��ܵͣ���ͣ0.5���Եȴ���
		Thread.sleep(500);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,i am dead!");
		}
	}
	
//	[GC [PSYoungGen: 657K->336K(19136K)] 657K->336K(62848K), 0.0009389 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
//			[Full GC (System) [PSYoungGen: 336K->0K(19136K)] [PSOldGen: 0K->209K(43712K)] 336K->209K(62848K) [PSPermGen: 2977K->2977K(21248K)], 0.0056564 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
//			finalize method executed!
//			yes,I am still alive!
//			[GC [PSYoungGen: 329K->0K(19136K)] 538K->209K(62848K), 0.0008080 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
//			[Full GC (System) [PSYoungGen: 0K->0K(19136K)] [PSOldGen: 209K->209K(43712K)] 209K->209K(62848K) [PSPermGen: 2986K->2986K(21248K)], 0.0073832 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
//			no,i am dead!
//	ͬ������ִ�н����һ�����ѳɹ���һ������ʧ�ܣ�������Ϊ�κ�һ������ֻ�ᱻϵͳ�Զ�����һ�Σ��������������һ�λ��գ�����finalize()���������ٴ�ִ�С�
//	finalize()���������й�����ʹ��try-finally��������ʽ���������ø��á�����ʱ�����Կ�����ȫ����java�����л�����������Ĵ��ڡ�
}
