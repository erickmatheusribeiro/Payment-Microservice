package com.spring.payment.util.exception;

import com.spring.payment.util.MessageUtil;

public class BusinessException extends Exception {

    public BusinessException(String code) {
        super(MessageUtil.getMessage(code));
    }
}

