package com.axelrod.tacocloud.security;

import com.axelrod.tacocloud.entity.User;
import com.axelrod.tacocloud.repository.jpa.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    @Setter(onMethod = @__({@Autowired}))
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }
}