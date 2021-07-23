package fr.esgi.stonks.membership.apply.exposition.resquests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyRequest {
    private String name;
    private String email;
    private String birthdate;
    private String qualifications;
    private String driverLicenceName;
    private String driverLicenceValidityDate;
    private String driverLicenceDate;
    private String paymentCardName;
    private String paymentCardValidityDate;
    private String paymentCardNumber;
    private String paymentCardCryptogram;
}
