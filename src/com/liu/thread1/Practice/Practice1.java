package com.liu.thread1.Practice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** 
 * @ClassName: Practice1 
 * @author: lyd
 * @date: 2017��11��12�� ����6:08:19 
 * @describe:
 * ��Ŀ������ 
	ģ��һ������������16����־��¼��ÿ����־��¼��ӡʱ����Ҫ1�룬����������������16����¼ȥ��������Ҫ16��
	������Ϊ�����Ч�ʣ�׼������4���߳�ȥ��ӡ��4���Ӵ�ӡ�꣬ʵ�����demo��
 */
public class Practice1 {
	 public static void main(String[] args) {
	        //����һ���������У����д�С����װ16����Ϣ
	        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);

	        for(int i = 0; i < 4; i ++) { //�����ĸ��߳�ȥ����������ȡ��־��ӡ
	            new Thread(new Runnable() {

	                @Override
	                public void run() {
	                    while(true) {
	                        try {
	                            String log = queue.take();  //ȡ��־
	                            parseLog(log); //��ӡ��־
	                        } catch (InterruptedException e) {
	                            // TODO Auto-generated catch block
	                            e.printStackTrace();
	                        }
	                    }
	                }
	            }).start();
	        }

	        System.out.println("begin:" + (System.currentTimeMillis()/1000));

	        for(int i = 0; i < 16; i ++) {
	            final String log = "" + (i+1); //��ʾһ����־
	            try {
	                queue.put(log); //����������־��������������ȥ
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }

	    }

	    public static void parseLog(String log) { //��ӡ��־�ķ���
	        System.out.println(Thread.currentThread().getName() + "---"
	                + log + "---" + (System.currentTimeMillis()/1000));
	        try {
	            Thread.sleep(1000); //ģ���ӡһ����־��Ҫ1��
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}
