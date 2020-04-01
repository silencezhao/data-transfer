package com.datatransfer.api.transfer.base;

import com.datatransfer.exceptions.base.ManuAppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据转移抽象基础类
 * @author  panxiaobo
 * date 2019/8/29 18:15
 */

public abstract  class AbstractBase {


    /**
     * 配置信息
     */
    private Map<String,Object> config=new HashMap<>();

    /**
     * 前置过滤器
     */
    private transient List<AbstractFilter> beforeFilterList=new ArrayList<>();

    /**
     * 后置过滤器
     */
    private transient List<AbstractFilter> afterFilterList=new ArrayList<>();

    private String beforeFilters;
    private String afterFilters;
    private int type;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());



    /**
     * 处理过程，需要处理类实现
     * @param data
     * @return
     */
    protected abstract void execute(List<Map<String,Object>> data,AbstractDataSource dataSource);

    /**
     * 初始化准备工作
     * @return
     */
    public abstract Map<String,Object> init(AbstractDataSource dataSource);


    public void doExcute(List<Map<String,Object>> data,AbstractDataSource dataSource){
        for(AbstractFilter filter:beforeFilterList){
            filter.filter(this.config,data);
        }
        this.execute(data,dataSource);
        for(AbstractFilter filter:afterFilterList){
            filter.filter(this.config,data);
        }

    }

    public <T> T getConfig( String key, T defaultConfig, boolean isMust){
        if (config.containsKey(key)) {
            return (T) config.get(key);
        } else {
            if (!isMust) {
                return defaultConfig;
            } else {
                throw new ManuAppException(key + " must be specified");
            }
        }
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    public String getBeforeFilters() {
        return beforeFilters;
    }

    public void setBeforeFilters(String beforeFilters) {
        this.beforeFilters = beforeFilters;
    }

    public String getAfterFilters() {
        return afterFilters;
    }

    public void setAfterFilters(String afterFilters) {
        this.afterFilters = afterFilters;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

