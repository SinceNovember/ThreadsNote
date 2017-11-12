package com.liu.thread1;

 /** 
 * @ClassName: yieldThread 
 * @author: lyd
 * @date: 2017年11月7日 下午9:59:12 
 * @describe:
 * 线程让步
 * 暂停线程,将cpu让步给其他运行
 */
class yieldThread1 extends Thread{
	private String name;
	public yieldThread1(String name){
		this.name=name;
	}
//	@SuppressWarnings("static-access")
	public void run(){
		  for (int i = 1; i <= 50; i++) {  
	            System.out.println("" + this.getName() + "-----" + i);  
//	             当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）  
	            if (i ==30) {  
	                this.yield();  
	            }        
		  }
	}
	
}
public class yieldThread
{
	public static void main(String[] args) {
		yieldThread1 yt1=new yieldThread1("A");
		yieldThread1 yt2=new yieldThread1("B");
//		yt1.setPriority(Thread.MAX_PRIORITY);//设置线程的优先级
		yt1.start();
		yt2.start();
	}
}
