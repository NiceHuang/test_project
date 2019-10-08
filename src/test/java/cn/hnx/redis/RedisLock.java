package cn.hnx.redis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.util.ShardInfo;

/**
 * Created by viruser on 2019/8/9.
 */
public class RedisLock implements IDistributedLock {

    /** redis client */
    private static StringRedisTemplate redisTemplate;

    private String lockKey;                 // 锁的键值
    private int expireMsecs = 15 * 1000;    // 锁超时, 防止线程得到锁之后, 不去释放锁
    private int timeoutMsecs = 15 * 1000;   // 锁等待, 防止线程饥饿
    private boolean locked = false;         // 是否已经获取锁


    static {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName("172.0.0.1");

        JedisShardInfo shardInfo = new JedisShardInfo("172.0.0.1");
        factory.setShardInfo(shardInfo);
        redisTemplate = new StringRedisTemplate(factory);
    }

    public RedisLock(String lockKey) {
        this.lockKey = lockKey;
    }

    RedisLock(String lockKey, int timeoutMsecs) {
        this.lockKey = lockKey;
        this.timeoutMsecs = timeoutMsecs;
    }

    RedisLock(String lockKey, int expireMsecs, int timeoutMsecs) {
        this.lockKey = lockKey;
        this.expireMsecs = expireMsecs;
        this.timeoutMsecs = timeoutMsecs;
    }

    public String getLockKey() {
        return this.lockKey;
    }

    public StringRedisTemplate getRedisTemplate(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        return new StringRedisTemplate(factory);
    }

    @Override
    public boolean acquire() {
        int timeout = timeoutMsecs;

        if (redisTemplate == null) {
            redisTemplate = this.getRedisTemplate();
        }

        try {

            while (timeout >= 0) {

                long expires = System.currentTimeMillis() + expireMsecs + 1;
                String expiresStr = String.valueOf(expires); // 锁到期时间

                if (redisTemplate.opsForValue().setIfAbsent(lockKey, expiresStr)) {
                    locked = true;
                    System.out.println("[1] 成功获取分布式锁!");
                    return true;
                }
                String currentValueStr = redisTemplate.opsForValue().get(lockKey); // redis里的时间

                // 判断是否为空, 不为空的情况下, 如果被其他线程设置了值, 则第二个条件判断是过不去的
                if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {

                    String oldValueStr = redisTemplate.opsForValue().getAndSet(lockKey, expiresStr);

                    // 获取上一个锁到期时间, 并设置现在的锁到期时间
                    // 只有一个线程才能获取上一个线程的设置时间
                    // 如果这个时候, 多个线程恰好都到了这里, 但是只有一个线程的设置值和当前值相同, 它才有权利获取锁
                    if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                        locked = true;
                        System.out.println("[2] 成功获取分布式锁!");
                        return true;
                    }
                }

                timeout -= 100;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("获取锁出现异常, 必须释放: %s", e.getMessage()));
            this.release();
        }
        return false;
    }

    @Override
    public void release() {
        if (redisTemplate == null) {
            redisTemplate = this.getRedisTemplate();
        }

        try {
            if (locked) {

                String currentValueStr = redisTemplate.opsForValue().get(lockKey); // redis里的时间

                // 校验是否超过有效期, 如果不在有效期内, 那说明当前锁已经失效, 不能进行删除锁操作
                if (currentValueStr != null && Long.parseLong(currentValueStr) > System.currentTimeMillis()) {
                    redisTemplate.delete(lockKey);
                    locked = false;
                    System.out.println("[3] 成功释放分布式锁!");
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("释放锁出现异常, 必须释放: %s", e.getMessage()));
        }
    }

}
