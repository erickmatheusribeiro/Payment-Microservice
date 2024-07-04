package com.spring.payment.frameworks.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.payment.interfaceadapters.controllers.CardController;
import com.spring.payment.interfaceadapters.presenters.dto.CardDto;
import com.spring.payment.util.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value="/api/v1/payment/card")
@Tag(name = "Card", description = "Metódos para manipulçação de Cartão")
public class CardWeb {

    @Resource
    private CardController controller;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Adiciona um novo cartão")
    public ResponseEntity<CardDto> insert(@Valid @RequestBody CardDto dto) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(controller.insert(dto));

    }

    @GetMapping(value = "/{cardNumber}", produces = "application/json")
    @Operation(summary = "recuperar um cartão pelo número")
    public ResponseEntity<Optional<CardDto>> getCard(@PathVariable String cardNumber) throws BusinessException {
        return ResponseEntity.ok()
                .body(controller.findByCardNumber(cardNumber));
    }


    @PutMapping(value = "/{cardNumber}", produces = "application/json")
    @Operation(summary = "Inativa um cartão pelo número")
    public ResponseEntity<CardDto> inactiveCard(@PathVariable String cardNumber) throws BusinessException {
        return ResponseEntity.ok()
                .body(controller.inactiveCard(cardNumber));
    }
}
