package org.jon.lv.solrserve.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jon.lv.solrserve.exception.SolrServeException;
import org.apache.http.util.TextUtils;
import org.apache.solr.common.SolrInputDocument;

import java.util.*;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/30              1.0            创建文件
 */
public class JSONUtils {

    /**
     * 字符串转换成对象
     * @param jsonStr
     * @return
     * @throws SolrServeException
     */
    public static Collection<SolrInputDocument> parseJSON2Collection(String jsonStr) throws SolrServeException {

        LinkedList<SolrInputDocument> collection = new LinkedList<SolrInputDocument>();

        JSON_TYPE type = getJSONType(jsonStr);
        switch (type){
            case JSON_TYPE_OBJECT:
                collection.add(parseJSON2Document(jsonStr));
                break;
            case JSON_TYPE_ARRAY:
                collection.addAll(parseJSON2Array(jsonStr));
                break;
            case JSON_TYPE_ERROR:
                throw  new SolrServeException("非法的json字符串！！");
        }

        return collection;
    }


    /**
     * 字符串转换成List<SolrDocument>
     * @param jsonStr
     * @return
     */
    public static List<SolrInputDocument> parseJSON2Array(String jsonStr) {
        JSONArray jsonArr = JSONArray.parseArray(jsonStr);
        List<SolrInputDocument> list = new ArrayList<SolrInputDocument>();
        Iterator<Object> it = jsonArr.iterator();
        while (it.hasNext()) {
            JSONObject json2 = (JSONObject) it.next();
            list.add(parseJSON2Document(json2.toString()));
        }
        return list;
    }

    /**
     * 字符串转换成 SolrDocument
     * @param jsonStr
     * @return
     */
    public static SolrInputDocument parseJSON2Document(String jsonStr) {

        SolrInputDocument document = new SolrInputDocument();
        //最外层解析
        JSONObject json = JSONObject.parseObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
           if (v instanceof JSONArray) {
                JSONArray list = new JSONArray();
                Iterator<Object> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    Object obj = it.next();
                    if(obj instanceof  String || obj instanceof Double
                       || obj instanceof Integer || obj instanceof Float
                       || obj instanceof Long || obj instanceof Boolean
                       || obj instanceof Date){
                        list.add(JSON.toJSONString(obj));
                    }else{
                        list.add(parseJSON2Document(JSON.toJSONString(obj)));
                    }
                }
                document.addField(k.toString(), list);
            } else {
                document.addField(k.toString(), v);
            }
        }

        return document;
    }


    /**
     * 获取JSON类型
     * 判断规则
     * 判断第一个字母是否为{或[ 如果都不是则不是一个JSON格式的文本
     *
     * @param str
     * @return
     */
    public static JSON_TYPE getJSONType(String str) {
        if (TextUtils.isEmpty(str)) {
            return JSON_TYPE.JSON_TYPE_ERROR;
        }

        final char[] strChar = str.substring(0, 1).toCharArray();
        final char firstChar = strChar[0];

        if (firstChar == '{') {
            return JSON_TYPE.JSON_TYPE_OBJECT;
        } else if (firstChar == '[') {
            return JSON_TYPE.JSON_TYPE_ARRAY;
        } else {
            return JSON_TYPE.JSON_TYPE_ERROR;
        }
    }

    public enum JSON_TYPE {
        /**
         * JSONObject
         */
        JSON_TYPE_OBJECT,
        /**
         * JSONArray
         */
        JSON_TYPE_ARRAY,
        /**
         * 不是JSON格式的字符串
         */
        JSON_TYPE_ERROR
    }

}
