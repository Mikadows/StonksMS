package fr.esgi.stonks.membership.workflow.booking.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookingMessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(BookingMessageConsumer.class);

    @KafkaListener(topics = "bookingTopic", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
