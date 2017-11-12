package com.liu.thread1.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

 /** 
 * @ClassName: cacheLock 
 * @author: lyd
 * @date: 2017��11��11�� ����10:19:15 
 * @describe:������
 * ������ʹ�ö�д��дһ����΢�߼�һ���Ӧ��demo����ģ�⻺�����ݡ�ʵ�ֵĹ������£�
 * ������5���̶߳���Ҫ�����ݣ�һ��ʼ��û�����ݵģ�
 * ��������ȥ�����ݵ��Ǹ��̷߳���û���ݣ����͵�ȥ��ʼ��һ�����ݣ�
 * Ȼ�������߳������ݵ�ʱ��Ϳ���ֱ�����ˡ��������£�
 */
public class cacheLock {
	public static void main(String[] args) {
	CacheData cacheData=new CacheData();
	for(int i=1;i<5;i++)//����5���߳�
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				cacheData.processCache();//��ȥ������
				
			}
		}).start();
	}
	}
	static class CacheData{
		private Object data=null;// ��Ҫ���������
		private boolean cacheValid;//��������Ƿ��л�������
		private ReadWriteLock writeLock=new ReentrantReadWriteLock();// �����д��

		public void processCache(){
			writeLock.readLock().lock();;//�϶���
			if(!cacheValid)//���û�л��棬��˵���ǵ�һ�η��ʣ���Ҫ��data����ֵ
			{
				writeLock.readLock().unlock(); //�ȰѶ����ͷŵ�
				writeLock.writeLock().lock();//��д��
				 if(!cacheValid) {
		                System.out.println(Thread.currentThread().getName() + ": no cache!");
		                data = new Random().nextInt(1000); //��ֵ
		                cacheValid = true; //����Ѿ��л�����
		                System.out.println(Thread.currentThread().getName() + ": already cached!");
		            }
				 writeLock.readLock().lock();
				 writeLock.writeLock().unlock();
			}
			  System.out.println(Thread.currentThread().getName() + " get data: " + data);
		      writeLock.readLock().unlock(); //�ͷŶ���
		}
	}
}
