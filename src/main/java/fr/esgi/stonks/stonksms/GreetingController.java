package fr.esgi.stonks.stonksms;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GreetingController {

    @GetMapping
    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("Hello Stonks", HttpStatus.OK);
    }

}
