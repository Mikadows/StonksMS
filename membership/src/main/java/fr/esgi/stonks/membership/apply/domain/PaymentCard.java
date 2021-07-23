package fr.esgi.stonks.membership.apply.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentCard {
    private String name;
    private String validityDate;
    private String number;
    private String cryptogram;
}
