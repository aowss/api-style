package api.micasa.com.exceptions;

public class InvalidResponseException extends RuntimeException {
    private final String action;
    private final String reason;
    private final String response;
    public InvalidResponseException(String action, String reason, String response) {
        super("Failed to " + action + ": [ reason = " + reason + " ]");
        this.action = action;
        this.reason = reason;
        this.response = response;
    }
}
