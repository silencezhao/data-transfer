package com.datatransfer.plugins.input;



import com.datatransfer.api.transfer.base.AbstractDataSource;
import com.datatransfer.api.transfer.base.AbstractInput;

import java.io.File;
import java.sql.*;
import java.util.*;


/**
 * oracle10g输入类
 * @author  panxiaobo,zhaoheng
 * date 2019/8/30 17:04
 * update by zhaoheng,2019/9/10
 */

public class OracleInput extends AbstractInput {


    @Override
    protected void execute(List<Map<String, Object>> data, AbstractDataSource dataSource) {

    }

    @Override
    public Map<String, Object> init(AbstractDataSource dataSource) {
        return null;
    }
}