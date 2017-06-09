package org.jon.lv.solrserve.enums;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/27              1.0            创建文件
 */

/**
 * Solr 逻辑与逻辑或即AND OR查询
 */
public enum LogicType {

    DEFAULT("AND", "默认与"), AND("AND", "与"), OR("OR", "或");

    private String type;
    private String text;

    LogicType(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
