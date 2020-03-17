package pl.sda.rafal.zientara.cashMachine.securityLoad;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

public class Cryptology {

    private String key1;
    private String key2;
    private static final int KEY_LENGTH = 24;
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    private String myEncryptionScheme;
    private Cipher cipher;
    private SecretKey key;
    private String pin;
    private String cardNumber;

    private Cryptology(Builder builder) {
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        this.cardNumber = builder.cardNumber;
        this.pin = builder.pin;
        key1 = key2Generator(false);
        key2 = key2Generator(true);
        }

    public String encrypt(String unencryptedText) throws Exception {
        setEncryptionKey(key2);
        String encryptLevel_1_Str = encryptInner(unencryptedText);
        return encryptLevel_1_Str;
    }

    public String encrypt(String unencryptedText, String cardData) throws Exception {
        // 2 levels (level 1 for card wrong pin counter)
        setEncryptionKey(key1);
        String encryptLevel_2_Str = cardData + encrypt(unencryptedText);
        return encryptInner(encryptLevel_2_Str);
    }

    public String decryptLevel1(String encryptedText) throws Exception {
        setEncryptionKey(key1);
        return decryptInner(encryptedText);
    }

    public String decryptLevel2(String encryptedText) throws Exception {
        setEncryptionKey(key2);
        return decryptInner(encryptedText);
    }

    public String encryptToBeNeverDecrypted(String unencryptedText) throws Exception {
        setEncryptionKey(generatedRandomKey());
        return encryptInner(unencryptedText);
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

    private void setEncryptionKey(String secretKey) throws Exception {
        byte[] arrayBytes = secretKey.getBytes(UNICODE_FORMAT);
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }

    private String key2Generator(boolean keyTypeFlag_Key1False_Key2True) {
        StringBuilder keyBuilder = new StringBuilder();
        if(pin != null && keyTypeFlag_Key1False_Key2True) {
            StringBuilder pinBuilder = new StringBuilder(pin);
            for (int i = pin.length()-1; i >= 0; i--) {
                pinBuilder.append(pin.charAt(i));
            }
            int counter = 0;
            for (int i = 1 ; i <= KEY_LENGTH; i++){
                keyBuilder.append(pinBuilder.charAt(counter));
                counter++;
                if(counter >= pinBuilder.length()) counter = 0;
            }
        } else {
            int counter = 0;
            for (int i = 1 ; i <= KEY_LENGTH; i++){
                keyBuilder.append(cardNumber.charAt(counter));
                counter++;
                if(counter >= cardNumber.length()) counter = 0;
            }
        }
        return String.valueOf(keyBuilder);
    }

    private String generatedRandomKey() {
        return RandomStringUtils.random(KEY_LENGTH, true, true);
    }


    public static class Builder{

        private String pin;
        private String cardNumber;

        public Builder(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public Builder pin(String pin){
            this.pin = pin;
            return this;
        }

        public Cryptology build() throws Exception {
            return new Cryptology(this);
        }
    }
}


