package fr.esgi.stonks.authentication.controller;

import fr.esgi.stonks.authentication.model.Login;
import fr.esgi.stonks.authentication.model.LoginResponse;
import fr.esgi.stonks.authentication.security.TokenProvider;
import fr.esgi.stonks.authentication.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManager;
    private final AccountService accountService;

    public AuthenticationController(TokenProvider tokenProvider,
                                    AuthenticationManagerBuilder authenticationManager,
                                    AccountService accountService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody Login login) {
        if(login.getEmail() != null && login.getPassword() != null
                && !login.getEmail().isBlank() && !login.getPassword().isBlank()
                && !login.getEmail().isEmpty() && !login.getPassword().isEmpty()) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    login.getEmail(),
                    login.getPassword());
            authenticationManager.getObject().authenticate(authenticationToken);

            String _token = tokenProvider.createToken(this.accountService.getAccountByEmail(login.getEmail()));

            LoginResponse response = LoginResponse.builder().token(_token).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
