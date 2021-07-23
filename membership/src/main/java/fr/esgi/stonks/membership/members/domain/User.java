package fr.esgi.stonks.membership.members.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private String birthdate;
    private String qualifications;
    private DriverLicence driverLicence;
    private PaymentCard paymentCard;
}
