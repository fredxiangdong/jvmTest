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
		
		//对象第一次成功拯救自己
		SAVE_HOOK = null;
		System.gc();
		//因为Finalizer方法优先级很低，暂停0.5秒以等待它
		Thread.sleep(500);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,i am dead!");
		}
		
		//下面这段代码与上面的完全相同，但是这次自救却失败了
		SAVE_HOOK = null;
		System.gc();
		//因为Finalizer方法优先级很低，暂停0.5秒以等待它
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
//	同样代码执行结果是一次逃脱成功，一次逃脱失败，这是因为任何一个对象都只会被系统自动调用一次，如果对象面临下一次回收，它的finalize()方法不会再次执行。
//	finalize()能做的所有工作，使用try-finally或其他方式都可以做得更好、更及时，所以可以完全忘掉java语言中还有这个方法的存在。
}
