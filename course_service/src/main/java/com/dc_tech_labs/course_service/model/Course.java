package com.dc_tech_labs.course_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
@Document(value = "course")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Slf4j
public class Course {
    @Id
    private String id;

    @JsonProperty("course name")
    private String name;

    private String description;

    @JsonProperty("course number")
    @Indexed(unique = true)
    private String number;

    @JsonProperty("accommodation provided")
    private String accommodationProvided;

    @JsonProperty("training partner website")
    private String trainingPartnerWebsite;

    private ArrayList<String> topics;

    @JsonProperty("duration hours")
    private String durationHours;

    @JsonProperty("course duration (in minutes)")
    private int courseDurationInMin;

    @JsonProperty("training partner name")
    private String trainingPartnerName;

    @JsonProperty("training partner id")
    private int trainingPartnerId;

    @JsonProperty("training partner first name")
    private String trainingPartnerFirstName;

    @JsonProperty("training partner last name")
    private String trainingPartnerLastname;

    @JsonProperty("training partner email")
    private String trainingPartnerEmail;

    private ApprovalAction approvalAction;

    private ApprovalStatus approvalStatus;

    public Course(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Course deserializedCourse = objectMapper.readValue(jsonString, Course.class);

        // Defensive Programming (Optional)
        // Check for null values before copying properties
        if (deserializedCourse != null) {
            this.id = deserializedCourse.getId();
            this.name = deserializedCourse.getName();
            this.description = deserializedCourse.getDescription();
            this.number = deserializedCourse.getNumber();
            this.accommodationProvided = deserializedCourse.getAccommodationProvided();
            this.trainingPartnerWebsite = deserializedCourse.getTrainingPartnerWebsite();
            this.topics = deserializedCourse.getTopics();
            this.durationHours = deserializedCourse.getDurationHours();
            this.courseDurationInMin = deserializedCourse.getCourseDurationInMin();
            this.trainingPartnerName = deserializedCourse.getTrainingPartnerName();
            this.trainingPartnerId = deserializedCourse.getTrainingPartnerId();
            this.trainingPartnerFirstName = deserializedCourse.getTrainingPartnerFirstName();
            this.trainingPartnerLastname = deserializedCourse.getTrainingPartnerLastname();
            this.approvalAction = deserializedCourse.getApprovalAction();
            this.approvalStatus = deserializedCourse.getApprovalStatus();
            this.trainingPartnerEmail = deserializedCourse.getTrainingPartnerEmail();
        } else {
            // Handle the case where deserialization fails
            log.error("Error deserializing Course object from JSON: {}", jsonString);
            // You can throw a custom exception here if needed
        }
    }
    public Course(String id, String name, String description, String number, ArrayList<String> certificationOrLicensureProgram, String accommodationProvided, String trainingPartnerWebsite, ArrayList<String> topics, String durationHours, int courseDurationInMin, String trainingPartnerName, int trainingPartnerId, String trainingPartnerFirstName, String trainingPartnerLastname, ApprovalAction approvalAction, ApprovalStatus approvalStatus,String trainingPartnerEmail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.number = number;
        this.accommodationProvided = accommodationProvided;
        this.trainingPartnerWebsite = trainingPartnerWebsite;
        this.topics = topics;
        this.durationHours = durationHours;
        this.courseDurationInMin = courseDurationInMin;
        this.trainingPartnerName = trainingPartnerName;
        this.trainingPartnerId = trainingPartnerId;
        this.trainingPartnerFirstName = trainingPartnerFirstName;
        this.trainingPartnerLastname = trainingPartnerLastname;
        this.approvalAction = approvalAction;
        this.approvalStatus = approvalStatus;
        this.trainingPartnerEmail = trainingPartnerEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAccommodationProvided() {
        return accommodationProvided;
    }

    public void setAccommodationProvided(String accommodationProvided) {
        this.accommodationProvided = accommodationProvided;
    }

    public String getTrainingPartnerWebsite() {
        return trainingPartnerWebsite;
    }

    public void setTrainingPartnerWebsite(String trainingPartnerWebsite) {
        this.trainingPartnerWebsite = trainingPartnerWebsite;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<String> topics) {
        this.topics = topics;
    }

    public String getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(String durationHours) {
        this.durationHours = durationHours;
    }

    public int getCourseDurationInMin() {
        return courseDurationInMin;
    }

    public void setCourseDurationInMin(int courseDurationInMin) {
        this.courseDurationInMin = courseDurationInMin;
    }

    public String getTrainingPartnerName() {
        return trainingPartnerName;
    }

    public void setTrainingPartnerName(String trainingPartnerName) {
        this.trainingPartnerName = trainingPartnerName;
    }

    public int getTrainingPartnerId() {
        return trainingPartnerId;
    }

    public void setTrainingPartnerId(int trainingPartnerId) {
        this.trainingPartnerId = trainingPartnerId;
    }

    public String getTrainingPartnerFirstName() {
        return trainingPartnerFirstName;
    }

    public void setTrainingPartnerFirstName(String trainingPartnerFirstName) {
        this.trainingPartnerFirstName = trainingPartnerFirstName;
    }

    public String getTrainingPartnerLastname() {
        return trainingPartnerLastname;
    }

    public void setTrainingPartnerLastname(String trainingPartnerLastname) {
        this.trainingPartnerLastname = trainingPartnerLastname;
    }

    public ApprovalAction getApprovalAction() {
        return approvalAction;
    }

    public void setApprovalAction(ApprovalAction approvalAction) {
        this.approvalAction = approvalAction;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getTrainingPartnerEmail() {
        return trainingPartnerEmail;
    }

    public void setTrainingPartnerEmail(String trainingPartnerEmail) {
        this.trainingPartnerEmail = trainingPartnerEmail;
    }
}
