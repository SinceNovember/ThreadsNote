package com.liu.thread1.Practice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** 
 * @ClassName: Practice1 
 * @author: lyd
 * @date: 2017年11月12日 下午6:08:19 
 * @describe:
 * 题目描述： 
	模拟一个场景：处理16条日志记录，每条日志记录打印时间需要1秒，正常情况下如果将这16条记录去部打完需要16秒
	，现在为了提高效率，准备开启4个线程去打印，4秒钟打印完，实现这个demo。
 */
public class Practice1 {
	 public static void main(String[] args) {
	        //定义一个阻塞队列，队列大小可以装16个信息
	        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);

	        for(int i = 0; i < 4; i ++) { //开启四个线程去阻塞队列中取日志打印
	            new Thread(new Runnable() {

	                @Override
	                public void run() {
	                    while(true) {
	                        try {
	                            String log = queue.take();  //取日志
	                            parseLog(log); //打印日志
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
	            final String log = "" + (i+1); //表示一个日志
	            try {
	                queue.put(log); //将产生的日志塞到阻塞队列中去
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }

	    }

	    public static void parseLog(String log) { //打印日志的方法
	        System.out.println(Thread.currentThread().getName() + "---"
	                + log + "---" + (System.currentTimeMillis()/1000));
	        try {
	            Thread.sleep(1000); //模拟打印一次日志需要1秒
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}
