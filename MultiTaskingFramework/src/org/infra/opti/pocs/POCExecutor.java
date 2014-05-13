package org.infra.opti.pocs;

public class POCExecutor {
	public void pocMain(String[] args){
		//DemoParallerWork dpw = new DemoParallerWork();
		//dpw.process();
		
		//ConsumerProducer cp = new ConsumerProducer(10);
		//cp.startMarket();
		
		//CountDownLatchDemo cntDwnLatch = new CountDownLatchDemo(3);
		//cntDwnLatch.startProcessing();
		
		//ConsumerProducerNaive cpn = new ConsumerProducerNaive();
		////cpn.execute();
		//cpn.executeSyn();
		
		//ReentrantLockDemo rld = new ReentrantLockDemo();
		//rld.execute();
		
		//SynchronizersDemo syncDemo = new SynchronizersDemo();
		//syncDemo.executeSemaphoreDemo();
		
		//CyclicBarrierDemo cbd = new CyclicBarrierDemo(10);
		//cbd.execute();
		
		//ExchangersDemo ed = new ExchangersDemo();
		//ed.execute();
		
		SimpleLock sl= new SimpleLock();
		sl.execute();
	}
}
