package com.datatransfer.test.thread;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws Exception{
        BlockingQueue<String> queue= new LinkedBlockingQueue();
        Thread thread1 = new Thread(new Producer(queue));
        Thread thread2= new Thread(new Consumer(queue,thread1));
        thread1.setName("producer");
        thread2.setName("consumer");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("main end");
    }


    public static class Producer implements Runnable{

        private BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue){
            this.queue=queue;
        }

        @Override
        public void run() {
            int i=0;
            while (true){
                queue.offer("Hello World,counts :"+ ++i);
                System.out.println("put hello world ");
                try{
                    Thread.sleep(10000);
                }catch (InterruptedException ex){

                }
            }

        }
    }

    public static class Consumer implements Runnable{

        private BlockingQueue<String> queue;
        private Thread producer;

        public Consumer(BlockingQueue<String> queue,Thread producer){
            this.queue=queue;
            this.producer=producer;
        }

        @Override
        public void run(){

            while (true){
                try{
                    producer.join();
                    System.out.println("begin consumer...");
                    System.out.println("current thread is "+Thread.currentThread().getName()+queue.take());
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException ex){

                    }
                }catch (Exception ex){

                }

            }
        }
    }
}
