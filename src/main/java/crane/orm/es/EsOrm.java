package crane.orm.es;

import com.alibaba.fastjson.JSONObject;
import crane.model.EsBaseModel;
import crane.utils.ReflectionUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.*;

/**
 * Created by crane on 16/9/23.
 */
public class EsOrm {
    /**
     * 从es的一个hit中获取指定类型的实例
     * @param clazz 实例类型
     * @param hit es的一个查询hit
     * @param <T> 类型参数
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static <T> T parseEsModel(Class<T> clazz, SearchHit hit) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (hit == null)
            return null;
        Map<String, Object> map = hit.getSource();
        if (map == null)
            return null;
        return ReflectionUtils.getInstance(clazz, hit.getSource());
    }

    /**
     * 构造一个es更新或新增模型
     *
     * @param model
     * @return
     * @throws IOException
     */
    public static Map<String, XContentBuilder> buildEsData(EsBaseModel model) throws IOException {
        if (model == null) {
            return null;
        }
        Map<String, XContentBuilder> map = new HashMap<>();
        String id = model.getId();
        if (StringUtils.isBlank(id)) {
            id = UUID.randomUUID().toString();
            model.setId(id);
        }
        XContentBuilder contentBuilder = getXContentBuilder(JSONObject.parseObject(JSONObject.toJSONString(model)));
        map.put(id, contentBuilder);
        return map;
    }

    /**
     * 构造多个es 更新或新增模型
     *
     * @param models
     * @return
     * @throws IOException
     */
    public static Map<String, XContentBuilder> buildEsUserData(List<EsBaseModel> models) throws IOException {
        if (models == null || models.size() <= 0) {
            return null;
        }
        Map<String, XContentBuilder> map = new HashMap<>();
        for (EsBaseModel model : models) {
            if (model == null)
                continue;
            String id = model.getId();
            if (StringUtils.isBlank(id)) {
                id = UUID.randomUUID().toString();
                model.setId(id);
            }
            XContentBuilder contentBuilder = getXContentBuilder(JSONObject.parseObject(JSONObject.toJSONString(model)));
            if (contentBuilder == null)
                continue;
            map.put(id, contentBuilder);
        }
        return map;
    }

    /**
     * 根据jsonobject构造一个XContentBuilder
     *
     * @param jsonObject
     * @return
     * @throws IOException
     */
    private static XContentBuilder getXContentBuilder(JSONObject jsonObject) throws IOException {
        if (jsonObject == null)
            return null;
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            contentBuilder.field(key, jsonObject.get(key));
        }
        contentBuilder.endObject();
        return contentBuilder;
    }
}
