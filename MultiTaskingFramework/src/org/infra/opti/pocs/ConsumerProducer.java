package org.infra.opti.pocs;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class ConsumerProducer {
	java.util.concurrent.ArrayBlockingQueue<Integer> factoryInterface;
	Random random;
	public ConsumerProducer(int queueLimit){
		factoryInterface = new ArrayBlockingQueue<Integer>(queueLimit);
		random = new Random();
	}
	
	public void consume() throws InterruptedException{
		while(true){
			System.out.println("Consuming: " + factoryInterface.take() + "; Size="+factoryInterface.size());
		}
	}
	
	public void produce() throws InterruptedException{
		while(true){
			int newItem = random.nextInt(100);
			factoryInterface.put(newItem);
			System.out.println("Producing: " + newItem + "; Size="+factoryInterface.size());
		}
	}
	
	public void startMarket(){
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try {
					consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try {
					produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
	}
}
