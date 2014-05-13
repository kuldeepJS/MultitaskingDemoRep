package org.infra.opti.pocs;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class SimpleLock {
	
	public static void execute(){
		final SimpleLockProcessor smp = new SimpleLockProcessor(10);
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				smp.produce();
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				smp.consume();
			}
		});
		
		t1.start();
		t2.start();
	}
	
}

class SimpleLockProcessor{
	
	int currentIndex;
	int maxLength;
	Random random;
	Lock simpleLock;
	ArrayList<Integer> buffer;
	
	public SimpleLockProcessor(int maxLength){
		this.maxLength = maxLength;
		currentIndex = 0;
		random = new Random();
		buffer = new ArrayList<Integer>(maxLength);
		for(int i = 0; i<maxLength;i++)
			buffer.add(0);
	}
	
	public void produce(){
		while(true){
			buffer.add(currentIndex++, random.nextInt(100));
			
			if(currentIndex == maxLength - 1)
				currentIndex = 0;
		}
	}
	
	public void consume(){
		while(true){
			System.out.println("Getting the value relative to: " + currentIndex);
			int randomInt = random.nextInt(100);
			System.out.println("According to: " + randomInt);
			System.out.println(buffer.get(currentIndex == 0 ? currentIndex : randomInt%currentIndex));
		}
	}
	
}
