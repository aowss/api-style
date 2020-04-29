package uni.micasa.com.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;
import uni.micasa.com.domain.Course;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Component
public class CourseService {

    public List<Course> retrieveCourses(String departmentCode) throws IOException {
        Path csvData = Paths.get("src", "main", "resources", "courses.csv");
        CSVParser parser = CSVParser.parse(csvData, Charset.forName("UTF-8"), CSVFormat.DEFAULT.withFirstRecordAsHeader());
        var courses = parser.getRecords();
        return courses.stream()
                .filter(record -> record.get(2).equalsIgnoreCase(departmentCode))
                .map(record -> new Course(record.get(0), record.get(1)))
                .collect(toList());
    }

}
