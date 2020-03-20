package pl.sda.rafal.zientara.cashMachine.card;

import pl.sda.rafal.zientara.cashMachine.Check;
import pl.sda.rafal.zientara.cashMachine.StaticData;
import pl.sda.rafal.zientara.cashMachine.securityLoad.Cryptology;
import pl.sda.rafal.zientara.cashMachine.securityLoad.FileOperations;

public class CardLoader {

    private String cardNumber;

    private final static String SEPARATOR = StaticData.SEPARATOR;
    private FileOperations fileOperations;
    private Cryptology code;
    private String loadedData;
    private String decodedData;
    private String separatedEncodedData;
    private String pin;
    private Card card;
    private boolean isPassed;
    private boolean isBlocked;

    private int pinCondition;


    public CardLoader(String cardNumber) throws Exception {
        this.cardNumber = cardNumber;
        String path = StaticData.PATH_TO_RESOURCES + cardNumber + StaticData.CARD_FILE_EXTENSION;
        fileOperations = new FileOperations(path);
        loadedData = fileOperations.readDataFromFile();
        code = new Cryptology();
        isBlocked = false;
        pinCondition = getCondition();
        getseperatedData();
    }

    private int getCondition() {
        if(Check.isNumeric(String.valueOf(loadedData.charAt(0)))) return pinCondition = Integer.parseInt(String.valueOf(loadedData.charAt(0)));
        else return 9;

    }

/*
           <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
           <<<<                  BE CAREFUL METHOD 'ENTER_PIN' CAN CHANGE FILE PERMANENTLY               >>>>
           <<<<  IF THIRD ATTEMPT OF PIN ENTERING IS FAILED IT IS ENCODED PERMANENTLY WITH A RANDOM KEY  >>>>
           <<<<                             SO IT'S BETTER TO HAVE A BACKUP                              >>>>
           <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
*/

    public void ENTER_PIN(String pin) throws Exception {
        if(code.checkKey(separatedEncodedData, keyMaker(pin))){
            isPassed = true;
            decodedData=code.decrypt(separatedEncodedData,keyMaker(pin));
            fileOperations.writeDataToFile("1"+separatedEncodedData);
            createCard();
        }else{
            isPassed = false;
            pinCondition++;
            if(pinCondition>3) fileOperations.writeDataToFile("ERROR"+code.encrypt(separatedEncodedData,StaticData.BANK_ENCRYPT_PENALTY_CODE));
            else fileOperations.writeDataToFile(pinCondition+separatedEncodedData);
        }
    }

    private void createCard() {
        if (!isBlocked) {
            String[] tempArr = decodedData.split(SEPARATOR);
            card = new Card.Builder(cardNumber)
                    .ownerName(tempArr[0])
                    .ownerSurname(tempArr[1])
                    .balance(tempArr[2])
                    .build();
        }
    }

    private void getseperatedData(){
        separatedEncodedData = loadedData.substring(1);
    }

    private String keyMaker(String key) {
        return StaticData.BANK_ENCRYPT_CODE + key;
    }

    public Card getCard() {
        return card;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public int getPinCondition() {
        return pinCondition;
    }
}
