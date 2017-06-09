package org.jon.lv.solrserve.tools;

import org.apache.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/30              1.0            创建文件
 */
public class Entity2SolrInputDocument {

    private static final Logger LOG = Logger.getLogger(Entity2SolrInputDocument.class);
    /**
     * 实体类与SolrInputDocument转换
     *
     * @param obj
     *                         实体对象
     * @return SolrInputDocument
     *                         SolrInputDocument对象
     */
    public static SolrInputDocument entity2SolrInputDocument(Object obj) {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            Field[] filedArrays = cls.getDeclaredFields();                        //获取类中所有属性
            Method m = null;
            SolrInputDocument sid = new SolrInputDocument();
            for (Field f : filedArrays) {
                //因为如果对象序列化之后,会增加该属性,不用对该属性进行反射
                if(!f.getName().equals("serialVersionUID")){
                    try {
                        //跟进属性xx构造对应的getXx()方法
                        String dynamicGetMethod = EntityConvert.dynamicMethodName(f.getName(), "get");
                        //调用构造的getXx()方法
                        m = cls.getMethod(dynamicGetMethod);
                        //属性名,与对应的属性值 get方法获取到的值
                        LOG.info(f.getName() + ":" + m.invoke(obj));
                        sid.addField( f.getName(), m.invoke(obj));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }

            }
            return sid;
        }
        LOG.warn("Object to convert is null.");
        return null;
    }

    public static Collection<SolrInputDocument> entity2SolrInputDocument(Collection<?> objects){
        if(objects != null && objects.size() > 0){
           LinkedList<SolrInputDocument> documents = new LinkedList<SolrInputDocument>();
           for(Object obj: objects){
               documents.add(entity2SolrInputDocument(obj));
           }

            return documents;
        }
        return null;
    }
}
