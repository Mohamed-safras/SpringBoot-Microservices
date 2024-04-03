package com.dc_tech_labs.course_service.dto;
import com.dc_tech_labs.course_service.model.ApprovalAction;
import com.dc_tech_labs.course_service.model.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    @Id
    private String id;
    private String name;
    private String description;
    private String number;
    private String accommodationProvided;
    private String trainingPartnerWebsite;
    private ArrayList<String> topics;
    private String durationHours;
    private int courseDurationInMin;
    private String trainingPartnerName;
    private int trainingPartnerId;
    private String trainingPartnerFirstName;
    private String trainingPartnerEmail;
    private String trainingPartnerLastname;
    private ApprovalAction approvalAction;
    private ApprovalStatus approvalStatus;
}
