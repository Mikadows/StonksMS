package fr.esgi.stonks.authentication.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CLIENT, ROLE_BUSINESS, ROLE_ADMIN;

    public String getAuthority() {
        return name();
    }
}
