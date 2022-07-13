package com.axelrod.tacocloud.repository.jpa;

import com.axelrod.tacocloud.entity.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
