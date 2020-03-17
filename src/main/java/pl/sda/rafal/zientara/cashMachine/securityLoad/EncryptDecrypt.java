package pl.sda.rafal.zientara.cashMachine.securityLoad;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

public class EncryptDecrypt {

    private String key1;
    private String key2;
    private static final int KEY_LENGTH = 24;
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private String myEncryptionScheme;
    private Cipher cipher;
    private SecretKey key;
    private String pin;
    private String bankCode;
    private String cardNumber;


    private EncryptDecrypt(Builder builder) {
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        this.bankCode = builder.bankCode;
        this.cardNumber = builder.cardNumber;
        this.pin = builder.pin;
        key1 = bankCode + cardNumber;
        key2 = cardNumber + pin + pin.charAt(3) + pin.charAt(2) + pin.charAt(1) + pin.charAt(0);
    }




    public String encrypt(String unencryptedText, String cardData) throws Exception {
        setEncriptionKey(key2);
        String encryptLevel_1_Str = encryptInner(unencryptedText);
        setEncriptionKey(key1);
        String encryptLevel_2_Str = cardData + encryptLevel_1_Str;
        return encryptInner(encryptLevel_2_Str);
    }

    public String decryptLevel1(String encryptedText) throws Exception {
        setEncriptionKey(key1);
        return decryptInner(encryptedText);
    }

    public String decryptLevel2(String encryptedText) throws Exception {
        setEncriptionKey(key2);
        return decryptInner(encryptedText);
    }

    public String encryptToBeNeverDecrypted(String unencryptedText) throws Exception {
        setEncriptionKey(generatedRandomKey());
        return encryptInner(unencryptedText);
    }

    private String generatedRandomKey() {
        return RandomStringUtils.random(KEY_LENGTH, true, true);
    }

    private void setEncriptionKey(String secretKey) throws Exception {
        byte[] arrayBytes = secretKey.getBytes(UNICODE_FORMAT);
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }

    private String encryptInner(String unencryptedString) {
        String encryptedString;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            return null;
        }
        return encryptedString;
    }


    private String decryptInner(String encryptedString) {
        String decryptedText;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            return null;
        }
        return decryptedText;
    }




    public static class Builder{

        private String pin;
        private String bankCode;
        private String cardNumber;

        public Builder(String bankCode, String cardNumber) {
            this.bankCode = bankCode;
            this.cardNumber = cardNumber;
        }

        public Builder pin(String pin){
            this.pin = pin;
            return this;
        }

        public EncryptDecrypt build() throws Exception {
            return new EncryptDecrypt(this);
        }

    }
}