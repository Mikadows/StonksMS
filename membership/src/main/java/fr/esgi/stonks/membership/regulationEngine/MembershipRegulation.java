package fr.esgi.stonks.membership.regulationEngine;

import fr.esgi.stonks.membership.members.domain.User;

import java.util.Date;

public class MembershipRegulation {

    public static Boolean verifyApplication(User user){
        return verifyDriverLicence(user) && verifyPaymentCard(user);
    }

    public static Boolean verifyDriverLicence(User user){
        return verifyValidityDate(user.getDriverLicence().getValidityDate())
                && verifyGivenDate(user.getDriverLicence().getDate())
                && verifyUserName(user.getName(), user.getDriverLicence().getName());
    }

    public static Boolean verifyPaymentCard(User user){
        return verifyValidityDate(user.getPaymentCard().getValidityDate())
                && verifyUserName(user.getName(), user.getPaymentCard().getName());
    }

    private static boolean verifyUserName(String name, String nameOnDocument) {
        return name.equals(nameOnDocument);
    }

    private static boolean verifyGivenDate(String date) {
        return new Date().getTime() > new Date(date).getTime();
    }

    private static Boolean verifyValidityDate(String validityDate) {
        return new Date(validityDate).getTime() > new Date().getTime();
    }
}
