package org.jon.lv.solrserve.tools;

import org.jon.lv.solrserve.enums.ParamType;
import org.jon.lv.solrserve.model.QueryField;
import org.jon.lv.solrserve.model.SolrQuery;
import org.jon.lv.solrserve.model.SolrServeQuery;
import org.jon.lv.solrserve.model.SortField;
import org.jon.lv.solrserve.utils.DateHelper;
import org.apache.solr.common.StringUtils;
import org.apache.solr.common.params.ModifiableSolrParams;

import java.util.Date;
import java.util.List;

/**
 * Author: 吕斌
 * Description:
 * History: 变更记录
 * <author>           <time>             <version>        <desc>
 * 李军伟               2015/11/27              1.0            创建文件
 */
public class ConvertSolrParams {

    /**
     * 字符* *
     */
    private static final String WHOLE = "*";
    /**
     * 查询全部 *
     */
    private static final String ALL = "*:*";
    /**
     * 查询参数 *
     */
    private static final String QUERY = "q";
    /**
     * 过滤查询参数 *
     */
    private static final String FILTER_QUERY = "fq";
    /**
     * 返回域参数 *
     */
    private static final String FILTER_FILEDS = "fl";
    /**
     * 并查询 *
     */
    private static final String AND = " AND ";
    /**
     * 或查询 *
     */
    private static final String OR = " OR ";
    /**
     * 排序参数 *
     */
    private static final String SORT = "sort";
    /**
     * 查询条数参数 *
     */
    private static final String ROWS = "rows";
    /**
     * 起始行参数 *
     */
    private static final String START = "start";

    /**
     * doc的索引域all *
     */
    private static final String FIELD_ALL = "all:";


    /**
     * @param serveQuery
     * @return
     */
    public static ModifiableSolrParams convertSolrQuery2SolrParams(SolrQuery serveQuery) {
        ModifiableSolrParams mp = new ModifiableSolrParams();
        // 查询 q
        if (serveQuery.getQueryString() != null) {
            mp.add(QUERY, serveQuery.getQueryString());
        } else {
            mp.add(QUERY, ALL);
        }
        // 查询 fq
        if (serveQuery.getFilterString() != null) {
            mp.add(FILTER_QUERY, serveQuery.getFilterString());
        }
        // 字段过滤
        if (serveQuery.getShowFileds() != null) {
            mp.add(FILTER_FILEDS, serveQuery.getShowFileds());
        }
        // 排序
        if (serveQuery.getSortFields() != null) {
            mp.add(SORT, assembleSortField(serveQuery.getSortFields()));
        }
        // 每页条数 默认10条
        if (serveQuery.getPageSize() == null) {
            serveQuery.setPageSize(10);
        }
        mp.add(ROWS, String.valueOf(serveQuery.getPageSize()));

        // 参数分页
        if (serveQuery.getPageNum() != null) {
            long start = (serveQuery.getPageNum() - 1) * serveQuery.getPageSize();
            mp.add(START, String.valueOf(start));
        } else {
            mp.add(START, "0");
        }


        return mp;
    }


    /**
     * @param serveQuery
     * @return
     */
    public static ModifiableSolrParams convertSolrServeQuery2SolrParams(SolrServeQuery serveQuery) {
        ModifiableSolrParams mp = new ModifiableSolrParams();

        // 查询 q
        if (serveQuery.getFields() != null) {
            QueryField[] queryFields = serveQuery.getFields();
            StringBuilder builder = new StringBuilder();
            String keyword = serveQuery.getKeyword();
            if (StringUtils.isEmpty(keyword)) {
                keyword = WHOLE;
            }
            builder.append(FIELD_ALL).append(escapeQueryChars(keyword));
            for (QueryField queryField : queryFields) {
                builder.append(convertQueryField2String(queryField));
            }
            mp.add(QUERY, builder.toString());
        } else {
            mp.add(QUERY, ALL);
        }
        // 查询 fq
        if (serveQuery.getFilterFields() != null) {
            QueryField[] queryFields = serveQuery.getFilterFields();
            StringBuilder builder = new StringBuilder(ALL);
            for (QueryField queryField : queryFields) {
                builder.append(convertQueryField2String(queryField));
            }
            mp.add(FILTER_QUERY, builder.toString());
        }
        // 字段过滤
        if (serveQuery.getShowFileds() != null) {
            mp.add(FILTER_FILEDS, serveQuery.getShowFileds());
        }
        // 排序
        if (serveQuery.getSortFields() != null) {
            mp.add(SORT, assembleSortField(serveQuery.getSortFields()));
        }
        // 每页条数 默认10条
        if (serveQuery.getPageSize() == null) {
            serveQuery.setPageSize(10);
        }
        mp.add(ROWS, serveQuery.getPageSize() + "");

        // 参数分页
        if (serveQuery.getPageNum() != null) {
            long start = (serveQuery.getPageNum() - 1) * serveQuery.getPageSize();
            mp.add(START, String.valueOf(start));
        } else {
            mp.add(START, "0");
        }


        return mp;
    }


    /**
     * @param queryField
     * @return
     */
    public static String convertQueryField2String(QueryField queryField) {
        String field = queryField.getFiledName();
        Object value = queryField.getValue();
        ParamType type = queryField.getParamType();
        String s = "";
        if (value instanceof String) {
            s = " " + queryField.getLogicType().getType() + " ";
            String result = (String) value;
            s += convertStrLogic2words(field, escapeQueryChars(result), type);
        } else if (value instanceof Integer ||
                value instanceof Long ||
                value instanceof Double ||
                value instanceof Float) {
            s = " " + queryField.getLogicType().getType() + " ";
            s += convertStrLogic2words(field, String.valueOf(value), type);
        } else if (value instanceof Date) {
            s = " " + queryField.getLogicType().getType() + " ";
            s += convertStrLogic2words(field, DateHelper.formateDateToSolrDate((Date) value), type);
        } else if (value instanceof List) {
            List<String> list = (List<String>) value;
            for (int i = 0; i < list.size(); i++) {
                // 逻辑条件 and or
                s += " " + queryField.getLogicType().getType() + " ";
                // 参数类型
                s += convertStrLogic2words(field, escapeQueryChars(list.get(i)), type);

            }
        }

        return s;
    }


    /**
     * @param field
     * @param value
     * @param type
     * @return
     */
    public static String convertStrLogic2words(String field, String value, ParamType type) {
//        String s = "";
        StringBuilder builder = new StringBuilder();
        switch (type) {
            // filed:value
            case NORMAL:
                builder.append(field).append(":").append(value);
                break;
            // field:{value TO *]
            case GT:
                builder.append(field).append(":{").append(value).append(" TO *]");
                break;
            // field:[* TO value}
            case LT:
                builder.append(field).append(":[* TO ").append(value).append("}");
                break;
            // field:[value TO *]
            case GE:
                builder.append(field).append(":[").append(value).append(" TO *]");
                break;
            // field:[* TO value]
            case LE:
                builder.append(field).append(":[* TO ").append(value).append("]");
                break;
            // field:*value
            case PF:
                builder.append(field).append(":*").append(value);
                break;
            // field:value*
            case BF:
                builder.append(field).append(":").append(value).append("*");
                break;
            // field:*value*
            case WF:
                builder.append(field).append(":*").append(value).append("*");
                break;
            // NOT field : value
            case NOT:
                builder.append(" NOT ").append(field).append(":").append(value);
                break;


        }
        return builder.toString();

    }

    /**
     * 组装排序字段
     *
     * @param sortFields
     * @return
     */
    private static String assembleSortField(SortField... sortFields) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < sortFields.length; i++) {
            builder.append(sortFields[i].getFieldName()).append(" ").append(sortFields[i].getSortType().getType());
            if (i < sortFields.length - 1) {
                builder.append(" , ");
            }
        }

        return builder.toString();
    }

    /**
     * 过滤参数值中的特殊字符
     * @param s
     * @return
     */
    public static String escapeQueryChars(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c == '\\') || (c == '+') || (c == '-') || (c == '!')
                    || (c == '(') || (c == ')') || (c == ':') || (c == '^')
                    || (c == '[') || (c == ']') || (c == '"') || (c == '{')
                    || (c == '}') || (c == '~') || (c == '*') || (c == '?')
                    || (c == '|') || (c == '&') || (c == ';') || (c == '/')
                    || (Character.isWhitespace(c)))
                continue;
            sb.append(c);
        }
        return sb.toString();
    }
}
