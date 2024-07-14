package com.spring.payment.frameworks.web;

import com.spring.payment.interfaceadapters.controllers.PaymentController;
import com.spring.payment.interfaceadapters.presenters.dto.PaymentDto;
import com.spring.payment.util.enums.PaymentStatus;
import com.spring.payment.util.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/payment")
@Tag(name = "Payment", description = "Metódos para manipulçação de Pagamento")
public class PaymentWeb {
    @Resource
    private PaymentController controller;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Adiciona um novo cartão")
    public ResponseEntity<?> insert(@Valid @RequestBody PaymentDto dto) throws BusinessException {
        controller.insert(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Payment successfully registered!");
    }

    @PutMapping(value = "/{PaymentId}", produces = "application/json")
    @Operation(summary = "Atualiza o status do pagamentodo Pagamento")
    public ResponseEntity<?> update(@PathVariable Long PaymentId, @Valid @RequestParam PaymentStatus status){
        controller.updateStatus(PaymentId, status);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Payment status changed successfully!");
    }
}
