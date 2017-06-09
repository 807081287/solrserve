package org.jon.lv.solrserve.tools;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/30              1.0            创建文件
 */
public class EntityConvert {
    public static String dynamicMethodName(String paramName, String methodType){
        return (new StringBuilder()).append(methodType)
                .append(Character.toUpperCase(paramName.charAt(0)))
                .append(paramName.substring(1)).toString();
    }

}
