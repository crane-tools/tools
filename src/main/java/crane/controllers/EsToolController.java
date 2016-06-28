package crane.controllers;

import crane.tools.EsHelper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by crane on 16/6/26.
 */
@RestController
@RequestMapping("es")
public class EsToolController {
    @Value("${es.sku.indiceName}")
    String indiceName;
    @Value("${es.sku.typeName}")
    String typeName;
    @Autowired
    EsHelper esHelper;

    @RequestMapping("/search")
    public Object search() {
        SearchRequestBuilder searchRequestBuilder = esHelper.getSearchRequestBuilder(indiceName, typeName);
//        if (false) {
//            searchRequestBuilder.setQuery(new IdsQueryBuilder());
//        } else {
//            searchRequestBuilder.setQuery(new BoolQueryBuilder());
//        }
        searchRequestBuilder.setQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("content", "中国美国")));
        searchRequestBuilder = searchRequestBuilder.addSort("order", SortOrder.ASC);//一级排序
        searchRequestBuilder = searchRequestBuilder.addSort("order2", SortOrder.DESC);//二级排序
        searchRequestBuilder = searchRequestBuilder.setFrom(0).setSize(10);
        // 设置是否按查询匹配度排序 如果没有设置上面的自定义排序则使用匹配度排序,如果设置了自定义排序则匹配度排序无效
        searchRequestBuilder.setExplain(true);
        //设置高亮显示
        searchRequestBuilder.addHighlightedField("content");
//        searchRequestBuilder.setHighlighterPreTags("<span style=\"color:red\">");
//        searchRequestBuilder.setHighlighterPostTags("</span>");
        SearchResponse response = esHelper.search(searchRequestBuilder);
        List<Map<String, Object>> list = new ArrayList<>();
        if (response != null && response.getHits().totalHits() > 0) {
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                list.add(hit.getSource());

                //获取对应的高亮域
                Map<String, HighlightField> result = hit.highlightFields();
                //从设定的高亮域中取得指定域
                HighlightField titleField = result.get("content");
                //取得定义的高亮标签
                Text[] titleTexts = titleField.fragments();
                //为title串值增加自定义的高亮标签
                String title = "";
                for (Text text : titleTexts) {
                    title += text;
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("contentHighLight", title);
                list.add(map);
            }
        }
        return list;
    }

    @RequestMapping("/mapping")
    public Object mapping(@RequestParam(value = "param", defaultValue = "", required = false) String param) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("hl");//type

        builder.startObject("_all").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").field("include_in_all", "true").endObject();

        builder.startObject("properties");
        builder.startObject("content").field("type", "string").field("store", "false").field("term_vector", "with_positions_offsets").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").field("include_in_all", "true").endObject();
        builder.endObject();

        builder.endObject();
        builder.endObject();
        return null;
    }
}


// reference https://github.com/medcl/elasticsearch-analysis-ik
// es version:2.3.3
//http://127.0.0.1:9200/test/hl/_mapping/
//        {
//        "hl": {
//        "_all": {
//        "analyzer": "ik_smart",
//        "search_analyzer": "ik_smart",
//        "term_vector": "no",
//        "store": "false"
//        },
//        "properties": {
//        "content": {
//        "type": "string",
//        "store": "no",
//        "term_vector": "with_positions_offsets",
//        "analyzer": "ik_smart",
//        "search_analyzer": "ik_smart",
//        "include_in_all": "true",
//        "boost": 8
//        }
//        }
//        }
//        }


//curl -XPOST http://localhost:9200/test/hl -d'{"content":"美国留给伊拉克的是个烂摊子吗"}'
//curl -XPOST http://localhost:9200/test/hl -d'{"content":"公安部：各地校车将享最高路权"}'
//curl -XPOST http://localhost:9200/test/hl -d'{"content":"中韩渔警冲突调查：韩警平均每天扣1艘中国渔船"}'
//curl -XPOST http://localhost:9200/test/hl -d'{"content":"中国驻洛杉矶领事馆遭亚裔男子枪击 嫌犯已自首"}'