package org.infra.opti.pocs;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

class NaiveProcessor{
	
	private Object lock = new Object();
	private LinkedList<Integer> list = new LinkedList<Integer>();
	
	public void produceSyn() throws InterruptedException{
		int value = 0;
		
		while(true){
			synchronized (lock) {
				while(list.size() == 10){
					lock.wait();
				}
				
				list.add(value++);
				lock.notify();
			}
		}
	}
	
	public void consumeSyn() throws InterruptedException{
		Random random = new Random();
		while(true){
			synchronized (lock) {
				while(list.size() == 0){
					lock.wait();
				}
				
				System.out.println("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("Removed value is: " + value);
				lock.notify();
			}
			
			Thread.sleep(random.nextInt(1000));
		}
	}
	
	public void produce() throws InterruptedException{
		synchronized (this) {
			System.out.println("Producer thread running...");
			wait();
			System.out.println("Producer resumed.");
		}
	}
	
	public void consume() throws InterruptedException{
		Scanner scn = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized (this) {
			System.out.println("Waiting for the return key.");
			scn.nextLine();
			System.out.println("Return key pressed.");
			notify();
			System.out.println("Notification sent...");
			Thread.sleep(5000);
			System.out.println("Thread waking up...");
		}
		scn.close();
	}
}

public class ConsumerProducerNaive {
	
	public void execute(){
		final NaiveProcessor processor = new NaiveProcessor();
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try {
					processor.produce();
				} catch (InterruptedException e) {
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
	}
	
	public void executeSyn(){
		final NaiveProcessor processor = new NaiveProcessor();
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try {
					processor.produceSyn();
				} catch (InterruptedException e) {
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try {
					processor.consumeSyn();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
	}
}
