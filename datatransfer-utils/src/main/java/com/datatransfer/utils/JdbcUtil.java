package com.datatransfer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * jdbc工具类
 *
 * @author panxiaobo,zhaoheng
 * date 2019/9/5 14:10
 * update by zhaoheng 2019/9/8
 */

public class JdbcUtil {

    private static final Logger logger = LoggerFactory.getLogger(JdbcUtil.class);
    /**
     * 获取连接
     *
     * @param url
     * @param username
     * @param passwd
     * @return
     * @throws Exception
     */
    public static  Connection getConn(int id,String driver, String url, String username, String passwd) {
        Connection connection = null;
        boolean isConnect = false;
        while(!isConnect){
            try{
                connection=getConn(driver,url,username,passwd);
                isConnect = true;
            }catch (SQLException ex){
                logger.error("failed to connect to the data source, reconnect the data source, data source ID is {} ,error code is {}, error message is {}",id,ex.getErrorCode(),ex.getMessage());
                try{
                    Thread.sleep(10000);
                }catch (InterruptedException ie){
                    logger.error(ie.getMessage(),ie);
                }
            }
        }
        return connection;
    }


    private static synchronized Connection getConn(String driver, String url, String username, String passwd) throws SQLException{
        Connection connection=null;
        try {
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            logger.error("database driver not found, please check if the driver is imported ,err msg:{}",e.getMessage(),e);
            throw new RuntimeException("database driver not found, please check if the driver is imported "+e.getMessage());
        }
        connection = DriverManager.getConnection(url,username,passwd);
        return connection;
    }



    /**
     * 关闭连接
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void close(ResultSet resultSet, Statement statement,
                             Connection connection) {
        try {
            if (resultSet != null&&!resultSet.isClosed()) {
                resultSet.close();
            }
            if (statement != null&&!statement.isClosed()) {
                statement.close();
            }
            if (connection != null&&!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnetion(Connection connection) {
        try {
            if (connection != null&&!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeStatement(Statement statement) {
        try {
            if (statement != null&&!statement.isClosed()) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null&&!resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源
     * add by zhaoheng,2019/9/17
     */
    public static  <T> void close(T obj,String errorMsg,String ...debugMsg){
        if(!(obj instanceof AutoCloseable)){
            return;
        }
        try{
            AutoCloseable closeable = (AutoCloseable)obj;
            closeable.close();
            if(debugMsg.length>0){
                logger.info(debugMsg[0]);
            }
        }catch (Exception ex){
            logger.error(errorMsg,ex);
        }
    }
}
