package com.zepiox.mdr.feign.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "https://api.brevo.com/v3")
public interface BrevoEmailClient {

    @PostExchange("/emailCampaigns")
    void createEmailCampaign(
            @RequestHeader("api-key") String apiKey,
            @RequestBody String campaignDetails
    );

    @PostExchange("/smtp/email")
    void sendTransactionalEmail(
            @RequestHeader("api-key") String apiKey,
            @RequestBody String emailDetails
    );
}
