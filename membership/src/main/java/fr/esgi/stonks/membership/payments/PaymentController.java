package fr.esgi.stonks.membership.payments;

import fr.esgi.stonks.membership.members.domain.PaymentCard;
import fr.esgi.stonks.membership.members.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentController {

    public void processPayment(PaymentCard paymentCard) {
    }

    public void savePayment(String userId, User user) {
        //TODO insert payment in BDD
    }
}
