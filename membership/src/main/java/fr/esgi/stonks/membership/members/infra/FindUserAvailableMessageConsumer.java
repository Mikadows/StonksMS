package fr.esgi.stonks.membership.members.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FindUserAvailableMessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(FindUserAvailableMessageConsumer.class);

    @KafkaListener(topics = "userAvailableTopic", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
