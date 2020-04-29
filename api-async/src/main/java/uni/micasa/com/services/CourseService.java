package uni.micasa.com.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;
import uni.micasa.com.domain.Course;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.*;

@Component
public class CourseService {

    public CompletableFuture<List<Course>> retrieveCourses(String departmentCode) throws RuntimeException {
        var result = new CompletableFuture<List<Course>>();
        new Thread(() -> {
            try {
                Path csvData = Paths.get("src", "main", "resources", "courses.csv");
                CSVParser parser = CSVParser.parse(csvData, Charset.forName("UTF-8"), CSVFormat.DEFAULT.withFirstRecordAsHeader());
                var courses = parser.getRecords();
                result.complete(courses.stream()
                        .filter(record -> record.get(2).equalsIgnoreCase(departmentCode))
                        .map(record -> new Course(record.get(0), record.get(1)))
                        .collect(toList()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).start();
        return result;
    }

}
