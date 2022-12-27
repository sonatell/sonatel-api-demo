package sn.sonatel.api.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.sonatel.api.config.SecurityConfiguration;
import sn.sonatel.api.model.PublicKey;
import sn.sonatel.api.model.TransactionRequest;
import sn.sonatel.api.model.TransactionResponse;
import sn.sonatel.api.service.EncryptionService;
import sn.sonatel.api.service.TransactionService;

@Slf4j
@RequestMapping("/api/account/v1")
@RestController
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackageClasses = {SecurityConfiguration.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private final TransactionService transactionService;
    private final EncryptionService encryptionService;

    @GetMapping(value = "/cashins", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> cashins() {
        log.info("IN - Doing cashins");
        TransactionRequest request = TransactionRequest.forAmountAndCustomer(2f, "786258731");
        var response = transactionService.cashIn(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/publicKeys", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublicKey> getKey() {
        var response = transactionService.getPublicKey();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/encrypt", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> encrypt(@RequestBody String pin) {
        var response = encryptionService.encrypt(pin);
        return ResponseEntity.ok(response);
    }
}