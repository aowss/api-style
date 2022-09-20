package api.micasa.com.rest.representations;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * An error response that follows the [Problem Details for HTTP APIs format](https://tools.ietf.org/html/rfc7807)
 */
public record Problem(String type, String title, String instance, String detail) {
    public Problem {
        try {
            new URI(type);
        } catch (URISyntaxException e) {
            throw new RuntimeException("The 'type' must be a valid URI.");
        }
    }
}
