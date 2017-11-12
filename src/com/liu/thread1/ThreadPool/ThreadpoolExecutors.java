package com.liu.thread1.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 /** 
 * @ClassName: ThreadpoolExecutors 
 * @author: lyd
 * @date: 2017年11月10日 下午9:20:13 
 * @describe:线程池
 * 
 * 
 */
public class ThreadpoolExecutors {
	public static void main(String[] args) throws InterruptedException {
//		ExecutorService threadPool=Executors.newFixedThreadPool(3);//创建具有3个线程的线程池
		ExecutorService threadPool=Executors.newCachedThreadPool();//线程缓冲池，即来了几个任务就使用几个线程
		for(int i=1;i<=10;i++){//10个任务，循环像线程池中方任务
			final int task=i;
			threadPool.execute(new Runnable() {//放一个任务进入线程池，会从1-3然后循环，第一个线程执行i=1,第二个执行i=2第三个执行i=3,一起执行，执行完后循环执行
				
				@Override
				public void run() {
					for(int j=1;j<5;j++)
					{
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()
                                + " looping of " + j + " for task of " + task);
					}
						
					
				}
			});
		}
		Thread.sleep(1000);
		 System.out.println("all of 10 tasks have committed!");
	        threadPool.shutdown(); //执行完任务后关闭
	}
}
