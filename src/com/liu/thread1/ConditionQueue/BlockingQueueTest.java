package com.liu.thread1.ConditionQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 /** 
 * @ClassName: BlockingQueueTest 
 * @author: lyd
 * @date: 2017��11��12�� ����6:00:27 
 * @describe:����Condition��ʵ���������С���ʵ��java�У�
 * �и���ArrayBlockingQueue<E>�����ṩ���������еĹ��ܣ�
 * �������������Ҫʹ���������У���ȫû�б�Ҫ�Լ�ȥд�� 
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
	                            System.out.println(Thread.currentThread().getName() + "׼��������"
	                                    + (queue.size() == 3?"..�������������ڵȴ�":"..."));
	                            queue.put(1);
	                            System.out.println(Thread.currentThread().getName() + "�������ݣ�" 
	                                    + "����Ŀǰ��" + queue.size() + "������");
						}catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}).start();
			 new Thread() { //����һ���̲߳�ͣ�Ĵӻ�����ȡ����

		            @Override
		            public void run() {
		                while(true) {
		                    try {
		                        Thread.sleep(1000);
		                        System.out.println(Thread.currentThread().getName() + "׼��ȡ����"
		                                + (queue.size() == 0?"..�����ѿգ����ڵȴ�":"..."));
		                        queue.take();
		                        System.out.println(Thread.currentThread().getName() + "ȡ�����ݣ�" 
		                                + "����Ŀǰ��" + queue.size() + "������");
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
