package fr.esgi.stonks.marketplace.booking.exposition;

import fr.esgi.stonks.marketplace.booking.domain.Booking;
import fr.esgi.stonks.marketplace.booking.exposition.resquests.BookingRequest;
import fr.esgi.stonks.marketplace.booking.infra.BookingMessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/booking")
public class BookingController {

    private final BookingMessageProducer bookingMessageProducer;

    @PostMapping()
    public ResponseEntity<?> bookMember(@RequestBody BookingRequest request){
        Booking booking = Booking.builder()
                .memberId(request.getMemberId())
                .companyId(request.getCompanyId())
                .from(request.getFrom())
                .to(request.getTo())
                .build();
       // this.bookingMessageProducer.sendMessage(booking.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
