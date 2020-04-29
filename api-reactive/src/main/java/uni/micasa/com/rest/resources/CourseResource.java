package uni.micasa.com.rest.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.micasa.com.activities.RetrieveCourses;
import uni.micasa.com.rest.representations.CourseRepresentation;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sync")
public class CourseResource {

    RetrieveCourses retrieveCourses;

    public CourseResource(RetrieveCourses retrieveCourses) {
        this.retrieveCourses = retrieveCourses;
    }

    @GetMapping(value = "courses")
    public ResponseEntity<List<CourseRepresentation>> accountsList(@RequestParam("departmentCode") String departmentCode) throws IOException {
        var courses = retrieveCourses.retrieveCourses(departmentCode);
        return ResponseEntity.ok(courses.stream().map(CourseRepresentation.from).collect(Collectors.toList()));
    }

}
