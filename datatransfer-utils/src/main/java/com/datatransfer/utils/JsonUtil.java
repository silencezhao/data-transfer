package com.datatransfer.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * fastjson工具类
 * @author  panxiaobo
 * date 2019/8/30 16:26
 */

public class JsonUtil {
    //ObjectMapper是线程安全的，因此所有序列化使用一个对象即可
    public static volatile transient ObjectMapper objectMapper;

    public static <T> T readFile(String filepath, Class<T> valueType) throws Exception {
        String content = "";
        File f = new File(filepath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(f), "utf-8");
        int c;
        while((c = reader.read()) != -1) {
            content += (char)c;
        }
        reader.close();
        return readValue(content, valueType);
    }

    public static <T> T readFile(String filepath, TypeReference<T> valueTypeRef) throws Exception {
        String content = "";
        File f = new File(filepath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(f), "utf-8");
        int c;
        while((c = reader.read()) != -1) {
            content += (char)c;
        }
        reader.close();
        return readValue(content, valueTypeRef);
    }
    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if(jsonStr == null){
            return null;
        }
        checkObjectMapper();
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * json数组转List
     * @param jsonStr
     * @param valueTypeRef
     * @return 数据对象
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
        if(jsonStr == null){
            return null;
        }
        checkObjectMapper();
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJSon(Object object) {
        if(object == null){
            return null;
        }
        checkObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJSon(Object object, boolean allowNull, boolean allowEmpty) {
        if(object == null){
            return null;
        }
        checkObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public static void checkObjectMapper(){
        if (objectMapper == null) {
            synchronized (JsonUtil.class){
                if(objectMapper == null){
                    objectMapper = new ObjectMapper();
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    //该特性决定parser是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）。
                    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
                }
            }
        }
    }
}
