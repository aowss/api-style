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
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("async")
public class CourseResource {

    RetrieveCourses retrieveCourses;

    public CourseResource(RetrieveCourses retrieveCourses) {
        this.retrieveCourses = retrieveCourses;
    }

    @GetMapping(value = "courses")
    public CompletableFuture<ResponseEntity<List<CourseRepresentation>>> accountsList(@RequestParam("departmentCode") String departmentCode) throws IOException {
        return retrieveCourses.retrieveCourses(departmentCode)
                .thenApply(list -> list.stream().map(CourseRepresentation.from).collect(toList()))
                .thenApply(ResponseEntity::ok);
    }

}
