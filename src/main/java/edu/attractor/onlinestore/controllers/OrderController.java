package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.OrderDto;
import edu.attractor.onlinestore.services.OrderService;
import org.modelmapper.ModelMapper;
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

    @GetMapping("/{clientId}")
    public List<OrderDto> showClientAllOrders(@PathVariable String clientId){
        return this.orderService.getClientOrders(clientId).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/basket/{clientId}")
    public List<OrderDto> showClientBasket(@PathVariable String clientId){
        return this.orderService.getClientBasket(clientId).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }
}
