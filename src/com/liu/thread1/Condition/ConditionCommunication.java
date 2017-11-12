package com.liu.thread1.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 /** 
 * @ClassName: ConditionCommunication 
 * @author: lyd
 * @date: 2017年11月12日 下午4:53:29 
 * @describe:由于Condition可以用来替代wait、notify等方法，
 * 所以可以对比着之前写过的线程间通信的代码来看，再来看一下原来那个问题：
 * 有两个线程，子线程先执行10次，然后主线程执行5次，然后再切换到子线程执行10，再主线程执行5次……如此往返执行50次。
 */
public class ConditionCommunication {
		public static void main(String[] args) {
			Business business=new Business();
			new Thread(new Runnable() {//开启一个子线程
				
				@Override
				public void run() {
					for (int i = 1; i <= 50; i++) {//50次sub循环

                        business.sub(i);
                    }
					
				}
			}).start();
			for (int i = 1; i <= 50; i++) {//主线程，执行50次main1方法

		          business.main1(i);
		        }
		}
		  
}
class Business{
	Lock lock=new ReentrantLock();//声明锁
	Condition condition=lock.newCondition();//声明状态，
	private boolean bShouldSub=true;//标志
	public void sub(int i){
		lock.lock();//上锁
		try
		{
			while(!bShouldSub){//如果为假，则睡眠这个程序段，即线程睡眠
				try
				{
					condition.await();//睡眠
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			//如果不是，则运行完下面循环
			for(int j=1;j<=10;j++){
				 System.out.println("sub thread sequence of " + j
	                        + ", loop of " + i);
			}
			bShouldSub=false;//给另一个进行输出，自己下次睡眠
			condition.signal();		//唤醒其他睡眠中的线程
		}finally {
			lock.unlock();//解锁
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
