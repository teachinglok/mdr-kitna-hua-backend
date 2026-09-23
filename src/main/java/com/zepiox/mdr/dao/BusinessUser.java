package com.zepiox.mdr.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "business-users")
public class BusinessUser {
    @Id
    private String id;
    private String email;
    private String businessName;
    private String otp;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDateTime;
    private String updatedBy;
}
