/**
 *
 * @author jowyd
 */
package com.urijoel.curling.repository;

import com.urijoel.curling.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}