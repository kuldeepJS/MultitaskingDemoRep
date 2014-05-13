package org.infra.opti.pocs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
	
	CountDownLatch latch;
	
	public Processor(CountDownLatch latch){
		this.latch = latch;
	}
	
	public void run(){
		System.out.println("Started.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
	}
}

public class CountDownLatchDemo {
	CountDownLatch latch;
	
	public CountDownLatchDemo(int initCount){
		latch = new CountDownLatch(initCount);
	}
	
	public void startProcessing(){
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i=0; i< 3; i++){
			executor.submit(new Thread(new Processor(latch)));
		}
		executor.shutdown();
		System.out.println("Waiting for latch...");
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed.");
	}
}
