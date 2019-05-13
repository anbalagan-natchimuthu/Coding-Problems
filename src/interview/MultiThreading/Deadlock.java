package interview.MultiThreading;

import java.util.concurrent.locks.ReentrantLock;

/**
 * https://www.java2novice.com/java-interview-programs/thread-deadlock/
 * Write a program to create deadlock between two threads.
 *
 * Description:
 * Deadlock describes a situation where two or more threads are blocked forever, waiting for each other. Deadlocks can
 * occur in Java when the synchronized keyword causes the executing thread to block while waiting to get the lock,
 * associated with the specified object. Since the thread might already hold locks associated with other objects, two
 * threads could each be waiting for the other to release a lock. In such case, they will end up waiting forever.
 */
public class Deadlock {

  class DeadLockUsingSynchronized {

    String s1 = "object1";
    String s2 = "object2";

    Thread t1 = new Thread(() -> {
      while (true) {
        synchronized (s1) {
          synchronized (s2) {
            System.out.println(s1 + s2);
          }
        }
      }
    });

    Runnable r2 = () -> {
      while (true) {
        synchronized (s2) {
          synchronized (s1) {
            System.out.println(s2 + s1);
          }
        }
      }
    };
  }

  class DeadLockUsingLock {

    private ReentrantLock lockA = new ReentrantLock();
    private ReentrantLock lockB = new ReentrantLock();

    public void execute() {
      new Thread(this::process_Method_One).start();
      new Thread(this::process_Method_Two).start();
    }

    public void process_Method_One() {
      lockA.lock();
      try {
        Thread.sleep(1000);
        lockB.lock();
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      } finally {
        lockB.unlock();
        lockA.unlock();
      }
    }

    public void process_Method_Two() {
      lockB.lock();

      try {
        Thread.sleep(1000);
        lockA.lock();
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      } finally {
        lockA.unlock();
        lockB.unlock();
      }
    }
  }

  public static void main(String[] args) {
    DeadLockUsingSynchronized deadlock = new Deadlock().new DeadLockUsingSynchronized();
    deadlock.t1.start();
    Thread t2 = new Thread(deadlock.r2);
    t2.start();

    DeadLockUsingLock usingLock = new Deadlock().new DeadLockUsingLock();
    usingLock.execute();
  }
}
