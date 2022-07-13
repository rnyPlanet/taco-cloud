package com.axelrod.tacocloud.configuration;

import com.axelrod.tacocloud.entity.Ingredient;
import com.axelrod.tacocloud.repository.jpa.IngredientRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    @Setter(onMethod = @__({@Autowired}))
    private IngredientRepository ingredientRepo;

    @Override
    public Ingredient convert(String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
        return optionalIngredient.orElse(null);
    }

}