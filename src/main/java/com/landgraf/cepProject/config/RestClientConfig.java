package com.landgraf.cepProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

        @Bean
        public RestClient viaCepRestClient(RestClient.Builder builder) {
            return builder.baseUrl("https://viacep.com.br/ws/").build();
        }
}

