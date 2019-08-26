package interview.misc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://www.youtube.com/watch?v=UOr9kMCCa5g
 */
public class ImplementBlockingQueue {

  static MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<>(3);
  static AtomicInteger count = new AtomicInteger();

  static Random random = new Random();

  public static void main(String[] args) {
    // producer
    final Runnable producer = () -> {
      while (count.incrementAndGet() < 20) {
        try {
          // put method will block the queue if queue is full
          int val = random.nextInt();
          System.out.println("put:" + val + ": Queue Size:" + blockingQueue.size());
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
          System.out.println("take:" + val + ": Queue Size:" + blockingQueue.size());
        } catch (InterruptedException ie) {
          System.out.println(ie);
        }
      }
    };

    new Thread(consumer).start();
    new Thread(consumer).start();
  }
}

class MyBlockingQueue<E> {

  Queue<E> queue;
  int maxSize;
  ReentrantLock lock = new ReentrantLock();
  Condition notFull = lock.newCondition();
  Condition notEmpty = lock.newCondition();

  MyBlockingQueue(int size) {
    queue = new LinkedList<>();
    this.maxSize = size;
  }

  public void put(E e) throws InterruptedException {
    lock.lock();
    try {
      while (queue.size() == maxSize) {
        notFull.await();
      }
      queue.add(e);
      notEmpty.signalAll();
    } finally {
      lock.unlock();
    }
  }

  public E take() throws InterruptedException {
    lock.lock();
    E e;
    try {
      while (queue.size() == 0) {
        notEmpty.await();
      }
      e = queue.remove();
      notFull.signalAll();
    } finally {
      lock.unlock();
    }
    return e;
  }

  public int size() {
    return queue.size();
  }
}
