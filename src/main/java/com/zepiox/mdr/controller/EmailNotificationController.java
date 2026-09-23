package com.zepiox.mdr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zepiox.mdr.dto.EmailRequest;
import com.zepiox.mdr.feign.client.BrevoEmailClient;
import com.zepiox.mdr.service.BusinessUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/notifications")
public class EmailNotificationController {

    private final BrevoEmailClient brevoEmailClient;
    private final BusinessUserService businessUserService;

    @Value("${api-key.brevo}")
    private String brevoApiKey;

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationController.class);

    @Autowired
    public EmailNotificationController(BrevoEmailClient brevoEmailClient, BusinessUserService businessUserService) {
        this.brevoEmailClient = brevoEmailClient;
        this.businessUserService = businessUserService;
    }

    @PostMapping("/send-transactional-email")
    public void sendTransactionalEmail(@RequestBody EmailRequest emailRequest) {
        logger.info("Entering sendTransactionalEmail API with request: {}", emailRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String emailRequestJson = objectMapper.writeValueAsString(emailRequest);
            brevoEmailClient.sendTransactionalEmail(brevoApiKey, emailRequestJson);
            logger.info("Exiting sendTransactionalEmail API successfully.");
        } catch (Exception e) {
            logger.error("Error in sendTransactionalEmail API: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to convert EmailRequest to JSON", e);
        }
    }

    @PostMapping("/send-email-otp")
    public void sendEmailOTP(@RequestBody EmailRequest emailRequest) {
        logger.info("Entering sendEmailOTP API with request: {}", emailRequest);
        businessUserService.saveBusinessUserWithOTP(emailRequest);
    }
}
