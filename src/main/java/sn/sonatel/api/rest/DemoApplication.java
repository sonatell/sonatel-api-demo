package sn.sonatel.api.rest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import sn.sonatel.api.config.ApplicationProperties;
import sn.sonatel.api.config.SecurityConfiguration;
import sn.sonatel.api.model.PublicKey;
import sn.sonatel.api.utils.EncryptionUtils;

@Slf4j
@RequestMapping("/api/account/v1")
@RestController
@RequiredArgsConstructor
@EnableConfigurationProperties({ ApplicationProperties.class })
@SpringBootApplication(scanBasePackageClasses = {SecurityConfiguration.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private final WebClient webClient;
    private final ApplicationProperties applicationProperties;

    @GetMapping(value = "/publicKeys", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublicKey> getPublicKey() throws IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        log.info("1 - Retrieving public key");
        var body = webClient.get()
                .uri(applicationProperties.getBaseUrl() + applicationProperties.getPublicKeyUri())
                .retrieve()
                .bodyToMono(PublicKey.class)
                .block();
        log.info("2 - Received response : {}", body);

        if(Objects.nonNull(body) && Objects.nonNull(body.getKey())) {
            log.info("encrypted pin is : {}", EncryptionUtils.encrypt(applicationProperties.getMyPinCode(), body.getKey()));
        }

        return ResponseEntity.ok(body);
    }
}