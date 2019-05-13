package interview.misc;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Example:
 *
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageDataStream {

  private int[] window;
  private int n, insert;
  private long sum;

  /**
   * Initialize your data structure here.
   */
  public MovingAverageDataStream(int size) {
    window = new int[size];
    insert = 0;
    sum = 0;
  }

  public double next(int val) {
    if (n < window.length) {
      n++;
    }
    sum -= window[insert];
    sum += val;
    window[insert] = val;
    insert = (insert + 1) % window.length;

    return (double) sum / n;
  }
}
