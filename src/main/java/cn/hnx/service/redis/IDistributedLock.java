package cn.hnx.service.redis;

/**
 * Created by viruser on 2019/8/9.
 */
public interface IDistributedLock {

    boolean acquire();

    void release();

}
