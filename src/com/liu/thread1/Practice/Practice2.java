package com.liu.thread1.Practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 /** 
 * @ClassName: Practice2 
 * @author: lyd
 * @date: 2017��11��12�� ����6:31:04 
 * @describe:������˼·���ǣ��Ҳ�����־��ͬʱ����͸��Ҵ�ӡ�������ĸ��߳�һ��ɣ���ô����˼·�Ļ���
 * �͵��õ��̳߳��ˣ���һ��ʼ����һ���̳߳أ�
 * ����װ��4���̣߳�Ȼ�������־��ʱ����������̳߳����߳�ȥִ�������ɡ�demo���£�
 */
public class Practice2 {
	public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);//����һ���̳߳�
        System.out.println("begin:" + (System.currentTimeMillis()/1000));

        for(int i = 0; i < 16; i ++) {
            final String log = "" + (i+1); //��ʾһ����־
            service.execute(new Runnable() { //��һ���߳�ȥִ��

                @Override
                public void run() {
                    parseLog(log);
                }
            });
        }
        service.shutdown(); //�������˹ص��̳߳�

    }

    public static void parseLog(String log) {
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
