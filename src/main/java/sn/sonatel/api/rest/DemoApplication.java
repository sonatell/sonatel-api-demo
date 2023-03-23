package sn.sonatel.api.rest;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.sonatel.api.config.SecurityConfiguration;
import sn.sonatel.api.model.PinCode;
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
    public ResponseEntity<TransactionResponse> cashins(@RequestParam(defaultValue = "786258731") String customerMsisdn) {
        log.info("doing cashins to {}", customerMsisdn);
        TransactionRequest request = TransactionRequest.forAmountAndCustomer(2f, customerMsisdn);
        var response = transactionService.cashIn(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> merchantPaymentInit(@RequestParam(defaultValue = "786258731") String customerMsisdn) {
        log.info("doing merchant payment init to {}", customerMsisdn);
        TransactionRequest request = TransactionRequest.forAmountAndCustomer(2f, customerMsisdn).withOtp("1234567");
        var response = transactionService.webPayment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/publicKeys", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublicKey> getKey() {
        var response = transactionService.getPublicKey();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/retailer/balances", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Float> getRetailerBalance() {
        var response = transactionService.getRetailerBalance();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/merchant/balances", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Float> getMerchantBalance() {
        var response = transactionService.getMerchantBalance();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/encrypt", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> encrypt(@RequestBody PinCode pin) {
        log.info("Receive pin for encryption {}", pin);
        if(Objects.nonNull(pin) && PinCodeValidator.isValid(pin.getPin())) {
            var response = encryptionService.encrypt(pin.getPin());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body("Invalid pin code format, it should be 4 digits");
        }
    }

}

class PinCodeValidator {
    private static final String PIN_CODE_PATTERN = "^\\d{4}$";
    private static final Pattern pattern = Pattern.compile(PIN_CODE_PATTERN);

    private PinCodeValidator() {
    }

    static boolean isValid(String pinCode) {
        if (pinCode == null) {
        return false;
        }
        Matcher matcher = pattern.matcher(pinCode);
        return matcher.matches();
    }
}