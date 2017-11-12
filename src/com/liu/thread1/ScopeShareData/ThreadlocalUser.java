package com.liu.thread1.ScopeShareData;

import java.util.Random;

 /** 
 * @ClassName: ThreadlocalUser 
 * @author: lyd
 * @date: 2017年11月10日 下午7:40:33 
 * @describe:对User对象进行共享操作
 */
public class ThreadlocalUser {
	  private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();

	    public static void main(String[] args) {
	        for(int i = 0; i < 2; i ++) {//开启两个线程
	            new Thread(new Runnable() {

	                @Override
	                public void run() {
	                    int data = new Random().nextInt();
	                    System.out.println(Thread.currentThread().getName() + " has put a data: " + data);

	                    //每个线程中维护一个User，User中保存了name和age
	                    User user = new User();
	                    user.setName("name" + data);
	                    user.setAge(data);
	                    threadLocal.set(user); //向当前线程中存入user对象

	                    new TestA().getData();
	                    new TestB().getData();
	                }
	            }).start();
	        }
	    }

	    static class TestA {
	        public void getData() {

	            User user = threadLocal.get();//从当前线程中取出user对象
	            System.out.println("A get data from " + Thread.currentThread().getName() + ": " 
	                    + user.getName() + "," + user.getAge());
	        }
	    }

	    static class TestB {
	        public void getData() {

	            User user = threadLocal.get();//从当前线程中取出user对象
	            System.out.println("B get data from " + Thread.currentThread().getName() + ": " 
	                    + user.getName() + "," + user.getAge());

	        }
	    }

	}
	//定义一个User类来存储姓名和年龄
	class User {

	    private String name;
	    private int age;
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public int getAge() {
	        return age;
	    }
	    public void setAge(int age) {
	        this.age = age;
	    }   
}
