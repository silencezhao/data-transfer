package com.datatransfer.plugins.sink;


import com.datatransfer.api.transfer.base.AbstractDataSource;
import com.datatransfer.api.transfer.base.AbstractOutput;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * hive输出类
 * @author  panxiaobo,zhaoheng
 * date 2019/8/30 17:08
 * update by zhaoheng 2019/09/18 10:28
 */

public class HiveSink extends AbstractOutput {


    @Override
    protected void execute(List<Map<String, Object>> data, AbstractDataSource dataSource) {

    }

    @Override
    public Map<String, Object> init(AbstractDataSource dataSource) {
        return null;
    }
}