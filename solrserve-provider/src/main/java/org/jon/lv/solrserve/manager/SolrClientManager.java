package org.jon.lv.solrserve.manager;

import org.jon.lv.solrserve.bean.SolrServeClient;
import org.jon.lv.solrserve.exception.SolrServeException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.apache.solr.common.StringUtils;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/26              1.0            创建文件
 */
public class SolrClientManager {

    private static String MASTER = null;
    private static String SLAVE = null;
    private static String CORENAME = null;

    private static Map<String, SolrServeClient> solrServeClientMap = new HashMap<String, SolrServeClient>(5);

    public SolrClientManager(String master, String slave,String coreName){
        this.MASTER = master;
        this.SLAVE = slave;
        this.CORENAME = coreName;
    }

    /**
     * 初始化solr serve 服务
     * @throws SolrServeException
     */
    public void init() throws SolrServeException{
        if(StringUtils.isEmpty(MASTER) || StringUtils.isEmpty(SLAVE) || StringUtils.isEmpty(CORENAME))
        throw new SolrServeException("slor serve 参数不能为空,请检查配置!!!");
        String[] cores = CORENAME.split(",");
        for (String core: cores){
            String master_url = MASTER + core;
            String slave_url = SLAVE + core;
            HttpSolrClient masterClient = new HttpSolrClient(master_url);
            LBHttpSolrClient slaveClients = null;
            try {
                slaveClients = new LBHttpSolrClient(slave_url);
            } catch (MalformedURLException e) {
                throw new SolrServeException("slave_url 初始化错误!!!");
            }

            solrServeClientMap.put(core, new SolrServeClient(masterClient, slaveClients));
        }
    }

    /**
     * 根据core name获取solr服务
     * @param coreName
     * @return
     */
    public  SolrServeClient getSolrServeClient(String coreName){
        return solrServeClientMap.get(coreName);
    }


    /**
     * 获取solr 主服务
     * @param coreName
     * @return
     */
    public HttpSolrClient getMasterClient(String coreName){
        return solrServeClientMap.get(coreName).getMasterClient();
    }

    /**
     * 获取solr从服务
     * @param coreName
     * @return
     */
    public LBHttpSolrClient getSlaveClients(String coreName){
        return solrServeClientMap.get(coreName).getSlaveClients();
    }
}
