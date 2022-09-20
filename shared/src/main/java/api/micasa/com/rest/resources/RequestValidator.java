package api.micasa.com.rest.resources;

import api.micasa.com.exceptions.BadRequestException;
import api.micasa.com.rest.representations.Breakdown;

import java.time.Year;
import java.util.List;

public class RequestValidator {
    public static void validateRequest(List<String> tickers, Year from, Year to, Breakdown breakdown, List<String> info) throws BadRequestException {
        if (tickers == null || tickers.isEmpty()) throw new BadRequestException("tickers", "n/a", "At least one ticker must be specified");
        if (from != null && from.isAfter(Year.now())) throw new BadRequestException("from", from.toString(), "The year must be lower or equal to " + Year.now());
        if (to != null && to.isAfter(Year.now())) throw new BadRequestException("to", to.toString(), "The year must be lower or equal to " + Year.now());
        if (to != null && from != null && from.isAfter(to)) throw new BadRequestException("from & to", from + " & " + to, "The start of the date range must be lower or equal to the end of the range");
    }
}
