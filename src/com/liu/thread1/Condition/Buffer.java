package com.liu.thread1.Condition;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 /** 
 * @ClassName: Buffer 
 * @author: lyd
 * @date: 2017��11��12�� ����5:42:18 
 * @describe:���ڰ����ʵ�ʵ��������һ�£���������һ�����飬
 * ���ǿ�����������д�����ݣ�Ҳ���Դ������а�����ȡ�ߣ���Ҫ���������¾��ǿ��������̣߳�һ�������ݣ�
 * һ��ȡ���ݡ������������ˣ�������������ˣ�˵�����յ���Ϣ̫���ˣ������͹�������Ϣ̫���ˣ�����һ���̻߳�����������
 * ���������ڻ�����û�ط����ˣ���ô��ʱ�͵���������������̣߳�����ȴ����෴�������ת����̫�죬
 * ���ڻ������������ݶ����ҷ����ˣ���û���û����µ���Ϣ������ô��ʱ�͵�����ȡ��������̡߳� 
�������ˣ���������������������������У��������Condition������ʵ��һ�£�
 */
public class Buffer {
	   final Lock lock = new ReentrantLock(); //����һ����
	    final Condition notFull = lock.newCondition(); //���������������˵�Condition
	    final Condition notEmpty = lock.newCondition();//�����������п��˵�Condition

	    final Object[] items = new Object[10]; //Ϊ������ģ�⣬�����������еĴ�СΪ10����Ҫ��̫��

	    int putptr, takeptr, count; //�����±꣬�����궨λ�õ�

	    //�������д�����
	    public void put(Object x) throws InterruptedException {
	        lock.lock(); //����
	        try {
	            while (count == items.length) {
	                System.out.println(Thread.currentThread().getName() + " �������ˣ���ʱ�޷������ݣ�");
	                notFull.await();    //����������ˣ���ô��������������̣߳��ȴ�������
	            }
	            //���û������˳���������д�
	            items[putptr] = x;
	            if (++putptr == items.length) //���ǵ�������ĩ�˵��жϣ�������ˣ��ٻص�ʼ��
	                putptr = 0;
	            ++count;    //��Ϣ����
	            System.out.println(Thread.currentThread().getName() + " �����ֵ�� " + x);
	            notEmpty.signal(); //���ˣ����ڶ������������ˣ����Ѷ��пյ��Ǹ��̣߳�����ȡ������
	        } finally {
	            lock.unlock(); //����
	        }
	    }

	    //�Ӷ�����ȡ����
	    public Object take() throws InterruptedException {
	        lock.lock(); //����
	        try {
	            while (count == 0) {
	                System.out.println(Thread.currentThread().getName() + " �������ˣ���ʱ�޷�ȡ���ݣ�");
	                notEmpty.await();  //��������ǿգ���ô����ȡ��������̣߳��ȴ�������
	            }
	            //���û�գ���˳���������ȡ
	            Object x = items[takeptr];
	            if (++takeptr == items.length) //�ж��Ƿ񵽴�ĩ�ˣ�������ˣ��ٻص�ʼ��
	                takeptr = 0;
	            --count; //��Ϣ����
	            System.out.println(Thread.currentThread().getName() + " ȡ����ֵ�� " + x);
	            notFull.signal(); //���ˣ����ڶ�������λ���ˣ����Ѷ��������Ǹ��̣߳����Դ�������
	            return x;
	        } finally {
	            lock.unlock(); //����
	        }
	    }
	public static void main(String[] args) {
		Buffer buffer=new Buffer();
		  for(int i = 0; i < 5; i ++) { //����5���߳���������������
	            new Thread(new Runnable() {

	                @Override
	                public void run() {
	                    try {
	                        buffer.put(new Random().nextInt(1000)); //���������
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }).start();
	        }
		    for(int i = 0; i < 10; i ++) { //����10���̴߳ӻ�������ȡ����
	            new Thread(new Runnable() {

	                @Override
	                public void run() {
	                    try {
	                        buffer.take(); //�ӻ�����ȡ����
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }).start();
	        }
	    
	}
}
