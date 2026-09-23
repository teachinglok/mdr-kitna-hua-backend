package com.zepiox.mdr.repository;

import com.zepiox.mdr.dao.BusinessUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessUserRepository extends MongoRepository<BusinessUser, String> {
    Optional<BusinessUser> findByEmail(String email);
}
