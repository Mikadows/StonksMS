package fr.esgi.stonks.membership.members;

import fr.esgi.stonks.membership.members.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberController {

    public String addMember(User user) {
        //TODO insertBDD
        return "1";
    }

    public String findUserAvailable(String qualification, String from, String to) {
        return "1";
    }
}
