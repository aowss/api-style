package api.micasa.com.rest;

import api.micasa.com.exceptions.BadRequestException;
import api.micasa.com.exceptions.InvalidTickersException;
import api.micasa.com.rest.representations.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { InvalidTickersException.class })
    void tickersNotFound(Exception ex, WebRequest request) {
        System.out.println(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { BadRequestException.class })
    @ResponseBody
    Problem badRequest(BadRequestException ex, WebRequest request) {
        var webRequest = ((ServletWebRequest)request).getRequest();
        var uri = webRequest.getRequestURI();
        var queryString = webRequest.getQueryString();
        if (queryString != null && !queryString.isEmpty()) uri += "?" + queryString;
        return new Problem(
                "https://api.micasa.com/validation-error",
                "The request is invalid",
                uri,
                ex.getMessage()
        );
    }

}
