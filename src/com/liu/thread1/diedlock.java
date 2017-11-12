package com.liu.thread1;

 /** 
 * @ClassName: diedlock 
 * @author: lyd
 * @date: 2017年11月9日 下午9:28:42 
 * @describe:线程技术之死锁问题
 */
public class diedlock {
	public static void main(String[] args) {
		Business business=new Business();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					business.functionA();
				}
				
			}
		}).start();;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					business.functionB();
				}
				
			}
		}).start();;
	}
}
	 class Business{
		 
		public static final Object lock_a=new Object();
		public static final Object lock_b=new Object();
		
		public void functionA(){
			synchronized(lock_a){
				 System.out.println("---ThreadA---lock_a---");
		            synchronized(lock_b) {
		                System.out.println("---ThreadA---lock_b---");
		            }
			}
		}
		public void functionB(){
			synchronized(lock_b){
				 System.out.println("---ThreadB---lock_b---");
		            synchronized(lock_a) {
		                System.out.println("---ThreadB---lock_a---");
		            }
			}
		}
	}

