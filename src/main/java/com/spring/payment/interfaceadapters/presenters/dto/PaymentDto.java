package com.spring.payment.interfaceadapters.presenters.dto;


import com.spring.payment.entities.Card;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    @Schema(description = "Valor do pedido de compra", example = "100.00")
    private BigDecimal value;

    @Schema(description = "Total de Parcelas", example = "1")
    private int totalCardInstallment;

    @Schema(description = "Cartão utilizado para o pagamento da compra")
    private CardPaymentDto card;

    @Schema(description = "Número do pedido de compra", example = "1234567890")
    private String order;

    @Schema(description = "Identificação do usuário que realizou a compra", example = "1234567890")
    private String user;


}
