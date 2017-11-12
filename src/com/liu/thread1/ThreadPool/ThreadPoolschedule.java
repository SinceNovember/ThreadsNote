package com.liu.thread1.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

 /** 
 * @ClassName: ThreadPoolschedule 
 * @author: lyd
 * @date: 2017��11��10�� ����10:05:58 
 * @describe:���ж�ʱ�����̳߳�
 */
public class ThreadPoolschedule {
	public static void main(String[] args) {
		ScheduledExecutorService threadPool=Executors.newScheduledThreadPool(3);
		for(int i=1;i<5;i++){
			threadPool.schedule(new Runnable() {
				
				@Override
				public void run() {
					try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " bombing");
                }
			}, 2, TimeUnit.SECONDS);//�����ִ��
		}
		
		/////��ʱ�����û2������ظ�����
//		 Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
//
//	            @Override
//	            public void run() {
//	                System.out.println(Thread.currentThread().getName() + " bombing");
//	            }
//	        }, 5, 2, TimeUnit.SECONDS);   
	}
}
