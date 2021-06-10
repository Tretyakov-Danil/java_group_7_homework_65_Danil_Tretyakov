package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
