package org.jon.lv.solrserve.service;

import com.alibaba.fastjson.JSON;
import org.jon.lv.solrserve.exception.SolrServeException;
import org.jon.lv.solrserve.manager.SolrClientManager;
import org.jon.lv.solrserve.model.QueryField;
import org.jon.lv.solrserve.model.SolrQuery;
import org.jon.lv.solrserve.model.SolrServeQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.StringUtils;
import org.apache.solr.common.params.SolrParams;
import org.jon.lv.pagination.Page;
import org.jon.lv.solrserve.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/25              1.0            创建文件
 */
@Service
public class SolrServeServiceImpl implements SolrServeService {

    @Autowired
    private SolrClientManager solrClientManager;

    private HttpSolrClient masterClient = null;

    private LBHttpSolrClient slaveClients = null;

    @Override
    public Boolean initSolrClient(String coreName) {
        masterClient = solrClientManager.getMasterClient(coreName);
        slaveClients = solrClientManager.getSlaveClients(coreName);

        return true;
    }

    /**
     * 所有方法之前先做验证是否初始化solr client操作
     *
     * @throws SolrServeException
     */
    private void validate() throws SolrServeException {
        if (masterClient == null || slaveClients == null)
            throw new SolrServeException("请先执行initSolrClient方法初始化solr client!!!");
    }

    @Override
    public Boolean addDocument(Map<String, ?> doc) throws SolrServeException {
        validate();

        if(doc == null) return false;

        SolrInputDocument document = ConvertSolrDocument.convertMap2SolrDocument(doc);

        if (document == null) {
            throw new SolrServeException("doc 不能为空!!!");
        }

        try {
            masterClient.add(document);
            masterClient.commit();
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }

        return true;
    }

    @Override
    public Boolean addBean(Object obj) throws SolrServeException {
        validate();

        if(obj == null ) return false;

        SolrInputDocument document = Entity2SolrInputDocument.entity2SolrInputDocument(obj);

        if(document == null) return false;

        try {
            masterClient.add(document);
            masterClient.commit();
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }

        return true;
    }

    @Override
    public Boolean addDocuments(List<Map<String, ?>> docs) throws SolrServeException {
        validate();

        if(docs == null || docs.size() < 1) return false;

        List<SolrInputDocument> documents = ConvertSolrDocument.convertMap2SolrDocument(docs);
        if (documents == null || documents.size() < 1) {
            throw new SolrServeException("doc 不能为空!!!");
        }

        try {
            masterClient.add(documents);
            masterClient.commit();
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return true;
    }

    @Override
    public Boolean addBeans(Collection<?> objs) throws SolrServeException {
        validate();
        if(objs == null || objs.size() < 1) return false;
        Collection<SolrInputDocument> documents = Entity2SolrInputDocument.entity2SolrInputDocument(objs);

        if (documents == null || documents.size() < 1) return false;

        try {
            masterClient.add(documents);
            masterClient.commit();
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return true;
    }

    @Override
    public Boolean addJson(String jsonStr) throws SolrServeException {
        validate();
        if (StringUtils.isEmpty(jsonStr)) return false;

        Collection<SolrInputDocument> documents = JSONUtils.parseJSON2Collection(jsonStr);

        if (documents == null || documents.size() < 1) return false;

        try {
            masterClient.add(documents);
            masterClient.commit();
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return true;
    }

    @Override
    public Boolean updateDocument(Map<String, ?> doc) throws SolrServeException {
        addDocument(doc);
        return true;
    }

    @Override
    public Boolean updateBean(Object obj) throws SolrServeException {
        addBean(obj);
        return true;
    }

    @Override
    public Boolean updateDocuments(List<Map<String, ?>> docs) throws SolrServeException {
        addDocuments(docs);
        return true;
    }

    @Override
    public Boolean updateJson(String jsonStr) throws SolrServeException {
        addJson(jsonStr);
        return true;
    }

    @Override
    public Boolean updateBeans(Collection<?> objs) throws SolrServeException {
        addBeans(objs);
        return true;
    }

    @Override
    public Boolean deleteIndexById(Long id) throws SolrServeException {
        validate();
        if(id == null ) return false;
        try {
            masterClient.deleteById(String.valueOf(id));
            masterClient.commit();
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return true;
    }

    @Override
    public Boolean deleteIndexByIds(List<Long> ids) throws SolrServeException {
        validate();

        if (CollectionUtils.isEmpty(ids)) return false;

        try {
            List<String> listIds = new ArrayList<String>(ids.size());
            for (Long id : ids) {
                listIds.add(String.valueOf(id));
            }
            masterClient.deleteById(listIds);
            masterClient.commit();
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return true;
    }

    @Override
    public Boolean deleteBySolrQuery(String query) throws SolrServeException {
        validate();
        if(StringUtils.isEmpty(query)) return false;
        try {
            masterClient.deleteByQuery(query);
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return true;
    }

    @Override
    public Boolean deleteBySolrQuery(SolrServeQuery query) throws SolrServeException {
        validate();

        if(query == null || query.getFields() == null) return false;

        QueryField[] queryFields = query.getFields();

        if (queryFields != null && queryFields.length != 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("*:*");
            for (int i = 0; i < queryFields.length; i++) {
                QueryField queryField = queryFields[i];
                builder.append(ConvertSolrParams.convertQueryField2String(queryField));
            }
            try {
                masterClient.deleteByQuery(builder.toString());
                masterClient.commit();
            } catch (SolrServerException e) {
                throw new SolrServeException(e);
            } catch (IOException e) {
                throw new SolrServeException(e);
            }
        }
        return true;
    }

    @Override
    public String getJsonById(Long id) throws SolrServeException {
        validate();

        SolrDocument document;
        try {
            document = slaveClients.getById(String.valueOf(id));
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return JSON.toJSONString(document);
    }

    @Override
    public <T> T getById(Long id, Class<T> clzz) throws SolrServeException {
        validate();

        SolrDocument solrDocument;
        try {
            solrDocument = slaveClients.getById(String.valueOf(id));
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return SolrDocument2Entity.solrDocument2Entity(solrDocument, clzz);
    }

    @Override
    public String getJsonByIds(List<Long> ids) throws SolrServeException {
        validate();

        SolrDocumentList list;
        try {
            list = slaveClients.getById((Collection) ids);
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return JSON.toJSONString(list);
    }

    @Override
    public <T> List<T> getByIds(List<Long> ids, Class<T> clzz) throws SolrServeException {
        validate();

        SolrDocumentList list;
        try {
            list = slaveClients.getById((Collection) ids);
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        return SolrDocument2Entity.solrDocument2Entity(list, clzz);
    }

    @Override
    public String queryJsonByParams(SolrServeQuery query) throws SolrServeException {
        validate();

        SolrParams params = ConvertSolrParams.convertSolrServeQuery2SolrParams(query);
        QueryResponse response;
        try {
            response = slaveClients.query(params);
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        SolrDocumentList list = response.getResults();
        return JSON.toJSONString(list);
    }

    @Override
    public String queryJsonBySolrString(SolrQuery solrQuery) throws SolrServeException {
        validate();

        SolrParams params = ConvertSolrParams.convertSolrQuery2SolrParams(solrQuery);
        QueryResponse response;
        try {
            response = slaveClients.query(params);
        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        SolrDocumentList list = response.getResults();
        return JSON.toJSONString(list);
    }


    @Override
    public <T> List<T> queryListByParams(SolrServeQuery query, Class<T> clzz) throws SolrServeException {
        validate();

        SolrParams params = ConvertSolrParams.convertSolrServeQuery2SolrParams(query);
        QueryResponse response;
        try {
            response = slaveClients.query(params);

        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        SolrDocumentList list = response.getResults();
        return SolrDocument2Entity.solrDocument2Entity(list, clzz);
    }

    @Override
    public <T> List<T> queryListBySolrString(SolrQuery solrQuery, Class<T> clzz) throws SolrServeException {
        validate();

        SolrParams params = ConvertSolrParams.convertSolrQuery2SolrParams(solrQuery);
        QueryResponse response;
        try {
            response = slaveClients.query(params);

        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        SolrDocumentList list = response.getResults();
        return SolrDocument2Entity.solrDocument2Entity(list, clzz);
    }


    @Override
    public <T> Page<T> queryPage(SolrServeQuery query, Class<T> clzz) throws SolrServeException {
        validate();
        SolrParams params = ConvertSolrParams.convertSolrServeQuery2SolrParams(query);
        QueryResponse response;
        try {
            response = slaveClients.query(params);

        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        SolrDocumentList list = response.getResults();
        Page page = new Page();
        page.setData(SolrDocument2Entity.solrDocument2Entity(list, clzz));
        page.setPageSize(query.getPageSize());
        page.setPageNumber(query.getPageNum());
        page.setTotalSize(list.getNumFound());
        return page;
    }

    @Override
    public <T> Page<T> queryPage(SolrQuery solrQuery, Class<T> clzz) throws SolrServeException {
        validate();
        SolrParams params = ConvertSolrParams.convertSolrQuery2SolrParams(solrQuery);
        QueryResponse response;
        try {
            response = slaveClients.query(params);

        } catch (SolrServerException e) {
            throw new SolrServeException(e);
        } catch (IOException e) {
            throw new SolrServeException(e);
        }
        SolrDocumentList list = response.getResults();
        Page page = new Page();
        page.setData(SolrDocument2Entity.solrDocument2Entity(list, clzz));
        page.setPageSize(solrQuery.getPageSize());
        page.setPageNumber(solrQuery.getPageNum());
        page.setTotalSize(list.getNumFound());
        return page;
    }

}
