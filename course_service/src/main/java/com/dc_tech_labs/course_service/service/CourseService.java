package com.dc_tech_labs.course_service.service;
import com.dc_tech_labs.course_service.dto.CourseRequest;
import com.dc_tech_labs.course_service.dto.CourseResponse;
import com.dc_tech_labs.course_service.model.Course;
import com.dc_tech_labs.course_service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;

    public Course CreateCourse(CourseRequest courseRequest){
        Course course = Course.builder()
                .name(courseRequest.getName())
                .description(courseRequest.getDescription())
                .number(courseRequest.getNumber())
                .accommodationProvided(courseRequest.getAccommodationProvided())
                .trainingPartnerWebsite(courseRequest.getTrainingPartnerWebsite())
                .topics(courseRequest.getTopics())
                .durationHours(courseRequest.getDurationHours())
                .courseDurationInMin(courseRequest.getCourseDurationInMin())
                .trainingPartnerName(courseRequest.getTrainingPartnerName())
                .trainingPartnerId(courseRequest.getTrainingPartnerId())
                .trainingPartnerFirstName(courseRequest.getTrainingPartnerFirstName())
                .trainingPartnerLastname(courseRequest.getTrainingPartnerLastname())
                .trainingPartnerEmail(courseRequest.getTrainingPartnerEmail())
                .approvalAction(courseRequest.getApprovalAction())
                .approvalStatus(courseRequest.getApprovalStatus())
                .build();
        courseRepository.save(course);
        log.info("Course {} has been saved",course.getId());
        return course;
    }

    public List<CourseResponse> getAllCourse(){
        List<Course> courses = courseRepository.findAll();

        return courses.stream().map(this::mapToCourseResponse).toList();
    }

    private CourseResponse mapToCourseResponse(Course course) {
        return CourseResponse.builder().
                id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .number(course.getNumber())
                .accommodationProvided(course.getAccommodationProvided())
                .trainingPartnerWebsite(course.getTrainingPartnerWebsite())
                .topics(course.getTopics())
                .durationHours(course.getDurationHours())
                .courseDurationInMin(course.getCourseDurationInMin())
                .trainingPartnerName(course.getTrainingPartnerName())
                .trainingPartnerId(course.getTrainingPartnerId())
                .trainingPartnerFirstName(course.getTrainingPartnerFirstName())
                .trainingPartnerLastname(course.getTrainingPartnerLastname())
                .trainingPartnerEmail(course.getTrainingPartnerEmail())
                .approvalAction(course.getApprovalAction())
                .approvalStatus(course.getApprovalStatus())
                .build();
    }

    public void updateCourse (Course course){
        Course saveCourse = courseRepository.findById(course.getId()).orElseThrow(()-> new RuntimeException(String.format("Cannot find course by ID %s",course.getId())));

        saveCourse.setName(course.getName());
        saveCourse.setCourseDurationInMin(course.getCourseDurationInMin());
        saveCourse.setDescription(course.getDescription());
        saveCourse.setAccommodationProvided(course.getAccommodationProvided());

        courseRepository.save(course);
    }
}