package com.quentin.example.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 单例线程池
 * @author: GUOQUN.YANG.
 * @date: 2018/3/20.
 * @time: 10:07.
 */
public class SysThreadPool {
    /**
     * 线程缓冲队列
     */
    private static BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<>(100);
    /**
     * 核心线程数，会一直存活，即使没有任务，线程池也会维护线程的最少数量
     */
    private static final int SIZE_CORE_POOL = 5;
    /**
     * 线程池维护线程的最大数量
     */
    private static final int SIZE_MAX_POOL = 10;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final long ALIVE_TIME = 2000;


    /**
     * 线程策略：
     * AbortPolicy：如果线程池队列满了丢掉这个任务并且抛出RejectedExecutionException异常
     * DiscardPolicy：如果线程池队列满了，会直接丢掉这个任务并且不会有任何异常
     * DiscardOldestPolicy：如果队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列
     * CallerRunsPolicy：如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行
     */
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, ALIVE_TIME, TimeUnit.MILLISECONDS,
            bqueue, new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 创建所有核心线程
     * @Author: guoqun.yang
     * @Date: 2018/3/21 20:06
     * @param
     * @version 1.0
     */
    static {
        pool.prestartAllCoreThreads();
    }

    /**
     * 获取线程池
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/3/21 20:05
     * @version 1.0
     */
    public static ThreadPoolExecutor getPool() {
        return pool;
    }
}
