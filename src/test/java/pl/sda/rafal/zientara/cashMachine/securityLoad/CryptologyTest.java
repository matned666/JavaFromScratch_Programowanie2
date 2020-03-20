package pl.sda.rafal.zientara.cashMachine.securityLoad;

import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.cashMachine.StaticData;

import static org.junit.jupiter.api.Assertions.*;

class CryptologyTest {

//    @Test
//    void encryptionTest() throws Exception {
//        Cryptology crypt = new Cryptology("1234567890123456","1234");
//        String testText = "Testing Text jgfkhgfjhgfjhgfdjgf76587658";
//        String encryptedTxt = crypt.encrypt(testText, "");
//        String decryptedTxt = crypt.decryptLevel2(crypt.decryptLevel1(encryptedTxt));
//
//        assertNotEquals(encryptedTxt,testText);
//        assertEquals(decryptedTxt,testText);
//
//    }
//
// @Test
//    void encryptionTest_OnlyLevel2() throws Exception {
//        Cryptology crypt = new Cryptology("1234567890123456","1234");
//        String testText = "Testing Text jgfkhgfjhgfjhgfdjgf76587658";
//        String encryptedTxt = crypt.encrypt(testText);
//        String decryptedTxt = crypt.decryptLevel2(encryptedTxt);
//
//        assertNotEquals(encryptedTxt,testText);
//        assertEquals(decryptedTxt,testText);
//
//    }




    @Test
    void encryptingTest1_NoResultTest_OnCorrectPin() throws Exception {
        final String PIN = "1234";
        final String CARD_NUMBER = "4589619849657531";

        final String TEXT_TO_ENCRYPT ="Mateusz"+ StaticData.SEPARATOR + "Niedbal"+ StaticData.SEPARATOR + "2300";

        Cryptology code = new Cryptology();

//        String key1 = StaticData.BANK_ENCRYPT_CODE+StaticData.FIRST_PIN_TRY;
        String key2 = StaticData.BANK_ENCRYPT_CODE+PIN;

        String encryptedTextLVL1 = code.encrypt(TEXT_TO_ENCRYPT, key2);
//        String encryptedTextLVL2 = code.encrypt(encryptedTextLVL1, key1);
//
//        System.out.println(code.checkKey(encryptedTextLVL1, key2));
//        System.out.println(code.checkKey(encryptedTextLVL1, "sdfgasdffasdf"));
//        System.out.println(code.checkKey(encryptedTextLVL1, key1));
//        System.out.println(code.checkKey(encryptedTextLVL2, "null"));
//        System.out.println(code.checkKey(encryptedTextLVL2, key1));
//
//        System.out.println("key1 "+key1);
//        System.out.println("key2 "+key2);


//        String decryptedTextLVL1 = code.decrypt(encryptedTextLVL2, key1);
//        String decryptedTextLVL2 = code.decrypt(decryptedTextLVL1, key2);



            System.out.println("Card num: " + CARD_NUMBER);
            System.out.println("Text to encrypt: " + TEXT_TO_ENCRYPT);
            System.out.println("Encrypted data: " + encryptedTextLVL1);
//            System.out.println("Encrypted data: " + encryptedTextLVL2);
//            System.out.println("Decrypted card data: " + decryptedTextLVL1);
//            System.out.println("Decrypted text 2: " + decryptedTextLVL2);

    }

}