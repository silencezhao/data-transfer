package com.datatransfer.utils;
/**
 * 常量类
 * @author  panxiaobo
 * date 2019/9/4 18:57
 * update by zhaoheng 2019/9/10 15:45
 */

public class Constants {

    /**
     * 数据源配置文件
     */
    public static final String DATA_SOURCE_CONF_PATH="datasource.json";
    //系统配置文件,add by zhaoheng
    public static final String CONFIG_FILE="config.properties";


    /**
     * JDBC
     */
    public static final String JDBC_URL="jdbc.url";
    public static final String JDBC_USERNAME="jdbc.username";
    public static final String JDBC_PASSWORD="jdbc.password";
    public static final String JDBC_DRIVER="jdbc.driver";
    public static final String JDBC_SQL="sql";

    public static final String OFFSET="offset_";
    public static final String OFFSET_COL_NAME="NTIMESTAMP#";

    public static final String HIVE_TMP_TABLE_PREFIX = "hive_tmp_";
    public static final String HIVE_HIS_TABLE_PREFIX = "hive_his_";
    public static final String HIVE_VIEW_PREFIX = "hive_view_";

}
