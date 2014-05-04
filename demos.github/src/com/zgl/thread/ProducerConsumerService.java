package com.zgl.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerService {
	public static void main(String[] args) {
		BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		
		Thread consumerThread = new Thread(consumer);
		Thread producerThread = new Thread(producer);
		
		consumerThread.start();
		producerThread.start();
		
		//new Thread(producer).start();
	}
}
