package sn.sonatel.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({ ApplicationProperties.class })
public class DemoApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private WebClient webClient;

    @Scheduled(fixedRate = 5000)
    public void scheduledRequest() {
        webClient.get()
                .uri("https://api.sandbox.orange-sonatel.com/api/account/v1/publicKeys")
                .retrieve()
                .bodyToMono(String.class)
                .map(string
                        -> "Schedule request response: " + string)
                .subscribe(logger::info);
    }

    @Override
    public void run(String... args) throws Exception {
        String body = webClient.get()
                .uri("https://api.sandbox.orange-sonatel.com/api/account/v1/publicKeys")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.info(body);
    }
}