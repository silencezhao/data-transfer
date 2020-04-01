package com.datatransfer.api.transfer.base;

import com.datatransfer.enumer.InputClassType;
import com.datatransfer.enumer.OutputClassType;
import com.datatransfer.enumer.ProcessClassType;
import com.datatransfer.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据源基类
 * @author  panxiaobo
 * date 2019/8/30 17:12
 */

public abstract class AbstractDataSource implements Runnable{


    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    //数据源id
    private int id;
    //数据源名
    private String name;
    //读取间隔时间
    private int interval=60;
    //默认初始值，add by zhaoheng
    private String defaultOffsetValue;
    //接入的表名,add buy zhaoheng
    private String tableName;
    //处理类
    private String processes;
    //输出类
    private String outputs;
    //输入类
    private String input;


    private transient  List<AbstractProcess> processeList=new ArrayList<>();
    private transient List<AbstractOutput> outputList=new ArrayList<>();
    private transient  AbstractInput inputObj;


    public void init(){
        logger.info("Start initializing the data source with the data source ID '{}'",this.id);
        logger.info("The data source configuration information is : \ninit input={},\nprocess={},\noutput={}",input,processes,outputs);
        AbstractInput object= JsonUtil.readValue(input,AbstractInput.class);
        inputObj= (AbstractInput) JsonUtil.readValue(input,InputClassType.getClass(object.getType()));
        inputObj.init(this);
        if(StringUtils.isNotEmpty(this.processes)) {
            List<Map> processStrs = JsonUtil.readValue(this.processes, List.class);
            for (Map process : processStrs) {
                int type=Integer.parseInt(process.get("type").toString());
                AbstractProcess processObj = (AbstractProcess) JsonUtil.readValue(JsonUtil.toJSon(process), ProcessClassType.getClass(type));
                processObj.init(this);
                processeList.add(processObj);
            }
        }
        if(StringUtils.isNotEmpty(this.outputs)) {
            List<Map> outputStrs = JsonUtil.readValue(this.outputs,List.class);
            for (Map output : outputStrs) {
                int type=Integer.parseInt(output.get("type").toString());
                AbstractOutput outputObj = (AbstractOutput) JsonUtil.readValue(JsonUtil.toJSon(output), OutputClassType.getClass(type));
                outputObj.init(this);
                outputList.add(outputObj);
            }
        }
        logger.info("Initialize the data source successfully, the data source ID is '{}'",this.id);
    }

    /**
     *执行
     */
    public void execute(){
        List<Map<String,Object>> datas=new ArrayList<>();
        inputObj.doExcute(datas,this);
        for(AbstractProcess process:processeList){
            process.doExcute(datas,this);
        }
        for(AbstractOutput output:outputList){
            output.doExcute(datas,this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcesses() {
        return processes;
    }

    public void setProcesses(String processes) {
        this.processes = processes;
    }

    public String getOutputs() {
        return outputs;
    }

    public void setOutputs(String outputs) {
        this.outputs = outputs;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getDefaultOffsetValue() {
        return defaultOffsetValue;
    }

    public void setDefaultOffsetValue(String defaultOffsetValue) {
        this.defaultOffsetValue = defaultOffsetValue;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
