package com.liu.thread1.ScopeShareData;

import java.util.Random;

 /** 
 * @ClassName: ThreadlocalUser 
 * @author: lyd
 * @date: 2017��11��10�� ����7:40:33 
 * @describe:��User������й������
 */
public class ThreadlocalUser {
	  private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();

	    public static void main(String[] args) {
	        for(int i = 0; i < 2; i ++) {//���������߳�
	            new Thread(new Runnable() {

	                @Override
	                public void run() {
	                    int data = new Random().nextInt();
	                    System.out.println(Thread.currentThread().getName() + " has put a data: " + data);

	                    //ÿ���߳���ά��һ��User��User�б�����name��age
	                    User user = new User();
	                    user.setName("name" + data);
	                    user.setAge(data);
	                    threadLocal.set(user); //��ǰ�߳��д���user����

	                    new TestA().getData();
	                    new TestB().getData();
	                }
	            }).start();
	        }
	    }

	    static class TestA {
	        public void getData() {

	            User user = threadLocal.get();//�ӵ�ǰ�߳���ȡ��user����
	            System.out.println("A get data from " + Thread.currentThread().getName() + ": " 
	                    + user.getName() + "," + user.getAge());
	        }
	    }

	    static class TestB {
	        public void getData() {

	            User user = threadLocal.get();//�ӵ�ǰ�߳���ȡ��user����
	            System.out.println("B get data from " + Thread.currentThread().getName() + ": " 
	                    + user.getName() + "," + user.getAge());

	        }
	    }

	}
	//����һ��User�����洢����������
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
