package api.micasa.com.rest.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import api.micasa.com.activities.RetrieveCourses;
import api.micasa.com.rest.representations.CourseRepresentation;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("sync")
public class CourseResource {

    RetrieveCourses retrieveCourses;

    public CourseResource(RetrieveCourses retrieveCourses) {
        this.retrieveCourses = retrieveCourses;
    }

    @GetMapping(value = "courses")
    public ResponseEntity<List<CourseRepresentation>> coursesList(@RequestParam("departmentCode") String departmentCode) throws IOException {
        var courses = retrieveCourses.retrieveCourses(departmentCode);
        return courses.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(courses.stream().map(CourseRepresentation.from).toList());
    }

}
