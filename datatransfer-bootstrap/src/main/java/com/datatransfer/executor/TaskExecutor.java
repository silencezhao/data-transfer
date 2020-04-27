package com.datatransfer.executor;

import com.datatransfer.utils.SafeProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaoheng
 * @version 2019/9/10
 * propose
 *   workers who handle data transfer task
 */

public class TaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(TaskExecutor.class);

    //config file path
    private static final String CONFIG_FILE="config.properties";
    //real executor
    private ThreadPoolExecutor threadPoolExecutor;
    //config properties
    private SafeProperties properties;
    //thread number
    private AtomicInteger threadSerialNumber = new AtomicInteger(1);
    //core pool size
    private int corePoolSize;
    //max pool size
    private int maxPoolSize;
    //thread name prefix
    private String threadNamePrefix;
    //timeunit
    private TimeUnit timeUnit;
    //timeout
    private long timeOut;
    //blockingQueue
    private BlockingQueue<Runnable> blockingQueue;
    //reject policy
    private RejectedExecutionHandler rejectedExecutionHandler;
    //prepare to create core thread?
    private boolean isPrepareCoreThread;

    public TaskExecutor(){

    }

    public TaskExecutor(SafeProperties properties){
        this.properties=properties;
    }

    /**
     * read config
     */
    public void readConfig(){
        try {
            if(properties == null){
                properties = new SafeProperties();
                properties.load(TaskExecutor.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
            }
            corePoolSize = (int)properties.readValue("corePoolSize",10);
            maxPoolSize = (int)properties.readValue("maxPoolSize",10);
            threadNamePrefix = properties.readValue("threadNamePrefix","data-transfer-thread").toString();
            timeUnit= properties.getTimeUnit("timeUnit","MILLISECONDS");
            timeOut= (long)properties.readValue("timeOut",1000L);
            blockingQueue= (BlockingQueue<Runnable>) properties.readValue("blockQueue",new SynchronousQueue<Runnable>());
            rejectedExecutionHandler = (RejectedExecutionHandler)properties.readValue("rejectPolicy",new ThreadPoolExecutor.AbortPolicy() ) ;
            isPrepareCoreThread = (boolean)properties.readValue("isPrepareCoreThread",false);
            logger.info("thread pool config ger success");
        }catch (IOException ioe){
            logger.error("get config file fail",ioe);
        }
    }

    public void init(){
        logger.info("init threadPoolExecutor,config is :");
        logger.info("corePoolSize->{}\nmaxPoolSize->{}\nthreadNamePrefix->{}\ntimeUnit->{}\ntimeOut->{}\nblockingQueue->{}\nrejectedExecutionHandler->{}\nisPrepareCoreThread->{}\n",
                corePoolSize,maxPoolSize,threadNamePrefix,timeUnit,timeOut,blockingQueue,rejectedExecutionHandler,isPrepareCoreThread);

        //create threadpool executor
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,timeOut,timeUnit,blockingQueue);
        //set thread factory
        threadPoolExecutor.setThreadFactory((Runnable r)->{
                Thread newThread = new Thread(r);
                newThread.setName(threadNamePrefix+"-"+threadSerialNumber.getAndAdd(1));
                return newThread;
            }
        );
        //set reject policy,default abort
        threadPoolExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);
        //prestartAllCoreThreads?
        if(isPrepareCoreThread){
            threadPoolExecutor.prestartAllCoreThreads();
        }
        logger.info("init thread PoolExecutor success");
    }

    //submit task
    public void submitTask(Runnable task){
        threadPoolExecutor.submit(task);
    }

    public void shutdown(){
        threadPoolExecutor.shutdown();
    }
}
