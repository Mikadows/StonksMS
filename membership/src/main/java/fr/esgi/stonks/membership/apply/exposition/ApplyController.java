package fr.esgi.stonks.membership.apply.exposition;

import fr.esgi.stonks.membership.Payments.PaymentController;
import fr.esgi.stonks.membership.apply.domain.DriverLicence;
import fr.esgi.stonks.membership.apply.domain.PaymentCard;
import fr.esgi.stonks.membership.apply.domain.User;
import fr.esgi.stonks.membership.apply.exposition.resquests.ApplyRequest;
import fr.esgi.stonks.membership.regulationEngine.MembershipRegulation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/apply")
public class ApplyController {

    private final PaymentController paymentController;

    @PostMapping()
    public ResponseEntity<?> applyMember(@RequestBody ApplyRequest request){
        User user = null;
        try {
            DriverLicence driverLicence = DriverLicence.builder()
                    .date(request.getDriverLicenceDate())
                    .validityDate(request.getPaymentCardValidityDate())
                    .name(request.getDriverLicenceName())
                    .build();

            PaymentCard paymentCard = PaymentCard.builder()
                    .name(request.getPaymentCardName())
                    .number(request.getPaymentCardNumber())
                    .cryptogram(request.getPaymentCardCryptogram())
                    .validityDate(request.getPaymentCardValidityDate())
                    .build();

            user = User.builder()
                    .paymentCard(paymentCard)
                    .driverLicence(driverLicence)
                    .birthdate(request.getBirthdate())
                    .name(request.getName())
                    .email(request.getEmail())
                    .qualifications(request.getQualifications())
                    .build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if(MembershipRegulation.verifyApplication(user)){
            paymentController.processPayment(user.getPaymentCard());
            //TODO insert BDD
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
