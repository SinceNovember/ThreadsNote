package com.liu.thread1.CallableAndFuture;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallAndFuture {
	public static void main(String[] args) {
		ExecutorService threadPool=Executors.newCachedThreadPool();
		CompletionService<Integer> completionService=new ExecutorCompletionService<Integer>(threadPool);
		for(int i=1;i<=5;i++){
			final int seq=i;
			completionService.submit(new Callable<Integer>() {
				
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
		
			}
);
		}
		 for(int i = 0; i < 5; i ++) { //执行完了后，再取出来
	            try {
	                System.out.print(completionService.take().get() + " ");
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } 
	        }
	}
		
		
}
