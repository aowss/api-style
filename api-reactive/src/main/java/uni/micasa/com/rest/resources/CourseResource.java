package uni.micasa.com.rest.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import uni.micasa.com.activities.RetrieveCourses;
import uni.micasa.com.rest.representations.CourseRepresentation;

import java.io.IOException;

@RestController
@RequestMapping("reactive")
public class CourseResource {

    RetrieveCourses retrieveCourses;

    public CourseResource(RetrieveCourses retrieveCourses) {
        this.retrieveCourses = retrieveCourses;
    }

    @GetMapping(value = "courses")
    public ResponseEntity<Flux<CourseRepresentation>> coursesList(@RequestParam("departmentCode") String departmentCode) throws IOException {
        var courses = retrieveCourses.retrieveCourses(departmentCode);
        return ResponseEntity.ok(courses
                .map(CourseRepresentation.from));
    }

}
