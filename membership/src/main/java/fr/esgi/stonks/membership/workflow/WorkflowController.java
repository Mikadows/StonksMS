package fr.esgi.stonks.membership.workflow;

import fr.esgi.stonks.membership.members.MemberController;
import fr.esgi.stonks.membership.members.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WorkflowController {

    private final MemberController memberController;


    private String findUserAvailable(String qualification, String from, String to){
        return memberController.findUserAvailable(qualification, from, to);
    }



}
