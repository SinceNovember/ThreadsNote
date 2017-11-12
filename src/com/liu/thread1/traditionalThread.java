package com.liu.thread1;

 /** 
 * @ClassName: traditionalThread 
 * @author: lyd
 * @date: 2017��11��7�� ����9:01:25 
 * @describe:���̼̳߳�Thread
 */
public class traditionalThread extends Thread{
		private String name;
	public traditionalThread(String name){
		this.name=name;
	}
	
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(name+"����:"+i);
		}
		try
		{
			sleep((int) Math.random() * 10);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		 System.out.println(Thread.currentThread().getName()+"���߳����п�ʼ!");  
		traditionalThread thread1=new traditionalThread("A");
		traditionalThread thread2=new traditionalThread("B");
		thread1.start();
		System.out.println("1");
		thread2.start();
		 try {  
	            thread1.join();  //join �ȴ����߳̽������Ż�����������������ĳ���
	            System.out.println("2");
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        try {  
	            thread2.join();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println(Thread.currentThread().getName()+ "���߳����н���!");  
	}
}
