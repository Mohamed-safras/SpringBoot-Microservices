package com.dc_tech_labs.course_service.controller;
import com.dc_tech_labs.course_service.dto.CourseRequest;
import com.dc_tech_labs.course_service.dto.CourseResponse;
import com.dc_tech_labs.course_service.model.Course;
import com.dc_tech_labs.course_service.service.CourseService;
import com.dc_tech_labs.course_service.service.KafkaMessagePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
@Slf4j
@EnableKafka
public class CourseController {
    private final CourseService courseService;
    @Autowired
    private KafkaMessagePublisher publisher;

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseRequest courseRequest) throws JsonProcessingException {

           Course createCourse = courseService.CreateCourse(courseRequest);

           publisher.sendMessageToTopic("dc-tech-labs-microservice",createCourse);

           return ResponseEntity.ok(createCourse);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse> getAllCourse(){
      return   courseService.getAllCourse();
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Course updateCourse(@Valid @RequestBody Course course){
        courseService.updateCourse(course);
        return course;
    }

    // Exception handler for validation errors (optional)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
