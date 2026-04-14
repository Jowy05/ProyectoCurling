package com.urijoel.curling.service;

import com.urijoel.curling.dto.UserDTO;
import com.urijoel.curling.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;

public interface UserService {

    User registerUser(User user);
    List<User> findAll();
    User findById(String id);
    User findByEmail(String email);
    User updateUser(String id, UserDTO updates);
    void deleteUser(String id);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
