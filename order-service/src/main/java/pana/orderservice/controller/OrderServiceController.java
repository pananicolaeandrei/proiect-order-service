package pana.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pana.orderservice.common.TxResponse;
import pana.orderservice.entity.Order;
import pana.orderservice.service.OrderService;
import pana.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderServiceController {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/placeOrder/")
    public TxResponse placeOrder(@RequestBody Order order) {

        return service.placeOrder(order);
    }

    @GetMapping
    public List<Order> getOrders() {
        return service.getOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Integer id) {
        return orderRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        orderRepository.delete(order.get());

    }
}
