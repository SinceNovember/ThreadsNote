package com.liu.thread1.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 /** 
 * @ClassName: LockTest 
 * @author: lyd
 * @date: 2017年11月11日 下午6:00:42 
 * @describe:Lock锁基本用法，
 */
public class LockTest {
	public static void main(String[] args) {
		new  LockTest().init();
	}
	private void init(){
		final Outputer outputer=new Outputer();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				 while (true) {
	                    try {
	                        Thread.sleep(5);
	                    } catch (InterruptedException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	                    outputer.output("duoxiancheng");
	                }
				
			}
		}).start();
new Thread(new Runnable() {
			
			@Override
			public void run() {
				 while (true) {
	                    try {
	                        Thread.sleep(5);
	                    } catch (InterruptedException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	                    outputer.output("eson15");
	                }
				
			}
		}).start();
	}
	static class Outputer{
		Lock lock=new ReentrantLock();//Lock是个接口，需要实例
		public void output(String name){
			int len=name.length();
			lock.lock();
			try{
				for(int i=0;i<len;i++)
				{
					System.out.print(name.charAt(i));
				}
				System.out.println("");
			}finally {
				lock.unlock();
			}
		}
	}
	
}
