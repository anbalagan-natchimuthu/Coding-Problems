package interview.misc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Implement a RateLimiter Class with an isAllow method. Every request comes in with a unique clientID,
 * deny a request if that client has made more than 100 requests in the past second.
 */
public class RateLimiter {

  public static final long REQUESTS_COUNTER_TTL_SEC = 10L;
  public static final long REQUESTS_BLOCK_TTL_SEC = 24 * 60 * 60L;

  private final Map<String, ReqCounter> requestCounters = new ConcurrentHashMap<>();
  private final Map<String, Long> blockedRequestIds = new ConcurrentHashMap<>();
  private final ScheduledExecutorService reqTtlCleanupExecutor = Executors.newSingleThreadScheduledExecutor();
  private final ScheduledExecutorService blockedReqTtlCleanupExecutor = Executors.newSingleThreadScheduledExecutor();
  {
    reqTtlCleanupExecutor.scheduleAtFixedRate(() -> {
      long curSecond = System.currentTimeMillis() / 1000;
      requestCounters.entrySet().stream().filter(entry -> entry.getValue().second < curSecond).forEach(
          entry -> requestCounters.remove(entry.getKey()));
    }, REQUESTS_COUNTER_TTL_SEC, REQUESTS_COUNTER_TTL_SEC, TimeUnit.SECONDS);
    blockedReqTtlCleanupExecutor.scheduleAtFixedRate(() -> {
      long curSecond = System.currentTimeMillis() / 1000;
      blockedRequestIds.entrySet().stream().filter(entry -> (curSecond - entry.getValue()) > REQUESTS_BLOCK_TTL_SEC)
          .forEach(entry -> requestCounters.remove(entry.getKey()));
    }, REQUESTS_BLOCK_TTL_SEC, REQUESTS_BLOCK_TTL_SEC, TimeUnit.SECONDS);
  }
  public boolean isAllow(String reqId) {
    if (blockedRequestIds.containsKey(reqId)) {
      return false;
    } else {
      long curSecond = System.currentTimeMillis() / 1000;
      ReqCounter reqCounter = requestCounters.compute(reqId, (k, v) -> {
        ReqCounter newValue = v;
        if (newValue == null || newValue.second > curSecond) {
          newValue = new ReqCounter(curSecond, 0L);
        }
        newValue.count = 1L + newValue.count;
        return newValue;
      });
      if (reqCounter.count < 100) {
        return true;
      } else {
        blockedRequestIds.put(reqId, curSecond);
        return false;
      }
    }
  }

  private static class ReqCounter {

    private Long second = 0L;
    private Long count = 0L;

    public ReqCounter(Long second, Long count) {
      this.second = second;
      this.count = count;
    }
  }
}
