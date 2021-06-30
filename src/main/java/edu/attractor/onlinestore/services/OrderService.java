package edu.attractor.onlinestore.services;

import edu.attractor.onlinestore.entities.Order;
import edu.attractor.onlinestore.exceptions.ResourceNotFoundException;
import edu.attractor.onlinestore.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public List<Order> getClientOrders(int clientId) {
        return this.orderRepository.findAllByClientId(clientId);
    }

    public List<Order> getClientBasket(int clientId) {
        return this.orderRepository.findAllByClient_IdAndIsPaidFalse(clientId);
    }

    public Order saveNewOrder(Order newOrder) {
        return this.orderRepository.save(newOrder);
    }

    public void deleteOrder(int orderId) {
        this.orderRepository.deleteById(orderId);
    }

    public Optional<Order> getOrderById(int orderId) {
        return this.orderRepository.findById(orderId);
    }

    public void changeAmountOfProductOfOrder(int orderId, int amount) {
        this.orderRepository.changeAmountOfOrder(orderId, amount);
    }
}
