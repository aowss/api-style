package uni.micasa.com.activities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import uni.micasa.com.domain.Course;
import uni.micasa.com.services.CourseService;

import java.io.IOException;
import java.util.List;

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
