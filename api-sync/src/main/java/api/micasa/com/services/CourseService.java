package api.micasa.com.services;

import api.micasa.com.domain.Course;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CourseService {

    public List<Course> retrieveCourses(String departmentCode) throws IOException {
        var records = parseCSV();
        return records.stream()
                .filter(csvRecord -> csvRecord.get(2).equalsIgnoreCase(departmentCode))
                .map(csvRecord -> new Course(csvRecord.get(0), csvRecord.get(1)))
                .toList();
    }

    private List<CSVRecord> parseCSV() throws IOException {
        Path csvData = Paths.get("src", "main", "resources", "courses.csv");
        CSVParser parser = CSVParser.parse(csvData, StandardCharsets.UTF_8, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        return parser.getRecords();
    }

}
