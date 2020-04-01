package com.datatransfer.api.transfer.base;

import java.util.List;
import java.util.Map;

/**
 * 过滤器基类
 * @author  panxiaobo
 * date 2019/8/30 15:36
 */

public abstract class AbstractFilter {

    public abstract void filter(Map<String,Object> config, List<Map<String,Object>> data);
}
