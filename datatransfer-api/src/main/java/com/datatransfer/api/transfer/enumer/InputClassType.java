package com.datatransfer.api.transfer.enumer;

import com.datatransfer.exceptions.base.ManuAppException;
import com.datatransfer.plugins.input.*;

/**
 * 输入类类型
 * @author  panxiaobo
 * date 2019/9/2 11:45
 */

public enum  InputClassType {
    /**
     *
     * *
     */


    /**
     * oralce
     */
    ORACLE(1, OracleInput.class);


    private int type;
    private Class classType;

    private InputClassType(int type, Class classType) {
        this.type = type;
        this.classType = classType;
    }

    public static Class getClass(int type) {
        for (InputClassType t : InputClassType.values()) {
            if (t.type == type) {
                return t.classType;
            }
        }
        throw new ManuAppException("Can't get the corresponding input type");
    }

    public int getType() {
        return type;
    }

    public Class getClassType() {
        return classType;
    }
}
