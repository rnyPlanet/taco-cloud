package com.axelrod.tacocloud.controllers;

import com.axelrod.tacocloud.entity.Ingredient;
import com.axelrod.tacocloud.entity.Order;
import com.axelrod.tacocloud.entity.Taco;
import com.axelrod.tacocloud.repository.jdbc.IngredientRepository;
import com.axelrod.tacocloud.repository.jdbc.TacoRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.axelrod.tacocloud.entity.Ingredient.Type;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @Setter(onMethod = @__({@Autowired}))
    private IngredientRepository ingredientRepository;

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientRepository.findAll(), type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("taco", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute Taco taco, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("taco", taco);
            log.info("Processing design: " + taco);
            return "design";
        }

        log.info("Processing design: " + taco);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
