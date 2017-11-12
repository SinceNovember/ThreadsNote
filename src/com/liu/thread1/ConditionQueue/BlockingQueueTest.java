package com.liu.thread1.ConditionQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 /** 
 * @ClassName: BlockingQueueTest 
 * @author: lyd
 * @date: 2017年11月12日 下午6:00:27 
 * @describe:利用Condition来实现阻塞队列。其实在java中，
 * 有个叫ArrayBlockingQueue<E>的类提供了阻塞队列的功能，
 * 所以我们如果需要使用阻塞队列，完全没有必要自己去写。 
 */
public class BlockingQueueTest {

	public static void main(String[] args) {
		final BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(3);
		for(int i=0;i<2;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						try{
							 Thread.sleep((long) (Math.random()*1000));
	                            System.out.println(Thread.currentThread().getName() + "准备放数据"
	                                    + (queue.size() == 3?"..队列已满，正在等待":"..."));
	                            queue.put(1);
	                            System.out.println(Thread.currentThread().getName() + "存入数据，" 
	                                    + "队列目前有" + queue.size() + "个数据");
						}catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}).start();
			 new Thread() { //开启一个线程不停的从缓冲区取数据

		            @Override
		            public void run() {
		                while(true) {
		                    try {
		                        Thread.sleep(1000);
		                        System.out.println(Thread.currentThread().getName() + "准备取数据"
		                                + (queue.size() == 0?"..队列已空，正在等待":"..."));
		                        queue.take();
		                        System.out.println(Thread.currentThread().getName() + "取出数据，" 
		                                + "队列目前有" + queue.size() + "个数据");
		                    } catch (InterruptedException e) {
		                        // TODO Auto-generated catch block
		                        e.printStackTrace();
		                    }
		                }
		            }
		        }.start();
		}
	}
}
