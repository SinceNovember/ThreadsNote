package com.liu.thread1.synchronizedCommunication;

 /** 
 * @ClassName: communication2 
 * @author: lyd
 * @date: 2017年11月10日 上午11:04:43 
 * @describe:添加motify与wait
 */
public class communication3 {
	public static void main(String[] args) {
		 Business1 bussiness = new Business1(); //new一个线程任务处理类
	        //开启一个子线程
	        new Thread(new Runnable() {

	            @Override
	            public void run() {
	                for(int i = 1; i <= 50; i ++) {
	                    bussiness.sub(i);
	                }

	            }
	        }).start();

	        //main方法即主线程
	        for(int i = 1; i <= 50; i ++) {
	            bussiness.main1(i);
	        }
	    }
	}
class Business1{
	private boolean bShouldSub=true;
	public synchronized void sub(int i){
		while(!bShouldSub) //当为false时，就将此线程睡眠
		{
			try{
				this.wait(); //使当前线程睡眠
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		//正常运行后将sub为false唤醒宁以恶线程，大意为执行后将此线程睡眠一次
		for(int j=1;j<10;j++){
			System.out.println("sub thread sequence of " + j + ", loop of " + i);
			}
		bShouldSub=false;
		this.notify();//唤醒其他线程
		
	}
	public synchronized void main1(int i){
		while(bShouldSub)
		{
			try{
				this.wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		   }
		 for(int j = 1;j <= 5; j ++) {
	            System.out.println("main thread sequence of " + j + ", loop of " + i);
	        }
		 bShouldSub=true;
			this.notify();
	}
}
