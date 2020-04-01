package com.datatransfer.executor.reject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhaoheng
 * @version 2019/9/10
 * 拒绝策略，抛出异常
 */
public class ThrowExceptionPolicy implements RejectedExecutionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ThrowExceptionPolicy.class);

    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.error("The thread pool is full, please check the system configuration information, increase the thread pool capacity, the current thread pool size {}",executor.getPoolSize());
        throw new RejectedExecutionException("The thread pool is full, please check the system configuration information and increase the thread pool capacity.");
    }
}