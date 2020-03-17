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
    private String bankCode;
    private String cardNumber;

    private Cryptology(Builder builder) {
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        this.bankCode = builder.bankCode;
        this.cardNumber = builder.cardNumber;
        this.pin = builder.pin;
        key1 = bankCode + cardNumber;
        if(pin != null) {
            StringBuilder pinReversed = new StringBuilder();
            for (int i = 3; i >= 0; i--) {
                pinReversed.append(pin.charAt(i));
            }
            System.out.println(pinReversed);
            key2 = pin + pinReversed + pin + pinReversed  + pin + pinReversed;
        }
    }

    public String encrypt(String unencryptedText, String cardData) throws Exception {
        setEncryptionKey(key2);
        String encryptLevel_1_Str = encryptInner(unencryptedText);
        setEncryptionKey(key1);
        String encryptLevel_2_Str = cardData + encryptLevel_1_Str;
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

    private String generatedRandomKey() {
        return RandomStringUtils.random(KEY_LENGTH, true, true);
    }

    private void setEncryptionKey(String secretKey) throws Exception {
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

        public Cryptology build() throws Exception {
            return new Cryptology(this);
        }
    }
}

//class SecurityMainTest{
//    public static void main(String[] args) throws Exception {
//
//        final String PIN = "1234";
//        final String BANK_CODE = "Pk08pS4$";
//        final String CARD_NUMBER = "4234345623454567";
//
//        final String CARD_DATA = PIN+";"+BANK_CODE+";"+CARD_NUMBER+";";
//
//        final String TEXT_TO_ENCRYPT ="Manufacture-MRN sp. z o.o.;Skladowa;4;49-305;Brzeg;7471911153;";
//        String encryptedTextLVL1 = null;
//        String[] decryptedArray;
//        StringBuilder decryptedTextLVL1 = new StringBuilder();
//        String decryptedTextLVL2 = "";
//
//        System.out.println("Input your pin: ");
//        Scanner input = new Scanner(System.in);
//        String givenPin = input.nextLine();
//
//        if(givenPin.equals(PIN)){
//            EncryptDecrypt code = new EncryptDecrypt.Builder(BANK_CODE,CARD_NUMBER).pin(givenPin).build();
//            encryptedTextLVL1 = code.encrypt(TEXT_TO_ENCRYPT,CARD_DATA);
//            decryptedArray = code.decryptLevel1(encryptedTextLVL1).split(";");
//            for (int i = 0; i < decryptedArray.length - 1; i++) {
//                decryptedTextLVL1.append(decryptedArray[i]).append(";");
//            }
//            decryptedTextLVL2 = code.decryptLevel2(decryptedArray[decryptedArray.length-1]);
//
////            decryptedTextLVL1 = code.decryptLevel1(encryptedTextLVL1);
////            decryptedTextLVL2 = code.decryptLevel2(decryptedTextLVL1.replace(CARD_DATA, ""));
//
//            System.out.println("Card data: " + CARD_DATA);
//            System.out.println("Text to encrypt: " + TEXT_TO_ENCRYPT);
//            System.out.println("Encrypted data: " + encryptedTextLVL1);
////            assert decryptedTextLVL1 != null;
//            System.out.println("Decrypted card data: " + decryptedTextLVL1);
////            System.out.println("Decrypted text 1: " + decryptedTextLVL1);
//            System.out.println("Decrypted text 2: " + decryptedTextLVL2);
//
//        }else {
//
//            EncryptDecrypt code = new EncryptDecrypt.Builder(BANK_CODE,CARD_NUMBER).build();
//            String penaltyEncription = code.encryptToBeNeverDecrypted(CARD_DATA)+code.encryptToBeNeverDecrypted(TEXT_TO_ENCRYPT) ;
//            System.out.println("Wrong pin");
//            System.out.println("Your data has been encrypted. Bo to bank");
//            System.out.println("Encrypted data: " + penaltyEncription);
//        }
//
//
//    }
//}