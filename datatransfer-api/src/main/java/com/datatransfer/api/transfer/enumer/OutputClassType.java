package com.datatransfer.api.transfer.enumer;

import com.nsfocus.exception.ManuAppException;
import com.nsfocus.output.HiveOutput;

/**
 * 输入类类型
 * @author  panxiaobo
 * date 2019/9/2 11:45
 */

public enum OutputClassType {
    /**
     *
     * *
     */


    /**
     * hive
     */
    HIVE(1, HiveOutput.class);


    private int type;
    private Class classType;

    private OutputClassType(int type, Class classType) {
        this.type = type;
        this.classType = classType;
    }

    public static Class getClass(int type) {
        for (OutputClassType t : OutputClassType.values()) {
            if (t.type == type) {
                return t.classType;
            }
        }
        throw new ManuAppException("Can't get the corresponding output type");
    }

    public int getType() {
        return type;
    }

    public Class getClassType() {
        return classType;
    }
}
