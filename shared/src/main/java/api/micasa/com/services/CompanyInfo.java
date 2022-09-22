package api.micasa.com.services;

import api.micasa.com.exceptions.InvalidResponseException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

record Facts(@JsonIgnore Object dei, @JsonProperty("us-gaap") Map<String, Details> accounting) {}

record Details(String label, String description, Map<String, List<Entry>> units) {}

enum FiscalPeriod {
    FY, Q1, Q2, Q3, Q4
}

@JsonInclude(JsonInclude.Include.NON_NULL)
record Entry(LocalDate start, LocalDate end, long val, String accn, Year fy, FiscalPeriod fp, String form, LocalDate filed, String frame) {}

public record CompanyInfo(int cik, String entityName, Facts facts) {

    static ObjectMapper jacksonMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    static Function<String, CompanyInfo> from = body -> {
        try {
            CompanyInfo info = jacksonMapper.readValue(body, CompanyInfo.class);
            info.facts().accounting().entrySet().removeIf(entry -> entry.getValue().label().contains("Deprecated"));
            return info;
        } catch (JsonProcessingException e) {
            throw new InvalidResponseException("retrieve company info", e.getMessage(), body);
        }
    };

}
