package com.spring.payment.frameworks.web;

import com.spring.payment.interfaceadapters.controllers.PaymentController;
import com.spring.payment.interfaceadapters.presenters.dto.PaymentDto;
import com.spring.payment.util.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
    public ResponseEntity<PaymentDto> insert(@Valid @RequestBody PaymentDto dto){
        return ResponseEntity.ok()
                .body(controller.insert(dto));
    }

    @PutMapping(value = "/{PaymentId}", produces = "application/json")
    @Operation(summary = "Atualiza o status do pagamentodo Pagamento")
    public ResponseEntity<PaymentDto> update(@PathVariable Long PaymentId, @Valid @RequestParam PaymentStatus status){
        return ResponseEntity.ok()
                .body(controller.updateStatus(PaymentId, status));
    }
}
