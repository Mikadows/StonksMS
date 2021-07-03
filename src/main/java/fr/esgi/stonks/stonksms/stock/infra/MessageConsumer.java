package fr.esgi.stonks.stonksms.stock.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "stocks", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}