package com.urijoel.curling.service;

/**
 * @author jowyd
 */
import com.urijoel.curling.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;

public interface UserService {

    User registerUser(User user);

    List<User> findAll();

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
