package org.jon.lv.solrserve.model;

import java.io.Serializable;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/12/1              1.0            创建文件
 */
public class SolrQuery implements Serializable {
    private static final long serialVersionUID = 1066454336006335403L;

    /* solr查询链接字符q */
    private String queryString;
    /*solr过滤链接字符fq */
    private String filterString;
    /* 指定返回那些字段内容，用逗号或空格分隔多个 */
    private String showFileds;
    /* 排序 */
    private SortField[] sortFields;
    /* 开始位置 */
    private Integer pageNum = 1;
    /* 分页个数 默认10条 */
    private Integer pageSize = 10;

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
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
