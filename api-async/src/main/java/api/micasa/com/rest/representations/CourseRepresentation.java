package api.micasa.com.rest.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import api.micasa.com.domain.Course;

import java.util.function.Function;

public record CourseRepresentation(@JsonProperty String code, @JsonProperty String description) {

    public static Function<Course, CourseRepresentation> from = course -> new CourseRepresentation(course.code(), course.description());

}
