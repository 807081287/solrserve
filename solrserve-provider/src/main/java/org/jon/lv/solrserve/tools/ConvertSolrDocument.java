package org.jon.lv.solrserve.tools;

import org.apache.solr.common.SolrInputDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/26              1.0            创建文件
 */
public class ConvertSolrDocument {

    public static SolrInputDocument convertMap2SolrDocument(Map<String, ?> doc){

        if(doc == null || doc.size() == 0)
            return null;
        SolrInputDocument document = new SolrInputDocument();
        for(String key: doc.keySet()){
            document.addField(key, doc.get(key));
        }

        return document;
    }

    public static List<SolrInputDocument> convertMap2SolrDocument(List<Map<String, ?>> docs){

        if(docs == null || docs.size() == 0)
            return null;
        List<SolrInputDocument> inputDocuments = new ArrayList<SolrInputDocument>(docs.size());
        for(Map<String, ?> doc: docs){
            inputDocuments.add(convertMap2SolrDocument(doc));
        }

        return inputDocuments;
    }


}
