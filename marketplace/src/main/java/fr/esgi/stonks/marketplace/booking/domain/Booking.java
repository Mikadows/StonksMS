package fr.esgi.stonks.marketplace.booking.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Booking {
    private String memberId;
    private String companyId;
    private String from;
    private String to;
}
