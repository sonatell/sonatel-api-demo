package sn.sonatel.api.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific
 * <p>
 * Properties are configured in the {@code application.yml} file.
 */

@ConfigurationProperties(prefix = "sonatel", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final ApplicationProperties.Security security = new ApplicationProperties.Security();
    private final List<String> myNumbers = new ArrayList<>();
    private String baseUrl;

    public static class Security {
        private String clientId;
        private String clientSecret;

        public String getClientId() {
            return clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }

    public Security getSecurity() {
        return security;
    }

    public List<String> getMyNumbers() {
        return myNumbers;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
