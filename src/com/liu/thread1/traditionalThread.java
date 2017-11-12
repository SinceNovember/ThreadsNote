package com.liu.thread1;

 /** 
 * @ClassName: traditionalThread 
 * @author: lyd
 * @date: 2017年11月7日 下午9:01:25 
 * @describe:多线程继承Thread
 */
public class traditionalThread extends Thread{
		private String name;
	public traditionalThread(String name){
		this.name=name;
	}
	
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(name+"运行:"+i);
		}
		try
		{
			sleep((int) Math.random() * 10);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		 System.out.println(Thread.currentThread().getName()+"主线程运行开始!");  
		traditionalThread thread1=new traditionalThread("A");
		traditionalThread thread2=new traditionalThread("B");
		thread1.start();
		System.out.println("1");
		thread2.start();
		 try {  
	            thread1.join();  //join 等待此线程结束，才会运行主程序接下来的程序
	            System.out.println("2");
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        try {  
	            thread2.join();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");  
	}
}
