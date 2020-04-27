package com.datatransfer.plugins.processor;



import com.datatransfer.api.transfer.base.AbstractDataSource;
import com.datatransfer.api.transfer.base.AbstractProcess;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 写入avro文件
 * @author  zhaoheng
 * create date 2019/9/4 14:29
 * update by zhaoheng 2019/9/10
 */

public class TranToAvroDataProcessor extends AbstractProcess {

    @Override
    protected void execute(List<Map<String, Object>> data, AbstractDataSource dataSource) {

    }

    @Override
    public Map<String, Object> init(AbstractDataSource dataSource) {
        return null;
    }
}