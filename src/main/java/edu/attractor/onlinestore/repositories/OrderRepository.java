package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.client.id = ?1")
    List<Order> findAllByClientId(int clientId);

    List<Order> findAllByClient_IdAndIsPaidFalse(int clientId);
}
