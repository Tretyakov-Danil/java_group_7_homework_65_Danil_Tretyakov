package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.OrderDto;
import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper = new ModelMapper();

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String showClientAllOrders(Model model, Authentication auth){
        Client client = (Client) auth.getPrincipal();
        model.addAttribute("orders", this.orderService.getClientOrders(client.getId()).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList()));
        return "orders";
    }

    @GetMapping("/basket")
    public String showClientBasket(Model model, Authentication auth){
        Client client = (Client) auth.getPrincipal();
        model.addAttribute("orders", this.orderService.getClientBasket(client.getId()).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList()));
        return "orders";
    }

}
