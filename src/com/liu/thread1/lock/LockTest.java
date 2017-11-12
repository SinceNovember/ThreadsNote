package com.liu.thread1.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 /** 
 * @ClassName: LockTest 
 * @author: lyd
 * @date: 2017��11��11�� ����6:00:42 
 * @describe:Lock�������÷���
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
		Lock lock=new ReentrantLock();//Lock�Ǹ��ӿڣ���Ҫʵ��
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
