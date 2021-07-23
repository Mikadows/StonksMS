package fr.esgi.stonks.membership.apply.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverLicence {
    private String name;
    private String validityDate;
    private String date;
}
