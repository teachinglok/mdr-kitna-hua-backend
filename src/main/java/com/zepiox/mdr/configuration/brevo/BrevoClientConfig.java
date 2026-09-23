package com.zepiox.mdr.configuration.brevo;

import com.zepiox.mdr.feign.client.BrevoEmailClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class BrevoClientConfig {

    @Bean
    public BrevoEmailClient brevoEmailClient() {

        RestClient restClient = RestClient.builder()
                .baseUrl("https://api.brevo.com/v3")
                .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(adapter)
                        .build();

        return factory.createClient(BrevoEmailClient.class);
    }
}