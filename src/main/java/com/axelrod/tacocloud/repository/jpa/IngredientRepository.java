package com.axelrod.tacocloud.repository.jpa;

import com.axelrod.tacocloud.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
