package com.liu.thread1.Practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 /** 
 * @ClassName: Practice2 
 * @author: lyd
 * @date: 2017年11月12日 下午6:31:04 
 * @describe:　这种思路就是，我产生日志的同时，你就给我打印出来，四个线程一起干！那么这种思路的话，
 * 就得用到线程池了，我一开始创建一个线程池，
 * 里面装了4个线程，然后产生日志的时候，我让这个线程池拿线程去执行它即可。demo如下：
 */
public class Practice2 {
	public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);//创建一个线程池
        System.out.println("begin:" + (System.currentTimeMillis()/1000));

        for(int i = 0; i < 16; i ++) {
            final String log = "" + (i+1); //表示一个日志
            service.execute(new Runnable() { //拿一个线程去执行

                @Override
                public void run() {
                    parseLog(log);
                }
            });
        }
        service.shutdown(); //最后别忘了关掉线程池

    }

    public static void parseLog(String log) {
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
