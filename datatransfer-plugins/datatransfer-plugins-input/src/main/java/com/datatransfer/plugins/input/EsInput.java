package com.datatransfer.plugins.input;

import com.datatransfer.api.transfer.base.AbstractDataSource;
import com.datatransfer.api.transfer.base.AbstractInput;

import java.util.List;
import java.util.Map;

public class EsInput extends AbstractInput {
    @Override
    protected void execute(List<Map<String, Object>> data, AbstractDataSource dataSource) {

    }

    @Override
    public Map<String, Object> init(AbstractDataSource dataSource) {
        return null;
    }
}
