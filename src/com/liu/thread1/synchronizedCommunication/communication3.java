package com.liu.thread1.synchronizedCommunication;

 /** 
 * @ClassName: communication2 
 * @author: lyd
 * @date: 2017��11��10�� ����11:04:43 
 * @describe:���motify��wait
 */
public class communication3 {
	public static void main(String[] args) {
		 Business1 bussiness = new Business1(); //newһ���߳���������
	        //����һ�����߳�
	        new Thread(new Runnable() {

	            @Override
	            public void run() {
	                for(int i = 1; i <= 50; i ++) {
	                    bussiness.sub(i);
	                }

	            }
	        }).start();

	        //main���������߳�
	        for(int i = 1; i <= 50; i ++) {
	            bussiness.main1(i);
	        }
	    }
	}
class Business1{
	private boolean bShouldSub=true;
	public synchronized void sub(int i){
		while(!bShouldSub) //��Ϊfalseʱ���ͽ����߳�˯��
		{
			try{
				this.wait(); //ʹ��ǰ�߳�˯��
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		//�������к�subΪfalse�������Զ��̣߳�����Ϊִ�к󽫴��߳�˯��һ��
		for(int j=1;j<10;j++){
			System.out.println("sub thread sequence of " + j + ", loop of " + i);
			}
		bShouldSub=false;
		this.notify();//���������߳�
		
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
