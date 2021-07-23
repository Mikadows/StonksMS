package fr.esgi.stonks.membership.workflow;

import fr.esgi.stonks.membership.members.domain.User;
import fr.esgi.stonks.membership.workflow.booking.infra.NewMemberMessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkflowController {

    private final NewMemberMessageProducer newMemberMessageProducer;

    public void sendMessageUserAvailable(String userId, User user){
        this.newMemberMessageProducer.sendMessage(userId + user.toString());
    }



}
