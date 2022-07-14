package com.axelrod.tacocloud.configuration;

import com.axelrod.tacocloud.entity.Ingredient;
import com.axelrod.tacocloud.entity.Ingredient.Type;
import com.axelrod.tacocloud.entity.Order;
import com.axelrod.tacocloud.entity.Taco;
import com.axelrod.tacocloud.entity.User;
import com.axelrod.tacocloud.repository.jpa.IngredientRepository;
import com.axelrod.tacocloud.repository.jpa.OrderRepository;
import com.axelrod.tacocloud.repository.jpa.TacoRepository;
import com.axelrod.tacocloud.repository.jpa.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(
            IngredientRepository ingredientRepository,
            UserRepository userRepository,
            PasswordEncoder encoder,
            OrderRepository orderRepository,
            TacoRepository tacoRepository) {
        return args -> {
            List<Ingredient> ingredients = saveIngredients(ingredientRepository);

            User user = saveUser(userRepository, encoder);

            Taco taco = saveTaco(tacoRepository, ingredients);

            Order order = saveOrder(orderRepository, user);
        };
    }

    private List<Ingredient> saveIngredients(IngredientRepository ingredientRepository) {
        return (List<Ingredient>) ingredientRepository.saveAll(
                Arrays.asList(
                        new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                        new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                        new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                        new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                        new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                        new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                        new Ingredient("CHED", "Cheddar", Type.CHEESE),
                        new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                        new Ingredient("SLSA", "Salsa", Type.SAUCE),
                        new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
                )
        );
    }

    private User saveUser(UserRepository userRepository, PasswordEncoder encoder) {
        return userRepository.save(
                new User("ss", encoder.encode("ss"),
                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                        "76227", "123-123-1234")
        );
    }

    private Taco saveTaco(TacoRepository tacoRepository, List<Ingredient> ingredients) {
        int max = ingredients.size() - 1;
        int min = 1;
        max = (int)((Math.random() * ((max - min) + 1)) + min);

        return tacoRepository.save(new Taco("Tacdo", ingredients.subList(min, max)));
    }

    private Order saveOrder(OrderRepository orderRepository, User user) {
        return orderRepository.save(
                new Order("Order","street", "city", "state", "zip", "346712595916175", "12/22", "333", user)
        );
    }

}