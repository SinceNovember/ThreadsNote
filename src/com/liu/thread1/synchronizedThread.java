package com.liu.thread1;


 /** 
 * @ClassName: synchronizedThread 
 * @author: lyd
 * @date: 2017年11月9日 下午8:46:59 
 * @describe:
 * 传统线程互斥技术—synchronized
 * 同步的时候，需要用锁必须是同一个对象
 * 同步代码块的锁是任意对象。只要不同的线程都执行同一个同步代码块的时候，这个锁随便设。
 * 同步函数的锁是固定的this。当需要和同步函数中的逻辑实行同步的时候，代码块中的锁必须为this。 
 * 静态同步函数的锁是该函数所属类的字节码文件对象。该对象可以用this.getClass()方法获取，也可以使用 当前类名.class 表示。
 */
public class synchronizedThread {
	public static void main(String[] args) {
		new synchronizedThread().init();
	}
	private void init(){
		final Outputer outputer=new Outputer();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try{
						Thread.sleep(5);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output1("liuyudong");
				}
				
			}
		}).start();
new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try{
						Thread.sleep(5);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output2("sincenoverm");
				}
				
			}
		}).start();
	}

	 	 /** 
	 	 * @ClassName: Outputer 
	 	 * @author: lyd
	 	 * @date: 2017年11月9日 下午8:40:20 
	 	 * @describe:第一种同步，线程调用的是同一个Outputer,可以直接加一个随便对象即可，35行变为Output1
	 	 */
//	 	class Outputer{
//			private Object token="";
//			public void output1(String name){
//				synchronized(token){		//加锁，防止多个线程同时对其操作，防止修改混乱
//				int len=name.length();
//				  for(int i = 0; i < len; i++) {
//		                System.out.print(name.charAt(i));
//		            }
//		            System.out.println("");   
//				}
//			}
//	 }
 	 /** 
 	 * @ClassName: Outputer 
 	 * @author: lyd
 	 * @date: 2017年11月9日 下午8:42:17 
 	 * @describe:第二种,调用俩个不同的方法的时候。
 	 */
// 	class Outputer{
//		public void output1(String name){
//			synchronized(this){		//加锁，防止多个线程同时对其操作，防止修改混乱
//			int len=name.length();
//			  for(int i = 0; i < len; i++) {
//	                System.out.print(name.charAt(i));
//	            }
//	            System.out.println("");   
//			}
//		}
//
//			public synchronized void output2(String name){
//				int len=name.length();
//				  for(int i = 0; i < len; i++) {
//		                System.out.print(name.charAt(i));
//		            }
//		            System.out.println("");   
//				}
//			}	
	///
	  /** 
	 * @ClassName: Outputer 
	 * @author: lyd
	 * @date: 2017年11月9日 下午8:47:08 
	 * @describe:第三种，静态的等于了使用类名.class
	 */
	static class Outputer{
		public static synchronized void output1(String name){
			int len=name.length();
			  for(int i = 0; i < len; i++) {
	                System.out.print(name.charAt(i));
	            }
	            System.out.println("");   
			}
		public  void output2(String name){
			synchronized(Outputer.class){		//加锁，防止多个线程同时对其操作，防止修改混乱
			int len=name.length();
			  for(int i = 0; i < len; i++) {
	                System.out.print(name.charAt(i));
	            }
	            System.out.println("");   
			}
		}	
	 }
}
