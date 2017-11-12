package com.liu.thread1.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

 /** 
 * @ClassName: cacheLock 
 * @author: lyd
 * @date: 2017年11月11日 下午10:19:15 
 * @describe:缓存锁
 * 　现在使用读写锁写一个稍微高级一点的应用demo，即模拟缓存数据。实现的功能如下：
 * 现在有5个线程都需要拿数据，一开始是没有数据的，
 * 所以最先去拿数据的那个线程发现没数据，它就得去初始化一个数据，
 * 然后其他线程拿数据的时候就可以直接拿了。代码如下：
 */
public class cacheLock {
	public static void main(String[] args) {
	CacheData cacheData=new CacheData();
	for(int i=1;i<5;i++)//开启5个线程
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				cacheData.processCache();//都去拿数据
				
			}
		}).start();
	}
	}
	static class CacheData{
		private Object data=null;// 需要缓存的数据
		private boolean cacheValid;//用来标记是否有缓存数据
		private ReadWriteLock writeLock=new ReentrantReadWriteLock();// 定义读写锁

		public void processCache(){
			writeLock.readLock().lock();;//上读锁
			if(!cacheValid)//如果没有缓存，那说明是第一次访问，需要给data赋个值
			{
				writeLock.readLock().unlock(); //先把读锁释放掉
				writeLock.writeLock().lock();//上写锁
				 if(!cacheValid) {
		                System.out.println(Thread.currentThread().getName() + ": no cache!");
		                data = new Random().nextInt(1000); //赋值
		                cacheValid = true; //标记已经有缓存了
		                System.out.println(Thread.currentThread().getName() + ": already cached!");
		            }
				 writeLock.readLock().lock();
				 writeLock.writeLock().unlock();
			}
			  System.out.println(Thread.currentThread().getName() + " get data: " + data);
		      writeLock.readLock().unlock(); //释放读锁
		}
	}
}
