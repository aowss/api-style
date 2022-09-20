package api.micasa.com.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public final class InvalidTickersException extends RuntimeException {
    public InvalidTickersException(List<String> values) {
        super("Unknown tickers: " + values.stream().collect(Collectors.joining(",")));
    }
}
