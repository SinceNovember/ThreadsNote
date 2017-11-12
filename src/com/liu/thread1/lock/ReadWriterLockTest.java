package com.liu.thread1.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

 /** 
 * @ClassName: ReadWriterLockTest 
 * @author: lyd
 * @date: 2017年11月11日 下午9:48:49 
 * @describe:读写锁
 * 
 * 　锁又分为读锁和写锁，读锁与读锁不互斥，读锁与写锁互斥，写锁与写锁互斥，
 * 这是由jvm自己控制的。这很好理解，读嘛，大家都能读，不会对数据造成修改，
 * 只要涉及到写，那就可能出问题。 我们写代码的时候只要在正确的位置上相应的锁即可。
 * 读写锁有个接口叫ReadWriteLock，我们可以创建具体的读写锁实例，
 * 通过读写锁也可以拿到读锁和写锁。下面看一下读写锁的例子：
 */
public class ReadWriterLockTest {
	public static void main(String[] args) {
		final Query3 query3=new Query3();
		for(int i=0;i<3;i++){//三个线程写
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						query3.put(new Random().nextInt(10000));;
					}
					
				}
			}).start();
			new Thread(new Runnable() {//一个线程读
				
				@Override
				public void run() {
				while(true)
					query3.get();
				}
			}).start();
		}
		
	}
	static class Query3{
		private Object data=null;
		private ReadWriteLock rwl=new ReentrantReadWriteLock();
		public void get(){
		rwl.readLock().lock();
		 try {
	            System.out.println(Thread.currentThread().getName() 
	                    + ":before read: " + data); // 读之前打印数据显示

	            Thread.sleep((long) (Math.random() * 1000)); // 睡一会儿~

	            System.out.println(Thread.currentThread().getName() 
	                    + ":after read: " + data); // 读之后打印数据显示
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            rwl.readLock().unlock();// 释放读锁
	        }
		}
		public void put(Object data){
			rwl.writeLock().lock();
			try {
				 System.out.println(Thread.currentThread().getName() 
		                    + ":before write: " + this.data); // 读之前打印数据显示

		            Thread.sleep((long) (Math.random() * 1000)); // 睡一会儿~
		            this.data = data; //写数据

		            System.out.println(Thread.currentThread().getName() 
		                    + ":after write: " + this.data); // 读之后打印数据显示
			} catch (Exception e) {
			e.printStackTrace();
			}
			finally {
				rwl.writeLock().unlock();
			}
		}
	}
}
