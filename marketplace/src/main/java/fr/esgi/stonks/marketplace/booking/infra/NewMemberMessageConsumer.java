package fr.esgi.stonks.marketplace.booking.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewMemberMessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(NewMemberMessageConsumer.class);

    @KafkaListener(topics = "newMemberTopic", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
