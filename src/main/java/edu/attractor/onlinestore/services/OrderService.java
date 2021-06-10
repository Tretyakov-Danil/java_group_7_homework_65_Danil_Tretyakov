package edu.attractor.onlinestore.services;

import edu.attractor.onlinestore.entities.Order;
import edu.attractor.onlinestore.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getClientOrders(String clientId) {
        return this.orderRepository.findAllByClient_Id(clientId);
    }

    public List<Order> getClientBasket(String clientId) {
        return this.orderRepository.findAllByClient_IdAndIsPaidFalse(clientId);
    }
}