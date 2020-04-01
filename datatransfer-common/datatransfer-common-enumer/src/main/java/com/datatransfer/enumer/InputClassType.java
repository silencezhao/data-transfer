package com.datatransfer.enumer;

import com.datatransfer.exceptions.base.ManuAppException;

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
    ORACLE(1, "com.datatransfer.test");


    private int type;
    private String classType;

    private InputClassType(int type, String classType) {
        this.type = type;
        this.classType = classType;
    }

    public static Class getClass(int type) {
        for (InputClassType t : InputClassType.values()) {
            if (t.type == type) {
                try {
                    return Class.forName(t.classType);
                }catch (Exception ex){
                    return null;
                }
            }
        }
        throw new ManuAppException("Can't get the corresponding input type");
    }

    public int getType() {
        return type;
    }

    public Class getClassType() {
        try {
            return Class.forName(classType);
        }catch (Exception ex){
            return null;
        }
    }
}
