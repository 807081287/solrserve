package org.jon.lv.solrserve.enums;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/27              1.0            创建文件
 */
public enum ParamType {

    NORMAL("=", "默认值"),
    GT(">", "大于"), GE(">=", "大于等于"),
    LT("<", "小于"), LE("<=", "小于等于"),
    PF("*_", "前模糊"), BF("_*", "后模糊"), WF("*_*", "全模糊"),
    NOT("* NOT", "不在范围内");

    private String filed;
    private String text;

    ParamType(String filed, String text){
        this.filed = filed;
        this.text = text;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
