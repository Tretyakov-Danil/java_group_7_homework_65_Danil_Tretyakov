package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByClient_Id(String clientId);

    List<Order> findAllByClient_IdAndIsPaidFalse(String clientId);
}
