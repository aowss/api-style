package api.micasa.com.activities;

import api.micasa.com.domain.CompanyIdentifier;
import api.micasa.com.exceptions.InvalidTickersException;
import api.micasa.com.services.CompanyInfo;
import api.micasa.com.services.CompanyService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class RetrieveCompanyInfo {

    CompanyService companyService;

    public RetrieveCompanyInfo(CompanyService companyService) {
        this.companyService = companyService;
    }

    public CompletableFuture<List<CompanyInfo>> retrieveCompaniesInfo(List<String> tickers) {
        CompletableFuture<List<CompanyIdentifier>> selectedIdentifiers = companyService
                .getTickers()
                .thenApply(identifiers -> identifiers.filter(isRequestedTicker.apply(tickers)).toList());

        return selectedIdentifiers
                .thenCompose(identifiers -> {
                    if (identifiers.isEmpty()) throw new InvalidTickersException(tickers);
                    var allInfo = getAllInfo.apply(identifiers);
                    return CompletableFuture
                            .allOf(allInfo.toArray(CompletableFuture[]::new))
                            .thenApply(done -> allInfo.stream().map(CompletableFuture::join).toList());
                });
    }

    Function<List<CompanyIdentifier>, List<CompletableFuture<CompanyInfo>>> getAllInfo = identifiers -> identifiers
            .stream()
            .map(CompanyIdentifier::cik)
            .map(cik -> String.format("%1$10s", cik).replace(' ', '0'))
            .map(companyService::getInfo)
            .toList();

    Function<List<String>, Predicate<CompanyIdentifier>> isRequestedTicker = tickers -> identifier -> tickers
            .stream()
            .filter(ticker -> ticker.equalsIgnoreCase(identifier.ticker()))
            .findAny()
            .isPresent();

}
