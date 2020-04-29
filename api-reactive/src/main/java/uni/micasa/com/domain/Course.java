package uni.micasa.com.domain;

public class Course {

    private String code;
    private String description;

    public Course(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
