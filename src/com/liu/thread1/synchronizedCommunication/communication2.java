package com.liu.thread1.synchronizedCommunication;

 /** 
 * @ClassName: communication2 
 * @author: lyd
 * @date: 2017年11月10日 上午11:04:43 
 * @describe:改进后
 */
public class communication2 {
	public static void main(String[] args) {
		 Business bussiness = new Business(); //new一个线程任务处理类
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
	            bussiness.main(i);
	        }
	    }
	}
class Business{
	public synchronized void sub(int i){
		for(int j=1;j<10;j++){
			System.out.println("sub thread sequence of " + j + ", loop of " + i);
		}
	}
	public synchronized void main(int i){
		 for(int j = 1;j <= 5; j ++) {
	            System.out.println("main thread sequence of " + j + ", loop of " + i);
	        }
	}
}
