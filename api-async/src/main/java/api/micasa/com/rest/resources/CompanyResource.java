package api.micasa.com.rest.resources;

import api.micasa.com.activities.RetrieveCompanyInfo;
import api.micasa.com.rest.representations.Breakdown;
import api.micasa.com.services.CompanyInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static api.micasa.com.rest.resources.RequestValidator.validateRequest;

@RestController
@RequestMapping("async")
public class CompanyResource {

    RetrieveCompanyInfo retrieveCompanyInfo;

    public CompanyResource(RetrieveCompanyInfo retrieveCompanyInfo) {
        this.retrieveCompanyInfo = retrieveCompanyInfo;
    }

    @GetMapping(value = "companies")
    public CompletableFuture<ResponseEntity<List<CompanyInfo>>> companiesInfo(
            @RequestParam("tickers") List<String> tickers,
            @RequestParam(value = "from", required = false) Year from,
            @RequestParam(value = "to", required = false) Year to,
            @RequestParam(value = "breakdown", required = false) Breakdown breakdown,
            @RequestParam(value = "info", required = false) List<String> info
        ) {
        validateRequest(tickers, from, to, breakdown, info);
        return retrieveCompanyInfo.retrieveCompaniesInfo(tickers)
                .thenApply(ResponseEntity::ok);
    }

}
