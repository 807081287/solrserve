package org.jon.lv.solrserve.model;

import org.jon.lv.solrserve.enums.SortType;

import java.io.Serializable;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/12/1              1.0            创建文件
 */
public class SortField implements Serializable{
    private static final long serialVersionUID = -2926125874948021919L;

    /* 排序字段名称 */
    private String fieldName;
    /* 排序方式 */
    private SortType sortType;

    public SortField() {
    }
    public SortField(String fieldName, SortType sortType) {
        this.fieldName = fieldName;
        this.sortType = sortType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
}
