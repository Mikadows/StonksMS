package fr.esgi.stonks.marketplace.booking.exposition.resquests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingRequest {
    private String memberId;
    private String companyId;
    private String from;
    private String to;
}
