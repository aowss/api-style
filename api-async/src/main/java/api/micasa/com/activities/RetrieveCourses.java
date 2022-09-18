package api.micasa.com.activities;

import api.micasa.com.services.CourseService;
import org.springframework.stereotype.Component;
import api.micasa.com.domain.Course;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class RetrieveCourses {

    CourseService courseService;

    public RetrieveCourses(CourseService courseService) {
        this.courseService = courseService;
    }

    public CompletableFuture<List<Course>> retrieveCourses(String departmentCode) {
        return courseService.retrieveCourses(departmentCode);
    }

}
