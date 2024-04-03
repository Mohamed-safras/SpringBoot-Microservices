package com.dc_tech_labs.course_service.repository;

import com.dc_tech_labs.course_service.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course,String> {
}
