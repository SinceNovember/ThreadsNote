package com.liu.thread1.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

 /** 
 * @ClassName: ReadWriterLockTest 
 * @author: lyd
 * @date: 2017��11��11�� ����9:48:49 
 * @describe:��д��
 * 
 * �����ַ�Ϊ������д������������������⣬������д�����⣬д����д�����⣬
 * ������jvm�Լ����Ƶġ���ܺ���⣬�����Ҷ��ܶ����������������޸ģ�
 * ֻҪ�漰��д���ǾͿ��ܳ����⡣ ����д�����ʱ��ֻҪ����ȷ��λ������Ӧ�������ɡ�
 * ��д���и��ӿڽ�ReadWriteLock�����ǿ��Դ�������Ķ�д��ʵ����
 * ͨ����д��Ҳ�����õ�������д�������濴һ�¶�д�������ӣ�
 */
public class ReadWriterLockTest {
	public static void main(String[] args) {
		final Query3 query3=new Query3();
		for(int i=0;i<3;i++){//�����߳�д
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						query3.put(new Random().nextInt(10000));;
					}
					
				}
			}).start();
			new Thread(new Runnable() {//һ���̶߳�
				
				@Override
				public void run() {
				while(true)
					query3.get();
				}
			}).start();
		}
		
	}
	static class Query3{
		private Object data=null;
		private ReadWriteLock rwl=new ReentrantReadWriteLock();
		public void get(){
		rwl.readLock().lock();
		 try {
	            System.out.println(Thread.currentThread().getName() 
	                    + ":before read: " + data); // ��֮ǰ��ӡ������ʾ

	            Thread.sleep((long) (Math.random() * 1000)); // ˯һ���~

	            System.out.println(Thread.currentThread().getName() 
	                    + ":after read: " + data); // ��֮���ӡ������ʾ
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            rwl.readLock().unlock();// �ͷŶ���
	        }
		}
		public void put(Object data){
			rwl.writeLock().lock();
			try {
				 System.out.println(Thread.currentThread().getName() 
		                    + ":before write: " + this.data); // ��֮ǰ��ӡ������ʾ

		            Thread.sleep((long) (Math.random() * 1000)); // ˯һ���~
		            this.data = data; //д����

		            System.out.println(Thread.currentThread().getName() 
		                    + ":after write: " + this.data); // ��֮���ӡ������ʾ
			} catch (Exception e) {
			e.printStackTrace();
			}
			finally {
				rwl.writeLock().unlock();
			}
		}
	}
}
