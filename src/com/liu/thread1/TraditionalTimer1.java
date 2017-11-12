package com.liu.thread1;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


 /** 
 * @ClassName: TraditionalTimer 
 * @author: lyd
 * @date: 2017��11��9�� ����8:07:27 
 * @describe:��ͳ�̼߳����еĶ�ʱ�������໥Ƕ�׶�ʱ��
 */
public class TraditionalTimer1 {
	private static int count=0;
	public static void main(String[] args) {
		
		new Timer().schedule(new MyTimerTask1(), 2000);
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
class MyTimerTask1 extends TimerTask{

	@Override
	public void run() {
		System.out.println("--boom2--");
		new Timer().schedule(new MyTimerTask2(), 3000);
		
	}
	
}
class MyTimerTask2 extends TimerTask{

	@Override
	public void run() {
		System.out.println("--boom1--");
		new Timer().schedule(new MyTimerTask1(), 3000);
		
	}
	
}
