package com.bin.datatool.elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @program: datatool
 * @description:
 * @author: jyb
 * @create: 2019-12-25 20:43
 **/
public class ElasticsearchRangeQuery {
    public void esRangeQuery(String masterNodeIp, int port) {
        // 集群客户端配置
        Settings settings = Settings.builder().put("client.transport.sniff", false)
                .put("cluster.name", "testRangeQuery")
                .put("client.transport.ping_timeout", 60, TimeUnit.SECONDS)
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);

        try {
            // 添加传输端口号和地址
            TransportAddress transportAddress = new InetSocketTransportAddress(new InetSocketAddress(masterNodeIp, port));
            client.addTransportAddress(transportAddress);

            // 初始化索引和类型
            String index = "monitor_indices_name-*";
            String type = "monitor";

            // 多条件查询
            SearchResponse searchResponse = client.prepareSearch(index).setTypes(type)
                    .setQuery(QueryBuilders.boolQuery()
                            .must(QueryBuilders.matchPhraseQuery("testField", "testFieldValue"))
                            .must(QueryBuilders.rangeQuery("time").from("2019-04-05T02:37:48")
                                    .to("2019-04-05T07:57:48")))
                    .setExplain(true)
                    .setSize(50)
                    .execute()
                    .actionGet();

            SearchHits searchHits = searchResponse.getHits();
            if (searchHits.getTotalHits() > 0) {
                SearchHit[] hits = searchHits.getHits();
                // 循环打印结果数据
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsString());
                }
            }
        } catch (Throwable e) {
            e.getStackTrace();
        }
    }
}
