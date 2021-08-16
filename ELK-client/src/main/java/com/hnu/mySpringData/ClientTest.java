package com.hnu.mySpringData;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootTest
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

}
