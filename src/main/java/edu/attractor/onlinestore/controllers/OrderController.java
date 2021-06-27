package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.OrderDto;
import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.entities.Order;
import edu.attractor.onlinestore.services.ClientService;
import edu.attractor.onlinestore.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper = new ModelMapper();
    private final ClientService clientService;

    public OrderController(OrderService orderService, ClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @GetMapping
    public String showClientAllOrders(Model model, Authentication auth){
        Optional<Authentication> authOptional = Optional.of(auth);
        model.addAttribute("orders", this.orderService.getClientOrders(
                this.clientService.isClientOnline(authOptional, model).getId()).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList()));
        return "orders";
    }

    @GetMapping("/basket")
    public String showClientBasket(Model model, Authentication auth){
        Optional<Authentication> authOptional = Optional.of(auth);
        model.addAttribute("orders", this.orderService.getClientBasket(
                this.clientService.isClientOnline(authOptional, model).getId()).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList()));
        return "orders";
    }

    @PostMapping("/make")
    public String makeOrder(Model model,
                            @RequestBody OrderDto orderDto,
                            @RequestParam int amount,
                            Authentication auth,
                            RedirectAttributes redirectAttrs){
        Client client = (Client) auth.getPrincipal();
        LocalDateTime presentTime = LocalDateTime.now();

        Order newOrder = Order.builder()
                .client(this.clientService.findByEmail(client.getEmail()).get())
                .product(orderDto.getProduct())
                .amount(amount)
                .isPaid(false)
                .dateOfOrder(presentTime)
                .build();
        this.orderService.saveNewOrder(newOrder);
        return "redirect:/products/" + orderDto.getProduct().getId();

    }
}
