package com.hnu.mySpringData;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.Index;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.PushbackInputStream;

@SpringBootTest(classes={ClientTest.class})

public class ClientTest {

    @Autowired
    @Qualifier("Client")
    private RestHighLevelClient client;
//


    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest request=new CreateIndexRequest("hjEsTest");
        CreateIndexResponse indexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
    }


@Test
  public void testAddDocument() throws IOException {
    User documentTest = new User("hjFisrtDocumentTest", 2);

    IndexRequest request = new IndexRequest("hjFisrtDocumentTest");

    //规则 put/index/_doc/1
    request.id("1");
    request.timeout(TimeValue.MINUS_ONE);
    request.timeout("1s");
    //将我们的数据放入请求
    request.source(JSON.toJSON(documentTest), XContentType.JSON);
//客户端发送请求,获取响应结果
    IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
    System.out.println(indexResponse.status()+"  "+indexResponse.toString());

}
//获取文档判断是否存在

}
