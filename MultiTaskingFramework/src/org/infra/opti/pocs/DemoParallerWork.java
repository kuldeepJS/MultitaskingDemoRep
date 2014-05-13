package org.infra.opti.pocs;

import java.util.ArrayList;
import java.util.Random;

public class DemoParallerWork {
	Random random;
	ArrayList<Integer> list1;
	ArrayList<Integer> list2;
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public DemoParallerWork(){
		random = new Random();
		list1 = new ArrayList<Integer>();
		list2 = new ArrayList<Integer>();
		
		long startTime = System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				process();
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				process();
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
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time: " + (endTime - startTime) + ". List1 -> " + list1.size() + "; List2 -> " + list2.size());
	}
	
	public void method1() throws InterruptedException{
		synchronized (lock1) {
			//System.out.println("Thread: " + Thread.currentThread().getId() + " called Method1.");
			list1.add(random.nextInt(1000));
			Thread.sleep(1);
		}
	}
	
	public void method2() throws InterruptedException{
		synchronized (lock2) {
			//System.out.println("Thread: " + Thread.currentThread().getId() + " called Method2.");
			list2.add(random.nextInt(1000));
			Thread.sleep(1);
		}
	}
	
	public void process(){
		try {
			for(int i=0; i< 1000; i++){
				method1();
				method2();
			}
		} catch (InterruptedException e) {
			System.out.println("Exception completing the process...");
			e.printStackTrace();
		}
	}
}
