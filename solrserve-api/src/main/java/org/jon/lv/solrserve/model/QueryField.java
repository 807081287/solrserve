package org.jon.lv.solrserve.model;

import org.jon.lv.solrserve.enums.LogicType;
import org.jon.lv.solrserve.enums.ParamType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/27              1.0            创建文件
 */
public final class QueryField implements Serializable {

    private static final long serialVersionUID = 2910365492384424107L;
    /* 查询域名称 */
    private String filedName;
    /* 参数值 */
    private Object value;
    /* 参数类型 */
    private ParamType paramType;
    /* 默认逻辑与查询 */
    private LogicType logicType = LogicType.DEFAULT;

    public QueryField(String filedName, String value, ParamType paramType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
    }

    public QueryField(String filedName, String value, ParamType paramType, LogicType logicType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
        this.logicType = logicType;
    }

    public QueryField(String filedName, Integer value, ParamType paramType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
    }

    public QueryField(String filedName, Integer value, ParamType paramType, LogicType logicType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
        this.logicType = logicType;
    }

    public QueryField(String filedName, Long value, ParamType paramType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
    }

    public QueryField(String filedName, Long value, ParamType paramType, LogicType logicType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
        this.logicType = logicType;
    }

    public QueryField(String filedName, Double value, ParamType paramType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
    }

    public QueryField(String filedName, Double value, ParamType paramType, LogicType logicType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
        this.logicType = logicType;
    }

    public QueryField(String filedName, Float value, ParamType paramType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
    }

    public QueryField(String filedName, Float value, ParamType paramType, LogicType logicType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
        this.logicType = logicType;
    }

    public QueryField(String filedName, Date value, ParamType paramType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
    }

    public QueryField(String filedName, Date value, ParamType paramType, LogicType logicType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
        this.logicType = logicType;
    }

    public QueryField(String filedName, List<String> value, ParamType paramType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
    }

    public QueryField(String filedName, List<String> value, ParamType paramType, LogicType logicType) {
        this.filedName = filedName;
        this.value = value;
        this.paramType = paramType;
        this.logicType = logicType;
    }

    public Object getValue() {
        return value;
    }

    public String getFiledName() {
        return filedName;
    }

    public ParamType getParamType() {
        return paramType;
    }

    public void setLogicType(LogicType logicType) {
        this.logicType = logicType;
    }

    public LogicType getLogicType() {
        return logicType;
    }
}
