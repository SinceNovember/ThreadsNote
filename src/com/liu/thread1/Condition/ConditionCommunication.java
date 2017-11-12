package com.liu.thread1.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 /** 
 * @ClassName: ConditionCommunication 
 * @author: lyd
 * @date: 2017��11��12�� ����4:53:29 
 * @describe:����Condition�����������wait��notify�ȷ�����
 * ���Կ��ԶԱ���֮ǰд�����̼߳�ͨ�ŵĴ���������������һ��ԭ���Ǹ����⣺
 * �������̣߳����߳���ִ��10�Σ�Ȼ�����߳�ִ��5�Σ�Ȼ�����л������߳�ִ��10�������߳�ִ��5�Ρ����������ִ��50�Ρ�
 */
public class ConditionCommunication {
		public static void main(String[] args) {
			Business business=new Business();
			new Thread(new Runnable() {//����һ�����߳�
				
				@Override
				public void run() {
					for (int i = 1; i <= 50; i++) {//50��subѭ��

                        business.sub(i);
                    }
					
				}
			}).start();
			for (int i = 1; i <= 50; i++) {//���̣߳�ִ��50��main1����

		          business.main1(i);
		        }
		}
		  
}
class Business{
	Lock lock=new ReentrantLock();//������
	Condition condition=lock.newCondition();//����״̬��
	private boolean bShouldSub=true;//��־
	public void sub(int i){
		lock.lock();//����
		try
		{
			while(!bShouldSub){//���Ϊ�٣���˯���������Σ����߳�˯��
				try
				{
					condition.await();//˯��
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			//������ǣ�������������ѭ��
			for(int j=1;j<=10;j++){
				 System.out.println("sub thread sequence of " + j
	                        + ", loop of " + i);
			}
			bShouldSub=false;//����һ������������Լ��´�˯��
			condition.signal();		//��������˯���е��߳�
		}finally {
			lock.unlock();//����
		}
	}
	public void main1(int i){
		lock.lock();
		try
		{
			while(bShouldSub){
				try
				{
					condition.await();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			  for (int j = 1; j <= 10; j++) {
	                System.out.println("main thread sequence of " + j
	                        + ", loop of " + i);
	            }
			bShouldSub=true;
			condition.signal();		
		}finally {
			lock.unlock();
		}
	}
	
}
