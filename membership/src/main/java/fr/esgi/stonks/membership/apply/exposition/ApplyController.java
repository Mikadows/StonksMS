package fr.esgi.stonks.membership.apply.exposition;

import fr.esgi.stonks.membership.members.MemberController;
import fr.esgi.stonks.membership.payments.PaymentController;
import fr.esgi.stonks.membership.members.domain.DriverLicence;
import fr.esgi.stonks.membership.members.domain.PaymentCard;
import fr.esgi.stonks.membership.members.domain.User;
import fr.esgi.stonks.membership.apply.exposition.resquests.ApplyRequest;
import fr.esgi.stonks.membership.regulationEngine.MembershipRegulation;
import fr.esgi.stonks.membership.workflow.WorkflowController;
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
    private final MemberController memberController;
    private final WorkflowController workflowController;

    @PostMapping()
    public ResponseEntity<?> applyMember(@RequestBody ApplyRequest request){
        User user = null;
        try {

            DriverLicence driverLicence = this.buildDriverLicence(request);
            PaymentCard paymentCard = this.buildPaymentCard(request);
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
            String userId = memberController.addMember(user);
            this.paymentController.savePayment(userId, user);
            this.paymentController.processPayment(user.getPaymentCard());
            this.workflowController.sendMessageUserAvailable(userId, user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    private PaymentCard buildPaymentCard(ApplyRequest request) {
        return PaymentCard.builder()
                .name(request.getPaymentCardName())
                .number(request.getPaymentCardNumber())
                .cryptogram(request.getPaymentCardCryptogram())
                .validityDate(request.getPaymentCardValidityDate())
                .build();
    }

    private DriverLicence buildDriverLicence(ApplyRequest request) {
        return DriverLicence.builder()
                .date(request.getDriverLicenceDate())
                .validityDate(request.getPaymentCardValidityDate())
                .name(request.getDriverLicenceName())
                .build();
    }

}
