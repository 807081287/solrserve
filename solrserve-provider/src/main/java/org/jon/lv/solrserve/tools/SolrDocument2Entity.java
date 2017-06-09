package org.jon.lv.solrserve.tools;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.jon.lv.pagination.Page;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 吕斌               2015/11/30              1.0            创建文件
 */
public class SolrDocument2Entity {

    private static final Logger LOG = Logger.getLogger(SolrDocument2Entity.class);

    /**
     * SolrDocument与实体类转换
     *
     * @param document SolrDocument对象
     * @param clzz     泛型类
     * @return <T>
     * @author pudongping
     */
    public static <T> T solrDocument2Entity(SolrDocument document, Class<T> clzz) {
        if (null != document) {
            try {
                Object obj = clzz.newInstance();
                Method m = null;

                Class<?> fieldType = null;

                for (String fieldName : document.getFieldNames()) {

                    //需要说明的是返回的结果集中的FieldNames()比类属性多
                    Field[] filedArrays = clzz.getDeclaredFields();                        //获取类中所有属性
                    for (Field f : filedArrays) {
                        //如果实体属性名和查询返回集中的字段名一致,填充对应的set方法
                        if (f.getName().equals(fieldName)) {

                            //获取到的属性名
                            f = clzz.getDeclaredField(fieldName);

                            //属性类型
                            fieldType = f.getType();

                            //构造set方法名  setId
                            String dynamicSetMethod = EntityConvert.dynamicMethodName(f.getName(), "set");


                            if (fieldType.isArray()) {
                                //获取方法
                                List values = (List) document.getFieldValue(fieldName);
                                if (values != null) {
                                    Object[] objects = new Object[1];
                                    if (fieldType.equals(Integer[].class)) {
                                        objects[0] = values.toArray(new Integer[values.size()]);
                                    } else if (fieldType.equals(Float[].class)) {
                                        objects[0] = values.toArray(new Float[values.size()]);
                                    } else if (fieldType.equals(Double[].class)) {
                                        objects[0] = values.toArray(new Double[values.size()]);
                                    } else if (fieldType.equals(Boolean[].class)) {
                                        objects[0] = values.toArray(new Boolean[values.size()]);
                                    } else if (fieldType.equals(Short[].class)) {
                                        objects[0] = values.toArray(new Short[values.size()]);
                                    } else if (fieldType.equals(Long[].class)) {
                                        objects[0] = values.toArray(new Long[values.size()]);
                                    } else if (fieldType.equals(String[].class)) {
                                        objects[0] = values.toArray(new String[values.size()]);
                                    }
                                    m = clzz.getMethod(dynamicSetMethod, fieldType);
                                    m.setAccessible(true);
                                    m.invoke(obj, objects);
                                    LOG.info(f.getName() + "-->" + dynamicSetMethod + "=" + objects.toString());
                                }
                            } else {

                                //获取方法
                                m = clzz.getMethod(dynamicSetMethod, fieldType);
                                // 如果是 int, float等基本类型，则需要转型
                                if (fieldType.equals(Integer.TYPE)) {
                                    fieldType = Integer.class;
                                } else if (fieldType.equals(Float.TYPE)) {
                                    fieldType = Float.class;
                                } else if (fieldType.equals(Double.TYPE)) {
                                    fieldType = Double.class;
                                } else if (fieldType.equals(Boolean.TYPE)) {
                                    fieldType = Boolean.class;
                                } else if (fieldType.equals(Short.TYPE)) {
                                    fieldType = Short.class;
                                } else if (fieldType.equals(Long.TYPE)) {
                                    fieldType = Long.class;
                                } else if (fieldType.equals(String.class)) {
                                    fieldType = String.class;
                                } else if (fieldType.equals(Collection.class)) {
                                    fieldType = Collection.class;
                                }
                                m.invoke(obj, fieldType.cast(document.getFieldValue(fieldName)));
                                LOG.info(f.getName() + "-->" + dynamicSetMethod + "=" + fieldType.cast(document.getFieldValue(fieldName)));

                            }
                        }
                    }

                }
                return clzz.cast(obj);
            } catch (ClassCastException e) {
                LOG.error("请检查schema.xml中的各个field的数据类型与PO类的是否一致.", e);
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                LOG.error("请检查PO类中的field对应的各个setter和getter是否存在.", e);
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                LOG.error("请检查schema中的field是否不存在于PO类中.", e);
                e.printStackTrace();
            }
        }
        LOG.warn("solrDocument is null.");
        return null;
    }


    /**
     * 批量转换, 将solrDocumentList转换为实体类 List
     *
     * @param documents solrDocumentList对象
     * @param clzz      泛型类
     * @return List<T>
     * @author pudongping
     */
    public static <T> List<T> solrDocument2Entity(SolrDocumentList documents, Class<T> clzz) {
        if (null != documents && documents.size() > 0) {
            List<T> lists = new ArrayList<T>();
            for (SolrDocument sd : documents) {
                Object obj = solrDocument2Entity(sd, clzz);
                if (null != obj) {
                    lists.add(clzz.cast(obj));
                }
            }
            return lists;
        }
        LOG.warn("即将要转换的solrDocumentList为null或者size为0.");
        return null;
    }

    /**
     * 根据关键字查询 [使用 solr内部转换机制]
     *
     * @param <T>
     * @param pageNum  当前页码
     * @param pageSize 每页显示的大小
     * @param clzz     对象类型
     * @return
     */
    public static <T> Page<T> queryBinderBean(QueryResponse response, int pageNum, int pageSize, Class<T> clzz) {

        //查询结果集
        SolrDocumentList documents = response.getResults();

        //查询结果集
        List<T> items = response.getBeans(clzz);
        //查询到的记录总数
        long totalRow = Long.valueOf(response.getResults().getNumFound()).intValue();

        Page<T> page = new Page<T>();
        page.setTotalSize(totalRow);
        page.setPageNumber(pageNum);
        page.setPageSize(pageSize);
        page.setData(items);
        //填充page对象
        return page;
    }
}
