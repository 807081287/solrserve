package org.jon.lv.solrserve.model;

import java.io.Serializable;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/26              1.0            创建文件
 */
public class SolrServeQuery implements Serializable{
    private static final long serialVersionUID = -8145769468872547468L;

    /* 关键字查询 */
    private String keyword;
    /* 对应字段域查询 */
    private QueryField[] fields;
    /* 对应字段域过滤 */
    private QueryField[] filterFields;
    /* 指定返回那些字段内容，用逗号或空格分隔多个 */
    private String showFileds;
    /* 排序 */
    private SortField[] sortFields;
    /* 开始位置 */
    private Integer pageNum;
    /* 分页个数 默认10条 */
    private Integer pageSize ;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public QueryField[] getFields() {
        return fields;
    }

    public void setFields(QueryField... fields) {
        this.fields = fields;
    }

    public QueryField[] getFilterFields() {
        return filterFields;
    }

    public void setFilterFields(QueryField... filterFields) {
        this.filterFields = filterFields;
    }

    public String getShowFileds() {
        return showFileds;
    }

    public void setShowFileds(String showFileds) {
        this.showFileds = showFileds;
    }

    public SortField[] getSortFields() {
        return sortFields;
    }

    public void setSortFields(SortField... sortFields) {
        this.sortFields = sortFields;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
