package com.datatransfer.plugins.sink;

import com.datatransfer.api.transfer.base.AbstractDataSource;
import com.datatransfer.api.transfer.base.AbstractOutput;

import java.util.List;
import java.util.Map;

public class OracleSink extends AbstractOutput {
    @Override
    protected void execute(List<Map<String, Object>> data, AbstractDataSource dataSource) {

    }

    @Override
    public Map<String, Object> init(AbstractDataSource dataSource) {
        return null;
    }
}
