package api.micasa.com.rest.resources;

import api.micasa.com.activities.RetrieveCourses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import api.micasa.com.rest.representations.CourseRepresentation;

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
