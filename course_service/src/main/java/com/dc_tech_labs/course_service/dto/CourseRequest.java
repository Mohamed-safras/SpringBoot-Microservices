package com.dc_tech_labs.course_service.dto;

import com.dc_tech_labs.course_service.model.ApprovalAction;
import com.dc_tech_labs.course_service.model.ApprovalStatus;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotBlank(message = "course name is required")
    private String name;
    private String description;
    @NotBlank(message = "course is required")
    private String number;
    private ArrayList<String> certificationOrLicensureProgram;
    private String accommodationProvided;
    private String trainingPartnerWebsite;
    private ArrayList<String> topics;
    private String durationHours;
    private int courseDurationInMin;
    @NotBlank(message = "training partner name is required")
    private String trainingPartnerName;
    private int trainingPartnerId;
    @NotBlank(message = "training partner first name is required")
    private String trainingPartnerFirstName;
    @NotBlank(message = "training partner last name is required")
    private String trainingPartnerLastname;
    @NotBlank(message = "training email is required")
    private String trainingPartnerEmail;
    private ApprovalAction approvalAction;
    private ApprovalStatus approvalStatus;

}
