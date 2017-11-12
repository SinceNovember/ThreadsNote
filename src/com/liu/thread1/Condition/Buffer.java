package com.liu.thread1.Condition;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 /** 
 * @ClassName: Buffer 
 * @author: lyd
 * @date: 2017年11月12日 下午5:42:18 
 * @describe:现在把这个实际的问题抽象一下：缓冲区即一个数组，
 * 我们可以向数组中写入数据，也可以从数组中把数据取走，我要做的两件事就是开启两个线程，一个存数据，
 * 一个取数据。但是问题来了，如果缓冲区满了，说明接收的消息太多了，即发送过来的消息太快了，我另一个线程还来不及发完
 * ，导致现在缓冲区没地方放了，那么此时就得阻塞存数据这个线程，让其等待；相反，如果我转发的太快，
 * 现在缓冲区所有内容都被我发完了，还没有用户发新的消息来，那么此时就得阻塞取数据这个线程。 
　　好了，分析完了这个缓冲区的阻塞队列，下面就用Condition技术来实现一下：
 */
public class Buffer {
	   final Lock lock = new ReentrantLock(); //定义一个锁
	    final Condition notFull = lock.newCondition(); //定义阻塞队列满了的Condition
	    final Condition notEmpty = lock.newCondition();//定义阻塞队列空了的Condition

	    final Object[] items = new Object[10]; //为了下面模拟，设置阻塞队列的大小为10，不要设太大

	    int putptr, takeptr, count; //数组下标，用来标定位置的

	    //往队列中存数据
	    public void put(Object x) throws InterruptedException {
	        lock.lock(); //上锁
	        try {
	            while (count == items.length) {
	                System.out.println(Thread.currentThread().getName() + " 被阻塞了，暂时无法存数据！");
	                notFull.await();    //如果队列满了，那么阻塞存数据这个线程，等待被唤醒
	            }
	            //如果没满，按顺序往数组中存
	            items[putptr] = x;
	            if (++putptr == items.length) //这是到达数组末端的判断，如果到了，再回到始端
	                putptr = 0;
	            ++count;    //消息数量
	            System.out.println(Thread.currentThread().getName() + " 存好了值： " + x);
	            notEmpty.signal(); //好了，现在队列中有数据了，唤醒队列空的那个线程，可以取数据啦
	        } finally {
	            lock.unlock(); //放锁
	        }
	    }

	    //从队列中取数据
	    public Object take() throws InterruptedException {
	        lock.lock(); //上锁
	        try {
	            while (count == 0) {
	                System.out.println(Thread.currentThread().getName() + " 被阻塞了，暂时无法取数据！");
	                notEmpty.await();  //如果队列是空，那么阻塞取数据这个线程，等待被唤醒
	            }
	            //如果没空，按顺序从数组中取
	            Object x = items[takeptr];
	            if (++takeptr == items.length) //判断是否到达末端，如果到了，再回到始端
	                takeptr = 0;
	            --count; //消息数量
	            System.out.println(Thread.currentThread().getName() + " 取出了值： " + x);
	            notFull.signal(); //好了，现在队列中有位置了，唤醒队列满的那个线程，可以存数据啦
	            return x;
	        } finally {
	            lock.unlock(); //放锁
	        }
	    }
	public static void main(String[] args) {
		Buffer buffer=new Buffer();
		  for(int i = 0; i < 5; i ++) { //开启5个线程往缓冲区存数据
	            new Thread(new Runnable() {

	                @Override
	                public void run() {
	                    try {
	                        buffer.put(new Random().nextInt(1000)); //随机存数据
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }).start();
	        }
		    for(int i = 0; i < 10; i ++) { //开启10个线程从缓冲区中取数据
	            new Thread(new Runnable() {

	                @Override
	                public void run() {
	                    try {
	                        buffer.take(); //从缓冲区取数据
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }).start();
	        }
	    
	}
}
