package com.datatransfer.api.transfer.base;

import java.util.List;
import java.util.Map;

/**
 * 消息通知
 */
public interface INotice {

    void noticeDataFinish(List<Map> dataList);
}
