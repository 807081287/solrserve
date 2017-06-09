package org.jon.lv.solrserve.manager;

import org.jon.lv.solrserve.JunitBaseTest;
import org.jon.lv.solrserve.enums.ParamType;
import org.jon.lv.solrserve.exception.SolrServeException;
import org.jon.lv.solrserve.model.QueryField;
import org.jon.lv.solrserve.model.SolrServeQuery;
import org.jon.lv.solrserve.service.SolrServeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/26              1.0            创建文件
 */
public class SolrCilentManagerTest extends JunitBaseTest {
    @Autowired
    private SolrClientManager solrClientManager;


    @Autowired
    private SolrServeService solrServeService;

    @Test
    public void testInit() {


        solrServeService.initSolrClient("people");

        SolrServeQuery query = new SolrServeQuery();
        query.setFields(new QueryField("edlevel", 7, ParamType.NORMAL));
        query.setFilterFields(new QueryField("id", 10, ParamType.NORMAL));

        query.setPageSize(11);
//      query.setPageNum(2L);
//      query.setKeyword("强燕");
        try {
            String str = solrServeService.queryJsonByParams(query);
            System.out.println(str);
        } catch (SolrServeException e) {
            e.printStackTrace();
        }
    }
}
