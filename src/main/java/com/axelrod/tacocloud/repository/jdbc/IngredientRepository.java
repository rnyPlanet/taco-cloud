package com.axelrod.tacocloud.repository.jdbc;

import com.axelrod.tacocloud.entity.Ingredient;

import java.util.List;

public interface IngredientRepository {
    List<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
