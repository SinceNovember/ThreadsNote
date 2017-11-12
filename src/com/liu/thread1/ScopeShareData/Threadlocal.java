package com.liu.thread1.ScopeShareData;

import java.util.Random;

 /** 
 * @ClassName: Threadlocal 
 * @author: lyd
 * @date: 2017��11��10�� ����7:38:26 
 * @describe:ʹ��Threadlocalʹ�ù����������
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
                    threadLocal.set(data);//ֱ����threadLocal�������������ݼ���
                    new TestA().getData();
                    new TestB().getData();
					
				}
			}).start();
		}
	}
	static class TestA
	{
		  public void getData() {
	            System.out.println("A get data from " + Thread.currentThread().getName() + ": " + threadLocal.get());//ֱ��ȡ������ʲô�ؼ��֣���ֱ�Ӵӵ�ǰ�߳���ȡ
	        }
	}
	static class TestB
	{
		  public void getData() {
			    System.out.println("B get data from " + Thread.currentThread().getName() + ": " + threadLocal.get());//ֱ��ȡ������ʲô�ؼ��֣���ֱ�Ӵӵ�ǰ�߳���ȡ
	        }
	}
}
