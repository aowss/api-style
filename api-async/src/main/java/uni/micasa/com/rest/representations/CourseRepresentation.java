package uni.micasa.com.rest.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import uni.micasa.com.domain.Course;

import java.util.function.Function;
/*
public record CourseRepresentation(@JsonProperty("code") String code, @JsonProperty("description") String description) {

    public CourseRepresentation {
        this.description = description.substring(0, 100) + " ...";
    }

    public static Function<Course, CourseRepresentation> from = course -> new CourseRepresentation(course.getCode(), course.getDescription());

}
*/
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
