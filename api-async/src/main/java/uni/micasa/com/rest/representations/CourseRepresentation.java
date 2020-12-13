package uni.micasa.com.rest.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import uni.micasa.com.domain.Course;

import java.util.function.Function;

public class CourseRepresentation {

    @JsonProperty
    String code;
    @JsonProperty
    String description;

    public CourseRepresentation(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Function<Course, CourseRepresentation> from = course -> new CourseRepresentation(course.getCode(), course.getDescription());

}
