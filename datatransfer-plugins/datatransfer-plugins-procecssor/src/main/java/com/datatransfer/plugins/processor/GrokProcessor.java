package com.datatransfer.plugins.processor;

import com.datatransfer.api.transfer.base.AbstractDataSource;
import com.datatransfer.api.transfer.base.AbstractProcess;

import java.util.List;
import java.util.Map;

public class GrokProcessor extends AbstractProcess {
    @Override
    protected void execute(List<Map<String, Object>> data, AbstractDataSource dataSource) {

    }

    @Override
    public Map<String, Object> init(AbstractDataSource dataSource) {
        return null;
    }
}
