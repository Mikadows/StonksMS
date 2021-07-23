package fr.esgi.stonks.authentication.controller;

import fr.esgi.stonks.authentication.model.Account;
import fr.esgi.stonks.authentication.model.Login;
import fr.esgi.stonks.authentication.model.LoginResponse;
import fr.esgi.stonks.authentication.security.TokenProvider;
import fr.esgi.stonks.authentication.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AccountService accountService;

    public AuthenticationController(TokenProvider tokenProvider,
                                    AccountService accountService) {
        this.tokenProvider = tokenProvider;
        this.accountService = accountService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody Login login) {
        if(login.getEmail() != null && login.getPassword() != null
                && !login.getEmail().isBlank() && !login.getPassword().isBlank()
                && !login.getEmail().isEmpty() && !login.getPassword().isEmpty()) {
            boolean isCredentialValid = this.accountService.isCredentialValid(login.getEmail(), login.getPassword());

            if(!isCredentialValid) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }

            String _token = tokenProvider.createToken(this.accountService.getAccountByEmail(login.getEmail()));

            LoginResponse response = LoginResponse.builder().token(_token).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody final Account user) {
        if(user.getEmail() == null || user.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some fields are empty.");
        }
        try {
            Optional<Account> account = this.accountService.findByEmail(user.getEmail());
            if(account.isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(this.accountService.createAccount(user), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
