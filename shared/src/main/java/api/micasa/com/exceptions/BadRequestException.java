package api.micasa.com.exceptions;

public final class BadRequestException extends RuntimeException {
    private final String parameter;
    private final String value;
    private final String reason;

    public BadRequestException(String parameter, String value, String reason) {
        super("The following value '" + value + "' for parameter '" + parameter + "' is invalid: " + reason + ".");
        this.parameter = parameter;
        this.value = value;
        this.reason = reason;
    }

}
