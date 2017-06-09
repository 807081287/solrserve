package org.jon.lv.solrserve.exception;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/26              1.0            创建文件
 */
public class SolrServeException extends Exception{
    public SolrServeException() {
        super();
    }

    public SolrServeException(String message) {
        super(message);
    }

    public SolrServeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SolrServeException(Throwable cause) {
        super(cause);
    }

    protected SolrServeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
