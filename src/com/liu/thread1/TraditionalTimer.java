package com.liu.thread1;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


 /** 
 * @ClassName: TraditionalTimer 
 * @author: lyd
 * @date: 2017��11��9�� ����8:07:27 
 * @describe:��ͳ�̼߳����еĶ�ʱ������
 */
public class TraditionalTimer {
	private static int count=0;
	public static void main(String[] args) {
		class MyTimerTask extends TimerTask{

			@Override
			public void run() {
				count=(count+1)%2;
				System.out.println("--boom--");
				new Timer().schedule(new MyTimerTask(), 3000);
				
			}
			
		}
		new Timer().schedule(new MyTimerTask(), 2000);
		while(true){
			System.out.println(new Date().getSeconds());
			try {
                Thread.sleep(1000);
            } catch(Exception e) {
                e.printStackTrace();
            }
		}
	}
}
