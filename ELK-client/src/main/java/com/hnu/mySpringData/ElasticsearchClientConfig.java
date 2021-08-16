package com.hnu.mySpringData;


//import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.Objects;

//import java.util.Arrays;
//import java.util.Objects;

//@ConfigurationProperties(prefix = "elasticsearch")
@Configuration
@PropertySource(value = "classpath:application.properties")
//@Data
public class ElasticsearchClientConfig {

    private String host="hadoop-101" ;
    private Integer port =9200;
    private static final int ADDRESS_LENGTH = 3;
    @Value("${elasticsearch.scheme}")
    private String scheme;
    @Value("${elasticsearch.cluster-nodes}")
    private String[] ipAddress;
    @Value("${es_conRequTout}")
    private String connectionRequestTimeout;
    @Value("${elasticsearch.socket-timeout}")
    private String socketTimeout;
    @Value("${elasticsearch.connect-timeout}")
    private String connectTimeout;
    @Bean(name="Client")
   public RestHighLevelClient elasticsearchClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port));
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        return restHighLevelClient;
    }

    @Bean
    public RestClientBuilder restClientBuilder() {
        HttpHost[] hosts = Arrays.stream(ipAddress)
                .map(this::makeHttpHost)
                .filter(Objects::nonNull)
                .toArray(HttpHost[]::new);
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host, port),
                new HttpHost("hadoop-101", port)
        ,new HttpHost("hadoop-101", port));
        // 设置一个监听器，每次节点出现故障时都会收到通知，以防需要采取措施，
        // 当启用故障嗅探时在内部使用。
        restClientBuilder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {

            }
        });
        // 设置允许修改默认请求配置的回调
        //（例如请求超时，身份验证或org.apache.http.client.config.RequestConfig.Builder允许设置的任何内容）。
        restClientBuilder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                return requestConfigBuilder
                        .setConnectionRequestTimeout(Integer.parseInt(connectionRequestTimeout))
                        .setSocketTimeout(Integer.parseInt(socketTimeout))
                        .setConnectTimeout(Integer.parseInt(connectTimeout));
            }
        });
        return restClientBuilder;
    }

    @Bean(name = "highLevelClient")
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        // TODO 此处可以进行其它操作
        return new RestHighLevelClient(restClientBuilder);
    }

    /**
     * 根据配置创建HttpHost
     * @param s
     * @return
     */
    private HttpHost makeHttpHost(String s) {
        String[] address = s.split(":");
        if (address.length == ADDRESS_LENGTH) {
            String ip = address[0];
            int port = Integer.parseInt(address[1]);
            return new HttpHost(ip, port, scheme);
        } else {
            return null;
        }
    }
}
