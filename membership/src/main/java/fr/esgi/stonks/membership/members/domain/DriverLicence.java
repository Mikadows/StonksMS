package fr.esgi.stonks.membership.members.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverLicence {
    private String name;
    private String validityDate;
    private String date;
}
