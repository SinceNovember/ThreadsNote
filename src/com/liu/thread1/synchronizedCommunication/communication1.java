package com.liu.thread1.synchronizedCommunication;

 /** 
 * @ClassName: communication1 
 * @author: lyd
 * @date: 2017��11��10�� ����11:00:37 
 * @describe:ͬ����������
 * 
 * �������̣߳����߳���ִ��10�Σ�Ȼ�����߳�ִ��5�Σ�Ȼ�����л������߳�ִ��10�������߳�ִ��5�Ρ����������ִ��50�Ρ�
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
	                //���߳�����ִ��5��
	                for(int j = 1;j <= 5; j ++) {
	                    System.out.println("main thread sequence of " + j + ", loop of " + i);
	                }   
	            }   
		  }
	}
}
