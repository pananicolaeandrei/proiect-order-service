package pana.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pana.orderservice.common.Payment;
import pana.orderservice.common.TxResponse;
import pana.orderservice.entity.Order;
import pana.orderservice.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate template;

    public List<Order> getOrders(){
        return repository.findAll();
    }

    public TxResponse placeOrder(Order order){
        repository.save(order);
        Payment paymentReq = new Payment();
        paymentReq.setOrderId(order.getId());
        paymentReq.setAmount(order.getQuantity()*order.getPrice());
        Payment paymentRes =
                template.postForObject("http://localhost:8098/payment/doPay/",
                paymentReq, Payment.class);
        TxResponse txResponse = new TxResponse();
        txResponse.setOrder(order);
        txResponse.setStatus(paymentRes.getPaymentStatus());
        txResponse.setAmount(paymentRes.getAmount());
        txResponse.setTxId(paymentRes.getTxId());
        return txResponse;
    }
}
