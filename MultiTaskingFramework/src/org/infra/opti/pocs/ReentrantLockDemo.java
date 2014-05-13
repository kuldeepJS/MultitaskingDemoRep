package org.infra.opti.pocs;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Runner{
	int count= 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
		
	public void increment(){
		for(int i=0; i< 10000; i++)
			count++;
	}
	
	public int getCount(){
		return count;
	}
	
	public void firstThread() throws InterruptedException{
		lock.lock();
		
		System.out.println("Waiting...");
		cond.await();
		System.out.println("First thread notified...");
		try{
			increment();
		}
		finally{
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException{
		System.out.println("Second thread going to sleep...");
		Thread.sleep(2000);
		System.out.println("Second thread waken up...");
		lock.lock();
		
		System.out.println("Press the return key...");
		Scanner scn = new Scanner(System.in);
		scn.nextLine();
		System.out.println("Got the return key...");
		cond.signal();
		
		try{
			increment();
		}
		finally{
			lock.unlock();
			scn.close();
		}
	}
}

public class ReentrantLockDemo {
	Runner runner;
	
	public ReentrantLockDemo(){
		runner = new Runner();
	}
	
	public void execute(){
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try {
					runner.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try {
					runner.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Final count: " + runner.getCount());
	}
}
