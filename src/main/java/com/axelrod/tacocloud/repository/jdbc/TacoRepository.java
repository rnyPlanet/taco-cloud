package com.axelrod.tacocloud.repository.jdbc;

import com.axelrod.tacocloud.entity.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
