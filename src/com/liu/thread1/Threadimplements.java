package com.liu.thread1;

 /** 
 * @ClassName: Threadimplements 
 * @author: lyd
 * @date: 2017��11��7�� ����9:09:09 
 * @describe:�ӿڶ��߳�
 */
public class Threadimplements implements Runnable{
	   private String name;  
	   
	    public Threadimplements(String name) {  
	        this.name=name;  
	    }  
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {  
            System.out.println(name + "����  :  " + i);  
            try {  
                Thread.sleep((int) Math.random() * 10);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
		
	}
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+"���߳����п�ʼ!");  
		Threadimplements threadimplements=new Threadimplements("A");
		Threadimplements threadimplements2=new Threadimplements("B");
		new Thread(threadimplements).start();
		new Thread(threadimplements2).start();
//		 try {  
//			 new Thread(threadimplements).join();
//	        } catch (InterruptedException e) {  
//	            e.printStackTrace();  
//	        }  
//		 try {  
//			 new Thread(threadimplements2).join();
//	        } catch (InterruptedException e) {  
//	            e.printStackTrace();  
//	        }  
		System.out.println(Thread.currentThread().getName()+"���߳̽���!");  
	}

}
