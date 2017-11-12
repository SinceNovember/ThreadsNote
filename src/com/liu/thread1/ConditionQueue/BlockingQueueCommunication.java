package com.liu.thread1.ConditionQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

 /** 
 * @ClassName: BlockingQueueCommunication 
 * @author: lyd
 * @date: 2017年11月12日 下午6:05:17 
 * @describe:所以使用阻塞队列就非常方便了，不用我们人为的去做判断了。 
　　之前在博客里介绍过线程间通信可以使用synchronized和wait、notify组合，
可以使用Condition，其实我们也可以使用阻塞队列来实现线程间的通信，下面举个示例：
 */
public class BlockingQueueCommunication {

    public static void main(String[] args) {
        Business bussiness = new Business();

        new Thread(new Runnable() {// 开启一个子线程

                    @Override
                    public void run() {
                        for (int i = 1; i <= 3; i++) {

                            bussiness.sub();
                        }

                    }
                }).start();

        // main方法主线程
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
            queue1.put(1); //如果主线程没执行完，则子线程缓冲区一直有数，子线程在这里被阻塞
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("子线程执行前i=" + i++);
        System.out.println("子线程执行后i=" + i);

        try {
            queue2.take(); //取出主线程中缓冲区的数，让主线程执行
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void main() {
        try {
            queue2.put(1); //如果子线程没执行完，则主线程缓冲区一直有数，主线程在这里被阻塞
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("主线程执行前i=" + i++);
        System.out.println("主线程执行后i=" + i);

        try {
            queue1.take(); //取出子线程中缓冲区的数，让子线程执行
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}