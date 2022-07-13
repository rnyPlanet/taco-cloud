package com.axelrod.tacocloud.repository.jdbc.impl;

import com.axelrod.tacocloud.entity.Order;
import com.axelrod.tacocloud.repository.jdbc.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    @Override
    public Order save(Order order) {
        return null;
    }
}
