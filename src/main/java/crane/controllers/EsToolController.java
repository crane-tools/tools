package crane.controllers;

import crane.tools.EsHelper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

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
    public Object search(int from,int size){
        SearchRequestBuilder searchRequestBuilder = esHelper.getSearchRequestBuilder().setIndices(indiceName).setTypes(typeName);
        if (false) {
            searchRequestBuilder.setQuery(new IdsQueryBuilder());
        } else {
            searchRequestBuilder.setQuery(new BoolQueryBuilder());
        }
        searchRequestBuilder = searchRequestBuilder.addSort("_id", SortOrder.DESC);
        searchRequestBuilder = searchRequestBuilder.setFrom(from).setSize(size);
        SearchResponse response = esHelper.search(searchRequestBuilder);
        if (response != null && response.getHits().totalHits() > 0) {
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                Map<String, Object> values = hit.getSource();
            }
        }
        return response;
    }
}
