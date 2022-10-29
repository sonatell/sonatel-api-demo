package sn.sonatel.api.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EncryptionUtilsTest {

    @Test
    void testEncrypt() throws Exception {

        var key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtZzGVQjEGJ97sFxPmuZ2sUX0F9UTmOY0EEcehsURFyv5u1pGV4/y9P9f0OmTeBjPslVKtF/rqQUsHpqdx0uU/pRFxmHft+phuu+9MCP/hmbFbyJNaF/EeD0A4Nx1j72AWyvctS7z1Xjfio+cuqS5szZ4iOJ1RO3K1gg91CrpxOOoHnQC7PsZ332wbsa/PnBJ5uDBDhA8szpw/OnBKXxxnluKGuD7wse3VH9T1j2yaJWflZlyEKJi6ftRj2+DV/3lA/0ggehOpVN+Px9MYTolGgriK7BZ0Lr4wsVz+hdls+EXJn8beIRkkmtyhF43R9ABbkUfMCoCEnAUSEdLVSwfrwIDAQAB";
        var message = "2022";
        var response = EncryptionUtils.encrypt(message, key);

        System.out.println("Encoded response is : " + response);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response)
        );
    }
}

