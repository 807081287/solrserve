package org.jon.lv.solrserve.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/25              1.0            创建文件
 */
public class DateHelper {


    public final static String pattern_default = "yyyy-MM-dd HH:mm:ss";

    public final static String fromate_solr = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static SimpleDateFormat sdf = new SimpleDateFormat(fromate_solr);


    /**
     * 字符串转换日期格式
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date formateStrToDate(String dateStr, String pattern){
        if(pattern == null || "".equals(pattern)){
            pattern = pattern_default;
        }

        SimpleDateFormat sd = new SimpleDateFormat(pattern);

        try {
            return  sd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转换成solr日期格式
     * @param date
     * @return
     */
    public static String formateDateToSolrDate(Date date){

        return sdf.format(date);
    }

    /**
     * solr日期格式转换成日期
     * @param date
     * @return
     */
    public static Date formateSolrDate(String date){

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws ParseException {

        Date date = formateStrToDate("2012-11-06 01:33:33", "");
        System.out.println(date);
        System.out.println(formateSolrDate("2012-11-06T01:33:33Z"));
        System.out.println(formateDateToSolrDate(date));

    }

}
