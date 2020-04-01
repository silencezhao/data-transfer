package com.datatransfer.bootstrap;

import com.datatransfer.api.transfer.base.AbstractDataSource;
import com.datatransfer.context.AppContext;
import com.datatransfer.utils.JsonUtil;
import com.datatransfer.utils.Constants;
import com.datatransfer.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;


public class Bootstrap {


    //logger
    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args){
//        //set thread name
//        Thread.currentThread().setName("data-transfer-bootstrap-thread");
//        //init appcontext
//        AppContext.getAppContext();
//        //init datasource
//        InitTaskFromConfig initTaskFromConfig = new InitTaskFromConfig();
//        initTaskFromConfig.init();
//        initTaskFromConfig.readCheckpoint();
//        initTaskFromConfig.start();
        System.out.println("package test");
    }




    public static class InitTaskFromConfig {
        //logger
        private static final Logger logger = LoggerFactory.getLogger(InitTaskFromConfig.class);
        //datasource list
        private List<AbstractDataSource> dataSourcesList=new ArrayList<>();
        //context object
        private AppContext context = AppContext.getAppContext();

        /**
         * 读取dataSource配置文件，从配置文件配置信息生成数据源
         */
        public void init() {

        }


        public void start(){

        }

        //恢复检查点
        public void readCheckpoint() {
        }
    }
}
