package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.FilterDto;
import edu.attractor.onlinestore.dtos.OrderDto;
import edu.attractor.onlinestore.dtos.ProductDto;
import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.entities.Order;
import edu.attractor.onlinestore.entities.Product;
import edu.attractor.onlinestore.exceptions.ResourceNotFoundException;
import edu.attractor.onlinestore.exceptions.UserNotFoundException;
import edu.attractor.onlinestore.services.ClientService;
import edu.attractor.onlinestore.services.OrderService;
import edu.attractor.onlinestore.services.ProductService;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper = new ModelMapper();
    private final ClientService clientService;
    private final ProductService productService;

    @GetMapping
    public String showClientAllOrders(Model model, Authentication auth){
        Optional<Authentication> authOptional = Optional.of(auth);
        model.addAttribute("orders", this.orderService.getClientOrders(
                this.clientService.isClientOnline(authOptional, model).getId()).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .sorted(Comparator.comparing(OrderDto::getDateOfOrder).reversed())
                .collect(Collectors.toList()));
        return "orders";
    }

    @GetMapping("/basket")
    public String showClientBasket(Model model, Authentication auth){
        Optional<Authentication> authOptional = Optional.of(auth);
        model.addAttribute("orders", this.orderService.getClientBasket(
                this.clientService.isClientOnline(authOptional, model).getId()).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .sorted(Comparator.comparing(OrderDto::getDateOfOrder).reversed())
                .collect(Collectors.toList()));
        return "orders";
    }

    @GetMapping("/{orderId}")
    public String showOrderDetails(Model model, @PathVariable int orderId, Authentication auth){
        Optional<Authentication> authOptional = Optional.of(auth);
        this.clientService.isClientOnline(authOptional, model);
        Order order = this.orderService.getOrderById(orderId).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("order", modelMapper.map(order, OrderDto.class));
        return "order_details";
    }

    @GetMapping("/{orderId}/pay")
    public String showPaymentPage(Model model, @PathVariable int orderId, Authentication auth){
        Optional<Authentication> authOptional = Optional.of(auth);
        this.clientService.isClientOnline(authOptional, model);
        Order order = this.orderService.getOrderById(orderId).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("order", modelMapper.map(order, OrderDto.class));
        return "order_payment";
    }

    @PostMapping("/{orderId}/pay")
    public String confirmPayment(Model model,
                                 @PathVariable int orderId,
                                 @RequestParam int productId,
                                 Authentication auth){

        Optional<Authentication> authOptional = Optional.of(auth);
        this.clientService.isClientOnline(authOptional, model);

        model.addAttribute("order",
                modelMapper.map(this.orderService.payForOrder(orderId, productId), OrderDto.class));
        return "order_payment";
    }

    @PostMapping("/{orderId}/changeAmount")
    public String changeAmountInOrder(RedirectAttributes redirAttrs,
                                      @PathVariable int orderId,
                                      @RequestParam int amount,
                                      Authentication auth){
        Optional<Authentication> authOptional = Optional.of(auth);
        this.clientService.isClientOnline(authOptional, redirAttrs);

        this.orderService.changeAmountOfProductOfOrder(orderId, amount);

        return "redirect:/orders/" + orderId;
    }

    @PostMapping(value = "/make")
    public String makeOrder(@RequestParam int productId,
                            @RequestParam int amount,
                            Authentication auth){
        Client client = (Client) auth.getPrincipal();
        LocalDateTime presentTime = LocalDateTime.now();
        Product product = this.productService.getProductInfo(productId)
                .orElseThrow(ResourceNotFoundException::new);
        Order newOrder = Order.builder()
                .client(this.clientService.findByEmail(client.getEmail()).orElseThrow(UserNotFoundException::new))
                .product(product)
                .amount(amount)
                .isPaid(false)
                .dateOfOrder(presentTime)
                .build();

        this.orderService.saveNewOrder(newOrder);

        return "redirect:/products/" + product.getId();

    }


    @PostMapping("/delete")
    public String deleteOrderFromBasket(RedirectAttributes redirAttrs,
                                        Authentication auth,
                                        @RequestParam int orderId){
        this.orderService.deleteOrder(orderId);

        Optional<Authentication> authOptional = Optional.of(auth);
        redirAttrs.addFlashAttribute("orders", this.orderService.getClientBasket(
                this.clientService.isClientOnline(authOptional, redirAttrs).getId()).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .sorted(Comparator.comparing(OrderDto::getDateOfOrder).reversed())
                .collect(Collectors.toList()));

        return "redirect:/orders/basket";
    }
}
