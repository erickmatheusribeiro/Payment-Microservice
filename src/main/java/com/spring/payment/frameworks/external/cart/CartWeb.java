package com.spring.payment.frameworks.external.cart;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CartWeb {

    @Autowired
    private CartWebInterface cartService;

    public void confirmOder(String orderId) {
        log.info("confirm order id is " + orderId);
        cartService.confirmOrder(orderId);
    }

    public void cancelOder(String orderId) {
        log.info("cancel order id is " + orderId);
        cartService.cancelOrder(orderId);
    }

}
