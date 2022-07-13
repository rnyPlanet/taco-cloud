package com.axelrod.tacocloud.repository.jdbc;

import com.axelrod.tacocloud.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
