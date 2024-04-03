package com.dc_tech_labs.course_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, String> template; // Keep String for key

    @Autowired
    private ObjectMapper objectMapper; // Inject ObjectMapper bean

    Logger log = LoggerFactory.getLogger(KafkaMessagePublisher.class);

    public void sendMessageToTopic(String topic, Object message) {
        try {
            String serializedMessage = objectMapper.writeValueAsString(message);
            CompletableFuture<SendResult<String, String>> future = template.send(topic, serializedMessage);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent message=[" + serializedMessage + "] with offset=["
                            + result.getRecordMetadata().offset() + "] to topic: " + topic);
                } else {
                    log.error("Unable to send message=[" + serializedMessage + "] to topic: " + topic + " due to : " + ex.getMessage());
                }
            });
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON: {}", e.getMessage());
        }
    }
}
