package pana.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pana.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
