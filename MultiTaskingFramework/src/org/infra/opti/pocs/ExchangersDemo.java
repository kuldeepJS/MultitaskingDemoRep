package org.infra.opti.pocs;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Exchanger;

public class ExchangersDemo {
	ArrayList<String> emptyBuffer;
	ArrayList<String> fullBuffer;
	
	Thread consumer;
	Thread producer;
	
	public ExchangersDemo(){
		emptyBuffer = new ArrayList<String>();
		fullBuffer = new ArrayList<String>();
		Exchanger<ArrayList<String>> exchanger = new Exchanger<ArrayList<String>>();
		producer = new Thread(new Producer(emptyBuffer, exchanger));
		consumer = new Thread(new Consumer(fullBuffer, exchanger));
	}
	
	public void execute(){
		producer.start();
		consumer.start();
	}
}

class Producer implements Runnable{
	
	ArrayList<String> buffer;
	Exchanger<ArrayList<String>> exchanger;
	
	public Producer(ArrayList<String> buffer, Exchanger<ArrayList<String>> exchanger){
		this.buffer = buffer;
		this.exchanger = exchanger;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		try{
			while(true){
				buffer.add("Number: " + random.nextInt(100));
				if(buffer.size() == 10){
					System.out.println("Producer waiting for exchanging the buffer...");
					buffer = exchanger.exchange(buffer);
					System.out.println("Producer exchanged the buffers!");
				}
			}
		}
		catch(InterruptedException ie){
			
		}
	}
	
}

class Consumer implements Runnable{
	
	ArrayList<String> buffer;
	Exchanger<ArrayList<String>> exchanger;
	
	public Consumer(ArrayList<String> buffer, Exchanger<ArrayList<String>> exchanger){
		this.buffer = buffer;
		this.exchanger = exchanger;
	}
	
	@Override
	public void run() {
		try{
			Thread.sleep(2000);
			while(true){
				if(buffer.size() == 0){
					System.out.println("Consumer waiting for exchanging the buffer...");
					buffer = exchanger.exchange(buffer);
					System.out.println("Consumer exchanged the buffer!");
				}
				System.out.println("Removed element: " + buffer.remove(0));
			}
		}
		catch(InterruptedException ie){
		}
	}
}
