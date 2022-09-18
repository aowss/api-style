package api.micasa.com.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import api.micasa.com.domain.Course;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CourseService {

    public CompletableFuture<List<Course>> retrieveCourses(String departmentCode) throws RuntimeException {
        var result = new CompletableFuture<List<Course>>();
        new Thread(() -> {
            try {
                var records = parseCSV();
                result.complete(records.stream()
                        .filter(csvRecord -> csvRecord.get(2).equalsIgnoreCase(departmentCode))
                        .map(csvRecord -> new Course(csvRecord.get(0), csvRecord.get(1)))
                        .toList());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).start();
        return result;
    }

    private List<CSVRecord> parseCSV() throws IOException {
        Path csvData = Paths.get("src", "main", "resources", "courses.csv");
        CSVParser parser = CSVParser.parse(csvData, StandardCharsets.UTF_8, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        return parser.getRecords();
    }

}
