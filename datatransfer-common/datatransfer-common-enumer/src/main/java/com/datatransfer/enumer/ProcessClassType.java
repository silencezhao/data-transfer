package com.datatransfer.enumer;

import com.datatransfer.exceptions.base.ManuAppException;

/**
 * 输入类类型
 * @author  panxiaobo
 * date 2019/9/2 11:45
 */

public enum ProcessClassType {
    /**
     *
     * *
     */


    /**
     * 扩展字段
     */
    TRAN_TO_AVRO_DATA(1, ProcessClassType.class);


    private int type;
    private Class classType;

    private ProcessClassType(int type, Class classType) {
        this.type = type;
        this.classType = classType;
    }

    public static Class getClass(int type) {
        for (ProcessClassType t : ProcessClassType.values()) {
            if (t.type == type) {
                return t.classType;
            }
        }
        throw new ManuAppException("Can't get the corresponding process type");
    }

    public int getType() {
        return type;
    }

    public Class getClassType() {
        return classType;
    }
}
