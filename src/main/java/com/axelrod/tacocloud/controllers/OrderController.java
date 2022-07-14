package com.axelrod.tacocloud.controllers;

import com.axelrod.tacocloud.configuration.AppConfiguration;
import com.axelrod.tacocloud.entity.Order;
import com.axelrod.tacocloud.entity.User;
import com.axelrod.tacocloud.repository.jdbc.IngredientRepository;
import com.axelrod.tacocloud.repository.jpa.OrderRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    @Setter(onMethod = @__({@Autowired}))
    private OrderRepository orderRepository;
    @Setter(onMethod = @__({@Autowired}))
    private AppConfiguration.OrderProps props;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, PageRequest.of(0, props.getPageSize())));

        return "orderList";
    }
}
