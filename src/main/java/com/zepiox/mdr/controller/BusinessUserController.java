package com.zepiox.mdr.controller;

import com.zepiox.mdr.dao.BusinessUser;
import com.zepiox.mdr.dto.ApplicationSuccessResponse;
import com.zepiox.mdr.repository.BusinessUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/business-user")
public class BusinessUserController {

    private final BusinessUserRepository businessUserRepository;

    @Autowired
    public BusinessUserController(BusinessUserRepository businessUserRepository) {
        this.businessUserRepository = businessUserRepository;
    }

    @GetMapping("/verify-otp")
    public ResponseEntity<ApplicationSuccessResponse> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        Optional<BusinessUser> businessUserOptional = businessUserRepository.findByEmail(email);

        if (businessUserOptional.isPresent()) {
            BusinessUser businessUser = businessUserOptional.get();
            if (businessUser.getOtp().equals(otp)) {
                ApplicationSuccessResponse response = new ApplicationSuccessResponse(null, "OTP verified successfully.", null);
                return ResponseEntity.ok(response);
            } else {
                ApplicationSuccessResponse response = new ApplicationSuccessResponse(null, "Invalid OTP.", null);
                return ResponseEntity.ok(response);
            }
        } else {
            ApplicationSuccessResponse response = new ApplicationSuccessResponse(null, "Email not found.", null);
            return ResponseEntity.ok(response);
        }
    }
}
