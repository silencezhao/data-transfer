package com.datatransfer.api.transfer.datasource;

import com.datatransfer.api.transfer.base.AbstractDataSource;


/**
 * 基础数据源
 * @author  panxiaobo
 * date 2019/8/30 18:00
 */

public class BaseDateSource extends AbstractDataSource {
    @Override
    public void run() {
        while(true){
            try {
                long startTime = System.currentTimeMillis();
                logger.info("Data source ID: '{}' to start the task",getId());
                this.execute();
                long endTime = System.currentTimeMillis();
                logger.info("Data source ID: '{}' End of task execution, spending time:{}s",getId(),(endTime-startTime)/1000);
            }catch (Exception e){
                logger.error("The task failed to execute, data source ID: '{}'",this.getId());
                logger.error("Exception information:",e);
            }finally {
                try {
                    Thread.sleep(this.getInterval() * 1000);
                } catch (InterruptedException e) {
                    logger.error("Generate an interruption",e);
                }
            }
        }
    }
}
