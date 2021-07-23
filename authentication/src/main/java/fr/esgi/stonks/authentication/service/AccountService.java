package fr.esgi.stonks.authentication.service;

import fr.esgi.stonks.authentication.model.Account;
import fr.esgi.stonks.authentication.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder bCryptEncoder;

    /**
     * Constructor Injection
     * better than @Autowired
     */
    public AccountService(AccountRepository accountRepository, PasswordEncoder encoder) {
        this.accountRepository = accountRepository;
        this.bCryptEncoder = encoder;
    }

    public Account createAccount(Account account) {
        return this.accountRepository.save(
                Account.builder()
                        .email(account.getEmail())
                        .password(this.bCryptEncoder.encode(account.getPassword()))
                        .role("CLIENT")
                        .build()
        );
    }

    public boolean isCredentialValid(String email, String password) {
        Account account = this.getAccountByEmail(email);
        return this.bCryptEncoder.matches(password, account.getPassword());
    }

    public Optional<Account> findByEmail(String email) {
        return this.accountRepository.findByEmail(email);
    }

    public Account getAccountByEmail(String email) {
        Optional<Account> account = this.accountRepository.findByEmail(email);
        if(!account.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find email: " + email);
        }
        return account.get();
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> user = this.accountRepository.findByEmail(email);
        if(user.isPresent()) {
            List<GrantedAuthority> authorities = getUserAuthority(user.get().getRole());

            return buildUserForAuthentication(user.get(), authorities);
        } else {
            throw new UsernameNotFoundException("Email not found");
        }
    }
    private List<GrantedAuthority> getUserAuthority(String userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRoles));
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(Account user, List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
