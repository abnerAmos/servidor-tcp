package com.springrestmsr.projectspring.servidor;

import java.util.concurrent.BlockingQueue;

public class TaskConsumer implements Runnable {

    private BlockingQueue<String> queue;

    public TaskConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String command = queue.take();
            System.out.println("Consumindo comando " + command);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
