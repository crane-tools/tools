package crane.tools;


import com.alibaba.fastjson.JSONObject;
import com.carrotsearch.hppc.ObjectLookupContainer;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequestBuilder;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesResponse;
import org.elasticsearch.action.admin.indices.alias.exists.AliasesExistRequestBuilder;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by crane on 16/6/26.
 */

@Component
public class EsHelper {
    /**
     * 插入es数据时使用的数据类型
     */
    public enum EsDocType {
        JsonString, Object, Map
    }

    private TransportClient es_client;
    @Value("${es.host}")
    private String host;
    @Value("${es.clustername}")
    private String clusterName;
    @Value("${es.tcp.port}")
    private int port;

    /**
     * 获取主机名字
     *
     * @return 主机名字
     */
    public String getHost() {
        return host;
    }

    /**
     * 获取集群名字
     *
     * @return 集群名字
     */
    public String getClusterName() {
        return clusterName;
    }

    /**
     * 构造器 私有 未提供单例获取方式 只通过注入
     */
    private EsHelper() {
    }

    /**
     * 注入 构造器执行后的初始化
     */
    @PostConstruct
    public void init() {
        Settings settings = Settings.settingsBuilder().put("client.transport.sniff", true).put("cluster.name", clusterName).build();

        try {
            es_client = TransportClient.builder().settings(settings).build().addTransportAddress(
                    new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查索引是否已经存在
     *
     * @param indiceName 索引名
     * @return true:存在 false:不存在
     */
    public boolean IsIndiceExists(String indiceName) {
        IndicesExistsResponse response = es_client.admin().indices().exists(new IndicesExistsRequest(indiceName)).actionGet();
        if (response == null)
            return false;
        return response.isExists();
    }

    /**
     * 新增一条数据
     *
     * @param indice      索引名
     * @param type        类型名
     * @param id          数据id
     * @param fieldValues 数据field-value集合
     * @return 新增响应结果
     */
    public IndexResponse insert(String indice, String type, String id, Map<String, Object> fieldValues) throws IOException {
        if (fieldValues == null || fieldValues.keySet().size() <= 0)
            return null;
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
        for (String key : fieldValues.keySet()) {
            builder.field(key, fieldValues.get(key));
        }
        builder.endObject();
        return es_client.prepareIndex(indice, type, id).setSource(builder).execute().actionGet();
    }

    /**
     * 批量新增
     *
     * @param indice 索引名
     * @param type   类型名
     * @param data   新增的数据,key为数据id,value为该条数据对应的XContentBuilder
     * @return 批量插入结果
     * @throws IOException
     */
    public BulkResponse bulkInsert(String indice, String type, Map<String, XContentBuilder> data) throws IOException {
        if (data == null || data.keySet().size() <= 0)
            return null;
        BulkRequestBuilder bulkRequestBuilder = es_client.prepareBulk();
        Set<String> ids = data.keySet();
        for (String id : ids) {
            IndexRequestBuilder builder = es_client.prepareIndex(indice, type, id);
            builder.setSource(data.get(id));
            bulkRequestBuilder.add(builder);
        }
        return bulkRequestBuilder.get();
    }

    /**
     * 根据id更新一条文档的指定列数据
     *
     * @param indiceName 索引名称
     * @param type       文档类型
     * @param id         文档ID
     * @param field      更新的字段名称
     * @param value      更新后的值
     * @return 更新结果
     */
    public UpdateResponse update(String indiceName, String type, String id, String field, Object value) {
        UpdateRequestBuilder updateRequestBuilder = es_client.prepareUpdate(indiceName, type, id);
        updateRequestBuilder.setDoc(field, value);
        return es_client.update(updateRequestBuilder.request()).actionGet();
    }

    /**
     * 批量新增
     *
     * @param indice 索引名
     * @param type   类型名
     * @param data   要修改的数据,<id,<field,value>>
     * @return 批量修改结果
     * @throws IOException
     */
    public BulkResponse bulkUpdateSet(String indice, String type, Map<String, Map<String, Object>> data) throws IOException {
        if (data == null || data.keySet().size() <= 0)
            return null;
        BulkRequestBuilder bulkRequestBuilder = es_client.prepareBulk();
        Set<String> ids = data.keySet();
        for (String id : ids) {
            for (String inid : data.get(id).keySet()) {
                UpdateRequestBuilder builder = es_client.prepareUpdate(indice, type, id);
                builder.setDoc(inid, data.get(id).get(inid));
                bulkRequestBuilder.add(builder);
            }
        }
        return bulkRequestBuilder.get();
    }

    /**
     * 删除一条es数据
     *
     * @param indiceName 索引目录名
     * @param type       索引类型名
     * @param id         要删除的数据id
     * @return 删除响应结果
     */
    public DeleteResponse delete(String indiceName, String type, String id) {
        DeleteRequestBuilder deleteRequestBuilder = es_client.prepareDelete(indiceName, type, id);
        return deleteRequestBuilder.get();
    }

    /**
     * 批量删除索引数据
     *
     * @param indiceName 索引目录名
     * @param type       索引类型
     * @param idList     要删除的数据id集合
     * @return 批量删除响应结果
     */
    public BulkResponse bulkDelete(String indiceName, String type, List<String> idList) {
        if (idList == null || idList.size() <= 0)
            return null;
        BulkRequestBuilder bulkRequestBuilder = es_client.prepareBulk();
        for (String id : idList) {
            DeleteRequestBuilder deleteRequestBuilder = es_client.prepareDelete(indiceName, type, id);
            bulkRequestBuilder.add(deleteRequestBuilder);
        }
        return bulkRequestBuilder.get();
    }

    /**
     * 获取SearchRequestBuilder
     *
     * @return 查询结果
     */
    public SearchRequestBuilder getSearchRequestBuilder(String indice, String type) {
        return es_client.prepareSearch().setIndices(indice).setTypes(type);
    }

    /**
     * elasticsearch 执行查询
     *
     * @param requestBuilder 查询请求构造器
     * @return 查询结果
     */
    public SearchResponse search(SearchRequestBuilder requestBuilder) {
        return requestBuilder.execute().actionGet();
    }

    /**
     * 创建mapping
     *
     * @param indiceName 索引名
     * @param type       索引类型
     * @param builder    映射信息
     * @return
     * @throws IOException
     */
    public PutMappingResponse createMapping(String indiceName, String type, XContentBuilder builder) throws IOException {
        PutMappingRequest mappingRequest = Requests.putMappingRequest(indiceName).type(type).source(builder);
        return this.es_client.admin().indices().putMapping(mappingRequest).actionGet();
    }

    /**
     * 创建索引
     *
     * @param indiceName 索引名
     * @return
     */
    public CreateIndexResponse createIndice(String indiceName) {
        return this.es_client.admin().indices().prepareCreate(indiceName).execute().actionGet();
    }

    /**
     * 别名是否存在
     *
     * @param aliasName 别名名称
     * @return
     */
    public boolean isAliasExist(String aliasName) {
        AliasesExistRequestBuilder builder = this.es_client.admin().indices().prepareAliasesExist(aliasName);
        return builder.execute().actionGet().isExists();
    }

    /**
     * 替换索引别名 如果其他索引使用了当前别名会被删除
     *
     * @param aliasName  别名名称
     * @param indiceName 要配置别名的索引实际名称
     * @return 别名创建响应
     */
    public IndicesAliasesResponse replaceAlias(String aliasName, String indiceName) {
        GetAliasesResponse aliasesResponse = this.es_client.admin().indices().prepareGetAliases(aliasName).execute().actionGet();
        IndicesAliasesRequestBuilder builder = this.es_client.admin().indices().prepareAliases();
        if (aliasesResponse != null && aliasesResponse.getAliases() != null) {
            ObjectLookupContainer<String> indiceNames = aliasesResponse.getAliases().keys();
            String[] strings = indiceNames.toArray(String.class);
            for (String s : strings) {
                builder.removeAlias(s, aliasName);
            }
        }
        builder.addAlias(indiceName, aliasName);
        IndicesAliasesResponse response = builder.execute().actionGet();
        return response;
    }
}

