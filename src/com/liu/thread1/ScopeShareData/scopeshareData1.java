package com.liu.thread1.ScopeShareData;

import java.util.Random;

public class scopeshareData1 {
	public static int data=0;
	public static void main(String[] args) {
		for(int i=0;i<2;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int temp=new Random().nextInt();
					System.out.println(Thread.currentThread().getName()+ " has put a data: " + temp);
					data=temp;
					new TestA().getData();
					new TestB().getData();
					
				}
			}).start();
		}
	}
	 static class TestA{
		public void getData(){
			   System.out.println("A get data from " + Thread.currentThread().getName() + ": " + data);
		}
}
	static class TestB{
		public void getData(){
			  System.out.println("B get data from " + Thread.currentThread().getName() + ": " + data);
		}
}
}

	
