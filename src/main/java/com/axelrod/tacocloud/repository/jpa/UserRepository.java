package com.axelrod.tacocloud.repository.jpa;

import com.axelrod.tacocloud.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
