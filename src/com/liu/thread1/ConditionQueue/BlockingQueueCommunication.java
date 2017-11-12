package com.liu.thread1.ConditionQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

 /** 
 * @ClassName: BlockingQueueCommunication 
 * @author: lyd
 * @date: 2017��11��12�� ����6:05:17 
 * @describe:����ʹ���������оͷǳ������ˣ�����������Ϊ��ȥ���ж��ˡ� 
����֮ǰ�ڲ�������ܹ��̼߳�ͨ�ſ���ʹ��synchronized��wait��notify��ϣ�
����ʹ��Condition����ʵ����Ҳ����ʹ������������ʵ���̼߳��ͨ�ţ�����ٸ�ʾ����
 */
public class BlockingQueueCommunication {

    public static void main(String[] args) {
        Business bussiness = new Business();

        new Thread(new Runnable() {// ����һ�����߳�

                    @Override
                    public void run() {
                        for (int i = 1; i <= 3; i++) {

                            bussiness.sub();
                        }

                    }
                }).start();

        // main�������߳�
        for (int i = 1; i <= 3; i++) {

            bussiness.main();
        }
    }   
}

class Business {

    private int i = 0;

    BlockingQueue queue1 = new ArrayBlockingQueue(1);
    BlockingQueue queue2 = new ArrayBlockingQueue(1);

    {
        try {
            queue2.put(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sub() {
        try {
            queue1.put(1); //������߳�ûִ���꣬�����̻߳�����һֱ���������߳������ﱻ����
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("���߳�ִ��ǰi=" + i++);
        System.out.println("���߳�ִ�к�i=" + i);

        try {
            queue2.take(); //ȡ�����߳��л����������������߳�ִ��
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void main() {
        try {
            queue2.put(1); //������߳�ûִ���꣬�����̻߳�����һֱ���������߳������ﱻ����
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("���߳�ִ��ǰi=" + i++);
        System.out.println("���߳�ִ�к�i=" + i);

        try {
            queue1.take(); //ȡ�����߳��л����������������߳�ִ��
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}