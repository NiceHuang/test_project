package cn.hnx.service.redis;

/**
 * Created by viruser on 2019/8/9.
 */
public class DistributedLockUtil {
    /**
     * 获取分布式锁
     * 默认获取锁15s超时, 锁过期时间15s
     */
    public static IDistributedLock getDistributedLock(String lockKey) {
        lockKey = assembleKey(lockKey);
        return new RedisLock(lockKey);
    }

    /**
     * 获取分布式锁
     */
    public static IDistributedLock getDistributedLock(String lockKey, int timeoutMsecs) {
        lockKey = assembleKey(lockKey);
        return new RedisLock(lockKey, timeoutMsecs);
    }

    /**
     * 获取分布式锁
     */
    public static IDistributedLock getDistributedLock(String lockKey, int timeoutMsecs, int expireMsecs) {
        lockKey = assembleKey(lockKey);
        return new RedisLock(lockKey, expireMsecs, timeoutMsecs);
    }

    /**
     * 对 key 进行拼接
     */
    private static String assembleKey(String lockKey) {
        return String.format("test_distributed_lock_%s", lockKey);
    }
}
