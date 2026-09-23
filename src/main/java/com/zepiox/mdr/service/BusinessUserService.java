package com.zepiox.mdr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zepiox.mdr.dao.BusinessUser;
import com.zepiox.mdr.dto.EmailRequest;
import com.zepiox.mdr.feign.client.BrevoEmailClient;
import com.zepiox.mdr.repository.BusinessUserRepository;
import com.zepiox.mdr.util.OTPGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BusinessUserService {
    private final BrevoEmailClient brevoEmailClient;
    private final MongoTemplate mongoTemplate;
    private final BusinessUserRepository businessUserRepository;

    @Value("${api-key.brevo}")
    private String brevoApiKey;
    private static final Logger logger = LoggerFactory.getLogger(BusinessUserService.class);


    public BusinessUserService(BrevoEmailClient brevoEmailClient, MongoTemplate mongoTemplate, BusinessUserRepository businessUserRepository) {
        this.brevoEmailClient = brevoEmailClient;
        this.mongoTemplate = mongoTemplate;
        this.businessUserRepository = businessUserRepository;
    }

    public void saveBusinessUserWithOTP(EmailRequest emailRequest) {
        String email = emailRequest.getTo().getFirst().getEmail();
        BusinessUser businessUser = businessUserRepository.findByEmail(email).orElse(new BusinessUser());

        if (businessUser.getId() == null) {
            businessUser.setEmail(email);
            businessUser.setBusinessName("Default Business Name"); // Populate with actual business name if available
            businessUser.setCreatedDatetime(LocalDateTime.now());
        }

        String otp = OTPGenerator.generateOTP();
        businessUser.setOtp(otp);
        businessUser.setUpdatedDateTime(LocalDateTime.now());
        businessUser.setUpdatedBy("System");

        businessUserRepository.save(businessUser);

        emailRequest.setHtmlContent("<html><head></head><body><p>Hello Business User,</p><p>Your OTP is: " + otp + "</p></body></html>");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String emailRequestJson = objectMapper.writeValueAsString(emailRequest);
            brevoEmailClient.sendTransactionalEmail(brevoApiKey, emailRequestJson);
            logger.info("Exiting sendEmailOTP API successfully.");
        } catch (Exception e) {
            logger.error("Error in sendEmailOTP API: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to convert EmailRequest to JSON", e);
        }
    }
}
