package com.liu.thread1;

 /** 
 * @ClassName: yieldThread 
 * @author: lyd
 * @date: 2017��11��7�� ����9:59:12 
 * @describe:
 * �߳��ò�
 * ��ͣ�߳�,��cpu�ò�����������
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
//	             ��iΪ30ʱ�����߳̾ͻ��CPUʱ���õ��������������Լ����߳�ִ�У�Ҳ����˭������˭ִ�У�  
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
//		yt1.setPriority(Thread.MAX_PRIORITY);//�����̵߳����ȼ�
		yt1.start();
		yt2.start();
	}
}
