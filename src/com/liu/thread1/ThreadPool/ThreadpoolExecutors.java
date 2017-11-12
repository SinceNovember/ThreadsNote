package com.liu.thread1.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 /** 
 * @ClassName: ThreadpoolExecutors 
 * @author: lyd
 * @date: 2017��11��10�� ����9:20:13 
 * @describe:�̳߳�
 * 
 * 
 */
public class ThreadpoolExecutors {
	public static void main(String[] args) throws InterruptedException {
//		ExecutorService threadPool=Executors.newFixedThreadPool(3);//��������3���̵߳��̳߳�
		ExecutorService threadPool=Executors.newCachedThreadPool();//�̻߳���أ������˼��������ʹ�ü����߳�
		for(int i=1;i<=10;i++){//10������ѭ�����̳߳��з�����
			final int task=i;
			threadPool.execute(new Runnable() {//��һ����������̳߳أ����1-3Ȼ��ѭ������һ���߳�ִ��i=1,�ڶ���ִ��i=2������ִ��i=3,һ��ִ�У�ִ�����ѭ��ִ��
				
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
	        threadPool.shutdown(); //ִ���������ر�
	}
}
