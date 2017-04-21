package com.leekyoungil.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by leekyoungil (leekyoungil@gmail.com) on 4/14/2017.
 */
@Configuration
public class RestTemplateConfig {

    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 10;
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 10;

    @Bean
    public ClientHttpRequestFactory restTemplateHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(3 * 1000)
                .setConnectionRequestTimeout(5 * 1000)
                .build();

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean
    public RestTemplate restTemplate() {
        return  new RestTemplate(restTemplateHttpRequestFactory());
    }

}
