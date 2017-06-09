package org.jon.lv.solrserve.service;

import org.jon.lv.solrserve.exception.SolrServeException;
import org.jon.lv.solrserve.model.SolrQuery;
import org.jon.lv.solrserve.model.SolrServeQuery;
import org.jon.lv.pagination.Page;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 在使用范例
 * @Autowired
 * private SolrServeService solrServeService
 * @PostConstruct
 * private void init(){
 *     solrServeService.initSolrClient("people");
 * }
 */
public interface SolrServeService {

    /**
     * 初始化solr client
     * @param coreName 根据coreName初始化对应的core
     */
    Boolean initSolrClient(String coreName);

    /**
     * 添加索引
     * Map doc = new HashMap();
     * doc.put("id", "1000015"); //id 必不可少
     * doc.put("name", "测试");
     * doc.put("email", new String[]{"panyingkun@haowu.com", "lijunwei@haowu.com"});
     * ...
     * @param doc
     */
    Boolean addDocument(Map<String, ?> doc) throws SolrServeException;

    /**
     *  根据对象添加
     *  NewCore core = new NewCore();
     *  core.setId(1000017l); //id 必不可少
     *  core.setName("王某某7");
     *  core.setCity("上海");
     *  ...
     * @param obj
     */
     Boolean addBean(Object obj) throws SolrServeException;

    /**
     * 批量添加索引
     * @param docs
     */
     Boolean addDocuments(List<Map<String, ?>> docs) throws SolrServeException;

    /**
     * 根据对象集合添加
     * @param objs
     */
     Boolean addBeans(Collection<?> objs) throws SolrServeException;

    /**
     * json字符串方式添加单个/多个document
     * 单个示例:{"address":"宝山区呼兰路911弄6号楼好屋中国","city":"上海","email":["panyingkun@haowu.com","lijunwei@haowu.com"],"id":2000017,"name":"测试","phone":"15221833906"}
     * 多个示例:[{"address":"宝山区呼兰路911弄6号楼好屋中国","city":"上海","email":["panyingkun@haowu.com","lijunwei@haowu.com"],"id":2000017,"name":"测试","phone":"15221833906"},{"address":"宝山区呼兰路911弄6号楼好屋中国","city":"上海","email":["panyingkun@haowu.com","lijunwei@haowu.com"],"id":2000017,"name":"测试","phone":"15221833906"}]
     * @param jsonStr
     * @throws SolrServeException
     */
     Boolean addJson(String jsonStr) throws SolrServeException;

    /**
     * 更新索引
     * 参考新增
     * @param doc
     */
     Boolean updateDocument(Map<String, ?> doc) throws SolrServeException;

    /**
     * 更新索引
     * 参考新增
     * @param obj
     * @throws SolrServeException
     */
     Boolean updateBean(Object obj) throws SolrServeException;

    /**
     * 批量更新索引
     * @param docs
     */
     Boolean updateDocuments(List<Map<String, ?>> docs) throws SolrServeException;

    /**
     * json字符串方式更新单个/多个document
     * 参考新增
     * @param jsonStr
     * @throws SolrServeException
     */
     Boolean updateJson(String jsonStr) throws SolrServeException;

    /**
     * 根据对象集合更新
     * @param objs
     */
     Boolean updateBeans(Collection<?> objs) throws SolrServeException;

    /**
     * 根据Id删除索引
     * @param id
     */
     Boolean deleteIndexById(Long id) throws SolrServeException;

    /**
     * 根据Id批量删除索引
     * @param ids
     */
     Boolean deleteIndexByIds(List<Long> ids) throws SolrServeException;

    /**
     * 根据solr查询条件删除索引
     * 示例:query = "name:张* AND age:{* TO 20]"
     * @param query
     */
     Boolean deleteBySolrQuery(String query) throws SolrServeException;

    /**
     * 根据solr查询条件删除索引
     * @param query
     */
     Boolean deleteBySolrQuery(SolrServeQuery query) throws SolrServeException;

    /**
     * 根据ID查找
     * @param id
     * @return
     */
     String getJsonById(Long id) throws SolrServeException;

    /**
     * 根据ID查找对象
     * @param id
     * @param clzz
     * @param <T>
     * @return
     * @throws SolrServeException
     */
     <T> T getById(Long id, Class<T> clzz) throws SolrServeException;

    /**
     * 根据ID批量查找
     * @param ids
     * @return
     */
     String getJsonByIds(List<Long> ids) throws SolrServeException;

    /**
     * 根据ID查找对象
     * @param ids
     * @param clzz
     * @param <T>
     * @return
     * @throws SolrServeException
     */
     <T> List<T> getByIds(List<Long> ids, Class<T> clzz) throws SolrServeException;

    /**
     * 根据条件查找
     * @param query
     * @return
     */
     String queryJsonByParams(SolrServeQuery query) throws SolrServeException;

    /**
     * 使用solr条件查询
     * @param solrQuery
     * @return
     * @throws SolrServeException
     */
     String queryJsonBySolrString(SolrQuery solrQuery) throws SolrServeException;

    /**
     * 根据查询参数返回List结果
     * @param query
     * @param clzz
     * @param <T>
     * @return
     * @throws SolrServeException
     */
     <T> List<T> queryListByParams(SolrServeQuery query, Class<T> clzz) throws SolrServeException;

    /**
     * 根据solr查询字符串返回List结果
     * @param solrQuery
     * @param clzz
     * @param <T>
     * @return
     * @throws SolrServeException
     */
     <T> List<T> queryListBySolrString(SolrQuery solrQuery, Class<T> clzz) throws SolrServeException;

    /**
     * 分页返回结果
     * @param query
     * @param <T>
     * @return
     * @throws SolrServeException
     */
     <T> Page<T> queryPage(SolrServeQuery query, Class<T> clzz) throws SolrServeException;

    /**
     * 分页返回结果
     * @param solrQuery
     * @param <T>
     * @return
     * @throws SolrServeException
     */
     <T> Page<T> queryPage(SolrQuery solrQuery, Class<T> clzz) throws SolrServeException;
}
