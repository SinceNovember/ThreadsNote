package com.liu.thread1.synchronizedCommunication;

 /** 
 * @ClassName: communication1 
 * @author: lyd
 * @date: 2017年11月10日 上午11:00:37 
 * @describe:同步交互问题
 * 
 * 有两个线程，子线程先执行10次，然后主线程执行5次，然后再切换到子线程执行10，再主线程执行5次……如此往返执行50次。
 */
public class communication1 {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<10;i++){
					synchronized(communication1.class)
					{
						for(int j=1;j<10;j++){
							 System.out.println("sub thread sequence of " + j + ", loop of " + i);
						}
					}
				}
				
			}
		}).start();;
		  for(int i = 1; i <= 10; i ++) {

	            synchronized (communication1.class) {
	                //主线程任务：执行5次
	                for(int j = 1;j <= 5; j ++) {
	                    System.out.println("main thread sequence of " + j + ", loop of " + i);
	                }   
	            }   
		  }
	}
}
