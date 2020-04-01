package com.datatransfer.context;

import com.datatransfer.bootstrap.Bootstrap;
import com.datatransfer.executor.TaskExecutor;
import com.datatransfer.utils.Constants;
import com.datatransfer.utils.SafeProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoheng
 * @version 2019/9/10
 * ApplicationContext
 */
public class AppContext {
    //get logger
    private static final Logger logger = LoggerFactory.getLogger(AppContext.class);

    //init global collection
    private ConcurrentHashMap<String,Object> globalCollection ;
    //system config map
    private SafeProperties properties ;
    //executors
    private TaskExecutor taskExecutor;
    //global appContext
    private static  volatile transient AppContext appContext;


    private AppContext(){
        globalCollection = new ConcurrentHashMap<>();
        readConfig();
        initThreadPoolExecutor();
    }

    public static AppContext getAppContext(){
        if(appContext==null){
            synchronized (AppContext.class){
                if(appContext==null){
                    appContext = new AppContext();
                }
            }
        }
        return appContext;
    }


    //read config property
    private void readConfig(){
        logger.info("start read config,file path is {}", Constants.CONFIG_FILE);
        properties = new SafeProperties();
        try{
            properties.load(Bootstrap.class.getClassLoader().getResourceAsStream(Constants.CONFIG_FILE));
        }catch (IOException ex){
            logger.error("read config fail",ex);
        }
        logger.info("read config success");
    }

    //init thread pool executor
    private void initThreadPoolExecutor(){
        taskExecutor= new TaskExecutor(properties);
        taskExecutor.readConfig();
        taskExecutor.init();
    }

    //set a global value
    public void setValue(String key,Object value){
        appContext.globalCollection.put(key,value);
    }
    //get a global value
    public Object getValue(String key){
        return appContext.globalCollection.get(key);
    }

    //get properties
    public SafeProperties getProperties() {
        return properties;
    }
    //get taskExecutor
    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    //check not null
    public void checkNotNull(Object obj){
        if(obj==null)
            throw new RuntimeException("value must not null");
    }
}
