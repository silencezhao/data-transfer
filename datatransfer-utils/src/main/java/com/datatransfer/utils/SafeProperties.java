package com.datatransfer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoheng
 * @version 2019/9/10
 * safe properties type
 */
public class SafeProperties extends Properties {

    private static final Logger logger = LoggerFactory.getLogger(SafeProperties.class);

    //read safe value
    public <T> Object readValue(String property,T defalutValue){
        try{
            if(defalutValue instanceof Integer){
                return Integer.parseInt(getProperty(property));
            }else if(defalutValue instanceof Long){
                return Long.parseLong(getProperty(property));
            }else if(defalutValue instanceof Double){
                return Double.parseDouble(getProperty(property));
            }else if(defalutValue instanceof Float){
                return Float.parseFloat(getProperty(property));
            }else if(defalutValue instanceof Boolean){
                return Boolean.parseBoolean(getProperty(property));
            }else if(defalutValue instanceof String){
                return getProperty(property,defalutValue.toString());
            }else {
                return Class.forName(getProperty(property)).newInstance();
            }
        }catch (Exception ex){
            logger.error("init property fail,error property：{},default value {},err msg {}",property,defalutValue,ex.getMessage());
            return defalutValue;
        }
    }

    //get time unit
    public TimeUnit getTimeUnit(String property,String defaultValue){
        switch (getProperty(property,defaultValue)){
            case "SECONDS":return TimeUnit.SECONDS;
            case "MINUTES":return TimeUnit.MINUTES;
            case "MICROSECONDS":return TimeUnit.MICROSECONDS;
            case "NANOSECONDS":return TimeUnit.NANOSECONDS;
            case "DAYS":return TimeUnit.DAYS;
            case "HOURS":return TimeUnit.HOURS;
            default:return TimeUnit.MILLISECONDS;
        }
    }
}