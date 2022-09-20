package api.micasa.com.domain;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public record CompanyIdentifier(String ticker, int cik) {

    public static Function<String, CompanyIdentifier> from = line -> new CompanyIdentifier(line.split("\\s+")[0].trim().toUpperCase(), parseInt(line.split("\\s+")[1].trim()));

}
