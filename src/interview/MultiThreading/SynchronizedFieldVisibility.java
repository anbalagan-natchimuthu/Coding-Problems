package interview.MultiThreading;

/**
 * https://www.youtube.com/watch?v=Z4hMFBvCDV4&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=2
 *
 * Happens before applicable for Volatile, synchronized, locks and concurrent collections
 */
public class SynchronizedFieldVisibility {

  int a = 1, b = 1, c = 1, x = 0;

  public void writerThread() {
    a = 5;
    b = 5;
    c = 5;
    synchronized (this) {
      x = 10;
    }
  }

  public void readerThread() {
    // Testing Happens before relations.
    // since x is synchronized, anything updated before x, should be updated when you read it.
    synchronized (this) {
      System.out.println("x:" + x);
    }
    System.out.println("a:" + a + ":b:" + b + ":c:" + c);
  }

  public static void main(String[] args) {

    // Testing synchronized on different instances. Expected that happens before relations won't work on different
    // instances
    SynchronizedFieldVisibility instance1 = new SynchronizedFieldVisibility();
    Thread writerThread = new Thread(() -> {
      instance1.writerThread();
    });

    SynchronizedFieldVisibility instance2 = new SynchronizedFieldVisibility();
    Thread readerThread = new Thread(() -> {
      instance2.readerThread();
    });

    writerThread.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
    readerThread.start();

    // Testing synchronized on same instances. Expected that happens before relations will work on same instances
    SynchronizedFieldVisibility instance3 = new SynchronizedFieldVisibility();
    Thread wt = new Thread(() -> {
      instance3.writerThread();
    });
    Thread rt = new Thread(() -> {
      instance3.readerThread();
    });

    wt.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
    rt.start();
  }
}
