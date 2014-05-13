package org.infra.opti.pocs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
	ExecutorService[] executors;
	int numberOfParties;
	public CyclicBarrierDemo(int parties){
		numberOfParties = parties;
		executors = new ExecutorService[numberOfParties];
	}
	
	public void execute(){
		CyclicBarrier cb = new CyclicBarrier(numberOfParties, new Runnable(){
			public void run(){
				System.out.println("Barrier crossed...");
			}
		});
		
		for(int i=0; i< numberOfParties; i++){
			executors[i] = Executors.newSingleThreadExecutor();
			executors[i].submit(new BarrierProcessor(cb));
			executors[i].shutdown();
		}
	}
}

class BarrierProcessor implements Runnable{
	
	CyclicBarrier barrier;
	
	public BarrierProcessor(CyclicBarrier barrier){
		this.barrier = barrier;
	}
	
	public void run(){
		System.out.println("Execution by thread: " + Thread.currentThread().getId());
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Performing: " + Thread.currentThread().getId());
	}
}