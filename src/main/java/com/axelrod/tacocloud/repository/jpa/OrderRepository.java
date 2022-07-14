package com.axelrod.tacocloud.repository.jpa;

import com.axelrod.tacocloud.entity.Order;
import com.axelrod.tacocloud.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
