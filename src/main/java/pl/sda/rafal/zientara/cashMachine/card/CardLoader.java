package pl.sda.rafal.zientara.cashMachine.card;

import pl.sda.rafal.zientara.cashMachine.securityLoad.Cryptology;
import pl.sda.rafal.zientara.cashMachine.securityLoad.FileOperations;

public class CardLoader {

    private String cardNumber;

    private final static String SEPARATOR = ";;;;";
    private final String PATH;
    private FileOperations fileOperations;
    private Cryptology crypt;
    private String loadedData;
    private String decryptedData_1_FirstStep;
    private String encryptedSecondStageData;
    private String decryptedData_2_SecondStep;
    private Card card;
    private PinCondition pinCondition;


    public CardLoader(String cardNumber) throws Exception {
        this.cardNumber = cardNumber;
        PATH = "src\\main\\resources\\"+ cardNumber +".card";
        initialize();
    }

    /*
           <<<< BE CAREFUL THIS OPTION CAN CHANGE FILE PERMANENTLY IF THIRD ATTEMPT OF PIN ENTERING IS FAILED >>>>
        */
    public void ENTER_PIN(String pin) throws Exception {
        crypt = new Cryptology(cardNumber,pin);
        try {
            decryption_2_SecondStage();
            String temp = crypt.encrypt(encryptedSecondStageData, (PinCondition.FIRST_ATTEMPT+SEPARATOR));
            fileOperations.writeDataToFile(temp);

        } catch (Exception e) {
            crypt = new Cryptology(cardNumber);
            if(card.getPinCondition() == PinCondition.THIRD_ATTEMPT) fileOperations.writeDataToFile(crypt.encryptToBeNeverDecrypted(loadedData));
            else {

                card.setPinCondition(PinCondition.nextTo(card.getPinCondition()));
                String temp = crypt.encrypt(getCardPinCondition()+SEPARATOR+encryptedSecondStageData);
                fileOperations.writeDataToFile(temp);
            }
        }
    }

    private void initialize() throws Exception {
        fileOperations = new FileOperations(PATH);
        loadedData = fileOperations.readDataFromFile();
        crypt = new Cryptology(cardNumber);
        decryption_1_FirstStage();
        splitThatShit();
    }

    private String getCardPinCondition() {
        return String.valueOf(card.getPinCondition());
    }

    private void decryption_1_FirstStage() throws Exception {
        decryptedData_1_FirstStep = crypt.decryptLevel1(loadedData);
    }

    private void splitThatShit() {
        String[] tempArr = decryptedData_1_FirstStep.split(SEPARATOR);
        String temp = tempArr[0];
        pinCondition = setPinCondition(temp);

        encryptedSecondStageData = tempArr[1];
        card = new Card.Builder(cardNumber).pinCondition(pinCondition).build();
    }

    private void decryption_2_SecondStage() throws Exception {

        decryptedData_2_SecondStep = crypt.decryptLevel2(encryptedSecondStageData);
        createCard();
    }

    private void createCard() throws Exception {
        String[] tempArr = decryptedData_2_SecondStep.split(SEPARATOR);
        card = new Card.Builder(cardNumber)
                .pinCondition(PinCondition.FIRST_ATTEMPT)
                .ownerName(tempArr[0])
                .ownerSurname(tempArr[1])
                .balance(tempArr[2])
                .build();
        fileOperations.writeDataToFile(crypt.encrypt(decryptedData_2_SecondStep, getCardPinCondition()));
    }

    private PinCondition setPinCondition(String pinCondition) {
        switch (pinCondition){
            case "FIRST_ATTEMPT" : return PinCondition.FIRST_ATTEMPT;
            case "SECOND_ATTEMPT" : return PinCondition.SECOND_ATTEMPT;
            case "THIRD_ATTEMPT" : return PinCondition.THIRD_ATTEMPT;
            default: return null;
        }
    }

    public Card getCard() {
        return card;
    }

    public PinCondition getPinCondition() {
        return pinCondition;
    }
}
