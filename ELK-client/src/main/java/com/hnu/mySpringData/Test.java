package com.hnu.mySpringData;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElasticsearchClientConfig.class);
        RestHighLevelClient client = (RestHighLevelClient) context.getBean("Client");
        CreateIndexRequest request=new CreateIndexRequest("hjEsTest");

        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(response);
        GetIndexRequest request1 = new GetIndexRequest("hjEsTest");
        boolean exists = client.indices().exists(request1, RequestOptions.DEFAULT);
        System.out.println(exists);

        DeleteIndexRequest hjEsTest = new DeleteIndexRequest("hjEsTest");
        AcknowledgedResponse delete = client.indices().delete(hjEsTest, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());

    }
}


