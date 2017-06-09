package org.jon.lv.solrserve.bean;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;

import java.io.Serializable;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/26              1.0            创建文件
 */
public class SolrServeClient implements Serializable {
    private static final long serialVersionUID = 3026136053732278030L;

    private HttpSolrClient masterClient;
    private LBHttpSolrClient slaveClients;

    public SolrServeClient() {
    }

    public SolrServeClient(HttpSolrClient masterClient, LBHttpSolrClient slaveClients) {
        this.masterClient = masterClient;
        this.slaveClients = slaveClients;
    }

    public HttpSolrClient getMasterClient() {
        return masterClient;
    }

    public void setMasterClient(HttpSolrClient masterClient) {
        this.masterClient = masterClient;
    }

    public LBHttpSolrClient getSlaveClients() {
        return slaveClients;
    }

    public void setSlaveClients(LBHttpSolrClient slaveClients) {
        this.slaveClients = slaveClients;
    }
}
