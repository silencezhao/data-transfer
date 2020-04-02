package com.datatransfer.bootstrap;

import com.datatransfer.api.transfer.base.AbstractDataSource;
import com.datatransfer.api.transfer.datasource.BaseDateSource;
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
        //set thread name
        Thread.currentThread().setName("data-transfer-bootstrap-thread");
        //init appcontext
        AppContext.getAppContext();
        //init datasource
        InitTaskFromConfig initTaskFromConfig = new InitTaskFromConfig();
        initTaskFromConfig.init();
        initTaskFromConfig.readCheckpoint();
        initTaskFromConfig.start();
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
            logger.info("******read data source configuration information******");
            Set<Integer> checkSet = new HashSet<>();
            String datasourceJson= FileUtil.getFileContent(Constants.DATA_SOURCE_CONF_PATH);
            logger.info("datasourceJson={}",datasourceJson);
            List<Map> maps= JsonUtil.readValue(datasourceJson,List.class);
            for(Map map:maps){
                BaseDateSource dataSource=JsonUtil.readValue(JsonUtil.toJSon(map), BaseDateSource.class);
                if(checkSet.contains(dataSource.getId())){
                    logger.error("Data source initialization failed, duplicate data source ID found,data source ID is {}",dataSource.getId());
                    throw new RuntimeException("Data source initialization failed, duplicate data source ID found,data source ID is "+dataSource.getId()+"'");
                }else {
                    checkSet.add(dataSource.getId());
                }
                dataSourcesList.add(dataSource);
            }
            for(AbstractDataSource dataSource:dataSourcesList){
                //设置默认offset值
                context.setValue(Constants.OFFSET+dataSource.getId(), dataSource.getDefaultOffsetValue());
                dataSource.init();
            }
            logger.info("******Data source initialization completed******");
        }


        public void start(){
            for(AbstractDataSource dataSource:dataSourcesList){
                //提交任务至线程池
                context.getTaskExecutor().submitTask(dataSource);
            }
        }

        //恢复检查点
        public void readCheckpoint(){
            if(dataSourcesList.isEmpty()){
                return;
            }
            String checkPointDir = context.getProperties().getProperty("checkpointDir","/data-transfer/checkpoint");
            File checkpointDirPath = new File(checkPointDir);
            Map<String,String> offsetMap = new HashMap<>();
            if(checkpointDirPath.exists()&&checkpointDirPath.list().length>0){
                for(String fileName : checkpointDirPath.list()){
                    String offset=FileUtil.getFileContent(checkPointDir,fileName);
                    String dataSourceId  = fileName.split("\\.")[0];
                    if(!offset.trim().equals("")){
                        offsetMap.put(dataSourceId,offset.trim());
                    }
                }
            }
            for(AbstractDataSource dataSource : dataSourcesList){
                String dataSourceNameId = dataSource.getName()+"_"+dataSource.getId();
                if(offsetMap.get(dataSourceNameId)!=null){
                    context.setValue(Constants.OFFSET+dataSource.getId(),offsetMap.get(dataSourceNameId));
                    logger.info("The data source recovery checkpoint with ID {} is successful, the current checkpoint is {}",dataSource.getId(),offsetMap.get(dataSourceNameId));
                }
            }
        }
    }
}
