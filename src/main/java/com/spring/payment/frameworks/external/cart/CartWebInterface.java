package com.spring.payment.frameworks.external.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cart-service")
public interface CartWebInterface {
    @PutMapping(value = "/confirm-cart", produces = "application/json")
    void confirmOrder(@RequestParam String id);


    @PutMapping(value = "/cancel-cart", produces = "application/json")
    void cancelOrder(@RequestParam String id);
}
