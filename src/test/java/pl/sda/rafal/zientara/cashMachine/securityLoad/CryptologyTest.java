package pl.sda.rafal.zientara.cashMachine.securityLoad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CryptologyTest {

    @Test
    void encryptionTest() throws Exception {
        Cryptology crypt = new Cryptology.Builder("1234567890123456").pin("1234").build();
        String testText = "Testing Text jgfkhgfjhgfjhgfdjgf76587658";
        String encryptedTxt = crypt.encrypt(testText, "");
        String decryptedTxt = crypt.decryptLevel2(crypt.decryptLevel1(encryptedTxt));

        assertNotEquals(encryptedTxt,testText);
        assertEquals(decryptedTxt,testText);

    }

 @Test
    void encryptionTest_OnlyLevel2() throws Exception {
        Cryptology crypt = new Cryptology.Builder("1234567890123456").pin("1234").build();
        String testText = "Testing Text jgfkhgfjhgfjhgfdjgf76587658";
        String encryptedTxt = crypt.encrypt(testText);
        String decryptedTxt = crypt.decryptLevel2(encryptedTxt);

        assertNotEquals(encryptedTxt,testText);
        assertEquals(decryptedTxt,testText);

    }




    @Test
    void encryptingTest1_NoResultTest_OnCorrectPin() throws Exception {
        final String PIN = "1234";
        final String CARD_NUMBER = "4234345623454567";

        final String CARD_DATA = CARD_NUMBER+";";

        final String TEXT_TO_ENCRYPT ="Manufacture-MRN sp. z o.o.;Skladowa;4;49-305;Brzeg;7471911153;";
        String encryptedTextLVL1 = null;
        String[] decryptedArray;
        StringBuilder decryptedTextLVL1 = new StringBuilder();
        String decryptedTextLVL2 = "";

        String givenPin = PIN;
        if(givenPin.equals(PIN)){
            Cryptology code = new Cryptology.Builder(CARD_NUMBER).pin(givenPin).build();
            encryptedTextLVL1 = code.encrypt(TEXT_TO_ENCRYPT,CARD_DATA);
            decryptedArray = code.decryptLevel1(encryptedTextLVL1).split(";");
            for (int i = 0; i < decryptedArray.length - 1; i++) {
                decryptedTextLVL1.append(decryptedArray[i]).append(";");
            }
            decryptedTextLVL2 = code.decryptLevel2(decryptedArray[decryptedArray.length-1]);

            System.out.println("Card data: " + CARD_DATA);
            System.out.println("Text to encrypt: " + TEXT_TO_ENCRYPT);
            System.out.println("Encrypted data: " + encryptedTextLVL1);
            System.out.println("Decrypted card data: " + decryptedTextLVL1);
            System.out.println("Decrypted text 2: " + decryptedTextLVL2);

        }else {
            Cryptology code = new Cryptology.Builder(CARD_NUMBER).build();
            String penaltyEncription = code.encryptToBeNeverDecrypted(CARD_DATA)+code.encryptToBeNeverDecrypted(TEXT_TO_ENCRYPT) ;
            System.out.println("Wrong pin");
            System.out.println("Your data has been encrypted. Bo to bank");
            System.out.println("Encrypted data: " + penaltyEncription);

        }
    }

    @Test
    void encryptingTest1_NoResultTest_OnWrongPin() throws Exception {
        final String PIN = "1234";
        final String CARD_NUMBER = "4234345623454567";

        final String CARD_DATA = CARD_NUMBER+";";

        final String TEXT_TO_ENCRYPT ="Manufacture-MRN sp. z o.o.;Skladowa;4;49-305;Brzeg;7471911153;";
        String encryptedTextLVL1 = null;
        String[] decryptedArray;
        StringBuilder decryptedTextLVL1 = new StringBuilder();
        String decryptedTextLVL2 = "";

        String givenPin = "2345";
        if(givenPin.equals(PIN)){
            Cryptology code = new Cryptology.Builder(CARD_NUMBER).pin(givenPin).build();
            encryptedTextLVL1 = code.encrypt(TEXT_TO_ENCRYPT,CARD_DATA);
            decryptedArray = code.decryptLevel1(encryptedTextLVL1).split(";");
            for (int i = 0; i < decryptedArray.length - 1; i++) {
                decryptedTextLVL1.append(decryptedArray[i]).append(";");
            }
            decryptedTextLVL2 = code.decryptLevel2(decryptedArray[decryptedArray.length-1]);

            System.out.println("Card data: " + CARD_DATA);
            System.out.println("Text to encrypt: " + TEXT_TO_ENCRYPT);
            System.out.println("Encrypted data: " + encryptedTextLVL1);
            System.out.println("Decrypted card data: " + decryptedTextLVL1);
            System.out.println("Decrypted text 2: " + decryptedTextLVL2);
        }else {

            Cryptology code = new Cryptology.Builder(CARD_NUMBER).build();
            String penaltyEncription = code.encryptToBeNeverDecrypted(CARD_DATA)+code.encryptToBeNeverDecrypted(TEXT_TO_ENCRYPT) ;
            System.out.println("Wrong pin");
            System.out.println("Your data has been encrypted. Bo to bank");
            System.out.println("Encrypted data: " + penaltyEncription);

        }
    }

}