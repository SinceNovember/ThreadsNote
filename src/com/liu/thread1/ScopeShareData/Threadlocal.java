package com.liu.thread1.ScopeShareData;

import java.util.Random;

 /** 
 * @ClassName: Threadlocal 
 * @author: lyd
 * @date: 2017年11月10日 下午7:38:26 
 * @describe:使用Threadlocal使用共享操作问题
 */
public class Threadlocal {
	private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>();
	public static void main(String[] args) {
		for(int i=0;i<2;i++)
		{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put a data: " + data);
                    threadLocal.set(data);//直接往threadLocal里面里面扔数据即可
                    new TestA().getData();
                    new TestB().getData();
					
				}
			}).start();
		}
	}
	static class TestA
	{
		  public void getData() {
	            System.out.println("A get data from " + Thread.currentThread().getName() + ": " + threadLocal.get());//直接取，不用什么关键字，它直接从当前线程中取
	        }
	}
	static class TestB
	{
		  public void getData() {
			    System.out.println("B get data from " + Thread.currentThread().getName() + ": " + threadLocal.get());//直接取，不用什么关键字，它直接从当前线程中取
	        }
	}
}
