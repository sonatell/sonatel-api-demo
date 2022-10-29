package sn.sonatel.api.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import sn.sonatel.api.model.KeyType;

@Slf4j
public final class EncryptionUtils {

    public static String encrypt(String message, String publicKey) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {

        var cipher = Cipher.getInstance(KeyType.RSA.toString()); //NOSONAR

        var publicKeyBytes = Base64.decodeBase64(publicKey);
        KeyFactory publicKeyFactory = KeyFactory.getInstance(KeyType.RSA.toString());
        var publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        var key = publicKeyFactory.generatePublic(publicKeySpec);

        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    private EncryptionUtils() {
    }
}
