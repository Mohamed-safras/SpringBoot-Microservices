package org.dc_tech_labs.service;
import com.dc_tech_labs.course_service.model.ApprovalAction;
import com.dc_tech_labs.course_service.model.ApprovalStatus;
import com.dc_tech_labs.course_service.model.Course;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dc_tech_labs.entity.EmailDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    @Autowired
    public EmailService emailService;
    @Autowired
    private ObjectMapper objectMapper; // For deserialization

    @KafkaListener(topics = "dc-tech-labs-microservice",groupId = "dc-tech")
    public void consumeMessage(String message) {
        try {
            // Deserialize with field name annotations
            Course deserializedCourse = objectMapper.readValue(message, Course.class);
            log.info("Received message for course: {}", deserializedCourse.getName()); // Replace with property name
            // Process the deserialized course object and trigger notification logic
            // ... Notification logic here ...

            String subject = EmailServiceImpl.getCourseEmailSubject(ApprovalAction.APPROVED.toString(), deserializedCourse.getApprovalStatus().toString(), deserializedCourse.getTrainingPartnerFirstName() + deserializedCourse.getTrainingPartnerLastname());
            String body = EmailServiceImpl.getCourseEmailBody(ApprovalAction.APPROVED.toString(),deserializedCourse.getApprovalStatus().toString(),deserializedCourse.getTrainingPartnerFirstName() + " " + deserializedCourse.getTrainingPartnerLastname(),deserializedCourse.getTrainingPartnerName());

            EmailDetails emailDetails = new EmailDetails();

            emailDetails.setSubject(subject);
            emailDetails.setMsgBody(body);
            emailDetails.setRecipient(deserializedCourse.getTrainingPartnerEmail());

           emailService.sendSimpleMail(emailDetails);
        } catch (JsonProcessingException e) {
            log.error("Error parsing message: {}", e.getMessage());
        }
        }
}
