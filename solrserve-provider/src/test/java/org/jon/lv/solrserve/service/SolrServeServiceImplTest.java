package org.jon.lv.solrserve.service;

import com.alibaba.fastjson.JSON;
import org.jon.lv.solrserve.JunitBaseTest;
import org.jon.lv.solrserve.NewCore;
import org.jon.lv.solrserve.enums.ParamType;
import org.jon.lv.solrserve.enums.SortType;
import org.jon.lv.solrserve.exception.SolrServeException;
import org.jon.lv.solrserve.model.QueryField;
import org.jon.lv.solrserve.model.SolrQuery;
import org.jon.lv.solrserve.model.SolrServeQuery;
import org.jon.lv.solrserve.model.SortField;
import org.jon.lv.pagination.Page;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolrServeServiceImplTest extends JunitBaseTest {

    @Autowired
    private SolrServeService solrServeService;

    @Before
    public void before() {
        solrServeService.initSolrClient("new_core");
    }

    @Test
    public void testAddDocument() throws Exception {
        Map doc = new HashMap();
        doc.put("id", "1100013");
        doc.put("name", "张哟哟");
        doc.put("phone", "15221833906");
        doc.put("email", new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        doc.put("city", "上海");
        doc.put("address", "宝山区呼兰路911弄6号楼好屋中国");
        doc.put("text", "减肥是劳动法");

        solrServeService.addDocument(doc);
    }


    @Test
    public void testAddBean() throws Exception {
        NewCore core = new NewCore();
        core.setId(1000014l);
        core.setName("王某某");
        core.setPhone("15221833906");
        core.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core.setCity("上海");
        core.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core.setText("减肥是劳动法");

        solrServeService.addBean(core);

    }

    @Test
    public void testAddDocuments() throws Exception {
        List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();

        Map doc = new HashMap();
        doc.put("id", "1000015");
        doc.put("name", "张哟哟1");
        doc.put("phone", "15221833906");
        doc.put("email", new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        doc.put("city", "上海");
        doc.put("address", "宝山区呼兰路911弄6号楼好屋中国");
        doc.put("text", "减肥是劳动法");

        list.add(doc);
        Map doc2 = new HashMap();
        doc2.put("id", "1000016");
        doc2.put("name", "张哟哟2");
        doc2.put("phone", "15221833906");
        doc2.put("email", new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        doc2.put("city", "上海");
        doc2.put("address", "宝山区呼兰路911弄6号楼好屋中国");
        doc2.put("text", "减肥是劳动法");

        list.add(doc2);

        solrServeService.addDocuments(list);
    }

    @Test
    public void testAddBeans() throws Exception {
        NewCore core = new NewCore();
        core.setId(1000017l);
        core.setName("王某某7");
        core.setPhone("15221833906");
        core.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core.setCity("上海");
        core.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core.setText("减肥是劳动法");

        NewCore core2 = new NewCore();
        core2.setId(1000018l);
        core2.setName("王某某8");
        core2.setPhone("15221833906");
        core2.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core2.setCity("上海");
        core2.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core2.setText("减肥是劳动法");

        List<NewCore> newCores = new ArrayList<NewCore>();
        newCores.add(core);
        newCores.add(core2);

        solrServeService.addBeans(newCores);
    }
    @Test
    public void testAddJson() throws Exception {
        NewCore core = new NewCore();
        core.setId(2000017L);
        core.setName("刘某某7");
        core.setPhone("15221833906");
        core.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core.setCity("上海");
        core.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core.setText("减肥是劳动法");

        solrServeService.addJson(JSON.toJSONString(core));
    }

    @Test
    public void testAddJsons() throws Exception {
        NewCore core = new NewCore();
        core.setId(2000019L);
        core.setName("刘某某7");
        core.setPhone("15221833906");
        core.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core.setCity("上海");
        core.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core.setText("减肥是劳动法");

        NewCore core2 = new NewCore();
        core2.setId(2000018L);
        core2.setName("刘某某8");
        core2.setPhone("15221833906");
        core2.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core2.setCity("上海");
        core2.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core2.setText("减肥是劳动法");

        List<NewCore> newCores = new ArrayList<NewCore>();
        newCores.add(core);
        newCores.add(core2);

        solrServeService.addJson(JSON.toJSONString(newCores));
    }

    @Test
    public void testUpdateDocument() throws Exception {
        Map doc = new HashMap();
        doc.put("id", "1000013");
        doc.put("name", "张哟哟3");
        doc.put("phone", "15221833906");
        doc.put("email", new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        doc.put("city", "上海");
        doc.put("address", "宝山区呼兰路911弄6号楼好屋中国");
        doc.put("text", "减肥是劳动法");

        solrServeService.updateDocument(doc);
    }

    @Test
    public void testUpdateBean() throws Exception {
        NewCore core = new NewCore();
        core.setId(1000014l);
        core.setName("王某某44444");
        core.setPhone("15221838906");
        core.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core.setCity("上海");
        core.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core.setText("减肥是劳动法");

        solrServeService.updateBean(core);
    }

    @Test
    public void testUpdateDocuments() throws Exception {
        List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();

        Map doc = new HashMap();
        doc.put("id", "1000015");
        doc.put("name", "张哟哟155555555555555");
        doc.put("phone", "15221833906");
        doc.put("email", new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        doc.put("city", "上海");
        doc.put("address", "宝山区呼兰路911弄6号楼好屋中国");
        doc.put("text", "减肥是劳动法");

        list.add(doc);
        Map doc2 = new HashMap();
        doc2.put("id", "1000016");
        doc2.put("name", "张哟哟255666666666666");
        doc2.put("phone", "15221833906");
        doc2.put("email", new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        doc2.put("city", "上海");
        doc2.put("address", "宝山区呼兰路911弄6号楼好屋中国");
        doc2.put("text", "减肥是劳动法");

        list.add(doc2);

        solrServeService.updateDocuments(list);
    }

    @Test
    public void testUpdateBeans() throws Exception {
        NewCore core = new NewCore();
        core.setId(1000017l);
        core.setName("王某某7");
        core.setPhone("15221833906");
        core.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core.setCity("上海");
        core.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core.setText("减肥是劳动法");

        NewCore core2 = new NewCore();
        core2.setId(1000018l);
        core2.setName("王某某8");
        core2.setPhone("15221833906");
        core2.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core2.setCity("上海");
        core2.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core2.setText("减肥是劳动法");

        List<NewCore> newCores = new ArrayList<NewCore>();
        newCores.add(core);
        newCores.add(core2);

        solrServeService.updateBeans(newCores);
    }

    @Test
    public void testUpdateJson() throws Exception {
        NewCore core = new NewCore();
        core.setId(2000017L);
        core.setName("刘某某2000017");
        core.setPhone("15221833906");
        core.setEmail(new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
        core.setCity("上海");
        core.setAddress("宝山区呼兰路911弄6号楼好屋中国");
        core.setText("减肥是劳动法");

        solrServeService.addJson(JSON.toJSONString(core));
    }

    @Test
    public void testDeleteIndexById() throws Exception {

        solrServeService.deleteIndexById(1000014L);
    }

    @Test
    public void testDeleteIndexByIds() throws Exception {
        List<Long> list = new ArrayList<Long>();
        list.add(1000017L);
        list.add(1000018L);
        solrServeService.deleteIndexByIds(list);
    }

    @Test
    public void testDeleteBySolrQuery() throws Exception {
        solrServeService.deleteBySolrQuery("id:1000015");
    }

    @Test
    public void testDeleteBySolrQuery1() throws Exception {
        SolrServeQuery query = new SolrServeQuery();
        query.setFields(new QueryField("id", "1000016", ParamType.NORMAL));
        solrServeService.deleteBySolrQuery(query);
    }

    @Test
    public void testGetJsonById() throws Exception {
        System.out.println(solrServeService.getJsonById(1000013L));
    }

    @Test
    public void testGetById() throws Exception {
        NewCore newCore = solrServeService.getById(1000013L, NewCore.class);
        System.out.println(newCore.getName());
        System.out.println(newCore.getAddress());
        System.out.println(newCore.getId());
        System.out.println(newCore.getEmail());
    }

    @Test
    public void testGetJsonByIds() throws Exception {
        List<Long> ids = new ArrayList<Long>();
        ids.add(1000013L);
        ids.add(1000012L);
        System.out.println(solrServeService.getJsonByIds(ids));
    }

    @Test
    public void testGetByIds() throws Exception {
        List<Long> ids = new ArrayList<Long>();
        ids.add(1000013L);
        ids.add(1000012L);
        List<NewCore> newCores = solrServeService.getByIds(ids, NewCore.class);
        for (NewCore core : newCores) {
            System.out.println(core.getId());
            System.out.println(core.getName());
            System.out.println(core.getAddress());
        }
    }

    @Test
    public void testQueryJsonByParams() throws Exception {
        SolrServeQuery query = new SolrServeQuery();
        query.setFields(new QueryField("name", "张", ParamType.PF));
//        query.setFilterFields(new QueryField("city", "上海", ParamType.NORMAL), new QueryField("name", "1", ParamType.PF));
//        query.setFilterFields(new QueryField("id", 10, ParamType.NORMAL));

        query.setPageSize(2);
        query.setPageNum(2);
//        query.setKeyword("强燕");
        query.setSortFields(new SortField("id", SortType.DESC));
        query.setShowFileds("id,name city");

        try {
            String str = solrServeService.queryJsonByParams(query);
            System.out.println(str);
        } catch (SolrServeException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testQueryJsonBySolrString() throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQueryString("city:上海");
        query.setFilterString("name : *4");

//        query.setPageSize(2);
//        query.setPageNum(2);

        query.setSortFields(new SortField("id", SortType.DESC));
        query.setShowFileds("id,name city");

        try {
            String str = solrServeService.queryJsonBySolrString(query);
            System.out.println(str);
        } catch (SolrServeException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testQueryListByParams() throws Exception {
        SolrServeQuery query = new SolrServeQuery();
        query.setFields(new QueryField("name", "张", ParamType.PF));
//        query.setFilterFields(new QueryField("city", "上海", ParamType.NORMAL), new QueryField("name", "1", ParamType.PF));
//        query.setFilterFields(new QueryField("id", 10, ParamType.NORMAL));

        query.setPageSize(2);
        query.setPageNum(2);
//        query.setKeyword("强燕");
        query.setSortFields(new SortField("id", SortType.DESC));
        query.setShowFileds("id,name city");

        try {
            List<NewCore> list = solrServeService.queryListByParams(query, NewCore.class);
            for (NewCore core : list) {
                System.out.println(core.getId());
                System.out.println(core.getName());
                System.out.println(core.getCity());
            }
        } catch (SolrServeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryListBySolrString() throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQueryString("city:上海");
        query.setFilterString("name : *4");

//        query.setPageSize(2);
//        query.setPageNum(2);

        query.setSortFields(new SortField("id", SortType.DESC));
        query.setShowFileds("id,name city");

        try {
            List<NewCore> list = solrServeService.queryListBySolrString(query, NewCore.class);
            for (NewCore core : list) {
                System.out.println(core.getId());
                System.out.println(core.getName());
                System.out.println(core.getCity());
            }
        } catch (SolrServeException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testQueryPage1() throws Exception {
        SolrServeQuery query = new SolrServeQuery();
        query.setFields(new QueryField("name", "张", ParamType.PF));
//        query.setFilterFields(new QueryField("city", "上海", ParamType.NORMAL), new QueryField("name", "1", ParamType.PF));
//        query.setFilterFields(new QueryField("id", 10, ParamType.NORMAL));

        query.setPageSize(2);
        query.setPageNum(2);
//        query.setKeyword("强燕");
        query.setSortFields(new SortField("id", SortType.DESC));
        query.setShowFileds("id,name city");

        try {
            Page<NewCore> page = solrServeService.queryPage(query, NewCore.class);

            List<NewCore> cores = page.getData();
            for (NewCore core : cores) {
                System.out.println(core.getId());
                System.out.println(core.getName());
                System.out.println(core.getCity());
            }
        } catch (SolrServeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryPage2() throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQueryString("city:上海");
        query.setFilterString("name : *4");

//        query.setPageSize(2);
//        query.setPageNum(2);

        query.setSortFields(new SortField("id", SortType.DESC));
        query.setShowFileds("id,name city");

        try {
            Page<NewCore> page = solrServeService.queryPage(query, NewCore.class);

            List<NewCore> cores = page.getData();
            for (NewCore core : cores) {
                System.out.println(core.getId());
                System.out.println(core.getName());
                System.out.println(core.getCity());
            }
        } catch (SolrServeException e) {
            e.printStackTrace();
        }
    }

}