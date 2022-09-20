package api.micasa.com.services;

import api.micasa.com.domain.CompanyIdentifier;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Component
public class CompanyService {

    private final HttpClient httpClient;
    private final HttpRequest tickerRequest = HttpRequest.
            newBuilder().
            uri(URI.create("https://www.sec.gov/include/ticker.txt")).
            GET().
            build();

    public CompanyService() {
        this.httpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public CompletableFuture<Stream<CompanyIdentifier>> getTickers() {
        return httpClient
                .sendAsync(tickerRequest, HttpResponse.BodyHandlers.ofLines())
                .thenApply(response ->
                    response
                        .body()
                        .map(CompanyIdentifier.from::apply)
                );
    }

    public CompletableFuture<CompanyInfo> getInfo(String companyCIK) {
        var infoRequest = HttpRequest.
                newBuilder().
                uri(URI.create("https://data.sec.gov/api/xbrl/companyfacts/CIK" + companyCIK + ".json")).
                GET().
                build();

        return httpClient
                .sendAsync(infoRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> CompanyInfo.from.apply(response.body()));
    }

}
