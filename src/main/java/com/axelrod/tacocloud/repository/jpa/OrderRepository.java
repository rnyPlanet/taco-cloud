package com.axelrod.tacocloud.repository.jpa;

import com.axelrod.tacocloud.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
