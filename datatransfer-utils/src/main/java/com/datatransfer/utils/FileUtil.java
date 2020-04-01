package com.datatransfer.utils;

import com.datatransfer.exceptions.base.ManuAppException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

/**
 * 文件工具类
 * @author  panxiaobo
 * date 2019/9/4 19:05
 */

public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 读取文件内容
     * @param fileName
     * @return
     */
    public static String getFileContent(String fileName) {
        String content=null;
        try {
            URL resource = FileUtil.class.getClassLoader().getResource((fileName));
            File f = new File(resource.getFile());
            content = FileUtils.readFileToString(f);
        } catch (Exception e) {
            logger.error("read file failure:" + fileName, e);
            throw new ManuAppException("read file failure",e);
        }
        return content;
    }

    /**
     *
     * @param fileName  文件名
     * @param content   写入的内容
     * @param isAppend  是否追加方式
     */
    public static void writeFile(String fileName,String content,boolean isAppend) {
        try {
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file,isAppend);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(content+"\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close();
        } catch (IOException e) {
            logger.error("write file failure:" + fileName, e);
            throw new ManuAppException("write file failure",e);
        }
    }

    /**
     * @author zhaoheng
     * @version 20190911
     * 获取指定路径的文件的内容
     */
    public static String getFileContent(String dirName,String name){
        String fullPath = dirName + File.separator +name;
        File file = new File(fullPath);
        BufferedReader br ;
        StringBuilder sb = new StringBuilder();
        String lineStr= "";
        if(!file.exists())
            return "";
        try {
            FileReader fileReader = new FileReader(fullPath);
            br = new BufferedReader(fileReader);
            while ((lineStr=br.readLine())!=null){
                sb.append(lineStr);
            }
        }catch (FileNotFoundException fnex){
            logger.error("file not found error,file name is {}",fullPath,fnex);
            return "";
        }catch (IOException ioe){
            logger.error("read data failed,file name is {}",fullPath,ioe);
        }
        return sb.toString();
    }
}