package api.micasa.com.activities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import api.micasa.com.domain.Course;
import api.micasa.com.services.CourseService;

import java.io.IOException;

@Component
public class RetrieveCourses {

    CourseService courseService;

    public RetrieveCourses(CourseService courseService) {
        this.courseService = courseService;
    }

    public Flux<Course> retrieveCourses(String departmentCode) throws IOException {
        return courseService.retrieveCourses(departmentCode);
    }

}
