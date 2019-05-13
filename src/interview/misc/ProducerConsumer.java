package interview.misc;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implement Producer and consumer pattern using Blocking Queue
 * https://www.youtube.com/watch?v=UOr9kMCCa5g
 */
public class ProducerConsumer {

  static AtomicInteger count = new AtomicInteger();

  static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(4);

  public static void main(String[] args) {
    Random random = new Random();
    // producer
    final Runnable producer = () -> {
      while (count.incrementAndGet() < 20) {
        try {
          // put method will block the queue if queue is full
          int val = random.nextInt();
          System.out.println("put:" + val + ": Queue Size:" +blockingQueue.size());
          blockingQueue.put(val);
          Thread.sleep(1000);
        } catch (InterruptedException ie) {
          System.out.println(ie.getMessage());
        }
      }
    };

    new Thread(producer).start();
    new Thread(producer).start();

    try {
      Thread.sleep(10000);
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }

    // consumer
    final Runnable consumer = () -> {
      while (true) {
        try {
          // take method will block the queue if queue is empty
          int val = blockingQueue.take();
          System.out.println("take:" + val+ ": Queue Size:" +blockingQueue.size());
        } catch (InterruptedException ie) {
          System.out.println(ie);
        }
      }
    };

    new Thread(consumer).start();
    new Thread(consumer).start();
  }
}
